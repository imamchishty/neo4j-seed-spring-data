package com.shedhack.dummy.app.example.spring.entity;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * The Class HobbyEntity.
 */
@NodeEntity
public class HobbyEntity
{

    /**
     * Instantiates a new hobby entity.
     */
    public HobbyEntity()
    {
    }

    /** The name. */
    private String name;

    /** The node id. */
    @GraphId
    private Long nodeId;

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name.
     * 
     * @param name
     *            the new name
     */
    public void setName(String name)
    {
        this.name = name;
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
        return "HobbyEntity [name=" + name + ", nodeId=" + nodeId + "]";
    }

}
