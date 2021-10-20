package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by Ihsan Hasanudin on 04/02/21.
 */
public class DANACashierPage extends BasePage {

    public DANACashierPage(IOSDriver<IOSElement> iosDriver) { super(iosDriver);}

    public void userOnDANACashierPage() {
        waitForVisibilityOf("dana_cashier_page_saldo_dana_text");
        swipeUpToElement("dana_cashier_page_kartu_text");
        verifyElementDisplayed("dana_cashier_page_kartu_text");
        HelperData.setLastActionPage(new DANACashierPage(iosDriver));
    }

    public void userOnDANACashierPageWithCredits() {
        waitForVisibilityOf("dana_cashier_page_saldo_dana_text");
        tapElement("dana_cashier_page_saldo_dana_text");
        verifyElementDisplayed("dana_cashier_page_saldo_bukalapak_credits_text");
        HelperData.setLastActionPage(new DANACashierPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
