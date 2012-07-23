/*
 * @(#)DBUnit.java 1.0.0 2011/12/05 Copyright (c) 1994-2011 NCR Corporation
 * Company Address (if required) All rights reserved. This software is the
 * confidential and proprietary information of NCR Corporation
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the license
 * agreement you entered into with NCR.
 */
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
