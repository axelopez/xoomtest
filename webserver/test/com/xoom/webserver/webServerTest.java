/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xoom.webserver;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author axel
 */
public class webServerTest {
    
    public webServerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getServerPort method, of class webServer.
     */
    @Test
    public void testGetServerPort() {
        System.out.println("getServerPort");
        webServer instance = null;
        int expResult = 0;
        int result = instance.getServerPort();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setServerPort method, of class webServer.
     */
    @Test
    public void testSetServerPort() {
        System.out.println("setServerPort");
        int serverPort = 0;
        webServer instance = null;
        instance.setServerPort(serverPort);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of debug method, of class webServer.
     */
    @Test
    public void testDebug_String() {
        System.out.println("debug");
        String mensaje = "";
        webServer instance = null;
        instance.debug(mensaje);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of debug method, of class webServer.
     */
    @Test
    public void testDebug_String_int() {
        System.out.println("debug");
        String mensaje = "";
        int gravedad = 0;
        webServer instance = null;
        instance.debug(mensaje, gravedad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class webServer.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        webServer.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of proccessParams method, of class webServer.
     */
    @Test
    public void testProccessParams() {
        System.out.println("proccessParams");
        webServer instance = null;
        boolean expResult = false;
        boolean result = instance.proccessParams();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class webServer.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        webServer instance = null;
        boolean expResult = false;
        boolean result = instance.start();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
