/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servioticy.restclient;

import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luca Capra <luca.capra@create-net.org>
 */
public class RestClientTest {
    
    public RestClientTest() {
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

//    @Test
//    public void testClose() throws Exception {
//        System.out.println("close");
//        RestClient instance = new RestClient();
//        instance.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of restRequest method, of class RestClient.
     */
    @Test
    public void testRestRequest() throws Exception {
        
        System.out.println("restRequest");
        String url = "https://api.github.com/users/muka";
        String body = "";
        int method = RestClient.GET;
        Map<String, String> headers = null;
        
        RestClient instance = new RestClient();
        FutureRestResponse result = instance.restRequest(url, body, method, headers);
        
        assertEquals(true, result.get().getResponse().length() > 0);
        
        instance.close();
    }
    
}
