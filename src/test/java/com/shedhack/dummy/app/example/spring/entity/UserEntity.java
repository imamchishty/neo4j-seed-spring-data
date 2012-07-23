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
    @Indexed()
    private String name;

    /** The public id. */
    @Indexed()
    private String publicId;

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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return "UserEntity [nodeId=" + nodeId + ", hobbies=" + hobbies + ", address=" + address + ", name=" + name + ", publicId="
                + publicId + "]";
    }

}
