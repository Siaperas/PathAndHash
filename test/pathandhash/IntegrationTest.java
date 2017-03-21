/*
 * Copyright (C) 2017 Pavlos Siaperas.
 *
 */
package pathandhash;

import PathAndHash.Integration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pavlos Siaperas
 */
public class IntegrationTest {
    
    public IntegrationTest() {
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
     * Test of integration method, of class Integration.
     */
    @Test
    public void testIntegration() {
        System.out.println("Integration Test");
        int number_zeros = 2;
        int start_x = 1;
        int start_y = 1;
        int end_x = 1;
        int end_y = 27;
        String directory = "graph";
        Integration.integration(number_zeros, start_x, start_y, end_x, end_y, directory);    }
    
}
