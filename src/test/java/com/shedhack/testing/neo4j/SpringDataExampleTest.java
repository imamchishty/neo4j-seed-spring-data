/*
 * 
 */
package com.shedhack.testing.neo4j;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.shedhack.dummy.app.example.spring.entity.HobbyEntity;
import com.shedhack.dummy.app.example.spring.entity.UserEntity;
import com.shedhack.dummy.app.example.spring.repo.UserRepository;
import com.shedhack.testing.neo4j.annotation.Neo4jTest;
import com.shedhack.testing.neo4j.spring.listener.Neo4jSeedTestExecutionListener;

/**
 * This is an example for how to use the {@link @Neo4jTest} and
 * {@link Neo4jSeedTestExecutionListener}.
 * 
 * @author ichishty
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/spring/example-spring-context.xml" })
@TransactionConfiguration(transactionManager = "neo4jTransactionManager", defaultRollback = true)
@Neo4jTest(fileLocation = "json/test.json")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class,
        Neo4jSeedTestExecutionListener.class })
@Transactional
public class SpringDataExampleTest
{
    @Autowired
    UserRepository userRepo;

    /**
     * Should find user entity with relationships.
     */
    @Test
    public void shouldFindUserEntityWithRelationships()
    {
        // Database is clean so we expect it at position 1, alternative is to
        // query by the name or public Id
        UserEntity user = userRepo.findOne(1L);
        validateUserEntity(user);
    }

    /**
     * Should find user using name index.
     */
    @Test
    @Ignore("ignored until the index __types__ issue is resolved")
    public void shouldFindUserUsingNameIndex()
    {
        UserEntity user = userRepo.findByPropertyValue("name", "imam");
        validateUserEntity(user);
    }

    /**
     * Should find user using public id.
     */
    @Test
    @Ignore("see above")
    public void shouldFindUserUsingPublicId()
    {
        UserEntity user = userRepo.findUserByPublicId("1");
        validateUserEntity(user);
    }

    /**
     * Validate user entity.
     */
    private void validateUserEntity(UserEntity user)
    {
        // validate
        assertNotNull(user);

        // name should be set to imam
        assertEquals("imam", user.getName());

        // public id was set to 1
        assertEquals("1", user.getPublicId());

        // should have an address
        assertNotNull(user.getAddress());

        // address should have the postcode set (please note for the purposes of
        // testing this property was set to Fetch
        assertEquals("b99", user.getAddress().getPostcode());

        // user should have two hobbies, football and tennis, lets check
        assertNotNull(user.getHobbies());
        assertEquals(2, user.getHobbies().size());

        int count = 0;
        for (HobbyEntity hobby : user.getHobbies())
        {
            if (hobby.getName().equals("tennis") || hobby.getName().equals("football"))
                count++;
        }

        // found both?
        assertEquals(2, count);
    }

}
