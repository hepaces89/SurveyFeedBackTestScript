/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hepaces.surveyfeedbacktestscript;

import junit.framework.Assert;
import org.apache.commons.codec.binary.StringUtils;
import org.hepaces.surveyfeedbacktestscript.KrogerFeedbackManager;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author hubert
 */
public class KrogerFeedBackUICheck {
    
    private static WebDriver browser;
        
    public KrogerFeedBackUICheck() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        browser = new FirefoxDriver();
    }
    
    @AfterClass
    public static void tearDownClass() {
        browser.close();
        browser.quit();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testErrorMessage(){
        DateTime date = new DateTime();
        String entryId = "014-695-15-858-88-0a";
        
        browser.get("https://www.krogerfeedback.com/");
        
        KrogerFeedbackManager.fillInDateAndTime(date, browser);
        KrogerFeedbackManager.fillInEntryId(entryId, browser);
        
        KrogerFeedbackManager.clickSubmit(browser);
        
        String expectedErrorMessage = "Sorry, we are unable to continue the survey based on the information you provided. Please try again.";
        
        Assert.assertTrue("The error message displayed: \"" + KrogerFeedbackManager.getErrorMessage(browser) + "\" did not match the expected error message:\"" + expectedErrorMessage, StringUtils.equals(expectedErrorMessage, KrogerFeedbackManager.getErrorMessage(browser).trim()));
        
    }
}
