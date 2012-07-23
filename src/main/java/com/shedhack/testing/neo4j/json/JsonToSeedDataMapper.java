package com.shedhack.testing.neo4j.json;

import org.codehaus.jackson.map.ObjectMapper;

import com.shedhack.testing.neo4j.dto.SeedData;
import com.shedhack.testing.neo4j.exception.SeedDataException;
import com.shedhack.testing.neo4j.seeding.SeedHandler;

/**
 * JsonToSeedsMapper is responsible for the conversion of a valid json file to
 * the the {@link SeedData} object which is used by the {@link SeedHandler}.
 * 
 * @author ichishty
 */
public class JsonToSeedDataMapper
{
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Maps the input json to the the {@link SeedData} object. The method will
     * look for the file on the classpath.
     * 
     * @param file
     *            the file
     * @return the seed data
     * @throws SeedDataException
     */
    public SeedData mapToSeedData(String file)
    {
        try
        {
            return mapper.readValue(this.getClass().getClassLoader().getResourceAsStream(file), SeedData.class);
        }
        catch (Exception ex)
        {
            throw new SeedDataException("Unable to convert the json file to the seed data object", ex);
        }
    }
}
