package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by NurdianSetyawan on 18/12/18.
 */
public class GeraiPage extends BasePage {

    public GeraiPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void userOnGeraiPage() {
        verifyElementExist("gerai_page_title");
        TransactionData.setPaymentMethod("");
        HelperData.setLastActionPage(new GeraiPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
