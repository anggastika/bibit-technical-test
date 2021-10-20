package bukalapak.pageObject.vp.tix.tiketEvent;

import bukalapak.data.HelperData;
import bukalapak.pageObject.VpBasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * @Author: Fixco Amrizal Candra
 **/

public class TiketEventTransactionDetailPage extends VpBasePage {
    public TiketEventTransactionDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateEventData() {
        swipeUpToElement("TICKET_EVENT_INFO_TRANSAKSI_NAME");
        swipeUpToElement("TICKET_EVENT_INFO_TRANSAKSI_LOCATION");
        validateDisplayed("TICKET_EVENT_INFO_TRANSAKSI_DATE");
        validateDisplayed("TICKET_EVENT_INFO_TRANSAKSI_TIME");
    }

    public void validateEventBuyer() {
        swipeUpToElement("TICKET_EVENT_INFO_BUYER_PHONE_NUMBER");
        validateDisplayed("TICKET_EVENT_INFO_BUYER_NAME");
        validateDisplayed("TICKET_EVENT_INFO_BUYER_EMAIL");
        validateDisplayed("TICKET_EVENT_INFO_BUYER_IDENTITY_NUMBER");
        HelperData.setLastActionPage(new TiketEventTransactionDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
