package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PengaturanPembayaranPage extends BasePage {

    public PengaturanPembayaranPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void removeCC(String numberCC) {
        tapElement("xpath_//XCUIElementTypeStaticText[contains(@label, '" + numberCC + "')]" +
                "/parent::XCUIElementTypeCell/following-sibling::XCUIElementTypeCell" +
                "//XCUIElementTypeButton[@label='Hapus']");
        tapElement("popup_alert_ya_button");
        HelperData.setLastActionPage(new PengaturanPembayaranPage(iosDriver));
    }

    public void tapPengaturanDANA() {
        if (isElementExist("pengaturan_pembayaran_dana_text",5)) {
            tapElement("pengaturan_pembayaran_dana_text");
        } else {
            tapElement("pengaturan_pembayaran_dana_text_alt");
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void userOnPengaturanPembayaranPage() {
        waitForVisibilityOf("pengaturan_pembayaran_title_text", 40);
    }

    public void tapOnCreditCardButton() {
        waitForVisibilityOf("pengaturan_pembayaran_credit_card_button", 3);
        tapElement("pengaturan_pembayaran_credit_card_button");
    }



}
