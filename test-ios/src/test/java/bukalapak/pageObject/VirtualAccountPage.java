package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.TransactionData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by NurdianSetyawan on 6/1/19.
 */
public class VirtualAccountPage extends BasePage {

    public VirtualAccountPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnVirtualAccountPage() {
        verifyElementExist("virtual_account_page_title");
        TransactionData.setPaymentMethod("VA-BCA");
        HelperData.setLastActionPage(new VirtualAccountPage(iosDriver));
    }

    public void userOnVirtualAccountVPPage() {
        verifyElementExist("virtual_account_page_vp_title");
        TransactionData.setPaymentMethod("VA-BCA");
        HelperData.setLastActionPage(new VirtualAccountPage(iosDriver));
    }

    public void isOnVirtualAccountPage() {
        swipeDownToElement("checkout_page_metode_pembayaran");
        verifyElementExist("checkout_page_metode_pembayaran");
        tapElement("checkout_page_metode_pembayaran");
        verifyElementExist("virtual_account_page_title");
        tapElement("virtual_account_page_title");
        waitForVisibilityOf("virtual_account_page_title", 10);
        HelperData.setLastActionPage(new VirtualAccountPage(iosDriver));
    }

    public void chooseBankAutomaticTransfer(String nameBank) {
        waitForVisibilityOf("virtual_account_page_title", 10);
        swipeUpToElement(constructLocator("virtual_account_option", nameBank));
        tapElement(constructLocator("virtual_account_option", nameBank));
        tapElement("virtual_account_metode_ini_button", 5);
        HelperData.setLastActionPage(new VirtualAccountPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
