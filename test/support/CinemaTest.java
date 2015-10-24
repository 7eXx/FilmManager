/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package support;

import java.util.Iterator;
import javafx.collections.ObservableList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marco
 */
public class CinemaTest {
    
    public CinemaTest() {
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
     * Test of getInfo method, of class Cinema.
     */
    @Test
    public void testGetInfo() {
        System.out.println("getInfo");
        Class o = Genere.class;
        ObservableList expResult = null;
        ObservableList result = Cinema.getInfo(o, null);
       
        for (Iterator iterator = result.iterator(); iterator.hasNext();) {
            Object next = iterator.next();
            Genere g = (Genere) next;
            System.out.println(g.getGenere() +" " + g.getDescrizione());
        }
    }

    /**
     * Test of updateInfo method, of class Cinema.
     */
    @Test
    public void testUpdateInfo() {
        System.out.println("updateInfo");
        Object obj = null;
        boolean expResult = false;
        boolean result = Cinema.updateInfo(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertInfo method, of class Cinema.
     */
    @Test
    public void testInsertInfo() {
        System.out.println("insertInfo");
        Object obj = null;
        boolean expResult = false;
        boolean result = Cinema.insertInfo(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteInfo method, of class Cinema.
     */
    @Test
    public void testDeleteInfo() {
        System.out.println("deleteInfo");
        Object obj = null;
        boolean expResult = false;
        boolean result = Cinema.deleteInfo(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
    
}
