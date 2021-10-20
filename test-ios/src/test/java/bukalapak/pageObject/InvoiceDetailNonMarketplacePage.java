package bukalapak.pageObject;

import bukalapak.data.*;
import bukalapak.data.vp.prepaid.PulsaPrabayarData;
import bukalapak.data.vp.tix.TiketKeretaData;
import bukalapak.data.vp.tix.TiketPesawatData;
import com.bukalapak.salad.util.Direction;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;

/**
 * @Author: Ayu Musfita
 * @Date: 28/12/18, Fri
 **/
public class InvoiceDetailNonMarketplacePage extends BasePage {

    public InvoiceDetailNonMarketplacePage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnInvoiceDetailPage() {
        waitForVisibilityOf("invoice_non_marketplace_detail_tagihan_header", 20);
        HelperData.setLastActionPage(new InvoiceDetailNonMarketplacePage(iosDriver));
    }

    public void userOnOldInvoiceDetailPage() {
        waitForVisibilityOf("invoice_non_marketplace_detail_old_tagihan_header", 20);
        HelperData.setLastActionPage(new InvoiceDetailNonMarketplacePage(iosDriver));
    }

    public void clickLihatTagihanPembayaran() {
        if (MtxData.getPaymentMethod().equals("Transfer Bank Manual") || MtxData.getPaymentMethod().equals("Transfer Bank")) {
            swipeUpToElement("checkout_marketplace_label_detail_tagihan");
            tapElement("checkout_marketplace_label_detail_tagihan");
        }
        else {
            swipeUpToElement("checkout_marketplace_confirmation_lihat_tagihan_pembayaran_button");
            tapElement("checkout_marketplace_confirmation_lihat_tagihan_pembayaran_button");
        }
        HelperData.setLastActionPage(new CheckoutMarketplaceConfirmationPage(iosDriver));
    }

    public void validateTotalTagihan() {
        waitForVisibilityOf("invoice_non_marketplace_total_pesanan", 30);
        assertEquals(TransactionData.getTotalPrice(), getElementLabel("invoice_non_marketplace_total_pesanan"));
    }

    public void clickUbahMetodePembayaran() {
        tapElement("invoice_non_marketplace_ubah_button");
        waitForVisibilityOf("checkout_non_marketplace_pilih_metode_text");
    }

    public void clickButtonUbah() {
        swipeDownToElement("invoice_non_marketplace_ubah_button");
        tapElement("invoice_non_marketplace_ubah_button");
    }

    public void clickLanjutPembayaran(String paymentMethod) {
        swipeDownToElement("invoice_non_marketplace_lanjut_pembayaran_button");
        tapElement("invoice_non_marketplace_lanjut_pembayaran_button");
        if (paymentMethod.equals("bcaKlikPay")) {
            waitForVisibilityOf("tagihan_bcaKlikPay_text");
        }
    }

    //PRIO - Pembeli Prioritas
    public void checkPriorityBuyerInvoiceFromLanding() {
        tapElement("infromasi_tagihan_text");
        verifyElementExist("total_tagihan_price_text");
        verifyElementExist("harga_total_belanja_price_text");
    }

    public void verifyCopyFunctionOfVANumber() {
        swipeUpToElement("invoice_non_marketplace_salin_va_text");
        copyAndVerify("invoice_non_marketplace_salin_va_text", "invoice_non_marketplace_va_text");
    }

    private void copyAndVerify(String copyButtonLocator, String elementToBeCopied) {
        tapElement(copyButtonLocator);
        verifyCopiedText(getTextFromElement(elementToBeCopied).replaceAll("[^0-9]", ""));
    }

    //BukaReksa
    public void verifyBukaReksaInvoiceDetail(String paymentMethod) {
        boolean goalInvestmentActive = InvestmentData.getBeliRutinActivation();
        swipeToDirection(Direction.UP);
        waitForVisibilityOf("tagihan_informasi_tagihan_text");

        if (paymentMethod.equals("bukadompet")) {
            tapElement("tagihan_informasi_tagihan_text");
            verifyElementExist("tagihan_dibayar_text");
        }
        assertTrue(InvestmentData.getInvestmentNominal().
                        equals(TransactionData.getTotalPrice()), "Total pembelian berbeda");
        verifyElementExist("tagihan_" + paymentMethod + "_text");

        if (paymentMethod.equals("transfer")) {
            verifyElementExist("tagihan_kode_pembayaran_text");
        }

        swipeDownToElement("tagihan_produk_bukareksa_text");

        assertEquals("nama produk tidak sama", InvestmentData.getInvestmentProduct(),
                getTextFromElement("tagihan_produk_bukareksa_text"));

        swipeDownToElement("invoice_non_marketplace_bukareksa_keterangan_text");

        if (goalInvestmentActive) {
            verifyElementExist("invoice_non_marketplace_bukareksa_belirutin_text");
        } else {
            verifyElementNotExist("invoice_non_marketplace_bukareksa_belirutin_text");
        }
    }

