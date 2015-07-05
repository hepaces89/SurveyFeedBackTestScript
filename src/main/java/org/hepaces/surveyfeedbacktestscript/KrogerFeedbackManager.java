/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hepaces.surveyfeedbacktestscript;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

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
    
    public static void fillInDateAndTime(DateTime date, WebDriver browser){
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
        String entryId = "014-695-15-858-88-28";
        browser.get("https://www.krogerfeedback.com/");
        
        fillInDateAndTime(date, browser);
        fillInEntryId(entryId, browser);
        browser.close();
        browser.quit();
    }
}
