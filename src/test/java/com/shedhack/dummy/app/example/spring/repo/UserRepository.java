package com.shedhack.dummy.app.example.spring.repo;

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
    UserEntity findUserByPublicId(String publicId);
}
