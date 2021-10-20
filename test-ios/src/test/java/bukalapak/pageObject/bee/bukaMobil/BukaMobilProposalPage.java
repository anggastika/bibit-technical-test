package bukalapak.pageObject.bee.bukaMobil;

import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaMobilProposalPage extends BasePage {
    public BukaMobilProposalPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateProposalPage() {
        waitForVisibilityOf("BUKAMOBIL_PROPOSAL_TITLE", 10);
        verifyElementDisplayed("BUKAMOBIL_PROPOSAL_CAR_NAME");
        HelperData.setLastActionPage(new BukaMobilProposalPage(iosDriver));
    }

    public void uploadKTP() {
        tapAmbilFotoKTPBtn();
        selectFoto();
    }

    private void tapAmbilFotoKTPBtn() {
        tapElement("BUKAMOBIL_PROPOSAL_AMBIL_FOTO_BTN");
    }

    private void selectFoto() {
        waitForElementClickable("BUKAMOBIL_PROPOSAL_ALL_IMAGES", 10);
        tapElement("BUKAMOBIL_PROPOSAL_ALL_IMAGES");
        waitForElementClickable("BUKAMOBIL_PROPOSAL_KTP_IMAGES", 5);
        tapElement("BUKAMOBIL_PROPOSAL_KTP_IMAGES");
        waitForElementClickable("BUKAMOBIL_PROPOSAL_LANJUT_UPLOAD_BTN", 5);
        tapElement("BUKAMOBIL_PROPOSAL_LANJUT_UPLOAD_BTN");
    }

    public void validateProposalSummary() {
        verifyElementDisplayed("BUKAMOBIL_PROPOSAL_DATA_MOBIL_TITLE");
        swipeUpToElement("BUKAMOBIL_PROPOSAL_DATA_DIRI_TITLE");
        verifyElementDisplayed("BUKAMOBIL_PROPOSAL_DATA_DIRI_TITLE");
    }

    public void validateTermCondition() {
        verifyElementDisplayed("BUKAMOBIL_PROPOSAL_TERM_CONDITION_TITLE");
        verifyElementDisplayed("BUKAMOBIL_PROPOSAL_BOOKINGFEE_CHECKBOX");
        verifyElementDisplayed("BUKAMOBIL_PROPOSAL_PRIVACY_CHECKBOX");
    }

    public void selectTermCondictionModal() {
        tapElement("BUKAMOBIL_PROPOSAL_BOOKINGFEE_CHECKBOX");
        validateValue().equals("true", getElementValue("BUKAMOBIL_PROPOSAL_BOOKINGFEE_CHECKBOX"));
        tapElement("BUKAMOBIL_PROPOSAL_PRIVACY_CHECKBOX");
        validateValue().equals("true", getElementValue("BUKAMOBIL_PROPOSAL_PRIVACY_CHECKBOX"));
    }

    public void tapAjukanBtn() {
        tapElement("BUKAMOBIL_PROPOSAL_AJUKAN_BTN");
        HelperData.setLastActionPage(new BukaMobilProposalPage(iosDriver));
    }

    public void validateSubmitProposal() {
        waitForVisibilityOf("BUKAMOBIL_PROPOSAL_SUBMIT_INFO", 10);
    }

    public void tapBayarBookingFeeBtn() {
        tapElement("BUKAMOBIL_PROPOSAL_BAYAR_BOOKING_FEE_BTN");
    }

    public void validatePaymentMethod() {
        waitForVisibilityOf("checkout_non_marketplace_pilih_metode_text");
    }
}
