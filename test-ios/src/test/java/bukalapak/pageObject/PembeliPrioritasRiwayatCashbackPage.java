package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PembeliPrioritasRiwayatCashbackPage extends BasePage {

    public PembeliPrioritasRiwayatCashbackPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnRiwayatCashbackPage() {
        waitForVisibilityOf("priority_riwayat_cashback", 3);
        HelperData.setLastActionPage(new PembeliPrioritasRiwayatCashbackPage(iosDriver));
    }

    public void checkRiwayatCashback() {
        waitForVisibilityOf("priority_riwayat_cashback_date", 5);
        verifyElementExist("priority_riwayat_cashback_nominal");
        verifyElementExist("priority_riwayat_cashback_transaction_id");
        verifyElementExist("priority_riwayat_cashback_active_date");
        HelperData.setLastActionPage(new PembeliPrioritasRiwayatCashbackPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
