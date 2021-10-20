package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PromotedRiwayatKlikPage extends BasePage {

    public PromotedRiwayatKlikPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void verifyPromotedRiwayatKlikPageDisplayed() {
        HelperData.setLastActionPage(new PromotedRiwayatKlikPage(iosDriver));
        waitForVisibilityOf("promoted_history_product_list_section");
        validateDisplayed("promoted_history_klik_title");
        validateDisplayed("prom_text_field");
        validateDisplayed("prom_datepicker_icon");
    }

    public void verifyBidPromotedHistory(String productName, int bid, String date) {
        HelperData.setLastActionPage(new PromotedRiwayatKlikPage(iosDriver));
        waitForVisibilityOf(constructLocator("promoted_history_product_name", productName));
        validateDisplayed(constructLocator("promoted_history_product_date_clicked", date));
        assertEquals(bid, Math.abs(getIntegerFromTextElement("promoted_history_product_bid")));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
