package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class GantiPeriodePendapatanPage extends BasePage {
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d");
    private DateTimeFormatter monthAndYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("id", "ID"));
    private DateTimeFormatter fullDateFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", new Locale("id", "ID"));

    public GantiPeriodePendapatanPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifyPage() {
        HelperData.setLastActionPage(new GantiPeriodePendapatanPage(iosDriver));
        waitForVisibilityOf("ganti_periode_title_text", 15);
        waitForVisibilityOf("ganti_periode_ubah_button", 15);
    }

    public void verifyCalendarSheet() {
        HelperData.setLastActionPage(new GantiPeriodePendapatanPage(iosDriver));
        waitForVisibilityOf("ganti_periode_calendar_title_text", 15);
    }

    public void verifyTodayDateDisabled() {
        HelperData.setLastActionPage(new GantiPeriodePendapatanPage(iosDriver));
        selectDateBeforeToday(0);
        // verify selected date
        waitForVisibilityOf("ganti_periode_selected_date_period_text");
        validateValue().equalsFalse(getText("ganti_periode_selected_date_period_text").contains(LocalDate.now().format(fullDateFormatter)));
    }

    public void verifyFilterByDatesNotes(String notes) {
        validateValue().equals(notes, getText("ganti_periode_date_period_notes_text"));
    }

    public void selectDateBeforeToday(int range) {
        HelperData.setLastActionPage(new GantiPeriodePendapatanPage(iosDriver));
        LocalDate date = LocalDate.now().minusDays(range);
        slideMonthPage(date);
        // tap date and then save
        tapElement(constructLocator("ganti_periode_calendar_date_text", date.format(dateFormatter)), 10);
        waitFor(1);
        tapElement("ganti_periode_calendar_terapkan_button", 10);
    }

    private void slideMonthPage(LocalDate date) {
        String thisMonth = LocalDate.now().format(monthAndYearFormatter);
        String dateMonth = date.format(monthAndYearFormatter);
        if (!thisMonth.equals(dateMonth)) {
            tapElement("ganti_periode_calendar_prev_month_page_button", 10);
            waitFor(1);
        }
    }

    public void verifySelectedMaximumDatePeriod(int range) {
        HelperData.setLastActionPage(new GantiPeriodePendapatanPage(iosDriver));
        String expectedSelectedPeriod = LocalDate.now().minusDays(range).format(fullDateFormatter) + " - " + LocalDate.now().minusDays(1).format(fullDateFormatter);
        waitForVisibilityOf("ganti_periode_selected_date_period_text");
        validateValue().equals(expectedSelectedPeriod, getText("ganti_periode_selected_date_period_text"));
    }
}
