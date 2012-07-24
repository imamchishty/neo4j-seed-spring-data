Neo4j-seed-spring-data
======================

About
-----

This component came about after requiring a simply way of seeding test data for integration tests with Neo4j and Spring. 

Usage
-----

The best way to see how this can be used is with an example. Lets look at a simple spring backed application where seed data is required for tests.

### Context file

Lets start with a simple context file which defines a test graph database, the transaction manager and the location of the spring data repositories (please note that even though the example is focusing on SDN backed applications it should be pretty straighforward to use the native api.)

  	<?xml version="1.0" encoding="UTF-8"?>
			<beans xmlns="http://www.springframework.org/schema/beans"
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
			xmlns:neo4j="http://www.springframework.org/schema/data/neo4j"
			default-lazy-init="true"
			xmlns:context="http://www.springframework.org/schema/context"
			xmlns:tx="http://www.springframework.org/schema/tx"
			xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
				http://www.springframework.org/schema/data/neo4j http://www.springframework.org/schema/data/neo4j/spring-neo4j-2.0.xsd
				http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-2.5.xsd
				http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-3.0.xsd">

			<neo4j:config graphDatabaseService="graphDatabaseService" />

			<bean id="graphDatabaseService" class="org.neo4j.test.ImpermanentGraphDatabase" />

			<tx:annotation-driven mode="aspectj" transaction-manager="neo4jTransactionManager" />

			<neo4j:repositories base-package="com.shedhack.dummy.app.example.spring.repo" />

		</beans>

### Entities

Now that we've defined a context file lets take a look at the entities and the repositories:

	@NodeEntity
	public class UserEntity
	{	

	    public UserEntity()
	    {
	    }

	    @GraphId
	    private Long nodeId;

	    @RelatedTo(elementClass = HobbyEntity.class, type = "HOBBY")
	    @Fetch
	    private Set<HobbyEntity> hobbies;

	    @RelatedTo(elementClass = AddressEntity.class, type = "ADDRESS")
	    @Fetch
	    private AddressEntity address;

	    @Indexed(indexName = "usernameIndex")
	    private String username;

	    @Indexed(indexName = "publicIdIndex")
	    private String publicId;

	    ... rest omitted

This simple example shows the central entity as the User. The user has a set of hobbies, an address, a nodeId (which is dynamicaly created by Neo4j), a username with an index called username and a publicId also with an index. Please note that the indexName must be provided if you're using SDN. It also must be unique.

### JSON example

	{
		"entities": 
			[
				{ 
					"rowId": 1,  "type" : "com.shedhack.dummy.app.example.spring.entity.UserEntity", 		
					"properties": {"username": "imam",  "publicId": "1", "description" : "something" }, 
					"indexes" :
						[
							{"indexName": "usernameIndex", "fieldName" : "username", "indexValue" : "imam" }, 
							{"indexName": "publicIdIndex", "fieldName" : "publicId", "indexValue" : "1" }, 
							{"indexName": "descriptionIndex", "fieldName" : "description", "indexValue" : "something" } 
						]
				},
				{ 
					"rowId": 2,  "type" : "com.shedhack.dummy.app.example.spring.entity.AddressEntity", 	
					"properties": {"postcode": "b99"} 
				},
				{ 
					"rowId": 3,  "type" : "com.shedhack.dummy.app.example.spring.entity.HobbyEntity", 	
					"properties": {"name": "football", "description" : "something"},
					"indexes" :
						[ 
							{"indexName": "hobbyDescriptionIndex", "fieldName" : "description", "indexValue" : "something" } 
						]
				},
				{ 
					"rowId": 4,  "type" : "com.shedhack.dummy.app.example.spring.entity.HobbyEntity", 	
					"properties": {"name": "tennis", "description" : "something"},
					"indexes" :
						[ 
							{"indexName": "hobbyDescriptionIndex", "fieldName" : "description", "indexValue" : "something" } 
						] 
				}
			],
			
		"relationships":
			[
				{ "fromRowId": 1, "toRowId": 2, "direction": "BOTH", "type": "ADDRESS", "properties": {"message": "home"} },
				{ "fromRowId": 1, "toRowId": 3, "direction": "BOTH", "type": "HOBBY" },
				{ "fromRowId": 1, "toRowId": 4, "direction": "BOTH", "type": "HOBBY" }
			]
	}

Points of interest:

* rowId must always be provided and be unique. This is not stored in neo4j and used purely for mapping relationships.
* If you're using SDN then the type property must be FQN.
* Properties can be of any type, the example only show strings.
* If you have indexes then the indexName must be unqiue and match the indexName specified in the entity.
* In relationships the direction is not currently being used.

### Example test

The following is how this should be used:

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

	    @Autowired
	    HobbyRepository hobbyRepo;

	    ... rest omitted

Key points
 
* @Neo4jTest must be provided, fileLocation property should be available in the classpath.
* Neo4jSeedTestExecutionListener.class should be added to the listeners.
* The test data will be inserted and removed in the background (before class for insertion and after class for deletion).

Caveats
-------

* This component was created in a few hours and is far from perfect. I've successfully consumed it for a simple SDN +  Neo4j project, but am pretty sure it's not 100% yet.


Contact
-------

	Please feel free to contact me via email, imamchishty@gmail.com