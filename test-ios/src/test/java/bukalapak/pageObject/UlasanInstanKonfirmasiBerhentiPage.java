package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class UlasanInstanKonfirmasiBerhentiPage extends BasePage {

    public UlasanInstanKonfirmasiBerhentiPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnKonfirmasiBerhentiPage() {
        waitForVisibilityOf("ulasan_instan_konfirmasi_berhenti_info_auto_extend_text", 20);
        waitForVisibilityOf("ulasan_instan_konfirmasi_berhenti_alasan_1_radio_button", 15);
        waitForVisibilityOf("ulasan_instan_konfirmasi_berhenti_title_text");
        waitForVisibilityOf("ulasan_instan_konfirmasi_berhenti_ask_info_text");
        waitForVisibilityOf("ulasan_instan_konfirmasi_berhenti_alasan_lain_field");
        waitForVisibilityOf("ulasan_instan_konfirmasi_berhenti_berhenti_berlangganan_button");
        HelperData.setLastActionPage(new UlasanInstanKonfirmasiBerhentiPage(iosDriver));
    }

    public void selectReason(String reason) {
        waitForVisibilityOf(constructLocator("ulasan-instant_konfirmasi_berhenti", reason), 20);
        if (reason.equals("Alasan Lain")) {
            tapElement(constructLocator("ulasan-instant_konfirmasi_berhenti", reason));
            typeAndEnterValue("ulasan_instan_konfirmasi_berhenti_alasan_lain_field", "Alasan Lain Testing!");
        } else {
            tapElement(constructLocator("instant_konfirmasi_berhenti", reason));
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
