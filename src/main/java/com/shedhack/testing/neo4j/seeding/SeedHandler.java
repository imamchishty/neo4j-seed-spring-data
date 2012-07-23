package com.shedhack.testing.neo4j.seeding;

import java.util.Set;

import com.shedhack.testing.neo4j.dto.SeedData;

/**
 * Responsible for the insertion of valid nodes, indexes and the associated
 * relationships. If you're using this with Spring data graph then you must
 * provide the <code>type</code> property for the entity, this is because Spring
 * is using <code>TypeRepresentationStrategy</code>. Please note that this
 * implementation only currently supports IndexingNodeTypeRepresentationStrategy
 * if you're using SDN. If you're not using SDN then the imports should work as
 * expected. An example for SDN:
 * 
 * <pre>
 * 
 * {
 *   "entities": 
 *       [
 *           { "rowId": 1,  "type" : "com.shedhack.dummy.app.example.spring.entity.UserEntity", ...
 *       ]
 *    }
 * 
 * </pre>
 * 
 * @author ichishty
 */
public interface SeedHandler
{
    /**
     * Inserts seed data and returns a set of node ids that were created.
     * 
     * @param data
     *            the data
     * @return the list
     */
    Set<Long> createNodes(SeedData data);

    /**
     * Clears specified nodes based on the provided node ids.
     */
    void removeNodes(Set<Long> nodeIds);
}
