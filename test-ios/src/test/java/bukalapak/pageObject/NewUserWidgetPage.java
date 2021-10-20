package bukalapak.pageObject;

import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * @author Aris Nugraha 22/10/2019
 */

public class NewUserWidgetPage extends BasePage {

    public NewUserWidgetPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnNUWidget() {
        swipeDownToElement("verification_phone_button");
        verifyElementExist("new_promo_text");
    }

    public void validateBonusVoucherNewUser() {
        verifyElementExist("bonus_voucher_for_new_user");
        HelperData.setLastActionPage(new NewUserWidgetPage(iosDriver));
    }

    public void validateVerificationPhonePage() {
        if (isElementVisible("otp_confirm_page_title")) {
            verifyElementExist("verification_akun_title");
            LogUtil.info("User already input phone number but unconfirmed");
        } else {
            verifyElementExist("verification_phone_title");
            LogUtil.info("User redirect to confirm phone page");
        }

        HelperData.setLastActionPage(new NewUserWidgetPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
