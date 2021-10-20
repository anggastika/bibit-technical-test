package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PendapatanPage extends BasePage {
    private static DateTimeFormatter fullDateFormatter = DateTimeFormatter.ofPattern("d MMM yyyy", new Locale("id", "ID"));

    public PendapatanPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifyPage() {
        HelperData.setLastActionPage(new PendapatanPage(iosDriver));
        // verify onboarding display
        if (isElementVisible("pendapatan_onboarding_title", 5)) {
            tapElement("pendapatan_onboarding_kunjungi_fitur_button", 5);
        }
        waitForVisibilityOf("pendapatan_title_text", 20);
    }

    public void verifyOfferingSuperSeller() {
        waitForVisibilityOf("pendapatan_offering_ss_aktifkan_button", 15);
        verifyElementExist("pendapatan_offering_ss_fitur_unavailable_text");
        verifyElementExist("pendapatan_offering_ss_ajakan_text");
    }

    public void verifyIncomeSummary() {
        HelperData.setLastActionPage(new PolaPemasukanPage(iosDriver));
        waitForVisibilityOf("pendapatan_detail_pemasukan_button");
        validateRpFormat("pendapatan_total_sudah_remit_text");
        validateRpFormat("pendapatan_total_belum_remit_text");
        validateDisplayed("pendapatan_periode_dropdown");
    }

    public void verifyDatePeriode(int period) {
        String startDate = LocalDate.now().minusDays(period).format(fullDateFormatter);
        String endDate = LocalDate.now().minusDays(1).format(fullDateFormatter);
        waitForVisibilityOf("pendapatan_date_periode_txt", 20);
        assertEquals(startDate + " - " + endDate, getElementValue("pendapatan_date_periode_txt"));
        HelperData.setLastActionPage(new PendapatanPage(iosDriver));
    }

    public void clickPeriodeDropdown() {
        tapElement("pendapatan_periode_dropdown", 10);
    }

    public void verifyInfoPemasukan(String info) {
        validateValue().equals(info, getText("pendapatan_pemasukan_info_text"));
    }

    public void tapPantauPengeluaranmuLink(String linkText) {
        MobileElement link_section = getElement("pendapatan_pantau_pengeluaran_links");
        int axis_x = link_section.getLocation().x;
        int axis_y = link_section.getLocation().y;
        int width = link_section.getSize().width;
        int height = link_section.getSize().height;
        if (linkText.equalsIgnoreCase("Statistik Promoted Push")) {
            tapElement(axis_x + (int) (width * 0.2), axis_y + (int) (height * 0.5));
        }
        else if (linkText.equalsIgnoreCase("Rincian Biaya Promosi")) {
            tapElement(axis_x + (int) (width * 0.2), axis_y + (int) (height * 0.8));
        }
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
