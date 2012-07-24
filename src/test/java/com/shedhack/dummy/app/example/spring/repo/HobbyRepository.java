package com.shedhack.dummy.app.example.spring.repo;

import java.util.Set;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.NamedIndexRepository;

import com.shedhack.dummy.app.example.spring.entity.HobbyEntity;

public interface HobbyRepository extends GraphRepository<HobbyEntity>, NamedIndexRepository<HobbyEntity>
{

    /**
     * Find by description.
     * 
     * @param description
     *            the description
     * @return the sets the
     */
    Set<HobbyEntity> findByDescription(String description);
}
