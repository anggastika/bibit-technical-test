package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SuperSellerOptOutSurveyPage extends BasePage{
    public SuperSellerOptOutSurveyPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifyOptOutSurvey() {
        validateDisplayed("super_seller_opt_out_survey_close_button");
        validateDisplayed("super_seller_opt_out_survey_reason_text");
        validateDisplayed("super_seller_opt_out_survey_kirim_button_");
        validateDisplayed("super_seller_opt_out_survey_lewati_button_");
        HelperData.setLastActionPage(new SuperSellerOptOutSurveyPage(iosDriver));
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
