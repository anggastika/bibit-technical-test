package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by NurdianSetyawan on 9/1/19.
 */
public class ContinueCreditCardPage extends BasePage {

    public ContinueCreditCardPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    public void userOnContinueCreditCardPage() {
        try {
            waitForVisibilityOf("continue_credit_card_cvv_textfield", 30);
        } catch (Exception e) {
            waitForVisibilityOf("continue_credit_card_cvv_securetextfield", 30);
        }
        HelperData.setLastActionPage(new ContinueCreditCardPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        if(isElementVisible("home_review_close_button")) {
            tapElement("home_review_close_button");
        }
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
