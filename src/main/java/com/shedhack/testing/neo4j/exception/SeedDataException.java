package com.shedhack.testing.neo4j.exception;

/**
 * Runtime exception for the neo4j seed component.
 */
public class SeedDataException extends RuntimeException
{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new seed data exception.
     * 
     * @param message
     *            the message
     * @param ex
     *            the ex
     */
    public SeedDataException(String message, Exception ex)
    {
        super(message, ex);
    }
}
