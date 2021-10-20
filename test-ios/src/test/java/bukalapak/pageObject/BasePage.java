package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.utils.APICall;
import bukalapak.utils.Constants;
import bukalapak.utils.DataUtil;
import com.bukalapak.salad.module.XCUITest;
import com.bukalapak.salad.util.*;
import com.github.javafaker.Faker;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static bukalapak.TestInstrument.dotenv;

/**
 * Created by sekarayukarindra on 25/09/18.
 */
public class BasePage extends XCUITest {

    protected final static APICall API_CALL = new APICall();

    public BasePage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    //region VALIDATE ASSERT

    public void assertEquals(String expected, String actual) {
        validateValue().equals(expected, actual);
    }

    public void assertEquals(String expected, String actual, String message) {
        validateValue().equals(expected, actual, message);
    }

    public void assertEquals(int expected, int actual) {
        validateValue().equals(expected, actual);
    }

    public void assertEquals(int expected, int actual, String message) {
        validateValue().equals(expected, actual, message);
    }

    public void assertEquals(double expected, double actual, double delta) {
        validateValue().equals(expected, actual, delta);
    }

    public void assertEquals(boolean expected, boolean actual) {
        validateValue().equals(expected, actual);
    }

    public void assertEquals(Object expected, Object actual, String message) {
        Assert.assertEquals(message, expected, actual);
    }

    public void assertNotSame(Object expected, Object actual) {
        Assert.assertNotSame(expected, actual);
    }

    public void assertNotSame(Object expected, Object actual, String message) {
        Assert.assertNotSame(message, expected, actual);
    }

    public void assertTextContains(String expected, String actual) {
        validateValue().contains(expected, actual);
    }

    public void assertTextContains(String expected, String actual, String message) {
        validateValue().contains(expected, actual, message);
    }

    public void assertTrue(boolean condition) {
        validateValue().equalsTrue(condition);
    }

    public void assertTrue(boolean condition, String message) {
        validateValue().equalsTrue(condition, message);
    }

    public void assertFalse(boolean condition) {
        validateValue().equalsFalse(condition);
    }

    public void assertFalse(boolean condition, String message) {
        validateValue().equalsFalse(condition, message);
    }

    //endregion

    //region INPUT FUNCTION

    /**
     * this method to type a field then enter the value
     *
     * @param elementLocator specified element field locator
     * @param text           is value to enter
     */
    public void typeAndEnterValue(String elementLocator, String text) {
        typeText().element(elementLocator, text);
    }

    /**
     * this method to type a field then enter the value by index
     *
     * @param elementLocator specified element field locator
     * @param index          specified index of element field locator
     * @param text           is value to enter
     */
    public void typeAndEnterValue(String elementLocator, int index, String text) {
        typeText().elements(elementLocator, Constants.THREE_SECS_TIMEOUT, index, text);
    }

    /**
     * this method to type a field then enter the value with handle timeout
     *
     * @param elementLocator specified element field locator
     * @param text           is value to enter
     */
    public void typeAndEnterValueWithTimeOut(String elementLocator, String text) {
        typeText().element(elementLocator, Constants.THREE_SECS_TIMEOUT, text);
    }

    /**
     * this method to type a field then enter the value with handle timeout by index
     *
     * @param elementLocator specified element field locator
     * @param index          specified index of element field locator
     * @param text           is value to enter
     */
    public void typeAndEnterValueWithTimeOut(String elementLocator, int index, String text) {
        typeText().elements(elementLocator, Constants.THREE_SECS_TIMEOUT, index, text);
    }

    //endregion

    //region SWIPE FUNCTION

    public void swipeToLocator(String elementLocator) {
        swipe().toLocator(
                elementLocator,
                Direction.UP,
                Constants.DEFAULT_SWIPE_DURATION
        );
    }

    public void swipeToLocator(String elementLocator, Direction direction) {
        swipe().toLocator(
                elementLocator,
                direction,
                Constants.DEFAULT_SWIPE_DURATION
        );
    }

    public void swipeToLocator(String elementLocator, Direction direction, int swipeDuration) {
        swipe().toLocator(elementLocator,
                direction,
                swipeDuration
        );
    }

    protected void swipeWithCustomCoordinate(double xStart, double xEnd, double yStart, double yEnd) {
        swipe().to(xStart, xEnd, yStart, yEnd, Constants.DEFAULT_SWIPE_DURATION);
    }

    /**
     * swipe on the screen by direction
     * @param direction : up, down, left or right
     */
    public void swipeToDirection(Direction direction) {
        swipe().toDirection(direction, Constants.DEFAULT_SWIPE_DURATION);
    }

    public void swipeToDirection(Direction direction, int swipeDuration) {
        swipe().toDirection(direction, swipeDuration);
    }

    //region Swipe UP

    public void swipeUp() {
        swipe().up(Constants.DEFAULT_SWIPE_DURATION);
    }

    public void swipeUp(int swipeCount) {
        for (int i = 0; i < swipeCount; i++) {
            swipeUp();
        }
    }

    /**
     * this method is used to scrolling up with specific start and end position
     * Y1 should be less than Y2
     *
     * @param y1Percentage : start position
     * @param y2Percentage ; end positions
     */
    public void swipeUp(double y1Percentage, double y2Percentage) {
        swipe().up(y1Percentage, y2Percentage, Constants.DEFAULT_SWIPE_DURATION);
    }

