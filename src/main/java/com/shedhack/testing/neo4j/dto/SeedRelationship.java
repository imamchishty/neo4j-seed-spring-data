package com.shedhack.testing.neo4j.dto;

import java.util.HashMap;
import java.util.Map;

import org.neo4j.graphdb.Direction;

/**
 * SeedRelationship contains the mechanism to map from a node to another.
 */
public class SeedRelationship
{
    /** The from row id. */
    private Long fromRowId;

    /** The to row id. */
    private Long toRowId;

    /** The direction. */
    private Direction direction;

    /** The type. */
    private String type;

    /** The properties. */
    private Map<String, Object> properties = new HashMap<String, Object>();

    /**
     * Gets the from row id.
     * 
     * @return the from row id
     */
    public Long getFromRowId()
    {
        return fromRowId;
    }

    /**
     * Sets the from row id.
     * 
     * @param fromRowId
     *            the new from row id
     */
    public void setFromRowId(Long fromRowId)
    {
        this.fromRowId = fromRowId;
    }

    /**
     * Gets the to row id.
     * 
     * @return the to row id
     */
    public Long getToRowId()
    {
        return toRowId;
    }

    /**
     * Sets the to row id.
     * 
     * @param toRowId
     *            the new to row id
     */
    public void setToRowId(Long toRowId)
    {
        this.toRowId = toRowId;
    }

    /**
     * Gets the direction.
     * 
     * @return the direction
     */
    public Direction getDirection()
    {
        return direction;
    }

    /**
     * Sets the direction.
     * 
     * @param direction
     *            the new direction
     */
    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    /**
     * Gets the type.
     * 
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * Sets the type.
     * 
     * @param type
     *            the new type
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * Gets the properties.
     * 
     * @return the properties
     */
    public Map<String, Object> getProperties()
    {
        return properties;
    }

    /**
     * Sets the properties.
     * 
     * @param properties
     *            the properties
     */
    public void setProperties(Map<String, Object> properties)
    {
        this.properties = properties;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "SeedRelationship [fromRowId=" + fromRowId + ", toRowId=" + toRowId + ", direction=" + direction + ", type=" + type
                + ", properties=" + properties + "]";
    }

}
