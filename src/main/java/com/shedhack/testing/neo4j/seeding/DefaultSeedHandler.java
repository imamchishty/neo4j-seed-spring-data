package com.shedhack.testing.neo4j.seeding;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexManager;
import org.neo4j.index.impl.lucene.LuceneIndexImplementation;
import org.slf4j.LoggerFactory;

import com.shedhack.testing.neo4j.dto.SeedData;
import com.shedhack.testing.neo4j.dto.SeedEntity;
import com.shedhack.testing.neo4j.dto.SeedIndex;
import com.shedhack.testing.neo4j.dto.SeedRelationship;

/**
 * Default implementation, please refer to {@link SeedHandler}.
 */
public class DefaultSeedHandler implements SeedHandler
{
    /** The graph db. */
    private GraphDatabaseService graphDb;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(DefaultSeedHandler.class);

    private static final String SDN_TYPE_PROPERTY_KEY = "__type__";

    private static final String SDN_INDEX_NAME = "__types__";

    private static final String SDN_INDEX_KEY = "className";

    /**
     * Instantiates a new default seed handler.
     * 
     * @param graphDb
     *            the graph db
     */
    public DefaultSeedHandler(GraphDatabaseService graphDb)
    {
        this.graphDb = graphDb;
    }

    /**
     * {@inheritDoc}
     */
    public Set<Long> createNodes(SeedData data)
    {
        Set<Long> nodeIds = new HashSet<Long>();

        Transaction tx = graphDb.beginTx();
        try
        {
            Map<Long, Node> createdNodes = createNodes(data.getEntities());
            createRelationships(data.getRelationships(), createdNodes);
            tx.success();
            nodeIds = createListOfNodeIds(createdNodes);
        }
        finally
        {
            tx.finish();
        }

        return nodeIds;
    }

    /**
     * {@inheritDoc}
     */
    public void removeNodes(Set<Long> nodeIds)
    {
        Transaction tx = graphDb.beginTx();
        try
        {
            for (Long id : nodeIds)
            {
                Node node = graphDb.getNodeById(id);

                // delete all associated relationships
                for (Relationship relationship : node.getRelationships())
                {
                    relationship.delete();
                }

                // finally delete the node itself.
                node.delete();
            }

            tx.success();
        }
        finally
        {
            tx.finish();
        }
    }

    /**
     * Creates the nodes with the properties defined in the json properties
     * section.
     * 
     * @param entities
     *            the entities
     */
    private Map<Long, Node> createNodes(List<SeedEntity> entities)
    {
        Map<Long, Node> createdNodes = new HashMap<Long, Node>();

        for (SeedEntity entity : entities)
        {
            // Create the empty node
            Node node = graphDb.createNode();

            // create the properties
            createProperties(node, entity.getProperties());

            // create spring data property and index
            createSpringDataPropertyAndIndex(node, entity.getType());

            // create the indexes
            createIndexes(node, entity.getIndexes());

            // add to our master map, this row could have a relationship later
            createdNodes.put(entity.getRowId(), node);
        }

        return createdNodes;
    }

    /**
     * Creates the spring data property and the index.
     * 
     * @param node
     *            the node
     * @param type
     *            the type
     */
    private void createSpringDataPropertyAndIndex(Node node, String type)
    {
        if (type != null)
        {
            // set the property onto the node
            node.setProperty(SDN_TYPE_PROPERTY_KEY, type);

            // now set the SDN specific index (see
            // IndexingNodeTypeRepresentationStrategy for further details)
            IndexManager indexManager = graphDb.index();
            Index<Node> index = indexManager.forNodes(SDN_INDEX_NAME, LuceneIndexImplementation.EXACT_CONFIG);
            index.add(node, SDN_INDEX_KEY, type);
        }
    }

    /**
     * Creates the properties for a particular node.
     * 
     * @param node
     *            the node
     * @param properties
     *            the properties
     */
    private void createProperties(Node node, Map<String, Object> properties)
    {
        for (String key : properties.keySet())
        {
            node.setProperty(key, properties.get(key));
        }
    }

    /**
     * Creates the indexes.
     * 
     * @param node
     *            the node
     * @param indexes
     *            the indexes
     */
    private void createIndexes(Node node, List<SeedIndex> seedIndexes)
    {
        for (SeedIndex seedIndex : seedIndexes)
        {
            IndexManager indexManager = graphDb.index();
            Index<Node> index = indexManager.forNodes(seedIndex.getIndexName(), LuceneIndexImplementation.FULLTEXT_CONFIG);
            index.add(node, seedIndex.getFieldName(), seedIndex.getIndexValue());
        }
    }

    /**
     * Create the relationship between tow nodes. The <code>fromId</code> and
     * <code>toId</code> are critical to the relationships.
     * 
     * @param relationships
     *            the relationships
     */
    private void createRelationships(List<SeedRelationship> relationships, Map<Long, Node> createdNodes)
    {
        for (SeedRelationship rel : relationships)
        {
            Node from = createdNodes.get(rel.getFromRowId());
            Node to = createdNodes.get(rel.getToRowId());
            RelationshipWrapper wrapper = new RelationshipWrapper(rel.getType());
            Relationship relationship = from.createRelationshipTo(to, wrapper);

            // add the relationship properties
            for (String relationshipPropertyKey : rel.getProperties().keySet())
            {
                relationship.setProperty(relationshipPropertyKey, rel.getProperties().get(relationshipPropertyKey));
            }
        }
    }

    /**
     * Creates the list of node ids based upon the nodes that we've created.
     */
    private Set<Long> createListOfNodeIds(Map<Long, Node> createdNodes)
    {
        Set<Long> nodeIds = new HashSet<Long>();
        for (Node node : createdNodes.values())
        {
            nodeIds.add(node.getId());
        }
        return nodeIds;
    }

    /**
     * The json file contains a string relationship, this is a simple wrapper to
     * let the dbGraph be happy as it expects a {@link RelationshipType }.
     * 
     * @author ichishty
     */
    private class RelationshipWrapper implements RelationshipType
    {

        /**
         * Instantiates a new relationship wrapper.
         * 
         * @param name
         *            the name
         */
        RelationshipWrapper(String name)
        {
            this.name = name;
        }

        /** The name. */
        private String name;

        /*
         * (non-Javadoc)
         * @see org.neo4j.graphdb.RelationshipType#name()
         */
        public String name()
        {
            return name;
        }

    }

}
