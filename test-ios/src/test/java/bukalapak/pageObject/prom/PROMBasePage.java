package bukalapak.pageObject.prom;

import bukalapak.pageObject.BasePage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class PROMBasePage extends BasePage {

    private DateTimeFormatter fullDateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("id", "ID"));

    public PROMBasePage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    private static int monthsBetweenIgnoreDays(LocalDate end) {
        LocalDate start = LocalDate.now().withDayOfMonth(1);
        LocalDate tmpEnd = end.withDayOfMonth(1);
        return Math.abs(((tmpEnd.getYear() - start.getYear()) * 12) + tmpEnd.getMonthValue() - start.getMonthValue());
    }

    public void searchProduct(String text) {
        MobileElement field = getElement("prom_text_field");
        tapElement(field.getCenter().x, field.getCenter().y);
        field.clear();
        field.sendKeys(text);
        hideKeyboard();
    }

    /**
     * Need to be scrolled manual like this, due to some elements in page can not be scrolled.
     *
     * @param locator   specified element section to be swipe
     * @param swipeLoop maximum looping swipe to find the element
     */
    protected void scrollToElement(String locator, int swipeLoop) {
        int scroll = 0;
        do {
            swipeUp(0.8, 0.3);
            scroll++;
        } while (!isElementVisible(locator) && scroll < swipeLoop);
    }

    private void checkDatePickerPopup() {
        waitForVisibilityOf("prom_datepicker_prev_month_button", 20);
        verifyElementDisplayed("prom_datepicker_prev_month_button");
        verifyElementDisplayed("prom_datepicker_terapkan_button");
        verifyElementDisplayed("prom_datepicker_reset_button");
    }

    private void goToPrevMonth(String date) {
        LocalDate selectedStartDate = LocalDate.parse(date, fullDateFormatter);
        for (int i = 0; i < monthsBetweenIgnoreDays(selectedStartDate); i++) {
            tapElement("prom_datepicker_prev_month_button");
        }
    }

    private void selectDate(String startDate, String endDate) {
        String selectedStartDate = String.valueOf(LocalDate.parse(startDate, fullDateFormatter).getDayOfMonth());
        String selectedEndDate = String.valueOf(LocalDate.parse(endDate, fullDateFormatter).getDayOfMonth());
        goToPrevMonth(startDate);
        tapElement(constructLocator("prom_datepicker_date_option", selectedStartDate));
        waitFor(2);
        tapElement(constructLocator("prom_datepicker_date_option", selectedEndDate));
    }

    // Some date will return wrong elements. I've tried some dates, but the safest one is between 10 - 28
    public void selectDatePicker(String startDate, String endDate) {
        tapElement("prom_datepicker_icon");
        checkDatePickerPopup();
        selectDate(startDate, endDate);
    }

    public void hideLeakInfo() {
        if (isElementVisible("leak_icon", 3)) {
            waitForVisibilityOf("leak_icon", 2);
            tapElement("leak_icon");
            waitForVisibilityOf("leak_setting_button", 2);
            tapElement("leak_setting_button");
            waitForVisibilityOf("leak_hide_leak_button", 2);
            tapElement("leak_hide_leak_button");
            waitForVisibilityOf("leak_hide_until_restart_button", 2);
            tapElement("leak_hide_until_restart_button");
        }
    }
}
