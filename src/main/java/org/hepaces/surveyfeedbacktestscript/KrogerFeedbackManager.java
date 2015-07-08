/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hepaces.surveyfeedbacktestscript;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author hubert
 */
public class KrogerFeedbackManager {
    //Date InputId constants
    public static String inputMonthId = "InputMonth";
    public static String inputDayId = "InputDay";
    public static String inputYearId = "InputYear";
    
    //Time Input Id constants
    public static String inputHourId = "InputHour";
    public static String inputMinuteId = "InputMinute";
    public static String inputAMPM = "InputMeridian";
    
    public static String entryIdPrefix = "CN";
    
    public static Logger logger = LoggerFactory.getLogger(KrogerFeedbackManager.class);
    
    /**
     * Fills in the date and time fields
     * @param date
     * @param browser 
     */
    public static void fillInDateAndTime(DateTime date, WebDriver browser){
        logger.debug("Attempting to fill in the date and time inputs @:" + browser.getCurrentUrl());
        try{
            //Date Inputs
            WebElement monthInput = browser.findElement(By.id(inputMonthId));
            WebElement dayInput = browser.findElement(By.id(inputDayId));
            WebElement yearInput = browser.findElement(By.id(inputYearId));
            
            //Time Inputs
            WebElement hourInput = browser.findElement(By.id(inputHourId));
            WebElement minuteInput = browser.findElement(By.id(inputMinuteId));
            WebElement amPmInput = browser.findElement(By.id(inputAMPM));
            
            //handle date inputs (year is prepopulated, so not handled)
            numericDropDownHandler(date.getMonthOfYear(), monthInput);
            numericDropDownHandler(date.getDayOfMonth(), dayInput);
                        
            //handle time inputs
            numericDropDownHandler(date.get(DateTimeFieldType.clockhourOfHalfday()), hourInput);
            numericDropDownHandler(date.getMinuteOfHour(), minuteInput);
            numericDropDownHandler(date.get(DateTimeFieldType.halfdayOfDay())+1, amPmInput);
            
            
            
        } catch (Exception e){
        }
    }
    
    /**
     * Fills in the EntryId field
     * @param entryId
     * @param browser
     * @return 
     */
    public static String[] fillInEntryId(String entryId, WebDriver browser){
        String[] entryIdComponents = entryId.split("[\\-]");
        WebElement entryIdComponentInput = browser.findElement(By.id(entryIdPrefix+1));
        for(int i = 0; i < 6; i++){
            entryIdComponentInput = browser.findElement(By.id(entryIdPrefix+ (i+1)));
            entryIdComponentInput.click();
            entryIdComponentInput.sendKeys(entryIdComponents[i]);
        }
        return entryIdComponents;
    }
    
    public static void clickSubmit(WebDriver browser){
        WebElement submitButton = browser.findElement(By.id("NextButton"));
        submitButton.click();
    }
    
    public static String getErrorMessage(WebDriver browser){
        String errorMessage = null;
        WebElement error = browser.findElement(By.cssSelector(".Error"));
        
        if(error.isDisplayed() && !StringUtils.isEmpty(error.getText())){
            errorMessage = error.getText();
        }
        return errorMessage;
    }
    
    /**
     * Centralized handling for numeric dropdowns (select elements)
     * @param numberDownArrowPresses
     * @param input 
     */
    private static void numericDropDownHandler(int numberDownArrowPresses, WebElement input){
        input.click();
        for (int i = 1; i <= numberDownArrowPresses; i++) {
            input.sendKeys(Keys.ARROW_DOWN);
        }
        input.sendKeys(Keys.TAB);
    }
    
    public static void main(String[] args){
        //test inputs
        DateTime date = new DateTime();
        WebDriver browser = new FirefoxDriver();
        String entryId = "014-695-15-858-88-0a";
        browser.get("https://www.krogerfeedback.com/");
        
        fillInDateAndTime(date, browser);
        fillInEntryId(entryId, browser);
        
        clickSubmit(browser);
        
        System.out.println(getErrorMessage(browser));
        
        browser.close();
        browser.quit();
    }
}
