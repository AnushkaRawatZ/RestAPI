package features;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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


}