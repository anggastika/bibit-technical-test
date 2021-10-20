package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.utils.Constants;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class InvoiceListPage extends BasePage {

    public InvoiceListPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToInvoiceListPage() {
        HelperData.goToHomePage();

        if (isElementVisible("home_review_close_button", Constants.FIVE_SECS_TIMEOUT)) {
            tapElement("home_review_close_button");
        }
        tapElement("home_transaksi_tab");
        verifyElementExist("transaction_list_tagihan_tab");
        tapElement("transaction_list_tagihan_tab");
    }

    public void isOnInvoiceListPage() {
        assertTrue(isElementVisible("invoice_list_search_field"), "Invoice list is not opened yet!");
        HelperData.setLastActionPage(new InvoiceListPage(iosDriver));
    }

    public void search(String invoiceNo) {
        IOSElement iosElement = iosDriver.findElement(getLocator("invoice_list_search_field"));
        iosElement.click();
        iosElement.clear();
        iosElement.sendKeys(invoiceNo + "\n");
        // delay to make sure the search is done
        // no other element to wait and the page elements could be similar
        waitFor(5);
        HelperData.setLastActionPage(new InvoiceListPage(iosDriver));
    }

    public void tapOnInvoice() {
        tapElement("invoice_list_top_invoice_button");
    }

    public void tapOnlastInvoice() {
        waitForVisibilityOf("invoice_list_last_transaction", 50);
        tapElement("invoice_list_last_transaction");
    }

    public void verifyInvoiceStateIsMatch(String state) {
        verifyElementDisplayed("invoice_list_"+state.replaceAll(" ", "_").toLowerCase()+"_text");
    }

    public void verifySubscriptionInvoice() {
        waitForVisibilityOf("invoice_list_top_invoice_button", 50);
        swipeUpToElement("invoice_list_subscription");
        verifyElementDisplayed("invoice_list_subscription");
        HelperData.setLastActionPage(new InvoiceListPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
