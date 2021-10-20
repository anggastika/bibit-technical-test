package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class CODPage extends BasePage {

    public CODPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnCODPage() {
        verifyElementExist("cod_page_title");
        TransactionData.setPaymentMethod("cod");
        HelperData.setLastActionPage(new CODPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
