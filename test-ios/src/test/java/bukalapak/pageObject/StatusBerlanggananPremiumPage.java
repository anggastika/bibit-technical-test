package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class StatusBerlanggananPremiumPage extends BasePage {

    public StatusBerlanggananPremiumPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnStatusBerlangganan() {
        verifyElementExist("status_berlangganan_premium_title");
        verifyElementExist("base_back_button");
        verifyElementExist("status_berlangganan_premium_paket_text");
        assertTrue(getElementValue("status_berlangganan_premium_package_name_text").matches("Basic|Professional|Platinum"));
        verifyElementExist("status_berlangganan_premium_saldo_text");
        HelperData.setLastActionPage(new StatusBerlanggananPremiumPage(iosDriver));
    }

    public void userOnKonfirmasiBerhenti() {
        verifyElementExist("konfirmasi_berhenti_premium_title");
        verifyElementExist("base_back_button");
        verifyElementExist("konfirmasi_berhenti_premium_yakin_text");
        verifyElementExist("konfirmasi_berhenti_premium_beritahu_text");
        verifyElementExist("konfirmasi_berhenti_premium_option_tidak_tertarik_text");
        verifyElementExist("konfirmasi_berhenti_premium_option_hanya_tertentu_text");
        verifyElementExist("konfirmasi_berhenti_premium_option_bukadompet_text");
        verifyElementExist("konfirmasi_berhenti_premium_option_other_text");
        verifyElementExist("konfirmasi_berhenti_premium_option_other_text_field");
        verifyElementExist("konfirmasi_berhenti_premium_berhenti_button");
        HelperData.setLastActionPage(new StatusBerlanggananPremiumPage(iosDriver));
    }

    public void verifyPremiumPackage(String packageName) {
        assertEquals(packageName, getElementValue("status_berlangganan_premium_package_name_text"));
    }

    public void verifyAutoExtendStatus(boolean isActive) {
        if (isActive) {
            waitForVisibilityOf("status_berlangganan_premium_diperpanjang_pada_text");
            verifyElementExist("status_berlangganan_premium_diperpanjang_pada_text");
            verifyElementExist("status_berlangganan_premium_biaya_perpanjangan_text");
            verifyElementExist("status_berlangganan_premium_berhenti_langganan_button");
        } else {
            verifyElementExist("status_berlangganan_premium_berakhir_pada_text");
            verifyElementExist("status_berlangganan_premium_not_extend_text");
            verifyElementExist("status_berlangganan_premium_kembali_langganan_button");
        }
    }

    @Override
    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
