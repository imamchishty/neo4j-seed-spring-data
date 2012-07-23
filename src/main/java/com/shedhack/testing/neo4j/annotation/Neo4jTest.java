
package com.shedhack.testing.neo4j.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation should be used with suitable test execution listeners.
 * 
 * @author ichishty
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Neo4jTest
{

    /**
     * File location, eventually this will be expanded to take multiple files.
     * This property is always required. Failure to provide this will cause a
     * runtime exception.
     * 
     * @return the string
     */
    String fileLocation();
}
