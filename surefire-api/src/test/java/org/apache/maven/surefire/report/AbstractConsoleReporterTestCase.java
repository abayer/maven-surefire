package org.apache.maven.surefire.report;

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

import junit.framework.TestCase;

/**
 * Test for AbstractConsoleReporter
 *
 * @author <a href="mailto:carlos@apache.org">Carlos Sanchez</a>
 * @version $Id$
 */
public abstract class AbstractConsoleReporterTestCase
    extends TestCase
{

    private AbstractConsoleReporter consoleReporter;

    private ReportEntry report;

    protected void setUp()
        throws Exception
    {
        super.setUp();
        report = CategorizedReportEntry.nameGroup( "name", "group" );
    }

    protected void setConsoleReporter( AbstractConsoleReporter consoleReporter )
    {
        this.consoleReporter = consoleReporter;
    }

    protected AbstractConsoleReporter getConsoleReporter()
    {
        return consoleReporter;
    }

    public void testTestSetStarting()
        throws Exception
    {
        consoleReporter.testSetStarting( report );
    }

    public void testGetTestSetStartingMessage()
        throws Exception
    {
        String message = AbstractConsoleReporter.getTestSetStartingMessage( report );
        assertEquals( "Running name (of group)", message );

        report = CategorizedReportEntry.nameGroup( "name", null );

        message = AbstractConsoleReporter.getTestSetStartingMessage( report );
        assertEquals( "Running name", message );
    }

    public void testParseTestSetStartingMessage()
        throws Exception
    {
        String message = "Running name (of group)";
        ReportEntry actualReport = AbstractConsoleReporter.parseTestSetStartingMessage( message );
        assertEquals( report, actualReport );
    }

    public void testIsTestSetStartingMessage()
        throws Exception
    {
        String message = "Running name (of group)";
        assertTrue( AbstractConsoleReporter.isTestSetStartingMessage( message ) );

        message = "Running name";
        assertTrue( AbstractConsoleReporter.isTestSetStartingMessage( message ) );

        message = "Xxxx";
        assertFalse( AbstractConsoleReporter.isTestSetStartingMessage( message ) );
    }

}
