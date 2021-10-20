package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PolaPemasukanPage extends BasePage {
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMM", new Locale("id", "ID"));
    private DateTimeFormatter fullDateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy", new Locale("id", "ID"));
    private DateTimeFormatter fullMonthAndYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("id", "ID"));

    public PolaPemasukanPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifyPage() {
        HelperData.setLastActionPage(new PolaPemasukanPage(iosDriver));
        waitForVisibilityOf("pola_pemasukan_title_text", 20);
    }

    public void verifyOnboarding(String showStatus) {
        HelperData.setLastActionPage(new PolaPemasukanPage(iosDriver));
        if (showStatus.equals("is")) {
            waitForVisibilityOf("pola_pemasukan_onboarding_text", 15);
            validateDisplayed("pola_pemasukan_onboarding_dismiss_button");
            validateDisplayed("pola_pemasukan_graph");
        } else {
            validateNotDisplayed("pola_pemasukan_onboarding_text");
            validateNotDisplayed("pola_pemasukan_onboarding_dismiss_button");
        }
    }

    public void verifyCheckbox(String trxType, String check) {
        HelperData.setLastActionPage(new PolaPemasukanPage(iosDriver));
        waitForVisibilityOf(constructLocator("pola_pemasukan_transaction_checkbox", trxType));
        validateValue().equals(check.equals("checked"), Boolean.parseBoolean(getElementValue(constructLocator("pola_pemasukan_transaction_checkbox", trxType))));
    }

    public void clickCheckboxTransactionType(String trxType) {
        tapElement(constructLocator("pola_pemasukan_transaction_checkbox", trxType), 10);
    }

    public void verifyNoGraphWarning() {
        HelperData.setLastActionPage(new PolaPemasukanPage(iosDriver));
        waitForVisibilityOf("pola_pemasukan_no_graph_warning_text", 15);
        validateNotDisplayed("pola_pemasukan_graph");
    }

    public void clickGraphicDate() {
        HelperData.setLastActionPage(new PolaPemasukanPage(iosDriver));
        String pickDate = LocalDate.now().minusDays(3).format(dateFormatter);
        tapElement(constructLocator("pola_pemasukan_choose_date_graphic_button", pickDate));
    }

    public void verifyPemasukanDetailPickedDate(String type) {
        String pickDate;
        if (type.contains("before")) {
            pickDate = LocalDate.now().minusDays(4).format(fullDateFormatter);
        } else {
            pickDate = LocalDate.now().minusDays(3).format(fullDateFormatter);
        }
        HelperData.setLastActionPage(new PolaPemasukanPage(iosDriver));
        validateValue().contains(pickDate, getText("pola_pemasukan_transaction_detail_date_text"));
        verifyPemasukanDetail();
    }

    public void verifyPemasukanDetail() {
        validateDisplayed("pola_pemasukan_transaction_detail_total_pemasukan_txt");
        validateRpFormat("pola_pemasukan_transaction_detail_total_pemasukan_value_txt");
        validateDisplayed("pola_pemasukan_transaction_detail_sebelumnya_button");
        validateDisplayed("pola_pemasukan_transaction_detail_berikutnya_button");
    }

    public void clickLastPointOnGraph() {
        tapElement("pola_pemasukan_last_point_on_graph_image", 5);
    }

    public void verifyLatestPeriodOnPemasukanDetail(String period) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        String expectedPeriod = "";
        if (period.equals("month")) {
            expectedPeriod = yesterday.format(fullMonthAndYearFormatter);
        } else if (period.equals("day")) {
            expectedPeriod = yesterday.format(fullDateFormatter);
        }
        validateValue().equals(expectedPeriod, getText("pola_pemasukan_transaction_detail_date_text"));
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
