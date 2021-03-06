package com.shedhack.dummy.app.example.spring.entity;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * The Class UserEntity.
 */
@NodeEntity
public class UserEntity
{

    /**
     * Instantiates a new user entity.
     */
    public UserEntity()
    {
    }

    /** The node id. */
    @GraphId
    private Long nodeId;

    /** The hobbies. */
    @RelatedTo(elementClass = HobbyEntity.class, type = "HOBBY")
    @Fetch
    private Set<HobbyEntity> hobbies;

    /** The address. */
    @RelatedTo(elementClass = AddressEntity.class, type = "ADDRESS")
    @Fetch
    private AddressEntity address;

    /** The name. */
    @Indexed(indexName = "usernameIndex")
    private String username;

    /** The public id. */
    @Indexed(indexName = "publicIdIndex")
    private String publicId;

    /** The description. */
    @Indexed(indexName = "descriptionIndex")
    private String description;

    /**
     * Gets the hobbies.
     * 
     * @return the hobbies
     */
    public Set<HobbyEntity> getHobbies()
    {
        return hobbies;
    }

    /**
     * Sets the hobbies.
     * 
     * @param hobbies
     *            the new hobbies
     */
    public void setHobbies(Set<HobbyEntity> hobbies)
    {
        this.hobbies = hobbies;
    }

    /**
     * Gets the address.
     * 
     * @return the address
     */
    public AddressEntity getAddress()
    {
        return address;
    }

    /**
     * Sets the address.
     * 
     * @param address
     *            the new address
     */
    public void setAddress(AddressEntity address)
    {
        this.address = address;
    }

    /**
     * Gets the name.
     * 
     * @return the name
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * Sets the name.
     * 
     * @param username
     *            the new username
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * Gets the public id.
     * 
     * @return the public id
     */
    public String getPublicId()
    {
        return publicId;
    }

    /**
     * Sets the public id.
     * 
     * @param publicId
     *            the new public id
     */
    public void setPublicId(String publicId)
    {
        this.publicId = publicId;
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

    /**
     * Gets the description.
     * 
     * @return the description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets the description.
     * 
     * @param description
     *            the new description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "UserEntity [nodeId=" + nodeId + ", hobbies=" + hobbies + ", address=" + address + ", username=" + username
                + ", publicId=" + publicId + ", description=" + description + "]";
    }

}
