package bukalapak.pageObject.vp.insurance;

import bukalapak.data.HelperData;
import bukalapak.data.InsuranceData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by ferawati h.p. on 30/06/20.
 */
public class AsuransiBepergianLandingPage extends BasePage {

    public AsuransiBepergianLandingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage() {
        changeContext().toWebview();
        verifyElementExist("asuransi_bepergian_landing_page_header_text");
    }

    public void tapTanggalField(String tripType) {
        tapElement(constructLocator("asuransi_bepergian_landing_page_pilih_tanggal_date", tripType));
    }

    private void selectNextDate(int day) {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_YEAR, day);
        InsuranceData.setDate(new SimpleDateFormat("d").format(calendar.getTime()));
    }

    public void chooseCalendarDate(int day) {
        int tmpDay;
        selectNextDate(day);

        int indexDaySize = getElements(constructLocator("asuransi_bepergian_landing_page_calendar_date", InsuranceData.getDate())).size();

        for (tmpDay = 0; tmpDay <= indexDaySize-1; tmpDay++) {
            tapElements(constructLocator("asuransi_bepergian_landing_page_calendar_date", InsuranceData.getDate()), tmpDay);
        }
    }

    public void tapPilihTanggalButton() {
        waitForVisibilityOf("asuransi_bepergian_landing_page_choosedate_button", 10);
        tapElement("asuransi_bepergian_landing_page_choosedate_button");
    }

    public void tapLanjutkanButton() {
        swipeUpToElement("asuransi_bepergian_landing_page_lanjutkan_button");
        tapElement("asuransi_bepergian_landing_page_lanjutkan_button");
    }

    public void tapPackageType(String packagetype) {
        switch (packagetype) {
            case "individu":
                tapElement("asuransi_bepergian_landing_page_individu_package", 10);
                break;
            case "family":
                tapElement("asuransi_bepergian_landing_page_family_package", 10);
                break;
            default:
                Assert.fail("Please choose Individu or Family package");
                break;
        }
    }

    public void tapPelajariButton() {
        verifyElementExist("asuransi_bepergian_landing_page_pelajari_button");
        tapElement("asuransi_bepergian_landing_page_pelajari_button");
    }

    public void validatePelajariSection() {
        verifyElementExist("asuransi_bepergian_landing_page_header_section");
        verifyElementExist("asuransi_bepergian_landing_page_title_section_one_text");
        verifyElementExist("asuransi_bepergian_landing_page_content_section_one_text");
        verifyElementExist("asuransi_bepergian_landing_page_title_section_two_text");
        verifyElementExist("asuransi_bepergian_landing_page_content_section_two_text");
        verifyElementExist("asuransi_bepergian_landing_page_title_section_three_text");
        verifyElementExist("asuransi_bepergian_landing_page_content_section_three_text");
        verifyElementExist("asuransi_bepergian_landing_page_lihat_pilihan_paket_button");
        HelperData.setLastActionPage(new AsuransiBepergianLandingPage(iosDriver));
    }

    public void validatePackageType(String packageType) {

        switch (packageType) {
            case "individu":
                verifyElementExist("asuransi_bepergian_landing_page_individu_package");
                break;
            case "family":
                verifyElementExist("asuransi_bepergian_landing_page_family_package");
                break;
            default:
                Assert.fail("Please choose Individu or Family package");
                break;
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