    public void verifyInvoiceStateIsMatch(String state) {
        verifyElementDisplayed("tagihan_"+state.replaceAll(" ", "_").toLowerCase()+"_text");
    }

    // Premium Seller
    public void userOnDetailTagihanPremiumPage() {
        userOnInvoiceDetailPage();
        verifyElementExist("invoice_premium_title_tab");
        verifyElementExist("invoice_premium_pilihan_paket_text");
        assertTrue(getElementValue("invoice_premium_package_name_text").matches("(Basic|Professional|Platinum) \\(\\d+ hari\\)"));
        verifyElementExist("invoice_premium_masa_aktif_text");
        verifyElementExist("invoice_premium_status_pesanan_text");
    }

    // Virtual Product
    public void validateTextInvoiceId(String invoiceId) {
        validateExist(constructLocator("invoice_non_marketplace_no_pesanan", invoiceId), 15);
    }

    public void validateTextInvoiceStatus(String invoiceStatus){
        assertEquals(invoiceStatus.toLowerCase(), getElementValue("invoice_non_marketplace_status_pesanan").toLowerCase());
        HelperData.setLastActionPage(new InvoiceDetailNonMarketplacePage(iosDriver));
    }

    public void validateTextTotalTagihan(String price) {
        assertEquals(price, getTextFromElement("invoice_non_marketplace_total_pesanan"));
    }

    public void verifyPackageTypeIsMatch() {
        swipeUpToElement(constructLocator("invoice_non_marketplace_package_name", PrepaidData.getProductName()));
        verifyElementDisplayed(constructLocator("invoice_non_marketplace_package_name", PrepaidData.getProductName()));
    }

    public void verifyCustomerIDIsMatch() {
        swipeUpToElement(constructLocator("invoice_non_marketplace_meter_number", PrepaidData.getIdPelanggan()));
        verifyElementDisplayed(constructLocator("invoice_non_marketplace_meter_number", PrepaidData.getIdPelanggan()));
    }

    public void verifyPdamCustomerIDIsMatch() {
        swipeUpToElement(constructLocator("invoice_non_marketplace_pdam_customer_number", PostpaidData.getCustomerNumber()));
        verifyElementDisplayed(constructLocator("invoice_non_marketplace_pdam_customer_number", PostpaidData.getCustomerNumber()));
    }

    public void verifyElectricityCustomerNumberIsMatch() {
        swipeUpToElement(constructLocator("invoice_non_marketplace_electricity_customer_number", dotenv.get("ELECTRICITY_POSTPAID_NUMBER")));
        verifyElementDisplayed(constructLocator("invoice_non_marketplace_electricity_customer_number", dotenv.get("ELECTRICITY_POSTPAID_NUMBER")));
        HelperData.setLastActionPage(new CheckoutMarketplaceConfirmationPage(iosDriver));
    }

    public void verifyInsurancePaymentCustomerNumberIsMatch() {
        swipeUpToElement("invoice_non_marketplace_insurance_payment_customer_number");
        assertEquals(PostpaidData.getCustomerNumber(), getTextFromElement("invoice_non_marketplace_insurance_payment_customer_number"));
    }

    public void verifyPhoneNumberCreditIsMatch() {
        swipeUpToElement(constructLocator("invoice_non_marketplace_pulsa_number", dotenv.get("POSTPAID_PHONE_NUMBER")));
        assertEquals(PostpaidData.getPhoneNumber(), getText("invoice_non_marketplace_pulsa_number"));
        HelperData.setLastActionPage(new CheckoutMarketplaceConfirmationPage(iosDriver));
    }

    public void verifyDetailBepergianInsuranceIsCorrect() {
        verifyElementDisplayed("invoice_non_marketplace_bepergian_insurance_type");
    }

    public void verifyDetailMotorInsuranceIsCorrect() {
        verifyElementDisplayed("invoice_non_marketplace_insurance_type");
    }

    public void verifyTelkomProductIsTrue() {
        verifyElementDisplayed("invoice_non_marketplace_telkom_product_name");
        HelperData.setLastActionPage(new CheckoutMarketplaceConfirmationPage(iosDriver));
    }

