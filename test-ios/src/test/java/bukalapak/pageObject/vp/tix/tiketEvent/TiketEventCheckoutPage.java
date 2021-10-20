package bukalapak.pageObject.vp.tix.tiketEvent;

import bukalapak.data.EventData;
import bukalapak.data.HelperData;
import bukalapak.pageObject.VpBasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * @Author: Fixco Amrizal Candra
 **/

public class TiketEventCheckoutPage extends VpBasePage {
    public TiketEventCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateCheckoutDetail() {
        validateValue().equals(EventData.getEventTitle(), getText("TICKET_EVENT_CHECKOUT_PACKAGE_TEXT"));
        validateValue().equals(EventData.getEventPrice(), getText("TICKET_EVENT_CHECKOUT_TOTAL_PRICE_TEXT"));

        HelperData.setLastActionPage(new TiketEventTransactionDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
