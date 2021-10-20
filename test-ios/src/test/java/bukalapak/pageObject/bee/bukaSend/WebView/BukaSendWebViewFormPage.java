package bukalapak.pageObject.bee.bukaSend.WebView;

import bukalapak.data.CheckoutData;
import bukalapak.data.HelperData;
import bukalapak.data.bukasend.FormData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class BukaSendWebViewFormPage extends BasePage {
    public BukaSendWebViewFormPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateFormBukasendPage() {
        verifyElementExist("BUKASEND_WEBVIEW_SENDER_ADDRESS_BUTTON");
    }

    public void clickSenderAddressButton() {
        tapElement("BUKASEND_WEBVIEW_SENDER_ADDRESS_BUTTON");
    }

    public void selectSenderAddress(String address) {
        FormData.setSenderName(address);
        changeContext().toWebview();
        verifyElementExist("BUKASEND_WEBVIEW_SENDER_HEADER");
        webViewTapOnElement(constructLocator("BUKASEND_WEBVIEW_CONTAINS", address));
    }

    public void clickSelectButton() {
        tapElement("BUKASEND_WEBVIEW_SELECT_BUTTON");
    }

    public void clickReceiverButton() {
        webViewTapOnElement("BUKASEND_WEBVIEW_RECEIVER_BUTTON");
    }

    public void selectInputManual() {
        verifyElementExist("BUKASEND_WEBVIEW_RECEIVER_MANUAL");
        tapElement("BUKASEND_WEBVIEW_RECEIVER_MANUAL");
    }

    public void inputReceiverName(String address) {
        waitForVisibilityOf("BUKASEND_WEBVIEW_RECEIVER_INPUT");
        FormData.setReceiverName(address);
        webViewTypeElementValue("BUKASEND_WEBVIEW_RECEIVER_INPUT", FormData.getReceiverName());
    }

    public void inputPhoneNo(String phoneNo) {
        FormData.setReceiverPhone(phoneNo);
        webViewTypeElementValue("BUKASEND_WEBVIEW_RECEIVER_PHONENO", phoneNo);
    }

    public void inputPostalCode(String postalCode) {
        FormData.setPostalCode(postalCode);
        webViewTypeElementValue("BUKASEND_WEBVIEW_RECEIVER_POSTAL", postalCode);
    }

    public void inputAddress(String address) {
        FormData.setReceiverAddress(address);
        verifyElementExist("BUKASEND_WEBVIEW_RECEIVER_ADDRESS");
        webViews().swipeIntoElement("BUKASEND_WEBVIEW_RECEIVER_ADDRESS");
        webViewTypeElementValue("BUKASEND_WEBVIEW_RECEIVER_ADDRESS", address);
    }

    public void clickContinueButton() {
        webViews().swipeIntoElement("BUKASEND_WEBVIEW_CONTINUE_BUTTON");
        waitFor(2);
        webViewTapOnElement("BUKASEND_WEBVIEW_CONTINUE_BUTTON");
    }

    public void inputWeight(String weight) {
        FormData.setWeight(weight);
        webViewTypeElementValue("BUKASEND_WEBVIEW_WEIGHT_INPUT", weight);
    }

    public void selectIsiPaket(String option) {
        webViewTapOnElement("BUKASEND_WEBVIEW_ISI_PAKET_DROPDOWN");
        webViewTapOnElement(constructLocator("BUKASEND_WEBVIEW_CONTAINS", option));
    }

    public void inputNotes(String notes) {
        FormData.setNotesForm(notes);
        webViewSwipeToElement("BUKASEND_WEBVIEW_NOTES_INPUT");
        webViewTypeElementValue("BUKASEND_WEBVIEW_NOTES_INPUT", notes);
    }

    public void selectSendingType(String type) {
        switch (type) {
            case "dropoff":
                webViewTapOnElement("BUKASEND_WEBVIEW_DROPOFF");
                break;
            case "pickup":
                webViewTapOnElement("BUKASEND_WEBVIEW_PICKUP");
                break;
            default:
                Assert.fail("option not found!");
        }
    }

    public void selectCourier(String courier) {
        FormData.setCourier(courier);
        verifyElementExist("BUKASEND_WEBVIEW_SELECT_COURIER");
        webViewTapOnElement("BUKASEND_WEBVIEW_SELECT_COURIER");
        webViewTapOnElement(constructLocator("BUKASEND_WEBVIEW_CONTAINS", courier));
        webViewTapOnElement("BUKASEND_WEBVIEW_SELECT_COURIER_BUTTON");
    }

    /**
     * Option Insurance BukaSend automatic selected as default
     **/
    public void getInsuranceTrx() {
        String insurance = getTextFromElement("BUKASEND_WEBVIEW_INSURANCE_TXT");
        FormData.setInsurance(insurance);
    }

    public void getPriceCourier() {
        String price = getTextFromElement("BUKASEND_WEBVIEW_GET_PRICE_COURIER");
        FormData.setPriceBukasend(price);
    }

    public void goToSummaryPage() {
        webViewTapOnElement("BUKASEND_WEBVIEW_SUMMARY_BUTTON");
    }

    public void validateSummaryPage() {
        webViews().swipeIntoElement("BUKASEND_WEBVIEW_HEADER_SUMMARY");
        verifyElementExist(constructLocator("BUKASEND_WEBVIEW_CONTAINS", FormData.getReceiverName()));
        verifyElementExist(constructLocator("BUKASEND_WEBVIEW_CONTAINS", FormData.getReceiverAddress()));
        verifyElementExist(constructLocator("BUKASEND_WEBVIEW_CONTAINS", FormData.getReceiverPhone()));
        verifyElementExist(constructLocator("BUKASEND_WEBVIEW_CONTAINS", FormData.getCourier()));
        verifyElementExist(constructLocator("BUKASEND_WEBVIEW_CONTAINS", FormData.getNotesForm()));
        verifyElementExist(constructLocator("BUKASEND_WEBVIEW_CONTAINS", FormData.getPostalCode()));
        verifyElementExist(constructLocator("BUKASEND_WEBVIEW_CONTAINS", FormData.getSenderName()));
        verifyElementExist(constructLocator("BUKASEND_WEBVIEW_CONTAINS", FormData.getPriceBukasend()));
    }

    public void goToCheckoutPage() {
        webViewTapOnElement("BUKASEND_WEBVIEW_CONTINUE_TO_CHECKOUT");
    }

    public void validatePriceCheckout() {
        waitForVisibilityOf("BUKASEND_WEBVIEW_CHECKOUT_HEADER", 8);
        webViewSwipeToElement("BUKASEND_WEBVIEW_VALUE_ONGKIR_TEXT");
        validateValue().equals(getTextFromElement("BUKASEND_WEBVIEW_VALUE_ONGKIR_TEXT"), FormData.getPriceBukasend());
    }

    public void chooseMetodePembayaran(String args) {

        webViewTapOnElement(constructLocator("BUKASEND_WEBVIEW_CONTAINS", args));
    }

    public void clickBayarButton() {
        webViewTapOnElement("BUKASEND_WEBVIEW_BAYAR_BUTTON");
        if (isElementExist("BUKASEND_WEBVIEW_KONFIRMASI_TELPON_POPUP")) {
            webViewTapOnElement("BUKASEND_WEBVIEW_KONFIRMASI_TELPON_POPUP");
        }
        CheckoutData.setNomorPesanan(getTextFromElement("BUKASEND_WEBVIEW_NOMOR_PESANAN_TXT"));
    }

    public void clickDetailPesananButton() {
        webViewSwipeToElement("BUKASEND_WEBVIEW_LIHAT_DETAIL_PESANAN_BUTTON");
        webViewTapOnElement("BUKASEND_WEBVIEW_LIHAT_DETAIL_PESANAN_BUTTON");
    }

    public void validateTrxBukaSendInvoice() {
        String txtNomorPesanan = CheckoutData.getNomorPesanan();
        waitForVisibilityOf(constructLocator("BUKASEND_WEBVIEW_TRX_LIST_BUKASEND_TXT", txtNomorPesanan), 8);
        webViewTapOnElement(constructLocator("BUKASEND_WEBVIEW_TRX_LIST_BUKASEND_TXT", txtNomorPesanan));
        int totalfeeExpected = Integer.parseInt(FormData.getInsurance().
                replace("Rp", "")) + Integer.parseInt(FormData.getPriceBukasend().
                replace(".", "").replace("Rp", ""));
        int totalfeeActual = Integer.parseInt(getTextFromElement(constructLocator("BUKASEND_WEBVIEW_PRICE_ONGKIR_TXT",
                txtNomorPesanan)).replace(".", "").replace("Rp", ""));
        validateValue().equals(totalfeeExpected,totalfeeActual );
        HelperData.setLastActionPage(new BukaSendWebViewFormPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
