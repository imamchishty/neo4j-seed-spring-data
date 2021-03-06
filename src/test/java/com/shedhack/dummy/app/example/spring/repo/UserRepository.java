package com.shedhack.dummy.app.example.spring.repo;

import java.util.Set;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;

import com.shedhack.dummy.app.example.spring.entity.UserEntity;

public interface UserRepository extends GraphRepository<UserEntity>, NamedIndexRepository<UserEntity>
{

    /**
     * Find user by public id.
     * 
     * @param publicId
     *            the public id
     * @return the user entity
     */
    UserEntity findByPublicId(String publicId);

    /**
     * Find by name.
     * 
     * @param name
     *            the name
     * @return the user
     */
    UserEntity findByUsername(String name);

    /**
     * Find by description.
     * 
     * @param description
     *            the description
     * @return the user entity
     */
    Set<UserEntity> findByDescription(String description);
}