    protected void swipeUpAtSpecifiedLocator(String elementLocator) {
        swipe().upAtSpecifiedLocator(elementLocator, Constants.DEFAULT_SWIPE_DURATION);
    }

    public boolean swipeUpToElement(String elementLocator) {
        return swipe().toLocator(
                elementLocator,
                Constants.THREE_SECS_TIMEOUT,
                Direction.UP,
                Constants.DEFAULT_SWIPE_DURATION,
                Constants.DEFAULT_SWIPE_COUNT
        );
    }

    public boolean swipeUpToElement(String elementLocator, int swipeLoop) {
        return swipe().toLocator(
                elementLocator,
                Constants.THREE_SECS_TIMEOUT,
                Direction.UP,
                Constants.DEFAULT_SWIPE_DURATION,
                swipeLoop
        );
    }

    public boolean swipeUpToElement(String elementLocator, int swipeDuration, int swipeLoop) {
        return swipe().toLocator(
                elementLocator,
                Constants.THREE_SECS_TIMEOUT,
                Direction.UP,
                swipeDuration,
                swipeLoop
        );
    }

    public void nativeSwipeUp() {
        mobileGestures().swipe("up");
    }

    public void nativeSwipeUp(String locator) {
        mobileGestures().swipe(locator, "up");
    }

    /**
     * this method is used to native swipe up to expected element
     * @param elementLocator specified element section to be swipe
     */
    public void nativeSwipeUpToElement(String elementLocator) {
        for (int i = 0; i < Constants.DEFAULT_SWIPE_COUNT; i++) {
            if (!isElementVisible(elementLocator)) {
                nativeSwipeUp();
            }
        }
    }

    //endregion

    //region Swipe DOWN

    public void swipeDown() {
        swipe().down(Constants.DEFAULT_SWIPE_DURATION);
    }

    /**
     * this method is used to swipe down with specific start and end position
     * Y1 should be less than Y2
     *
     * @param y1Percentage : start position
     * @param y2Percentage ; end positions
     */
    public void swipeDown(double y1Percentage, double y2Percentage) {
        swipe().down(y1Percentage, y2Percentage, Constants.DEFAULT_SWIPE_DURATION);
    }

    /**
     * this method is used to swipe down in the locator section
     *
     * @param elementLocator specified element section to be swipe
     */
    public void swipeDownAtSpecifiedLocator(String elementLocator) {
        swipe().downAtSpecifiedLocator(elementLocator, Constants.DEFAULT_SWIPE_DURATION);
    }

    public boolean swipeDownToElement(String elementLocator) {
        return swipe().toLocator(
                elementLocator,
                Constants.THREE_SECS_TIMEOUT,
                Direction.DOWN,
                Constants.DEFAULT_SWIPE_DURATION,
                Constants.DEFAULT_SWIPE_COUNT
        );
    }

    public boolean swipeDownToElement(String elementLocator, int swipeLoop) {
        return swipe().toLocator(
                elementLocator,
                Constants.THREE_SECS_TIMEOUT,
                Direction.DOWN,
                Constants.DEFAULT_SWIPE_DURATION,
                swipeLoop
        );
    }

    public boolean swipeDownToElement(String elementLocator, int swipeDuration, int swipeLoop) {
        return swipe().toLocator(
                elementLocator,
                Constants.THREE_SECS_TIMEOUT,
                Direction.DOWN,
                swipeDuration,
                swipeLoop
        );
    }

    public void nativeSwipeDown() {
        mobileGestures().swipe("down");
    }

    public void nativeSwipeDown(String locator) {
        mobileGestures().swipe(locator, "down");
    }

    public void swipeDownToClickableElement(String elementLocator) {
        for (int i = 0; i < Constants.DEFAULT_SWIPE_COUNT; i++) {
            if (!isElementClickable(elementLocator)) {
                swipeToDirection(Direction.DOWN);
            }
        }
    }

    /**
     * this method is used to native swipe down to expected element
     * @param elementLocator specified element section to be swipe
     */
    public void nativeSwipeDownToElement(String elementLocator) {
        for (int i = 0; i < Constants.DEFAULT_SWIPE_COUNT; i++) {
            if (!isElementVisible(elementLocator)) {
                nativeSwipeDown();
            }
        }
    }

    //endregion

    //region Swipe RIGHT

    public void swipeRight() {
        swipe().right(Constants.DEFAULT_SWIPE_DURATION);
    }

    /**
     * this method is used to swipe right with specific start and end position
     * Y1 should be less than Y2
     *
     * @param y1Percentage : start position
     * @param y2Percentage ; end positions
     */
    public void swipeRight(double y1Percentage, double y2Percentage) {
        swipe().right(y1Percentage, y2Percentage, Constants.DEFAULT_SWIPE_DURATION);
    }

    /**
     * this method is used to swipe to the right with custom percentage
     *
     * @param startPercentage percentage to determine the start point to be tapped (must be higher)
     * @param endPercentage   percentage to determine the end point to be tapped (must be lower)
     * @param percentageY     percentage to determine the y point
     */
    public void swipeRight(double startPercentage, double endPercentage, double percentageY) {
        swipe().right(startPercentage, endPercentage, percentageY, Constants.DEFAULT_SWIPE_DURATION);
    }

    /**
     * this method is used to swipe left in the locator section
     *
     * @param elementLocator specified element section to be swipe
     */
    public void swipeRightAtSpecifiedLocator(String elementLocator) {
        swipe().rightAtSpecifiedLocator(elementLocator, Constants.DEFAULT_SWIPE_DURATION);
    }

