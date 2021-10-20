package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SuperSellerOptOutPage extends BasePage{
    public SuperSellerOptOutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifyInfoNonaktifkanSuperSeller() {
        validateDisplayed("super_seller_opt_out_page_title");
        validateDisplayed("super_seller_opt_out_berhenti_button");
        validateDisplayed("super_seller_opt_out_tetap_jadi_super_seller_button");
        HelperData.setLastActionPage(new SuperSellerOptOutPage(iosDriver));
    }

    @Override
    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
