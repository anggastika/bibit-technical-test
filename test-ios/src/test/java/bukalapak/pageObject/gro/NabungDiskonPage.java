package bukalapak.pageObject.gro;

import bukalapak.data.HelperData;
import bukalapak.data.NabungDiskonData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.Direction;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class NabungDiskonPage extends BasePage {

    public NabungDiskonPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateNabungDiskonPage() {
        changeContext().toWebview();
        validateExist("BANNER_CONTAINER", 25);
        validateExist("NABUNG_DISKON_IMAGE", 10);
        validateExist("HOWTO_CONTAINER", 10);
        validateExist("TITLE_CONTAINER", 10);
        validateExist("FAQ_CONTAINER", 10);
        HelperData.setLastActionPage(new NabungDiskonPage(iosDriver));
    }

    public void validateHowToPlaySection() {
        validateExist("HOWTO_STEP_CLASS", 10);
        validateExist(constructLocator("HOWTO_STEP_ICON", 1), 10);
        validateExist(constructLocator("HOWTO_STEP_ICON", 2), 10);
        validateExist(constructLocator("HOWTO_STEP_ICON", 3), 10);
        validateExist("STEP_ONE_TEXT", 10);
        validateExist("STEP_TWO_TEXT", 10);
        validateExist("STEP_THREE_TEXT", 10);
        HelperData.setLastActionPage(new NabungDiskonPage(iosDriver));
    }

    public void validateFAQSection() {
        validateExist("FAQ_TEXT", 10);
        validateExist("FAQ_LIST", 10);
        HelperData.setLastActionPage(new NabungDiskonPage(iosDriver));
    }

    public void validateProductList() {
        validateExist(constructLocator("NABUNG_DISKON_PRODUCT_CURRENT_PRICE_LIST", 1), 10);
        validateExist(constructLocator("NABUNG_DISKON_PRODUCT_DISCOUNTED_PRICE", 1), 10);
        validateExist(constructLocator("NABUNG_DISKON_PRODUCT_GUARANTEE", 1), 10);
        validateExist(constructLocator("NABUNG_DISKON_PRODUCT_DISCOUNT_PERCENTAGE", 1), 10);
        validateExist(constructLocator("NABUNG_DISKON_PRODUCT_ORIGINAL_PRICE", 1), 10);
        validateElementWithText(constructLocator("NABUNG_DISKON_PRODUCT_TITLE_LIST", 1), NabungDiskonData.getProductName());
        validateElementContainsText(constructLocator("NABUNG_DISKON_PRODUCT_TENOR_LIST", 1), Integer.toString(NabungDiskonData.getProductTenor()));
        if (NabungDiskonData.getProductCurrentStock() != 0) {
            validateExist(constructLocator("NABUNG_DISKON_PRODUCT_IMAGE", 1), 10);
            validateExist(constructLocator("NABUNG_DISKON_PRODUCT_MULAI_NABUNG_BUTTON", 1), 10);
        } else {
            LogUtil.warn("No active product on the list");
        }
        HelperData.setLastActionPage(new NabungDiskonPage(iosDriver));
    }

    public void validateAllProductCard() {
        int productCard = getElementsPresent("NABUNG_DISKON_ALL_CARD_PRODUCT").size();
        int expectedProductCard = NabungDiskonData.getTotalProduct();

        while (productCard < expectedProductCard) {
            LogUtil.info("Product Card : " + productCard);
            webViewSwipeToElement(constructLocator("NABUNG_DISKON_CARD_PRODUCT", productCard));
            productCard = getElementsPresent("NABUNG_DISKON_ALL_CARD_PRODUCT").size();
        }
        validateExist(constructLocator("NABUNG_DISKON_CARD_PRODUCT", productCard));
        HelperData.setLastActionPage(new NabungDiskonPage(iosDriver));
    }

    public void validateDisableProductCard() {
        validateExist("NABUNG_DISKON_PRODUCT_IMAGE_DISABLE", 20);
        validateExist("NABUNG_DISKON_PRODUCT_HABIS_BUTTON", 20);
        HelperData.setLastActionPage(new NabungDiskonPage(iosDriver));
    }

    public void validateMulaiNabungButtonProductCard() {
        validateExist(constructLocator("NABUNG_DISKON_PRODUCT_MULAI_NABUNG_BUTTON", 1), 10);
        HelperData.setLastActionPage(new NabungDiskonPage(iosDriver));
    }

    public void tapMulaiNabungButtonProductCard() {
        tapElement(constructLocator("NABUNG_DISKON_PRODUCT_MULAI_NABUNG_BUTTON", 1));
    }

    public void validateNabungDiskonPdp() {
        validateExist("NABUNG_DISKON_PDP_CLASS", 20);
        validateExist("NABUNG_DISKON_PRODUCT_IMAGE_PDP", 20);
        validateExist("NABUNG_DISKON_PRODUCT_DISCOUNT_PDP", 20);
        validateExist("NABUNG_DISKON_PRODUCT_TENOR_COUNT", 20);
        validateExist("NABUNG_DISKON_PRODUCT_CURRENT_PRICE_PDP", 20);
        validateExist("NABUNG_DISKON_PRODUCT_TENOR_PRICE_PDP", 20);
        validateExist("NABUNG_DISKON_PRODUCT_ORIGINAL_PRICE_PDP", 20);
        validateExist("NABUNG_DISKON_PRODUCT_TITTLE_PDP", 20);
        validateExist("NABUNG_DISKON_PRODUCT_INSTALLMENT_PDP", 20);
        validateExist("NABUNG_DISKON_PRODUCT_DETAIL_CONTAINER", 20);
        validateExist("NABUNG_DISKON_PAYMENT_CONTAINER", 20);
        validateExist("NABUNG_DISKON_MULAI_NABUNG_BUTTON", 20);
        HelperData.setLastActionPage(new NabungDiskonPage(iosDriver));
    }

    public void tapNabungDiskonPdpBackButton() {
        validateExist("NABUNG_DISKON_PDP_BACK_BUTTON", 20);
        tapElement("NABUNG_DISKON_PDP_BACK_BUTTON");
    }

    public void validateViewMore() {
        validateExist("NABUNG_DISKON_LIHAT_SELENGKAPNYA_TEXT", 20);
    }

    public void tapViewMore() {
        tapElement("NABUNG_DISKON_LIHAT_SELENGKAPNYA_TEXT");
    }

    public void validateProductInformationSheet() {
        validateExist("NABUNG_DISKON_SHEET_NAVBAR", 20);
        validateExist("NABUNG_DISKON_PAYMENT_STOCK", 20);
        validateExist("NABUNG_DISKON_SPECIFICATION", 20);
        validateExist("NABUNG_DISKON_DESCRIPTION_TEXT", 20);
        HelperData.setLastActionPage(new NabungDiskonPage(iosDriver));
    }

    public void validatePaymentMethodOnPdp() {
        validateExist("NABUNG_DISKON_PAYMENT_METHOD_BUTTON", 20);
        HelperData.setLastActionPage(new NabungDiskonPage(iosDriver));
    }

    public void tapPaymentMethodOnPdp() {
        tapElement("NABUNG_DISKON_PAYMENT_METHOD_BUTTON");
    }

    public void validateAllPaymentMethod() {
        validateExist("NABUNG_DISKON_PILIH_PEMBAYARAN_TEXT", 20);
        validateExist("NABUNG_DISKON_PAYMENT_SHEET", 20);
        validateExist("NABUNG_DISKON_PAYMENT_DANA", 20);
        validateExist("NABUNG_DISKON_PAYMENT_DEPOSIT", 20);
        validateExist("NABUNG_DISKON_PAYMENT_VA", 20);
        HelperData.setLastActionPage(new NabungDiskonPage(iosDriver));
    }

    public void tapMulaiNabungButton() {
        tapElement("NABUNG_DISKON_MULAI_NABUNG_BUTTON");
    }

    public void tapSetujuNabungDiskon() {
        waitForVisibilityOf("NABUNG_DISKON_KETENTUAN_NABUNG_SHEET", 15);
        waitForVisibilityOf("NABUNG_DISKON_AGREE_BUTTON", 20);
        tapElement("NABUNG_DISKON_AGREE_BUTTON");
    }

    public void validatePhoneConfirmSheet() {
        validateExist("NABUNG_DISKON_KONFIRMASI_NO_HP_TEXT", 20);
        validateExist("NABUNG_DISKON_CONFIRM_BUTTON", 20);
        HelperData.setLastActionPage(new NabungDiskonPage(iosDriver));
    }

    public void fillPhoneNumberNabungDiskon() {
        waitForVisibilityOf("NABUNG_DISKON_PHONE_NUMBER_FIELD", 10);
        typeValue("NABUNG_DISKON_PHONE_NUMBER_FIELD", "0856863495293");
    }

    public void tapCloseIconSheet() {
        waitForVisibilityOf("NABUNG_DISKON_CLOSE_ICON", 20);
        tapElement("NABUNG_DISKON_CLOSE_ICON");
    }

    public void validatePhoneConfirmSheetDisappear() {
        waitFor(5);
        validateNotDisplayed("NABUNG_DISKON_KONFIRMASI_NO_HP_TEXT", "Popup Phone Confirmation Still Exist");
        validateNotDisplayed("NABUNG_DISKON_CONFIRM_BUTTON");
        validateNotDisplayed("NABUNG_DISKON_PHONE_NUMBER_FIELD");
        HelperData.setLastActionPage(new NabungDiskonPage(iosDriver));
    }

    public void tapRiwayatNabungDiskon() {
        validateExist("NABUNG_DISKON_RIWAYAT_LINK_TEXT", 10);
        tapElement("NABUNG_DISKON_RIWAYAT_LINK_TEXT");
    }

    public void validateRiwayatPage() {
        validateExist("NABUNG_DISKON_RIWAYAT_TITLE", 20);
        validateExist("NABUNG_DISKON_TRANSACTION_CARD", 10);
        validateExist("NABUNG_DISKON_TRANSACTION_CARD_IMAGE", 10);
        validateExist("NABUNG_DISKON_TRANSACTION_CARD_CONTAINER", 10);
        HelperData.setLastActionPage(new NabungDiskonPage(iosDriver));
    }

    public void validateStatusCardRiwayat(String status) {
        int swipeCount = 1;
        while (!isElementExist(constructLocator("NABUNG_DISKON_TRANSACTION_CARD_STATE", status), 5) && swipeCount <= 5) {
            webViewSwipeToElement("NABUNG_DISKON_TRANSACTION_CARD");
            swipeCount++;
        }
        validateExist(constructLocator("NABUNG_DISKON_TRANSACTION_CARD_STATE", status), 10);
        HelperData.setLastActionPage(new NabungDiskonPage(iosDriver));
    }

    public void tapMulaiNabungButtonProductAvailableVA() {
        int loopIndex = 1;
        int getPriceProduct = 1;
        String priceElementContainsRp = getTextFromElement(constructLocator("NABUNG_DISKON_IMAGE_BANNER", Integer.toString(loopIndex)));

        if (priceElementContainsRp.contains("rb/hari")) {
            getPriceProduct = getIntegerFromTextElement(constructLocator("NABUNG_DISKON_IMAGE_BANNER", Integer.toString(loopIndex)));
        }

        while (getPriceProduct < 10 && loopIndex <= 12) {
            loopIndex++;
            priceElementContainsRp = getTextFromElement(constructLocator("NABUNG_DISKON_IMAGE_BANNER", Integer.toString(loopIndex)));
            if (priceElementContainsRp.contains("rb/hari")) {
                getPriceProduct = getIntegerFromTextElement(constructLocator("NABUNG_DISKON_IMAGE_BANNER", Integer.toString(loopIndex)));
            }
            LogUtil.info("Price : " + Integer.toString(getPriceProduct));
            LogUtil.info("DataIndex : " + Integer.toString(loopIndex));
        }

        webViewSwipeToElement(constructLocator("NABUNG_DISKON_PRODUCT_MULAI_NABUNG_BUTTON", loopIndex));
        tapElement(constructLocator("NABUNG_DISKON_PRODUCT_MULAI_NABUNG_BUTTON", loopIndex));
    }

    public void chooseVAPaymentMethod() {
        validateExist("NABUNG_DISKON_PAYMENT_VA", 5);
        tapElement("NABUNG_DISKON_PAYMENT_VA");
    }

    public void chooseVABank(String bank) {
        validateExist(constructLocator("NABUNG_DISKON_VA_BANK", bank), 10);
        tapElement(constructLocator("NABUNG_DISKON_VA_BANK", bank));
    }

    public void continueBayarWithVA() {
        validateExist("NABUNG_DISKON_VA_BAYAR_BUTTON", 5);
        tapElement("NABUNG_DISKON_VA_BAYAR_BUTTON");
    }

    public void confirmPhoneToContinueBayar() {
        validateExist("NABUNG_DISKON_CONFIRM_BUTTON", 10);
        tapElement("NABUNG_DISKON_CONFIRM_BUTTON");
    }

    public void validatePendingConfirmationCheckoutPage() {
        changeContext().toNative();
        validateExist("BATAS_WAKTU_BAYAR_TEXT", 45);
        validateExist("LIHAT_PESANAN_TEXT_BUTTON", 10);
        validateExist("TOTAL_TEXT", 10);
        HelperData.setLastActionPage(new NabungDiskonPage(iosDriver));
    }

    public void continueLihatDetailPesanan() {
        validateExist("LIHAT_PESANAN_TEXT_BUTTON", 10);
        tapElement("LIHAT_PESANAN_TEXT_BUTTON");
    }

    public void validatePendingStatusInvoiceDetail() {
        validateExist("INVOICE_STATUS_TITLE", 20);
        validateExist("PENDING_STATUS_INVOICE", 10);
        validateExist("UBAH_METODE_BAYAR_TEXT_BUTTON", 10);
        HelperData.setLastActionPage(new NabungDiskonPage(iosDriver));
    }

    public void validatePaymentMehodInvoiceDetail(String paymentMethod) {
        validateExist("PAYMENT_METHOD_INVOICE", 10);
        validateElementContainsText("PAYMENT_METHOD_INVOICE", paymentMethod);
        HelperData.setLastActionPage(new NabungDiskonPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
