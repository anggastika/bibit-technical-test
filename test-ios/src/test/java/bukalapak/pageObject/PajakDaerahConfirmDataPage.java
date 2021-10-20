package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PajakDaerahConfirmDataPage extends BasePage {

    public PajakDaerahConfirmDataPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void confirmDataNOP() {
        swipeDownToElement("pajak_daerah_pay_now_button");
        tapElement("pajak_daerah_pay_now_button");
    }

    public void validateInvalidNOP() {
        verifyElementExist("pajak_daerah_invalid_nop_message", 15, "Error message not exist");
        HelperData.setLastActionPage(new PajakDaerahConfirmDataPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
