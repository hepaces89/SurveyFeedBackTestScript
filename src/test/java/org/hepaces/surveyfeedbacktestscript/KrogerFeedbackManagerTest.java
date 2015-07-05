/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hepaces.surveyfeedbacktestscript;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author hubert
 */
public class KrogerFeedbackManagerTest {
    
    public KrogerFeedbackManagerTest() {
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
     * Test of fillInEntryId method, of class KrogerFeedbackManager.
     */
    @Test
    public void testFillInEntryId() {
        System.out.println("fillInEntryId");
        String entryId = "014-695-15-858-88-28";
        WebDriver browser = Mockito.mock(WebDriver.class);
        WebElement targetElement = Mockito.mock(WebElement.class);
        Mockito.when(browser.findElement(Mockito.any(org.openqa.selenium.By.class))).thenReturn(targetElement);
        String[] expResult = {"014", "695", "15", "858", "88", "28"};
        String[] result = KrogerFeedbackManager.fillInEntryId(entryId, browser);
        assertArrayEquals(expResult, result);
    }
    
}
