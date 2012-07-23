package com.shedhack.testing.neo4j.spring.listener;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.GraphDatabaseService;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;
import org.springframework.util.Assert;

import com.shedhack.testing.neo4j.annotation.Neo4jTest;
import com.shedhack.testing.neo4j.dto.SeedData;
import com.shedhack.testing.neo4j.json.JsonToSeedDataMapper;
import com.shedhack.testing.neo4j.seeding.DefaultSeedHandler;
import com.shedhack.testing.neo4j.seeding.SeedHandler;

/**
 * Class is responsible for the creation and the teardown of test data for
 * neo4j. It is assumed that the class has access to the spring context. The
 * path to the dataset will be required via the Neo4jTest annotation. Failure to
 * provide this will cause an exception.
 * 
 * @author ichishty
 */
public class Neo4jSeedTestExecutionListener extends AbstractTestExecutionListener
{
    /** The logger. */
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Neo4jSeedTestExecutionListener.class);

    private GraphDatabaseService graphDb;

    private JsonToSeedDataMapper mapper;

    private SeedHandler seedHandler;

    private boolean setupComplete;

    private Set<Long> nodeIds = new HashSet<Long>();

    /**
     * Sets up the seed data by delegating to the parser and neo4jUnit. The file
     * location is part of the Neo4jTest annotation which the test should
     * provide as meta data.
     * 
     * @param file
     * @throws Exception
     */
    private void setUpSeedData(String file) throws Exception
    {
        logger.info("Setting up the seed data using " + file);
        if (file != null && !file.isEmpty())
        {
            SeedData data = mapper.mapToSeedData(file);
            logger.info("Seed data: " + data);
            nodeIds = seedHandler.createNodes(data);
            logger.info("Created the following nodes " + nodeIds);
        }
        setupComplete = true;

    }

    /**
     * Tears down the data
     * 
     * @throws Exception
     */
    private void tearDownSeedData() throws Exception
    {
        logger.info("Tearing down the test data, ids " + nodeIds);
        seedHandler.removeNodes(nodeIds);
        graphDb.shutdown();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void prepareTestInstance(TestContext context) throws Exception
    {
        if (!setupComplete)
        {
            logger.info("Preparing the test instance setting up beans.");
            setupBeans(context);

            // get the file location or throw exception
            Neo4jTest neo = context.getTestInstance().getClass().getAnnotation(Neo4jTest.class);
            Assert.notNull(neo, "Seed data location is missing. Unable to continue.");
            setUpSeedData(neo.fileLocation());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afterTestClass(TestContext context) throws Exception
    {
        tearDownSeedData();
    }

    /**
     * Sets the up beans that are used.
     * 
     * @param context
     *            the new up beans
     */
    private void setupBeans(TestContext context)
    {
        graphDb = (GraphDatabaseService) context.getApplicationContext().getBean("graphDatabaseService");
        mapper = new JsonToSeedDataMapper();
        seedHandler = new DefaultSeedHandler(graphDb);
    }

}
