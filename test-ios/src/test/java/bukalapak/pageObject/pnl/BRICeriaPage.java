package bukalapak.pageObject.pnl;

import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BRICeriaPage extends BasePage {

    public BRICeriaPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnBRICeriaPage() {
        waitForVisibilityOf("bri_ceria_belum_punya_akun_text", 30);
        HelperData.setLastActionPage(new BRICeriaPage(iosDriver));
    }

    public void verifyUnbindedAlertShowing() {
        waitForVisibilityOf("bri_ceria_unbinded_alert_text", 120);
        verifyElementNotExist("checkout_marketplace_alchemy_metode_pembayaran_choosen_button");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}