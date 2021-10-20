package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import bukalapak.data.PostpaidData;
import bukalapak.data.vp.postpaid.AddOnIndihomeData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class AddonIndihomePage extends PostpaidBasePage {

    public AddonIndihomePage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage() {
        skipOnboarding("INDIHOME_BUTTON_NEXT", "INDIHOME_BUTTON_DONE");
        waitForVisibilityOf("INDIHOME_BANNER", 20);
        verifyElementExist("INDIHOME_HISTORY_BUTTON");
        HelperData.setLastActionPage(new AddonIndihomePage(iosDriver));
    }

    public void selectService(String service) {
        waitForVisibilityOf("INDIHOME_DROPDOWN", 30);
        tapElement("INDIHOME_DROPDOWN");
        waitForVisibilityOf("INDIHOME_SERVICE_SUBSCRIPTION", 10);

        if (service.contains("langganan")) {
            tapElement("INDIHOME_SERVICE_SUBSCRIPTION");
        } else {
            tapElement("INDIHOME_SERVICE_MONTHLY");
        }
    }

    public void inputCustomerNumber(String number) {
        verifyElementExist("INDIHOME_TEXT_FIELD", 15, "Indihome text field not found");
        PostpaidData.setCustomerNumber(number);
        typeAndEnterValueWithTimeOut("INDIHOME_TEXT_FIELD", (PostpaidData.getCustomerNumber()));
    }

    public void selectChannel() {
        waitForVisibilityOf("INDIHOME_CHANNEL_OPTION", 30);
        tapElement("INDIHOME_CHANNEL_OPTION");
        waitForVisibilityOf("INDIHOME_CHANNEL_SERVICE", 5);
        tapElements("INDIHOME_CHANNEL_SERVICE", 0);
    }

    public void submitData() {
        swipeUpToElement("INDIHOME_BAYAR_BUTTON");
        tapElement("INDIHOME_BAYAR_BUTTON");
    }

    public void inputInvalidNumber() {
        verifyElementExist("INDIHOME_TEXT_FIELD", 15, "Indihome text field not found");
        typeAndEnterValueWithTimeOut("INDIHOME_TEXT_FIELD", TestInstrument.dotenv.get("ADDON_INDIHOME_INVALID_NUMBER"));
    }

    public void showAlertMessage() {
        verifyElementExist("INDIHOME_ERROR_MESSAGE", 20, "Allert can't be shown");
        HelperData.setLastActionPage(new AddonIndihomePage(iosDriver));
    }

    public void tapOnIconTransactionHistory() {
        tapElement("INDIHOME_HISTORY_BUTTON");
    }

    public void validateHistoryPage() {
        verifyElementExist("INDIHOME_HISTORY_PAGE_TITLE_TEXT");
    }

    public void validateItemLoaded(String isValid) {
        verifyElementExist("INDIHOME_HISTORY_PAGE_TITLE_TEXT");
        if (isValid != null) {
            assertEquals( "Belum ada transaksi", "INDIHOME_HISTORY_PAGE_TITLE_EMPTY");
            verifyElementDisplayed("INDIHOME_HISTORY_PAGE_TITLE_EMPTY");
        }
    }

    public void setInquireData(String packageName) {
        waitForVisibilityOf("INDIHOME_INQUIRE_CUSTOMER_NAME_TEXT", 15);
        AddOnIndihomeData.setCustomerNumber(getTextFromElement("INDIHOME_INQUIRE_CUSTOMER_NUMBER_TEXT"));
        AddOnIndihomeData.setCustomerName(getTextFromElement("INDIHOME_INQUIRE_CUSTOMER_NAME_TEXT"));
        AddOnIndihomeData.setPackageName(packageName);
    }
}
