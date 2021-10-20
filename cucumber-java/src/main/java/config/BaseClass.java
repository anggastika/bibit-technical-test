package config;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Constans;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass extends AbstractTestNGCucumberTests {

    public static RemoteWebDriver driver;
    public static final Dotenv dotenv = Dotenv.load();
    private static Properties configProperties = getConfigProperties();
    private static Properties elementProperties = getElementProperties();

    private static Properties getConfigProperties() {
        Properties configProperties = new Properties();

        try {
            FileInputStream fileInputStream = new FileInputStream(Constans.CONFIG);
            configProperties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configProperties;
    }

    protected static Properties getElementProperties() {
        Properties elementProperties = new Properties();

        try {
            FileInputStream fileInputStream = new FileInputStream(Constans.ELEMENTS);
            elementProperties.load(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return elementProperties;
    }

    @BeforeMethod
    public static void setup() throws MalformedURLException {
        URL url = new URL(getConfigProperties().getProperty("SELENIUM_GRID"));
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new RemoteWebDriver(url, capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constans.TIMEOUT, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().logs();
    }


    public static void goToHomepage() {
        driver.get(getConfigProperties().getProperty("BASE_URL"));
    }

    @AfterMethod
    public static void close() {
        driver.quit();
    }

    public static void explicitWait(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Constans.FIVE_SECS_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