    public void verifyKartuKreditProductIsTrue() {
        verifyElementDisplayed("invoice_non_marketplace_kartu_kredit_name");
        HelperData.setLastActionPage(new CheckoutMarketplaceConfirmationPage(iosDriver));
    }

    public void verifyPBBProductIsTrue() {
        swipeUpToElement(constructLocator("invoice_non_marketplace_pbb_customer_number", dotenv.get("PBB_VALID_NOP")));
        verifyElementDisplayed(constructLocator("invoice_non_marketplace_pbb_customer_number", dotenv.get("PBB_VALID_NOP")));
        HelperData.setLastActionPage(new CheckoutMarketplaceConfirmationPage(iosDriver));
    }

    public void validateTravelInsurancePriceCorrect(String product) {
        swipeDownToElement("invoice_non_marketplace_travel_insurance_price");
        int insurancePrice = product.equals("train") ? TiketKeretaData.getInsurancePrice() : TiketPesawatData.getTotalInsurancePrice();

        validateValue().equals(insurancePrice, getIntFromRp(getText("invoice_non_marketplace_travel_insurance_price")));
    }

    public void validateTravelInsuranceSectionShown() {
        swipeUpToElement("invoice_non_marketplace_travel_insurance_lihat_manfaat");
        verifyElementDisplayed("invoice_non_marketplace_travel_insurance_nikmati_perjalanan");
    }

    public void validateTravelInsuranceStatus(String status) {
        validateValue().equals(status.toLowerCase(), getText("invoice_non_marketplace_travel_insurance_status").toLowerCase());
    }

    public void tapOnLihatManfaatButton() {
        tapElement("invoice_non_marketplace_travel_insurance_lihat_manfaat");
    }

    public void validateTravelInsuranceDialogShown() {
        verifyElementDisplayed("invoice_non_marketplace_travel_insurance_manfaat_asuransi");
        verifyElementDisplayed("invoice_non_marketplace_travel_insurance_cara_klaim");
    }

    public void validatePackagePriceAndName() {
        waitForVisibilityOf("invoice_non_marketplace_pulsa_price", 15);
        assertEquals(getElementLabel("invoice_non_marketplace_pulsa_price"), PulsaPrabayarData.getPackagePrice());
        swipeUpToElement("invoice_non_marketplace_pulsa_name");
        assertEquals(getElementLabel("invoice_non_marketplace_pulsa_name"), PulsaPrabayarData.getPackageName());
    }

    public void verifyDetailIsPremiAsuransi() {
        verifyElementDisplayed("invoice_non_marketplace_premi_asuransi");
    }

    public void verifyDetailAsuransiTambahan() {
        swipeUpToElement("INVOICE_ASURANSI_TAMBAHAN_INSURANCE_TYPE");
        verifyElementDisplayed("INVOICE_ASURANSI_TAMBAHAN_INSURANCE_TYPE");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        if(isElementVisible("home_review_close_button")) {
            tapElement("home_review_close_button");
        }
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void verifyPaymentMethodVP(String paymentMethod) {
        switch (paymentMethod) {
            case "Credit Card":
                verifyElementExist("tagihan_credit_card_text");
                break;
            case "Kredivo":
                verifyElementExist("tagihan_kredivo_text");
                break;
            case "Pos Indonesia":
                verifyElementExist("tagihan_pos_text");
                break;
            case "Transfer Bank":
                verifyElementExist("tagihan_transfer_text");
                break;
            case "Virtual Account":
                verifyElementExist("tagihan_va_text");
                break;
            default:
                LogUtil.error("Payment method " + paymentMethod + " is not found");
        }
    }

    public void validateTrxRefunded() {
        waitForVisibilityOf("invoice_non_marketplace_insurance_payment_info_transaksi_text",10);
        for (int i = 0; i < 5; i++) {
            if (!isElementVisible("invoice_non_marketplace_phone_number_text",5)) {
                swipeUpToElement("invoice_non_marketplace_phone_number_text");
            }
            if (isElementVisible("tagihan_dibatalkan_text",5)) {
                break;
            }
            doPullRefresh(2);
        }
        verifyElementExist("tagihan_dibatalkan_text");
    }

    public void tapUbahPembayaran() {
        swipeUpToElement("invoice_non_marketplace_ubah_pembayaran_button");
        tapElement("invoice_non_marketplace_ubah_pembayaran_button");
    }
}
