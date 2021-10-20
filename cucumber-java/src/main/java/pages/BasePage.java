package pages;

import config.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.Constans;

import java.util.ArrayList;
import java.util.List;

public class BasePage extends BaseClass {



    //Assertion function
    public static void assertEquals(Object expected, Object actual) {
        Assert.assertEquals(expected, actual);
    }

    public void assertNotSame(Object expected, Object actual) {
        Assert.assertNotSame(expected, actual);
    }

    //Input function
    public static void inputText(String locator, String text) {

        WebElement element = driver.findElement(By.cssSelector(getElementProperties().getProperty(locator)));
        explicitWait(element);
        element.clear();
        element.sendKeys(text);
    }

    //Click button function
    public static void clickButton(String locator) {
        WebElement element = driver.findElement(By.cssSelector(getElementProperties().getProperty(locator)));
        explicitWait(element);
        element.click();
    }

    public static void clickButtonByIndex(String locator, int index) {
        List<WebElement> listMenu = new ArrayList<>();
        listMenu = driver.findElements(By.cssSelector(getElementProperties().getProperty(locator)));
        listMenu.get(index).click();
    }

    //Select Dropdown
    public static void chooseDropdown(String locator, String textDropdown) {
        Select dropdown = new Select(driver.findElement(By.cssSelector(getElementProperties().getProperty(locator))));
        dropdown.selectByVisibleText(textDropdown);
    }

    //Get text function
    public static String getText(String locator) {
        WebElement element = driver.findElement(By.cssSelector(getElementProperties().getProperty(locator)));
        explicitWait(element);
        return element.getText();
    }

    //Refresh page
    public static void refreshPage() {
        driver.navigate().refresh();
    }
}
