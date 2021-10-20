package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class MidtransPage extends BasePage {

    public MidtransPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnMidtransPage() {
        waitForVisibilityOf("midtrans_title_text");
        verifyElementExist("midtrans_otp_field");
        HelperData.setLastActionPage(new MidtransPage(iosDriver));
    }

    public void inputOTP(String numberCC) {
        typeAndEnterValueWithTimeOut("midtrans_otp_field", numberCC);
        swipeLeft(0.1, 0.5, 0.5);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
