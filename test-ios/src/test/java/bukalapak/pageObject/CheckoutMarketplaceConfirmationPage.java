package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.MtxData;
import bukalapak.data.TransactionData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;


public class CheckoutMarketplaceConfirmationPage extends BasePage {

    public CheckoutMarketplaceConfirmationPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnCheckoutMarketplaceConfirmationPage() {
        waitForVisibilityOf("checkout_marketplace_confirmation_info_text", 40);
        HelperData.setLastActionPage(new CheckoutMarketplaceConfirmationPage(iosDriver));
    }

    public void clickLihatTagihanPembayaran() {
        String elementTarget = "checkout_marketplace_confirmation_lihat_tagihan_pembayaran_button";
        if (MtxData.getPaymentMethod().matches("Transfer Bank Manual|Transfer Bank")) {
            elementTarget = "checkout_marketplace_confirmation_lihat_tagihan_pembayaran_trf_button";
        } else if (MtxData.getPaymentMethod().matches("Transfer Bank Otomatis|gerai")) {
            elementTarget = constructLocator("checkout_marketplace_label_containts_text", "Lihat Detail Pesanan");
        }

        swipeUpToElement(elementTarget);
        tapCenterOfElement(elementTarget, 5);
        HelperData.setLastActionPage(new CheckoutMarketplaceConfirmationPage(iosDriver));
    }

    public void checkRecommendationPanelAppear() {
        assertTrue(isElementVisible("checkout_marketplace_confirmation_reco_panel", 10), "Recommendation panel is not appear");
        HelperData.setLastActionPage(new CheckoutMarketplaceConfirmationPage(iosDriver));
    }

    public void validateTotalTagihan(String transactionType, int totalTagihan) {
        if (transactionType.contains("cross-selling")) {
            assertEquals(totalTagihan, getIntegerFromTextElement("checkout_marketplace_confirmation_total_payment"), "Total transaction doesn't match!");
        }
    }

    public void validateTotalTagihan(String tagihan) {
        int totalTagihan = parseIntegerFromText(tagihan);
        waitForVisibilityOf("checkout_marketplace_confirmation_total_payment",60);
        assertEquals(totalTagihan, getIntegerFromTextElement("checkout_marketplace_confirmation_total_payment"), "Total transaction doesn't match!");
    }

    public void validateInvoiceNoOnBottomSection() {
        swipeUpToElement("checkout_marketplace_confirmation_total_tagihan");
        assertTrue(isElementVisible("checkout_marketplace_confirmation_total_tagihan", 60), "Invoice Number BL**** is not appear");
    }

    public int getTotalTagihanTextView() {
        return getIntFromRp(getText("checkout_marketplace_confirmation_total_payment"));
    }

    public void isOnSuccessPaymentPage() {
        if (isElementExist("checkout_marketplace_confirmation_total_payment",10)) {
            TransactionData.setTotalTagihanTransfer(getTotalTagihanTextView());
        }
        swipeUpToElement("payment_detail_order_button");
        validateExist("payment_detail_order_button", 10);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
