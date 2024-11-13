package features;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import pages.*;

public class UITests {
    private WebDriver driver;
    private WebDriverWait wait;
    PracticePage practicePage;
    NewTabPage newTabPage;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
        practicePage = new PracticePage(driver);
        newTabPage = new NewTabPage(driver);
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    //    1. Click on Radio3 button and verify whether it is selected.
    @Test
    public void testRadioButton(){
        practicePage.clickRadioButton3(); // Click directly on the located element
        assertTrue(practicePage.isRadioButton3Selected(), "Radio3 is not selected.");
    }

    //    2. Type Ind in the textbox, and select India from the options. Verify if India is populated in textbox.
    @Test
    public void testSuggestionClass(){
        practicePage.selectIndiaFromAutocomplete();
        assertEquals("India", practicePage.getAutocompleteTextValue());
    }

    //    3. Select Option2 from dropdown and verify whether Option2 is displayed in Dropdown.
    @Test
    public void testDropdown(){
        practicePage.selectOption2FromDropdown();
        assertEquals("option2", practicePage.getSelectedDropdownValue());
    }

    //    4. Select Option1 checkbox. Verify if it got checked.
    @Test
    public void testCheckbox(){
        practicePage.selectOption1Checkbox();
        assertTrue(practicePage.isOption1CheckboxSelected(), "Checkbox should be checked");
    }

    //   5. Switch Tab Example
//a. Click Open Tab button
//b. Verify whether the following buttons are displayed :
//i. Home
//ii. Courses
//iii. Access All our Courses
//iv. Learn More
//v. Apply Now
//vi. Contact
//vii. Blog
//viii. About us
    @Test
    public void switchTab(){
        practicePage.openNewTab();

        assertEquals("QAClick Academy - A Testing Academy to Learn, Earn and Shine", practicePage.switchWindowsAndGetTitle());

        assertTrue(newTabPage.verifyTabButtons());
    }

    //  6. Switch To Alert Example
    @Test
    public void testAlert() {
        practicePage.sendKeystoName();
        practicePage.handleAlert();
        assertTrue(practicePage.isAlertHandled());

        practicePage.sendKeystoName();
        practicePage.handleConfirmBtn();
        assertTrue(practicePage.isAlertHandled());
    }

    //  7. Write a test to verify if the price of the course 'Master Selenium Automation in simple Python Language' is 35.
    @Test
    public void webTable() {
        assertTrue(practicePage.isCoursePriceCorrect("Master Selenium Automation in simple Python Language", 35), "It is not 35");
    }

    //  8. Write a test to sum all the amounts. Verify if the total matches with the 'Total Amount Collected' value.
    @Test
    public void totalAmount() {
        int sum = practicePage.sumAllAmounts();
        System.out.println(sum);
        int totalS = practicePage.getTotalAmount();
        System.out.println(totalS);
        assertEquals(sum, totalS, "The sum is '"+sum+"' but the total amount is '"+totalS+"'");
    }

    //  9. Hover the Mouse Hover button (DO NOT CLICK) and select Reload.
    @Test
    public void mouseHover() {
        String initialUrl = driver.getCurrentUrl();
        practicePage.hoverAndClickReload();
        assertTrue(practicePage.checkIfPageReloaded(initialUrl), "The page did not reload");
    }
}