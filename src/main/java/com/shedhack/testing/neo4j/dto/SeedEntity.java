package com.shedhack.testing.neo4j.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * SeedEntity contains a unique <code>rowId</code>, <code>indexes</code> and
 * <code>properties</code>.
 */
public class SeedEntity
{
    /**
     * This is the class name, spring requires this property
     * <code>__type__</code>
     */
    private String type;

    /** The row id. */
    private Long rowId;

    /** The properties. */
    private Map<String, Object> properties = new HashMap<String, Object>();

    /** The indexes. */
    private Map<String, String> indexes = new HashMap<String, String>();

    /**
     * Gets the row id.
     * 
     * @return the row id
     */
    public Long getRowId()
    {
        return rowId;
    }

    /**
     * Sets the row id.
     * 
     * @param rowId
     *            the new row id
     */
    public void setRowId(Long rowId)
    {
        this.rowId = rowId;
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

    /**
     * Gets the indexes.
     * 
     * @return the indexes
     */
    public Map<String, String> getIndexes()
    {
        return indexes;
    }

    /**
     * Sets the indexes.
     * 
     * @param indexes
     *            the indexes
     */
    public void setIndexes(Map<String, String> indexes)
    {
        this.indexes = indexes;
    }

    /**
     * Gets the type <code>__type__</code>.
     * 
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * Sets the type <code>__type__</code>. Not a mandatory field. This can be
     * omitted if the application isn't based on spring data graph
     * 
     * @param type
     *            the new type
     */
    public void setType(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "SeedEntity [type=" + type + ", rowId=" + rowId + ", properties=" + properties + ", indexes=" + indexes + "]";
    }

}
