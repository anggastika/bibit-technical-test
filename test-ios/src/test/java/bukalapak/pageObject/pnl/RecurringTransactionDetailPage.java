package bukalapak.pageObject.pnl;

import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import java.util.NoSuchElementException;

import static bukalapak.TestInstrument.dotenv;

public class RecurringTransactionDetailPage extends BasePage {
    public RecurringTransactionDetailPage(IOSDriver<IOSElement> iosDriver) { super(iosDriver); }

    public void userOnRecuringTransactionDetailPage() {
        waitForVisibilityOf("recurring_transaction_detail_title", 5);
        HelperData.setLastActionPage(new RecurringTransactionDetailPage(iosDriver));
    }

    public void userVerifyDetailRecurringTransaction() {
        validateDisplayed("recurring_transaction_detail_metode_pembayaran_kredit_atau_debit_text");
        validateDisplayed(constructLocator("recurring_transaction_detail_bpjs_number_text", dotenv.get("BPJS_PNL_SEPULSA")));
    }

    public void tapOnLanjutButton() {
            tapElement("recurring_transaction_detail_lanjut_button");
    }

    public void userIsOnRecurringTransactionDetailPage() {
        try{
            waitForVisibilityOf("RECURRING_TRANSACTION_DETAIL_PAGE_LANJUT_BUTTON",10);
            tapElement("RECURRING_TRANSACTION_DETAIL_PAGE_LANJUT_BUTTON");
            waitForVisibilityOf("RECURRING_TRANSACTION_DETAIL_PAGE_SWITCH", 10);
        } catch (Exception e) {
            waitForVisibilityOf("RECURRING_TRANSACTION_DETAIL_PAGE_SWITCH", 10);
        }
    }

    public void tapOnSwitch() {
        tapElement("RECURRING_TRANSACTION_DETAIL_PAGE_SWITCH", 15);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
