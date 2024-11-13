package pages;

import map.Locators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NewTabPage {
    private WebDriver driver;

    public NewTabPage(WebDriver driver) {
        this.driver = driver;
    }

    // Verifying if the expected elements are visible in the new tab.
    public boolean verifyTabButtons() {
        return driver.findElement(Locators.homelink).isDisplayed() &&
                driver.findElement(Locators.courseslink).isDisplayed() &&
                driver.findElement(Locators.accessallcourseslink).isDisplayed() &&
                driver.findElement(Locators.learnmorelink).isDisplayed() &&
                driver.findElement(Locators.applynowlink).isDisplayed() &&
                driver.findElement(Locators.contactlink).isDisplayed() &&
                driver.findElement(Locators.bloglink).isDisplayed() &&
                driver.findElement(Locators.aboutuslink).isDisplayed();
    }
}
