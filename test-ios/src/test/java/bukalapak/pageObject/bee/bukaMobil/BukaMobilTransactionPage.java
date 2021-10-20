package bukalapak.pageObject.bee.bukaMobil;

import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaMobilTransactionPage extends BasePage {
    public BukaMobilTransactionPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateTransactionPage() {
        waitForVisibilityOf("BUKAMOBIL_TRX_TITLE");
        verifyElementDisplayed("BUKAMOBIL_TRX_INPROGRESS_TAB");
        verifyElementDisplayed("BUKAMOBIL_TRX_DONE_TAB");
        HelperData.setLastActionPage(new BukaMobilTransactionPage(iosDriver));
    }

    public void validateInProgressProposal() {
        waitForVisibilityOf("BUKAMOBIL_TRX_INPROGRESS_PROPOSAL_NUMBER");
        verifyElementDisplayed("BUKAMOBIL_TRX_INPROGRESS_SELECT_PAYMENT_BTN");
        verifyElementDisplayed("BUKAMOBIL_TRX_INPROGRESS_PROPOSAL_STATUS_BTN");
    }

    public void cancelProposal() {
        tapBatalkanPegajuan();
        validateCancelModal();
        inputCancelReason(null);
        tapCancelCheckbox();
        tapBatalkanBtn();
    }

    public void tapBatalkanPegajuan() {
        swipeUpToElement("BUKAMOBIL_TRX_INPROGRESS_BATALKAN_PENGAJUAN_BTN");
        tapElement("BUKAMOBIL_TRX_INPROGRESS_BATALKAN_PENGAJUAN_BTN");
    }

    public void validateCancelModal() {
        waitForVisibilityOf("BUKAMOBIL_TRX_CANCEL_MODAL_TITLE", 5);
    }

    public void inputCancelReason(String reason) {
        String input = (reason != null) ? reason : "Default Testing";
        typeAndEnterValue("BUKAMOBIL_TRX_CANCEL_REASON_FIELD", input);
    }

    public void tapCancelCheckbox() {
        tapElement("BUKAMOBIL_TRX_CANCEL_CHECKBOX");
    }

    public void tapBatalkanBtn() {
        tapElement("BUKAMOBIL_TRX_CANCEL_BTN");
        HelperData.setLastActionPage(new BukaMobilTransactionPage(iosDriver));
    }

    public void tapInProgressTab() {
        tapElement("BUKAMOBIL_TRX_INPROGRESS_TAB");
    }
}
