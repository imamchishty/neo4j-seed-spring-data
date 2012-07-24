package com.shedhack.testing.neo4j.dto;

/**
 * The Class SeedIndex.
 */
public class SeedIndex
{

    /** The index name. */
    private String indexName;

    /** The index key. */
    private String fieldName;

    /** The index value. */
    private Object indexValue;

    /**
     * Gets the index name.
     * 
     * @return the index name
     */
    public String getIndexName()
    {
        return indexName;
    }

    /**
     * Sets the index name.
     * 
     * @param indexName
     *            the new index name
     */
    public void setIndexName(String indexName)
    {
        this.indexName = indexName;
    }

    /**
     * @return the fieldName
     */
    public String getFieldName()
    {
        return fieldName;
    }

    /**
     * @param fieldName
     *            the fieldName to set
     */
    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    /**
     * Gets the index value.
     * 
     * @return the index value
     */
    public Object getIndexValue()
    {
        return indexValue;
    }

    /**
     * Sets the index value.
     * 
     * @param indexValue
     *            the new index value
     */
    public void setIndexValue(Object indexValue)
    {
        this.indexValue = indexValue;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "SeedIndex [indexName=" + indexName + ", fieldName=" + fieldName + ", indexValue=" + indexValue + "]";
    }

}
