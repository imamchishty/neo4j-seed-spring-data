package com.shedhack.testing.neo4j.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * SeedData is a wrapper for multiple {@link SeedEntity} and.
 * {@link SeedRelationship}.
 */
public class SeedData
{

    /** The entities. */
    private List<SeedEntity> entities = new ArrayList<SeedEntity>();

    /** The relationships. */
    private List<SeedRelationship> relationships = new ArrayList<SeedRelationship>();

    /**
     * Gets the entities.
     * 
     * @return the entities
     */
    public List<SeedEntity> getEntities()
    {
        return entities;
    }

    /**
     * Sets the entities.
     * 
     * @param entities
     *            the new entities
     */
    public void setEntities(List<SeedEntity> entities)
    {
        this.entities = entities;
    }

    /**
     * Gets the relationships.
     * 
     * @return the relationships
     */
    public List<SeedRelationship> getRelationships()
    {
        return relationships;
    }

    /**
     * Sets the relationships.
     * 
     * @param relationships
     *            the new relationships
     */
    public void setRelationships(List<SeedRelationship> relationships)
    {
        this.relationships = relationships;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "SeedData [entities=" + entities + ", relationships=" + relationships + "]";
    }

}
