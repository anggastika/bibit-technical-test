package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SaldoPage extends BasePage {

    public SaldoPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnSaldoPage() {
        verifyElementExist("saldo_page_title");
        TransactionData.setPaymentMethod("saldo");
        HelperData.setLastActionPage(new SaldoPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
