package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class RecurringPage extends BasePage {
    public RecurringPage(IOSDriver<IOSElement> iosDriver) { super(iosDriver);
}
    public void userOnRecurringPage() {
        waitForVisibilityOf("RECURRING_HEADER_TITTLE",30);
        waitForVisibilityOf("RECURRING_SELECTION_PRODUCT_FIELD",30);
    }

    public void chooseProduct(String product) {
        tapElement("RECURRING_SELECTION_PRODUCT_FIELD");
        if (!isElementExist(constructLocator("RECURRING_TRANSACTION_INPUT_TXT", product), 3)) {
            swipeDown(0.8, 0.55);
        }
        waitForVisibilityOf(constructLocator("RECURRING_TRANSACTION_INPUT_TXT",product));
        tapElement(constructLocator("RECURRING_TRANSACTION_INPUT_TXT",product));
    }

    public void tapOnMulaiButton() {
        tapElement("RECURRING_TRANSACTION_MULAI_BUTTON");
    }

    public void tapOnLatestTransaction() {
        if (!isElementExist("RECURRING_TRANSACTION_PEMBAYARAN_BERIKUTNYA_TXT", 15)) {
            swipeUp(0.8, 0.55);
        }
        tapElement("RECURRING_TRANSACTION_PEMBAYARAN_BERIKUTNYA_TXT");
    }

    public void validateInactiveTransaction() {
        nativeSwipeDown();
        validateElementContainsText("RECURRING_TRANSACTION_NEW_TRANSACTION_BUTTON", "Aktifkan Kembali");
        HelperData.setLastActionPage(new RecurringPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapOnLastTransactionCellButton() {
        nativeSwipeDown();
        waitForVisibilityOf("RECURRING_TRANSACTION_PEMBAYARAN_BERIKUTNYA_TXT", 20);
        tapElement("RECURRING_TRANSACTION_PEMBAYARAN_BERIKUTNYA_TXT");
    }
}
