package bukalapak.pageObject.vp.base;

import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assume;

public class VpBasePage extends BasePage {

    protected static final int VERY_SHORT_TIMEOUT = 2;
    protected static final int SHORT_TIMEOUT = 5;
    protected static final int MEDIUM_TIMEOUT = 15;
    protected static final int LONG_TIMEOUT = 30;
    protected static final int VERY_LONG_TIMEOUT = 60;

    public VpBasePage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    private void validateSelengkapnyaNotExist() {
        for (int i = 0; i < 2; i++) {
            if (isElementVisible("vp_landing_page_text_salin")) break;
            swipeUpAtSpecifiedLocator("vp_landing_page_scroll_element");
        }
        validateNotExist("vp_landing_page_text_selengkapnya", VERY_SHORT_TIMEOUT);
    }

    private void validateTutupTextNotExist() {
        for (int i = 0; i < 2; i++) {
            if (isElementVisible("vp_landing_page_text_salin")) break;
            swipeDown(0.65, 0.9);
        }
        validateNotExist("vp_landing_page_text_tutup", VERY_SHORT_TIMEOUT);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void validateToolbarTitle(String text) {
        waitForVisibilityOf(constructLocator("vp_toolbar_text_title_dynamic", text), MEDIUM_TIMEOUT);
    }

    public void tapOnIconTransactionHistory() {
        // debug pop up element covers target element, need to re tap
        for (int i = 0; i < 3; i++) {
            waitForVisibilityOf("vp_toolbar_button_right", MEDIUM_TIMEOUT);
            verifyElementDisplayed("vp_toolbar_button_right");
            tapElement("vp_toolbar_button_right");
            if (!isElementExist("vp_toolbar_button_right")) break;
        }
    }

    public void copyAvailablePromoCode() {
        Assume.assumeTrue("No available promo", isElementVisible("vp_landing_page_text_available_promo"));
        tapElement("vp_landing_page_text_available_promo");
        tapElement("vp_landing_page_text_selengkapnya");
        validateSelengkapnyaNotExist();
        tapElement("vp_landing_page_text_tutup");
        validateTutupTextNotExist();
        tapElement("vp_landing_page_text_salin");
        TransactionData.setAvailablePromoCode(getElementValue("vp_landing_page_text_promo_code"));
        tapElement("vp_landing_page_outside_promo");
    }
}
