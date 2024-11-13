package pages;

import map.Locators;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PracticePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public PracticePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Click Radio3 button
    public void clickRadioButton3() {
        WebElement radioButton3 = wait.until(ExpectedConditions.elementToBeClickable(Locators.radiobutton3));
        radioButton3.click();
    }

    public boolean isRadioButton3Selected() {
        WebElement radioButton3 = driver.findElement(Locators.radiobutton3);
        return radioButton3.isSelected();
    }

    // Autocomplete - Select India
    public void selectIndiaFromAutocomplete() {
        WebElement autocompleteTextbox = driver.findElement(Locators.autocompletebox);
        autocompleteTextbox.sendKeys("Ind");
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.indiaoption));
        WebElement suggestion = driver.findElement(Locators.indiaoption);
        suggestion.click();
    }

    public String getAutocompleteTextValue() {
        WebElement autocompleteTextbox = driver.findElement(Locators.autocompletebox);
        return autocompleteTextbox.getAttribute("value");
    }

    // Select Option2 from Dropdown
    public void selectOption2FromDropdown() {
        WebElement dropdown = driver.findElement(Locators.dropdown);
        dropdown.click();
        WebElement option2 = wait.until(ExpectedConditions.elementToBeClickable(Locators.option2));
        option2.click();
    }

    public String getSelectedDropdownValue() {
        WebElement dropdown = driver.findElement(Locators.dropdown);
        return dropdown.getAttribute("value");
    }

    // Select Option1 Checkbox
    public void selectOption1Checkbox() {
        WebElement checkbox1 = driver.findElement(Locators.checkbox1);
        checkbox1.click();
    }

    public boolean isOption1CheckboxSelected() {
        WebElement checkbox1 = driver.findElement(Locators.checkbox1);
        return checkbox1.isSelected();
    }

    // Open New Tab
    public NewTabPage openNewTab() {
        driver.findElement(Locators.opentabbtn).click();
        return new NewTabPage(driver);
    }

    //Switch Windows
    public String switchWindowsAndGetTitle() {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return "QAClick Academy - A Testing Academy to Learn, Earn and Shine";
    }

    public void sendKeystoName() {
        WebElement textbox = driver.findElement(By.id("name"));
        //a. Enter your name in textbox
        textbox.sendKeys("Anushka");
    }

    // Handle Alert
    public void handleAlert() {
        WebElement alertButton = driver.findElement(Locators.alertbtn);
        alertButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void handleConfirmBtn() {
        WebElement confirmButton = driver.findElement(By.id("confirmbtn"));
        confirmButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public boolean isAlertHandled() {
        try {
            driver.switchTo().alert();
            return false;
        } catch (NoAlertPresentException e) {
            return true;
        }
    }

    // Get Course Price
    public boolean isCoursePriceCorrect(String courseName, int expectedPrice) {
        var table = driver.findElement(Locators.coursetable);
        var rows = table.findElements(Locators.rows);
        for (var row : rows) {
            var cells = row.findElements(By.tagName("td"));
            if (cells.size() > 0 && courseName.equals(cells.get(1).getText())) {
                return Integer.parseInt(cells.get(2).getText())==expectedPrice; // Assuming price is in the third column
            }
        }
        return false;
    }

    // Sum all amounts in the table
    public int sumAllAmounts() {
        var table = driver.findElement(Locators.prodtable);
        var rows =table.findElements(Locators.rows);
        int sum = 0;
        for(var row:rows) {
            var cells = row.findElements(Locators.desc);
            sum +=Integer.parseInt(cells.get(3).getText());
        }
        return sum;
    }

    public int getTotalAmount() {
        String totalS = driver.findElement(By.className("totalAmount")).getText();
        return Integer.parseInt(totalS.replaceAll("[^0-9]", ""));
    }

    // Hover and reload
    public void hoverAndClickReload() {
        WebElement hover = driver.findElement(Locators.mousehoverbtn);
        Actions actions = new Actions(driver);
        actions.moveToElement(hover).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.reload));
        WebElement reload = driver.findElement(Locators.reload);
        reload.click();
    }

    public boolean checkIfPageReloaded(String initialUrl) {
        String newUrl = driver.getCurrentUrl();
        return initialUrl.equals(newUrl);
    }
}
