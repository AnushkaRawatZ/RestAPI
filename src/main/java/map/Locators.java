package map;

import org.openqa.selenium.By;

public class Locators {

    public static final By homelink = By.linkText("Home");
    public static final By courseslink = By.linkText("Courses");
    public static final By accessallcourseslink = By.linkText("Access all our Courses");
    public static final By learnmorelink = By.linkText("Learn More");
    public static final By applynowlink = By.xpath("//div[@class='apply-cont apply-color-2']/a");
    public static final By contactlink = By.xpath("//li[@class='nav-item']/a[text()='Contact']");
    public static final By bloglink = By.xpath("//li[@class='nav-item']/a[text()='Blog']");
    public static final By aboutuslink = By.xpath("//li[@class='nav-item']/a[text()='About us']");

    public static final By radiobutton3 = By.xpath("//input[@value='radio3']");

    public static final By autocompletebox = By.id("autocomplete");
    public static final By indiaoption = By.xpath("//div[contains(@class, 'ui-menu-item-wrapper') and text()='India']");

    public static final By dropdown = By.xpath("//select");

    public static final By checkbox1 = By.id("checkBoxOption1");

    public static final By opentabbtn = By.id("opentab");

    public static final By alertbtn = By.id("alertbtn");
    public static final By cnfrmbtn = By.id("confirmbtn");

    public static final By mousehoverbtn = By.id("mousehover");
    public static final By reload = By.linkText("Reload");

    public static final By prodtable = By.xpath("//div[@class='tableFixHead']/table[@id='product']/tbody");
    public static final By coursetable = By.xpath("//table[@name='courses']");
    public static final By rows = By.tagName("tr");
    public static final By desc = By.tagName("td");

    public static final By totamount = By.className("totalAmount");

    public static final By option2 = By.xpath("//option[@value='option2']");
}
