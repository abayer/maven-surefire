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
 *     http://www.apache.org/licenses/LICENSE-2.0
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
 * Test simple TestNG listener and reporter
 *
 * @author <a href="mailto:dfabulich@apache.org">Dan Fabulich</a>
 */
public class CheckTestNgListenerReporterIT
    extends AbstractSurefireIntegrationTestClass
{
    public void testTestNgListenerReporter()
        throws Exception
    {
        File testDir = ResourceExtractor.simpleExtractResources( getClass(), "/testng-listener-reporter" );

        Verifier verifier = new Verifier( testDir.getAbsolutePath() );
        this.executeGoal( verifier, "test" );
        verifier.verifyErrorFreeLog();
        verifier.resetStreams();

        HelperAssertions.assertTestSuiteResults( 1, 0, 0, 0, testDir );
        File targetDir = new File( testDir, "target" );
        assertFileExists( new File( targetDir, "resultlistener-output.txt" ) );
        assertFileExists( new File( targetDir, "suitelistener-output.txt" ) );
        assertFileExists( new File( targetDir, "reporter-output.txt" ) );
    }

    private void assertFileExists( File file )
    {
        assertTrue( "File doesn't exist: " + file.getAbsolutePath(), file.exists() );
    }
}
