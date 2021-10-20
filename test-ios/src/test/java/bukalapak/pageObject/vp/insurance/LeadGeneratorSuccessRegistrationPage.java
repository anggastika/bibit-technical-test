package bukalapak.pageObject.vp.insurance;

import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * @Author: Ayu Musfita
 * @Date: 16/07/20, Thu
 **/
public class LeadGeneratorSuccessRegistrationPage extends BasePage {

    public LeadGeneratorSuccessRegistrationPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage() {
        verifyElementExist("LEAD_GENERATOR_SUCCESS_REGISTRATION_TITLE_TEXT");
        verifyElementExist("LEAD_GENERATOR_SUCCESS_REGISTRATION_HISTORY_BUTTON");
        HelperData.setLastActionPage(new LeadGeneratorSuccessRegistrationPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
