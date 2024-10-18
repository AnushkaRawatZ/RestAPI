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

public class UITests {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.rahulshettyacademy.com/AutomationPractice/");
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

    //    1. Click on Radio3 button and verify whether it is selected.
    @Test
    public void testRadioButton() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement radioButton3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='radio3']")));
        radioButton3.click(); // Click directly on the located element
        assertTrue(radioButton3.isSelected(), "Radio3 is not selected.");
        Thread.sleep(2000);
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
        Thread.sleep(2000);
    }

    //    3. Select Option2 from dropdown and verify whether Option2 is displayed in Dropdown.
    @Test
    public void testDropdown() throws InterruptedException {
        WebElement dropdown = driver.findElement(By.xpath("//select"));
        dropdown.click();
        WebElement option2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[@value='option2']")));
        option2.click();
        String selectedValue = dropdown.getAttribute("value");
        assertEquals("option2", selectedValue);
        Thread.sleep(2000);
    }

    //    4. Select Option1 checkbox. Verify if it got checked.
    @Test
    public void testCheckbox() throws InterruptedException {
        WebElement checkbox1 = driver.findElement(By.id("checkBoxOption1"));
        checkbox1.click();
        assertTrue(checkbox1.isSelected(), "Checkbox should be checked");
        Thread.sleep(2000);
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
    public void switchTab() throws InterruptedException {
        driver.findElement(By.id("opentab")).click();

        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        //validate the new tab
        String expectedTitle = "QAClick Academy - A Testing Academy to Learn, Earn and Shine";
        assertTrue(driver.getTitle().contains(expectedTitle));

        assertTrue(driver.findElement(By.linkText("Home")).isDisplayed());
        assertTrue(driver.findElement(By.linkText("Courses")).isDisplayed());
        assertTrue(driver.findElement(By.linkText("Access all our Courses")).isDisplayed());
        assertTrue(driver.findElement(By.linkText("Learn More")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//div[@class='apply-cont apply-color-2']/a")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//li[@class='nav-item']/a[text()='Contact']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//li[@class='nav-item']/a[text()='Blog']")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//li[@class='nav-item']/a[text()='About us']")).isDisplayed());
        Thread.sleep(2000);
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

    //  8. Write a test to sum all the amounts. Verify if the total matches with the 'Total Amount Collected' value.
    @Test
    public void totalAmount() {
        var table = driver.findElement(By.xpath("//div[@class='tableFixHead']/table[@id='product']/tbody"));
        var rows =table.findElements(By.tagName("tr"));
        int sum = 0;
        for(var row:rows) {
            var cells = row.findElements(By.tagName("td"));
            sum +=Integer.parseInt(cells.get(3).getText());
        }
        String totalS = driver.findElement(By.className("totalAmount")).getText();
        int total = Integer.parseInt(totalS.replaceAll("[^0-9]", ""));
        assertEquals(sum, total, "The sum is '"+sum+"' but the total amount is '"+total+"'");
    }

    //  9. Hover the Mouse Hover button (DO NOT CLICK) and select Reload.
    @Test
    public void mouseHover() throws InterruptedException {
        String initialUrl = driver.getCurrentUrl();
        WebElement button = driver.findElement(By.id("mousehover"));
        Actions actions = new Actions(driver);
        actions.moveToElement(button).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Reload")));
        WebElement reload = driver.findElement(By.linkText("Reload"));
        Thread.sleep(3000);
        reload.click();
        String newUrl = driver.getCurrentUrl();
        assertEquals(initialUrl, newUrl, "The page did not reload");
        Thread.sleep(2000);
    }
}