    /**
     * this method is used to swipe left in the locator section with validate specific element locator visible
     *
     * @param elementContainer  specified element section to be swipe
     * @param elementLocator    specified element locator to be validate
     * @param swipeCount        custom parameter to set the swipe count
     */
    public void swipeRightAtSpecifiedLocator(String elementContainer, String elementLocator, int swipeCount) {
        swipe().rightAtSpecifiedLocator(elementContainer, elementLocator, swipeCount, Constants.DEFAULT_SWIPE_DURATION);
    }

    public void nativeSwipeRight() {
        mobileGestures().swipe("right");
    }

    public void nativeSwipeRight(String locator) {
        mobileGestures().swipe(locator, "right");
    }

    /**
     * this method is used to native swipe down to expected element
     * @param elementLocator specified element section to be swipe
     */
    public void nativeSwipeRightToElement(String elementLocator) {
        for (int i = 0; i < Constants.DEFAULT_SWIPE_COUNT; i++) {
            if (!isElementVisible(elementLocator)) {
                nativeSwipeRight();
            }
        }
    }

    //endregion

    //region Swipe LEFT

    public void swipeLeft() {
        swipe().left(Constants.DEFAULT_SWIPE_DURATION);
    }

    /**
     * this method is used to swipe left with specific start and end position
     * Y1 should be less than Y2
     *
     * @param y1Percentage : start position
     * @param y2Percentage ; end positions
     */
    protected void swipeLeft(double y1Percentage, double y2Percentage) {
        swipe().left(y1Percentage, y2Percentage, Constants.DEFAULT_SWIPE_DURATION);
    }

    /**
     * this method is used to swipe to the left with custom percentage
     *
     * @param startPercentage percentage to determine the start point to be tapped (must be lower)
     * @param endPercentage   percentage to determine the end point to be tapped (must be higher)
     * @param percentageY     percentage to determine the y point
     */
    protected void swipeLeft(double startPercentage, double endPercentage, double percentageY) {
        swipe().left(startPercentage, endPercentage, percentageY, Constants.DEFAULT_SWIPE_DURATION);
    }

    /**
     * this method is used to swipe left in the locator section
     *
     * @param elementContainer specified element section to be swipe
     */
    public void swipeLeftAtSpecifiedLocator(String elementContainer) {
        swipe().leftAtSpecifiedLocator(elementContainer, Constants.DEFAULT_SWIPE_DURATION);
    }

    /**
     * this method is used to swipe left in the locator section with validate specific element locator visible
     *
     * @param elementContainer  specified element section to be swipe
     * @param elementLocator    specified element locator to be validate
     * @param swipeCount        custom parameter to set the swipe count
     */
    public void swipeLeftAtSpecifiedLocator(String elementContainer, String elementLocator, int swipeCount) {
        swipe().leftAtSpecifiedLocator(elementContainer, elementLocator, swipeCount, Constants.DEFAULT_SWIPE_DURATION);
    }

    public void nativeSwipeLeft() {
        mobileGestures().swipe("left");
    }

    public void nativeSwipeLeft(String locator) {
        mobileGestures().swipe(locator, "left");
    }

    /**
     * this method is used to native swipe down to expected element
     * @param elementLocator specified element section to be swipe
     */
    protected void nativeSwipeLeftToElement(String elementLocator) {
        for (int i = 0; i < Constants.DEFAULT_SWIPE_COUNT; i++) {
            if (!isElementVisible(elementLocator)) {
                nativeSwipeLeft();
            }
        }
    }

    //endregion

    //region web view

    protected void webViewSwipeToElement(String locator) {
        webViews().swipeIntoElement(locator);
    }

    protected void webViewTapOnElement(String locator) {
        webViews().tapElement(locator, Constants.FIVE_SECS_TIMEOUT);
    }

    protected void webViewTapOnElement(String locator, int index) {
        webViews().tapElement(locator, index);
    }

    protected void webViewTypeElementValue(String locator, String text) {
        webViews().typeElement(locator, text);
    }

    protected void webViewTypeElementValue(String locator, int timeout, String text) {
        webViews().typeElement(locator, timeout, text);
    }

    protected void webViewTypeElementsValue(String locator, int index, String text) {
        webViews().typeElements(locator, index, text);
    }

    protected void webViewTypeElementsValue(String locator, int timeout, int index, String text) {
        webViews().typeElements(locator, timeout, index, text);
    }

    //endregion

    //endregion

    //region WAIT FOR ELEMENT

