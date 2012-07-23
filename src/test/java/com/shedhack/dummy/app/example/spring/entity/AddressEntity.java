package com.shedhack.dummy.app.example.spring.entity;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * The Class AddressEntity.
 */
@NodeEntity
public class AddressEntity
{

    /**
     * Instantiates a new address entity.
     */
    public AddressEntity()
    {

    }

    /** The postcode. */
    private String postcode;

    /** The node id. */
    @GraphId
    private Long nodeId;

    /**
     * Gets the postcode.
     * 
     * @return the postcode
     */
    public String getPostcode()
    {
        return postcode;
    }

    /**
     * Sets the postcode.
     * 
     * @param postcode
     *            the new postcode
     */
    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    /**
     * Gets the node id.
     * 
     * @return the node id
     */
    public Long getNodeId()
    {
        return nodeId;
    }

    /**
     * Sets the node id.
     * 
     * @param nodeId
     *            the new node id
     */
    public void setNodeId(Long nodeId)
    {
        this.nodeId = nodeId;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "AddressEntity [postcode=" + postcode + ", nodeId=" + nodeId + "]";
    }

}
