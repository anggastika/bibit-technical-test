package bukalapak.pageObject;

import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.NoSuchElementException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DatePickerAlchemyPage extends BasePage {

    private String currentDates;
    private String finalDates;
    private String finalMonth;
    // Set date format into locale ID & EN
    private Locale indonesia = new Locale("in", "ID");
    private Locale english = new Locale("en", "EN");
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat newDateFormat;
    private String getContext = iosDriver.getContext();

    public DatePickerAlchemyPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    private void validateDatePickerForm() {
        if (getContext.contains("NATIVE_APP")) {
            validateExist("DATE_PICKER_ALCHEMY_FRAME", 5,
                    "Date picker alchemy should be displayed");
        } else {
            validateExist("DATE_PICKER_WEB_FRAME", 5,
                    "Date picker web should be displayed");
        }
    }

    private boolean getStateOfMonthAndYear(String month, String year) {
        dateFormat = new SimpleDateFormat("MMMMMyyyy", indonesia);
        newDateFormat = new SimpleDateFormat("M yyyy", indonesia);

        try {
            Date dates = dateFormat.parse(month + year);
            finalDates = newDateFormat.format(dates);
            currentDates = LocalDate.now().format(DateTimeFormatter.ofPattern("M yyyy"));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("You are inputting wrong format Month and Year");
        }

        return !finalDates.equals(currentDates);
    }

    private void validateMonthAndYearComparison(String month, String year) {
        String concatText = month.concat(" ").concat(year);
        validateValue().equals(concatText, getCurrentMonthYearTxt(), "Month and year doesn't match!!!");
    }

    private String getCurrentMonthYearTxt() {
        return getContext.contains("NATIVE_APP")
                ? getText("DATE_PICKER_ALCHEMY_MONTH_YEAR_TXT")
                : getText("DATE_PICKER_WEB_MONTH_YEAR_TXT");
    }

    private void tapOnLeftArrow(int count) {
        try {
            if (getContext.contains("NATIVE_APP")) {
                tapMultipleOnElement("DATE_PICKER_ALCHEMY_PREV_BUTTON", count);
            } else {
                multipleTap("DATE_PICKER_WEB_PREV_BUTTON", count);
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Please enter valid month and year!");
        }
    }

    private void tapOnRightArrow(int count) {
        try {
            if (getContext.contains("NATIVE_APP")) {
                tapMultipleOnElement("DATE_PICKER_ALCHEMY_NEXT_BUTTON", count);
            } else {
                multipleTap("DATE_PICKER_WEB_NEXT_BUTTON", count);
            }
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Please enter valid month and year!");
        }
    }

    private void selectMonthAndYear(String month, String year) {
        String[] currentMonthYearTxt = getCurrentMonthYearTxt().split(" ");

        int currentMonth = 0;
        int expectedMonth = 0;
        int currentYear = 0;
        int expectedYear = 0;
        int loopCount;
        int monthCount;
        int yearCount;

        // Set new month format that can be use as integer value
        dateFormat = new SimpleDateFormat("MMMMM", indonesia);
        newDateFormat = new SimpleDateFormat("M", indonesia);

        // Set all variables to integer value
        try {
            currentMonth = Integer.parseInt(newDateFormat.format(dateFormat.parse(currentMonthYearTxt[0])));
            expectedMonth = Integer.parseInt(newDateFormat.format(dateFormat.parse(month)));
            currentYear = Integer.parseInt(currentMonthYearTxt[1]);
            expectedYear = Integer.parseInt(year);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Get loop count to calculate month
        monthCount = (currentMonth != expectedMonth) ? (currentMonth - expectedMonth) : 0;

        // Get loop count to calculate year
        yearCount = (currentYear != expectedYear)
                ? ((currentYear > expectedYear)
                ? currentYear - expectedYear
                : expectedYear - currentYear)
                : 0;

        // Get total loop count
        loopCount = (12 * yearCount) + monthCount;

        if (loopCount > 0) {
            tapOnLeftArrow(Math.abs(loopCount));
        } else {
            tapOnRightArrow(Math.abs(loopCount));
        }

        parseDatePickerMonth(month);
        validateMonthAndYearComparison(finalMonth, year);
    }

    private String parseDatePickerMonthtoEN(String month) {
        dateFormat = new SimpleDateFormat("MMMMM", indonesia);
        newDateFormat = new SimpleDateFormat("MMMM", english);

        try {
            finalMonth = newDateFormat.format(dateFormat.parse(month));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return finalMonth;
    }

    private void parseDatePickerMonth(String month) {
        if (getContext.contains("NATIVE_APP")) {
            parseDatePickerMonthtoEN(month);
        } else {
            finalMonth = month;
        }
    }

    private void selectDate(String date, String... rangeOfDate) {
        if (getContext.contains("NATIVE_APP")) {
            if (rangeOfDate.length > 0) {
                if (rangeOfDate[0] != null) {
                    // select first date
                    tapElement(constructLocator("DATE_PICKER_ALCHEMY_CALENDAR_TXT", date));

                    // select second date
                    tapElement(constructLocator("DATE_PICKER_ALCHEMY_CALENDAR_TXT", rangeOfDate[0]));
                }
            } else {
                tapElement(constructLocator("DATE_PICKER_ALCHEMY_CALENDAR_TXT", date));
            }
        } else {
            int i = 0;
            while (i < getElementsPresent("DATE_PICKER_WEB_CALENDAR_TXT").size()) {
                if (getText("DATE_PICKER_WEB_CALENDAR_TXT", i).equals(date)) {
                    tapElements("DATE_PICKER_WEB_CALENDAR_TXT", i);
                    break;
                }
                i++;
            }
        }
    }

    /* Example of date format to be inputted
       date = 1 or 9 or 29
       month = Maret not March
       year = 2020

       Input date as is the date picker view, example : 1 or 9 not 01 or 09
       Input month in descriptive Bahasa not English, example : Maret not March
     */
    public void selectDatePicker(String date, String month, String year, String... rangeOfDate) {
        // Dynamic waiting of date picker to be displayed
        validateDatePickerForm();

        // Final Date != Current Date means selecting date either future or past
        if (getStateOfMonthAndYear(month, year)) {
            selectMonthAndYear(month, year);
        }

        // Select date
        selectDate(date, rangeOfDate);
        tapOnApplyBtn();
    }

    private void tapOnApplyBtn() {
        if (getContext.contains("WEBVIEW")) {
            tapElement("DATE_PICKER_WEB_TERAPKAN_BUTTON");
        } else {
            LogUtil.info("Date already selected!");
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
