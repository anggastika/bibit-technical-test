package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TransaksiQBPage extends BasePage {

    public TransaksiQBPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnTransaksiQBPage() {
        waitForVisibilityOf("checkout_qb_header", 30);
        verifyElementExist("checkout_qb_kodepembeli");
        verifyElementExist("checkout_qb_cekstatus");
        HelperData.setLastActionPage(new TransaksiQBPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