    public void waitForElementClickable(String locator, int timeout) {
        By by = getLocator(locator);
        WebDriverWait wait = new WebDriverWait(iosDriver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void waitForVisibilityOf(String locator) {
        By by = getLocator(locator);
        WebDriverWait wait = new WebDriverWait(iosDriver, Constants.FIVE_SECS_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForVisibilityOf(String locator, int timeout) {
        By by = getLocator(locator);
        WebDriverWait wait = new WebDriverWait(iosDriver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    //endregion

    //region GET ELEMENT
    public IOSElement getElement(String elementLocator) {
        return getElement().withLocator(elementLocator);
    }

    public IOSElement getElement(String elementLocator, int timeout) {
        return getElement().withLocator(elementLocator, timeout);
    }

    /**
     * Get element with swipe the screen
     * @param elementLocator specified element field locator
     * @return specified element
     */
    public IOSElement getElementPresent(String elementLocator) {
        return getElement().withLocator(
                elementLocator,
                Direction.UP,
                Constants.DEFAULT_SWIPE_DURATION
        );
    }

    /**
     * Get element with swipe the screen
     * @param elementLocator specified element field locator
     * @param direction up, down, left or right
     * @return specified element
     */
    public IOSElement getElementPresent(String elementLocator, Direction direction) {
        return getElement().withLocator(
                elementLocator,
                direction,
                Constants.DEFAULT_SWIPE_DURATION
        );
    }

    /**
     * Get element with swipe the screen
     * @param elementLocator specified element field locator
     * @param direction up, down, left or right
     * @param swipeDuration custom duration value for swipe the screen
     * @return specified element
     */
    public IOSElement getElementPresent(String elementLocator, Direction direction, int swipeDuration) {
        return getElement().withLocator(
                elementLocator,
                direction,
                swipeDuration
        );
    }

    public String getElementValue(String elementLocator) {
        try {
            LogUtil.info("Retrieving element value: " + elementLocator);
            return getElement(elementLocator).getAttribute("value");
        } catch (Exception e) {
            throw new RuntimeException("Unable to retrieve value from: " + elementLocator + "\n Exception Details:" + e.fillInStackTrace());
        }
    }

    public String getElementValue(String locator, int index) {
        try {
            By by = getLocator(locator);
            String value = iosDriver.findElements(by).get(index).getAttribute("value");
            LogUtil.info("Retrieving element value: " + by.toString());
            return value;
        } catch (Exception e) {
            throw new RuntimeException("Unable to retrieve value from: " + locator + "\n Exception Details:" + e.fillInStackTrace());
        }
    }

    public String getElementLabel(String elementLocator) {
        try {
            LogUtil.info("Retrieving element value: " + elementLocator);
            return getElement(elementLocator).getAttribute("label");
        } catch (Exception e) {
            throw new RuntimeException("Unable to retrieve value from: " + elementLocator + "\n Exception Details:" + e.fillInStackTrace());
        }
    }

    /**
     * This method is used to get element of table
     *
     * @param parentClassName
     * @param childClassName
     * @return list of element
     */
    public List<MobileElement> getElementTable(String parentClassName, String childClassName) {
        return iosDriver.findElement(By.className(parentClassName)).findElements(By.className(childClassName));
    }

    //endregion

    //region GET MULTIPLE ELEMENT

    public List<IOSElement> getElements(String elementLocator) {
        return getMultipleElement().withLocator(elementLocator);
    }

    public List<WebElement> getElements(String elementLocator, int timeout) {
        return getMultipleElement().withLocator(elementLocator, timeout);
    }

    /**
     * Get elements with swipe the screen
     * @param elementLocator specified element field locator
     * @return specified element
     */
    public List<IOSElement> getElementsPresent(String elementLocator) {
        return getMultipleElement().withLocator(
                elementLocator,
                Direction.UP,
                Constants.DEFAULT_SWIPE_DURATION
        );
    }

    /**
     * Get elements with swipe the screen
     * @param elementLocator specified element field locator
     * @param direction up, down, left or right
     * @return specified element
     */
    public IOSElement getElementsPresent(String elementLocator, Direction direction) {
        return (IOSElement) getMultipleElement().withLocator(
                elementLocator,
                direction,
                Constants.DEFAULT_SWIPE_DURATION
        );
    }

    /**
     * Get elements with swipe the screen
     * @param elementLocator specified element field locator
     * @param direction up, down, left or right
     * @param swipeDuration custom duration value for swipe the screen
     * @return specified element
     */
    public IOSElement getElementsPresent(String elementLocator, Direction direction, int swipeDuration) {
        return getElement().withLocator(
                elementLocator,
                direction,
                swipeDuration
        );
    }

    /**
     * this method is used to get each element of table based on classname
     *
     * @param element
     * @param className
     * @return list of element
     */
    public List<MobileElement> getElementsByClassname(MobileElement element, String className) {
        return element.findElements(By.className(className));
    }

    //endregion

    //region IS ELEMENT VISIBLE

    public boolean isElementVisible(String locator) {
        return isElementExist(locator, Constants.THREE_SECS_TIMEOUT);
    }

    public boolean isElementVisible(String locator, int timeout) {
        return isElementExist(locator, timeout);
    }

    public boolean isElementClickable(String locator) {
        return isElementToBeClickable(locator, Constants.THREE_SECS_TIMEOUT);
    }

    public boolean isElementPresentWithScroll(String element) {
        if (!isElementVisible(element)) {
            return swipeDownToElement(element) || swipeUpToElement(element);
        }
        return true;
    }

    //endregion

    //region TAP ELEMENT

    public void tapElement(String element) {
        tapElement().element(element);
    }

    public void tapElement(String element, int timeout) {
        tapElement().element(element, timeout);
    }

    public void tapElement(int x, int y) {
        tapElement().location(x, y, Constants.DEFAULT_SWIPE_DURATION);
    }

    public void tapElement(int x, int y, int duration) {
        tapElement().location(x, y, duration);
    }

    public void tapElement(String element, Direction direction) {
        tapElement().element(element, direction, Constants.DEFAULT_SWIPE_DURATION);
    }

    public void tapElements(String element, int index) {
        tapElement().elements(element, Constants.THREE_SECS_TIMEOUT, index);
    }

    public void tapElements(String element, int index, int timeout) {
        tapElement().elements(element, timeout, index);
    }

    public void tapElements(String element, int index, Direction direction) {
        tapElement().elements(element, direction, Constants.DEFAULT_SWIPE_DURATION, index);
    }

    public void tapCenterOfElement(String element) {
        tapElement().centerOfElement(element, Constants.THREE_SECS_TIMEOUT);
    }

    public void tapCenterOfElement(String element, int timeout) {
        tapElement().centerOfElement(element, timeout);
    }

    public void tapMultipleOnElement(String element, int tapCount) {
        IOSElement iosElement = iosDriver.findElement(getLocator(element));
        TouchAction ta = new TouchAction(iosDriver);
        ElementOption elementOption = new ElementOption();
        elementOption.withElement(iosElement);
        TapOptions tapOptions = new TapOptions();
        tapOptions.withElement(elementOption).withTapsCount(tapCount);
        ta.tap(tapOptions).perform();
        LogUtil.info("Tap multiple on '" + element + "' done");
    }

    public void multipleTap(String elementLocator, int count) {
        try {
            IOSElement iosElement = iosDriver.findElement(this.getLocator(elementLocator));
            for(int i = 0; i < count; ++i) {
                iosElement.click();
                this.delay(150);
            }

        } catch (InvalidElementStateException var5) {
            throw new InvalidElementStateException("Problem at element : " + elementLocator, var5);
        } catch (NoSuchElementException var6) {
            throw new NoSuchElementException("Couldn't find this element : " + elementLocator, var6);
        }
    }

    public void nativeTap(String locator) {
        mobileGestures().tap(locator);
    }

    public void nativeTap(int x, int y) {
        mobileGestures().tap(x, y);
    }

    public void nativeDoubleTap(int x, int y) {
        mobileGestures().doubleTap(x, y);
    }

    public void nativeDoubleTap(String locator) {
        mobileGestures().doubleTap(locator);
    }

    //endregion

    //region TAP AND HOLD ELEMENT

    public void tapAndHoldElement(String element) {
        longTapElement().element(element, Constants.DEFAULT_HOLD_DURATION);
    }

    public void tapAndHoldElement(String element, int index) {
        longTapElement().element(element, index, Constants.DEFAULT_HOLD_DURATION);
    }

    public void tapAndHoldElements(String element, Direction direction) {
        longTapElement().element(
                element, direction,
                Constants.DEFAULT_SWIPE_DURATION,
                Constants.DEFAULT_HOLD_DURATION
        );
    }

    public void tapAndHoldElements(String element, Direction direction, int index) {
        longTapElement().element(
                element, direction,
                Constants.DEFAULT_SWIPE_DURATION,
                index,
                Constants.DEFAULT_HOLD_DURATION
        );
    }

    //endregion

    //region VALIDATE ELEMENT

    public void verifyElementExist(String elementLocator) {
        validateExist(elementLocator, Constants.TIMEOUT);
    }

    public void verifyElementExist(String elementLocator, int timeout, String errorMessage) {
        validateExist(elementLocator, timeout, errorMessage);
    }

    public void verifyElementsExist(String elementLocator, int index) {
        validateValue().equalsTrue(getElements(elementLocator, Constants.TIMEOUT).get(index).isDisplayed());
    }

    public void verifyElementsExist(String elementLocator, int index, int timeout, String message) {
        validateValue().equalsTrue(getElements(elementLocator, timeout).get(index).isDisplayed(), message);
    }

    public void verifyElementNotExist(String elementLocator) {
        validateNotExist(elementLocator, Constants.TIMEOUT);
    }

    public void verifyElementDisplayed(String elementLocator) {
        validateDisplayed(elementLocator);
    }

    public void verifyElementDisplayed(String elementLocator, String errorMessage) {
        validateDisplayed(elementLocator, errorMessage);
    }

    public void verifyElementListExist(@NotNull String[] element) {
        int i = 0;

        while (i < element.length) {
            verifyElementExist(element[i]);
            i++;
        }
    }
    //endregion

    //region GET TEXT

    public String getTextFromElement(String element) {
        try {
            waitForVisibilityOf(element);
            return getText(element);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get text on:" + element + "\n Exception Details:" + e.fillInStackTrace());
        }
    }

    public String getTextFromElement(String element, int index) {
        try {
            waitForVisibilityOf(element);
            return getText(element, index);
        } catch (Exception e) {
            throw new RuntimeException("Unable to get text on:" + element + "\n Exception Details:" + e.fillInStackTrace());
        }
    }

    // To get Integer value from String format of Rupiah
    protected int getIntFromRp(String text) {
        String price = text.substring(text.indexOf("p") + 1)
                .replace(".", "")
                .trim();
        String price1 = text.substring(text.indexOf("p") + 1)
                .trim();
        if (text.contains("-")) {
            if (text.contains(".")) {
                return Integer.parseInt(price) * -1;
            } else {
                return Integer.parseInt(price1) * -1;
            }
        } else if (text.contains("Rp")) {
            if (text.contains(".")) {
                return Integer.parseInt(price);
            } else {
                return Integer.parseInt(price1);
            }
        } else {
            return 0;
        }
    }

    // To get Double value from String format of Rupiah
    protected double getDoubleFromRp(String text) {
        String numberText;
        double numberDouble;
        if (text.contains(".")) {
            if (text.contains("Rp")) {
                numberText = text.substring(text.indexOf("p") + 1);
            } else {
                numberText = text;
            }
            numberDouble = Double.parseDouble(numberText
                    .replace(".", "")
                    .replace(",", ".")
                    .trim());
        } else {
            if (text.contains("Rp")) {
                numberText = text.substring(text.indexOf("p") + 1);
            } else {
                numberText = text;
            }
            numberDouble = Double.parseDouble(numberText.replace(",", ".").trim());
        }
        return numberDouble;
    }

    protected String getRpFromPrice(int money) {
        String idrFormat = (String.format("%,d", money)).replace(',', '.');
        return "Rp" + idrFormat;
    }

    public String getRpFromNumber(int text) {
        return getRpFromPrice(text);
    }

    /**
     * To get integer price from String format of Rp
     * @param price, example Rp100.000
     * @return integer price without Rp format, example 100000
     */
    public Integer convertPriceToInt(String price) {
        return Integer.parseInt(price.replaceAll("[^0-9]+", ""));
    }

    /**
     * To rounding up or down the decimal number
     *
     * @param value     is decimal values
     * @param precision is decimal places
     * @param up        boolean condition for round up or down
     * @return decimal value with round format
     */
    protected double round(double value, int precision, boolean up) {
        int scale = (int) Math.pow(10, precision);
        if (up) {
            // true -> round up
            return Math.ceil(value * scale) / scale;
        } else {
            // false -> round down
            return Math.floor(value * scale) / scale;
        }
    }

    /**
     * To get round decimal number to the last n significant digits after the decimal
     *
     * @param value     the number to be formatted
     * @param precision the rounding precision
     * @return decimal value with rounding precision
     */
    public double roundWithPrecision(double value, int precision) {
        BigDecimal instance = new BigDecimal(Double.toString(value));
        instance = instance.setScale(precision, RoundingMode.HALF_UP);
        return instance.doubleValue();
    }

    /**
     * To get weight of Gold and Unit product in Decimal count format
     *
     * @param text included of alphanumeric
     * @return String as a double format
     */
    protected static String getUnitToDecimalFormat(String text) {
        String result = null;
        String[] words = {"gram", "unit", "Unit"};
        Pattern pAlpha = Pattern.compile("[a-zA-Z]");
        Pattern pNum = Pattern.compile("[0-9.,]+");
        if (pAlpha.matcher(text).find()) {
            for (String s : words) {
                if (text.contains(s)) {
                    String[] bits = text.split(" ");
                    String lastTwo = bits[bits.length - 2];
                    if (pNum.matcher(text).find()) {
                        result = lastTwo
                                .replace(".", "")
                                .replace(",", ".");
                    } else {
                        result = lastTwo
                                .replace(",", ".");
                    }
                    break;
                }
            }
        } else {
            if (pNum.matcher(text).find()) {
                result = text
                        .replace(".", "")
                        .replace(",", ".");
            } else {
                result = text
                        .replace(",", ".");
            }
        }
        return result != null ? result : "Text does not contains the list of words";
    }

    //endregion

    public void tapBackButton() {
        tapElement("base_back_button");
    }

    public void tapBackButton(int repetition) {
        for (int i = 0; i <= repetition; i++) {
            tapElement("base_back_button");
        }
    }

    public void allowPopup() {
        autoAcceptAlert("home_allow_button");
    }

    protected void autoAcceptAlert(String locator) {
        boolean autoAllow = iosDriver.getCapabilities().is("autoAcceptAlerts");
        if (!autoAllow && isElementVisible(locator, 3)) {
            tapElement(locator);
        }
    }

    public boolean getAutoAcceptAlert() {
        return iosDriver.getCapabilities().is("autoAcceptAlerts");
    }

    public void okOnboarding() {
        if (isElementVisible("onboarding_lewati_button", 10)) {
            tapElement("onboarding_lewati_button");
        }
    }

    public void getTokenForAPIExclusive() {
        API_CALL.getTokenForAPIExclusive("53");
    }

    //region DeepLink Access

    /**
     * To get a state of running on the real device
     *
     * @return string if defined
     */
    private String getRealDeviceStatus() {
        String devStatus = "";
        if (iosDriver.getCapabilities().getCapability("xcodeOrgId") != null) {
            devStatus = iosDriver.getCapabilities().getCapability("xcodeOrgId").toString();
        }
        return devStatus;
    }

    /**
     * To get base url from ios device and simulator
     *
     * @return base url
     */
    private String getBaseUrl() {
        return !getRealDeviceStatus().equals("") ? "bukalapak:/" : "https://www.bukalapak.com";
    }

    /**
     * To check string has character only
     *
     * @param name is string value
     * @return state of string type
     */
    private boolean isAlpha(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    private static String urlPath(String deepLink, String indexOf) {
        return deepLink.substring(deepLink.indexOf(indexOf) + indexOf.length());
    }

    /**
     * Get specific URL deepLink based on running in Real device or Simulator
     *
     * @param deepLink url
     * @return url with any conditions:
     * real device: convert URL when it contains <http/bukalapak.com> into bukalapak://
     * real device: convert URL when it only has a PATH of URL into bukalapak://<path>
     * simulator: still using URL http://www.bukalapak.com/<path>
     * real device and simulator can access https://bl.app.link/<url path>
     * raise error when inputting url other than <http://www.bukalapak.com>, https://bl.app.link/<url path>, Only path: /<url path>
     */
    private String getUrlDeepLink(String deepLink) {
        String errorMessage = "Please input valid deepLink using url: " +
                "https://www.bukalapak.com, " +
                "https://bl.app.link/<url path>, " +
                "Only path: /<url path>";
        String urlPath;
        String url = null;
        if (deepLink.contains("http")) {
            if (!deepLink.contains("www.bukalapak.com")) {
                try {
                    if (deepLink.contains("bl.app.link")) {
                        urlPath = urlPath(deepLink, "link/");
                        if (!urlPath.equals("") && !isAlpha(urlPath)) url = "https://bl.app.link/" + urlPath;
                    } else {
                        url = deepLink;
                    }
                } catch (Exception e) {
                    throw new ExceptionInInitializerError(errorMessage);
                }
            } else {
                urlPath = urlPath(deepLink, "com");
                url = urlPath.equals("") ? getBaseUrl() + "/" : getBaseUrl() + urlPath;
            }
        } else {
            if (deepLink.contains("/")) {
                urlPath = urlPath(deepLink, "/");
                url = (!isAlpha(urlPath) && urlPath.matches("[^-/?]+")) ? "https://bl.app.link/" + urlPath : getBaseUrl() + "/" + urlPath;
            } else {
                throw new ExceptionInInitializerError(errorMessage);
            }
        }
        return url;
    }

    /**
     * This function is used to open page directly using deep link
     */
    public void openDeepLink(String deepLink) {
        String urlDeepLink = getUrlDeepLink(deepLink);
        if (!getRealDeviceStatus().equals("")) {
            // Clean safari
            iosDriver.executeScript("mobile: terminateApp", ImmutableMap.of("bundleId", "com.apple.mobilesafari"));
            // Set argument to add url deep link
            List<String> args = new ArrayList<>();
            args.add("--U");
            args.add(urlDeepLink);
            // Set argument to open safari browser
            // It necessary to bypass driver called Siri
            Map<String, Object> params = new HashMap<>();
            params.put("bundleId", "com.apple.mobilesafari");
            params.put("arguments", args);
            // Call and open apps
            iosDriver.executeScript("mobile: launchApp", params);
            iosDriver.findElementByAccessibilityId("Open").click();
        } else {
            iosDriver.get(urlDeepLink);
        }
    }

    /**
     * This function is used to open page directly using deep link retrieved from env variables
     */
    public void openDeeplinkFromEnv(String envVar) {
        openDeepLink(dotenv.get(envVar));
    }

    //endregion

    public void backToHomePage() {
        while (isElementVisible("base_back_button")) {
            try {
                tapElement("base_back_button");
            } catch (Exception e) {
                break;
            }
        }
        if ((!isElementVisible("home_lihatsemua_tab")) && (isElementVisible("home_blhome_tab"))) {
            tapElement("home_blhome_tab");
        }
        //HelperData.setLastActionPage(new HomePage());
    }

    public void logOutFromApp() {
        try {
            openDeepLink("https://www.bukalapak.com/logout");
        } catch (Exception e) {
            verifyElementExist("home_akun_tab");
        }
        HelperData.setLastActionPage(new HomePage(iosDriver));
        DataUtil.setDataAfterLogout();
    }

    /**
     * This function is used to go to home page directly using deeplink
     * Since the lastActionPage object use com.bukalapak.ios.pageobjects.HomePage@23e44287 format
     * We have to extract the actual page name first before comparing
     */
    public void backToHomePageViaDeeplink() {
        if (!HelperData.getLastActionPage().getClass().getSimpleName().equals("HomePage")) {
            openDeepLink("https://www.bukalapak.com/");
        }
    }

    protected void waitFor(int seconds) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(seconds));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDriverLocation(double latitude, double longitude, double altitude) {
        Location location = new Location(latitude, longitude, altitude);
        iosDriver.setLocation(location);
    }

    public void scenarioDone() {
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifyCopiedText(String textToBeVerified) {
        assertTrue(textToBeVerified.equals(iosDriver.getClipboardText()), "Copied text is not equals to \"" + textToBeVerified + "\" on clipboard.");
    }

    public void doPullRefresh(int refreshCount) {
        for (int i = 1; i <= refreshCount; i++) {
            waitFor(2);
            nativeSwipeDown();
            waitFor(2);
        }
    }

    public void refreshWebview() {
        iosDriver.navigate().refresh();
        waitFor(5);
    }

    public void selectPickerWheel(String value) {
        iosDriver.findElementByClassName("XCUIElementTypePickerWheel")
                .sendKeys(value);
    }

    /**
     * To get integer from an string
     *
     * @param text the selected string.
     * @return the integer value of the string. (negative or positive)
     */
    public int parseIntegerFromText(String text) {
        if (text.contains("-")) {
            return Integer.parseInt(text.replaceAll("[^0-9]", "")) * -1;
        } else {
            return Integer.parseInt(text.replaceAll("[^0-9]", ""));
        }
    }

    /**
     * To get integer from an element's text
     *
     * @param element a locator.
     * @return the integer value of the element. (negative or positive)
     */
    public int getIntegerFromTextElement(String element) {
        String text = getTextFromElement(element);
        return parseIntegerFromText(text);
    }

    /**
     * To get integer from an element's value
     *
     * @param element a locator.
     * @return the integer value of the element. (negative or positive)
     */
    public int getIntegerFromValueElement(String element) {
        String value = getElementValue(element);
        return parseIntegerFromText(value);
    }

    public void goToHomePage() {
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    /**
     * This function is used to drag element to another element
     */
    public void dragElement(String element, String targetElement) {
        if (isElementVisible(element)) {
            MobileElement mobileElement1 = super.iosDriver.findElement(getLocator(element));
            Point point1 = mobileElement1.getCenter();
            int point1x = point1.getX();
            int point1y = point1.getY();
            MobileElement mobileElement2 = super.iosDriver.findElement(getLocator(targetElement));
            Point point2 = mobileElement2.getCenter();
            int point2x = point2.getX();
            int point2y = point2.getY();

            Map<String, Object> params = new HashMap<>();
            params.put("duration", 1.0);
            params.put("fromX", point1x);
            params.put("fromY", point1y);
            params.put("toX", point2x);
            params.put("toY", point2y);
            super.iosDriver.executeScript("mobile: dragFromToForDuration", params);
        }
    }

    /**
     * Get Train Code or Train Time from random String.
     *
     * @param str    String to split that contains time and stationCode ex: 09:15(PSE)
     * @param strIdx Index that refers to part of string to get. Index 0 is station code and index 1 is station code
     * @return String train code or train time
     */
    public String splitStringByParanthesis(String str, int strIdx) {
        return str.split("\\(")[strIdx].replaceAll("\\)", "").replaceAll("\\s+", "").replaceAll("[^A-Za-z0-9:]", "").trim();
    }

    public void resetApp() {
        iosDriver.resetApp();

    }

    public void closeApp() {
        iosDriver.terminateApp("com.bukalapak.ios");
    }

    public void openApp() {
        iosDriver.activateApp("com.bukalapak.ios");
    }

    public void allowLocationPopup() {
        autoAcceptAlert("home_allow_location_button");
    }

    public void typeValue(String elementLocator, String text) {
        getElement(elementLocator).sendKeys(text);
    }

    public void backSpaceTextField(String element) {
        int stringSize = element.length();
        for (int i = 0; i <= stringSize; i++) {
            getElementPresent(element).sendKeys("\b");
        }
    }

    public void hideLeakLotek() {
        if (isElementExist("leaks_button")) {
            tapElement("leaks_button");
            waitForElementClickable("leaks_settings_button", 5);
            tapElement("leaks_settings_button");
            delay(5000); //wait before search for hide button. sometimes it tap by itself
            if (isElementExist("leaks_hide_button", 5)) {
                tapElement("leaks_hide_button");
                if (isElementExist("leaks_app_restarted_button", 5)) {
                    tapElement("leaks_app_restarted_button");
                }
            }
        }
    }

    public void typeValue(String elementLocator, int index, String text) {
        getElements(elementLocator).get(index).sendKeys(text);
    }

    public String fakerFullName(String prefix) {
        return prefix + new Faker().name().fullName();
    }

    public String fakerFirstName(String prefix) {
        return prefix + new Faker().name().firstName();
    }

    public String fakerLastName(String prefix) {
        return prefix + new Faker().name().lastName();
    }

    public String fakerName(String prefix) {
        return prefix + new Faker().name().name();
    }

    public String fakerNameWithMiddle(String prefix) {
        return prefix + new Faker().name().nameWithMiddle();
    }

    public String fakerEmail() {
        return new Faker().internet().emailAddress();
    }

    public String fakerID() {
        return new Faker().instance().idNumber().valid();
    }

    public String fakerPhoneNumber(String prefix, int digits) {
        return prefix + new Faker().number().digits(digits);
    }

    public String fakerAddress() {
        return new Faker().address().fullAddress();
    }

    public String fakerStreetAddress() {
        return new Faker().address().streetAddress();
    }

    public String fakerZipCode() {
        return new Faker().number().digits(5);
    }

    public void validateRpFormat(String elementLocator) {
        validateValue().equalsTrue(getText(elementLocator).matches("^(?:- ?)?Rp\\d+(?:[.,][\\d]+)*$"));
    }

    /**
     * url decoder to UTF-8
     * <p>
     * example:
     * - input  : /products?search%5Bkeywords%5D=kemeja
     * - result : /products?search[keywords]=kemeja
     */
    public String urlDecode(String url) {
        String parsed;

        try {
            parsed = URLDecoder.decode(url, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }

        assertTrue(!parsed.equals(""), "Failed decoding URL: \"" + url + "\" to UTF-8");

        return parsed;
    }

    /**
     * map url query string, best used when url is already in UTF-8
     * <p>
     * example:
     * - input  : /products?search[keywords]=kemeja&search[sort]=price
     * - result : { "search[keywords]": "kemeja", "search[sort]": "price" }
     * get params:
     * - result.get("search[keywords]") -> "kemeja"
     */
    public Map<String, String> urlQueryParams(String url) {
        String tmpUrl = url.substring(url.indexOf("?") + 1, url.length());

        String[] queries = tmpUrl.split("&");
        Map<String, String> queryObj = new HashMap<>();

        for (String query : queries) {
            String[] split = query.split("=");
            queryObj.put(split[0], split[1]);
        }

        return queryObj;
    }

    public String removeStringAfterComma(String str) {
        return str.split("[-!$%^&*()_+|~=`{}\\[\\]:\";'<>?,.\\/]")[0];
    }

    // get date tomorrow
    public String getDateTomorrow() {
        LocalDate dateTomorrow = LocalDate.now().plusDays(1);
        return dateTomorrow.format(DateTimeFormatter.ofPattern("d"));
    }

    // get date next week
    public String getDateNextWeek() {
        LocalDate dateTomorrow = LocalDate.now().plusDays(7);
        return dateTomorrow.format(DateTimeFormatter.ofPattern("d"));
    }

    public void relaunchApp() {
        iosDriver.terminateApp("com.bukalapak.ios");
        iosDriver.activateApp("com.bukalapak.ios");
    }
}
