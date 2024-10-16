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

}