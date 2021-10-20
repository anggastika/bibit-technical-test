package bukalapak.pageObject;

import bukalapak.data.PROMData;
import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class PromotedLoanDetailPage extends BasePage{

    public PromotedLoanDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnLoanDetailPage() {
        waitForVisibilityOf("promoted_push_loan_konfirmasi_pinjaman_text", 60);
        verifyElementExist("promoted_push_loan_nominal_budget_value_text");
        verifyElementExist("promoted_push_loan_saldo_sekarang_value_text");
        verifyElementExist("promoted_push_loan_total_pinjaman_value_text");
        verifyElementExist("promoted_push_loan_batalkan_pinjaman_button");
        HelperData.setLastActionPage(new PromotedLoanDetailPage(iosDriver));
    }

    public void checkLoanDetail() {
        assertEquals(getIntegerFromValueElement("promoted_push_loan_nominal_budget_value_text"), PROMData.getInputtedBudget());
        assertEquals(getIntegerFromValueElement("promoted_push_loan_total_pinjaman_value_text"), PROMData.getInputtedBudget());
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
