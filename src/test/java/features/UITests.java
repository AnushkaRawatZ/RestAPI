package features;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UITests {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/raramuri/Downloads/chromedriver-mac-arm64/chromedriver"); // Update the path
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
    }

//    1. Click on Radio3 button and verify whether it is selected.
    @Test
    public void testRadioButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement radioButton3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='radio3']")));
        radioButton3.click(); // Click directly on the located element
        assertTrue(radioButton3.isSelected(), "Radio3 is not selected.");
    }

//    2. Type Ind in the textbox, and select India from the options. Verify if India is populated in textbox.
    @Test
    public void testSuggestionClass() throws InterruptedException {
        WebElement autocompleteTextbox = driver.findElement(By.id("autocomplete"));
        autocompleteTextbox.sendKeys("Ind");
        Thread.sleep(2000); // Consider using WebDriverWait instead

        WebElement suggestion = driver.findElement(By.xpath("//div[contains(@class, 'ui-menu-item-wrapper') and text()='India']"));
        suggestion.click();

        String selectedValue = autocompleteTextbox.getAttribute("value");
        assertEquals("India", selectedValue);
    }
//    3. Select Option2 from dropdown and verify whether Option2 is displayed in Dropdown.
    @Test
    public void testDropdown() {
        WebElement dropdown = driver.findElement(By.xpath("//select"));
        dropdown.click();
        WebElement option2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[@value='option2']")));
        option2.click();
        String selectedValue = dropdown.getAttribute("value");
        assertEquals("option2", selectedValue);
    }

//    4. Select Option1 checkbox. Verify if it got checked.
    @Test
    public void testCheckbox() {
        WebElement checkbox1 = driver.findElement(By.id("checkBoxOption1"));
        checkbox1.click();
        assertTrue(checkbox1.isSelected(), "Checkbox should be checked");
    }

//  6. Switch To Alert Example
    @Test
    public void testAlert() throws InterruptedException {
        WebElement textbox = driver.findElement(By.id("name"));

        //a. Enter your name in textbox
        textbox.sendKeys("Anushka");

        //b. Click on Alert button and accept the alert
        WebElement alertButton = driver.findElement(By.id("alertbtn"));
        alertButton.click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert1 = driver.switchTo().alert();
        Thread.sleep(2000);
        alert1.accept();

        //c. Click on confirm button and cancel the alert
        textbox.sendKeys("Anushka");
        WebElement confirmButton = driver.findElement(By.id("confirmbtn"));
        confirmButton.click();

        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert2 = driver.switchTo().alert();
        Thread.sleep(2000);
        alert2.dismiss();
    }

//  7. Write a test to verify if the price of the course 'Master Selenium Automation in simple Python Language' is 35.
    @Test
    public void webTable() {
        var table = driver.findElement(By.xpath("//table[@name='courses']"));
        var rows = table.findElements(By.tagName("tr"));

        String coursePrice = null;

        for (var row : rows) {
            var cells = row.findElements(By.tagName("td"));
            if (cells.size() > 0 && "Master Selenium Automation in simple Python Language".equals(cells.get(1).getText())) {
                coursePrice = cells.get(2).getText(); // Assuming price is in the third column
                break;
            }
        }
        assertEquals("35", coursePrice, "Expected price to be '35', but got '" + coursePrice + "'");
    }
