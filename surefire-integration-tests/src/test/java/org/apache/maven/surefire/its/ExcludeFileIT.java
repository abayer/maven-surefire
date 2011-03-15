package org.apache.maven.surefire.its;
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;

import java.io.File;

/**
 * Test exclude file.
 *
 * @author Andrew Bayer
 * @version $Id$
 */
public class ExcludeFileIT
    extends AbstractSurefireIntegrationTestClass
{

    /**
     * Test surefire exclusions via excludeFile
     */
    public void testExcludeFile()
        throws Exception
    {
        File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/excludeFile" );

        Verifier verifier = new Verifier( testDir.getAbsolutePath() );
        executeGoal( verifier, "test" );
        verifier.assertFilePresent( "target/testTouchFile.txt" );
        verifier.assertFilePresent( "target/defaultTestTouchFile.txt" );
        verifier.verifyErrorFreeLog();
        verifier.resetStreams();

        HelperAssertions.assertTestSuiteResults( 2, 0, 0, 0, testDir );
    }

}
