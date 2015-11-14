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
public class webRequestTest {
    
    public webRequestTest() {
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
     * Test of postDebug method, of class webRequest.
     */
    @Test
    public void testPostDebug() {
        System.out.println("postDebug");
        String mensaje = "";
        webRequest instance = null;
        instance.postDebug(mensaje);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of debug method, of class webRequest.
     */
    @Test
    public void testDebug_String() {
        System.out.println("debug");
        String mensaje = "";
        webRequest instance = null;
        instance.debug(mensaje);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of debug method, of class webRequest.
     */
    @Test
    public void testDebug_String_int() {
        System.out.println("debug");
        String mensaje = "";
        int gravedad = 0;
        webRequest instance = null;
        instance.debug(mensaje, gravedad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class webRequest.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        webRequest instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retornaFichero method, of class webRequest.
     */
    @Test
    public void testRetornaFichero() {
        System.out.println("retornaFichero");
        String file = "";
        webRequest instance = null;
        instance.retornaFichero(file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
