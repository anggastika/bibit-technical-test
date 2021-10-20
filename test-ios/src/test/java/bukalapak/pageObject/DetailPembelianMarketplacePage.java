package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.*;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

/**
 * Created by NurdianSetyawan on 10/12/18.
 */
public class DetailPembelianMarketplacePage extends BasePage {

    private final static Logger LOGGER = LogManager.getLogger(DetailPembelianMarketplacePage.class);

    public DetailPembelianMarketplacePage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnDetailPembelianPage() {
        waitForVisibilityOf("detail_pembelian_page_title", 60);
        HelperData.setLastActionPage(new DetailPembelianMarketplacePage(iosDriver));
    }

    public void verifyTotalPaymentIsMatch() {
        waitForVisibilityOf("detail_pembelian_total_tagihan_text", 30);
        assertTrue(TransactionData.getTotalPrice().equals(getElementLabel("detail_pembelian_total_tagihan_text")));
        HelperData.setLastActionPage(new DetailPembelianMarketplacePage(iosDriver));
    }

    public void verifyTotalHargaBarangIsMatch() {
        assertTrue(TransactionData.getTotalPrice().equals(getElementLabel("detail_pembelian_total_harga_barang_text")));
        HelperData.setLastActionPage(new DetailPembelianMarketplacePage(iosDriver));
    }

    public void verifyTotalPaymentMinusUniqueCode() {
        Double uniqueCode = Double.parseDouble(getUniqueCode());
        Double totalBeforeUniqueCode = Double.parseDouble(TransactionData.getTotalPrice());
        Double totalNow = totalBeforeUniqueCode + uniqueCode;
        Double appearedNow = Double.parseDouble(getTotalPaymentInDetailPembelian());
        assertTrue(Math.abs(appearedNow - totalNow) < 0.1);
        HelperData.setLastActionPage(new DetailPembelianMarketplacePage(iosDriver));
    }

    public void verifyTotalPaymentPlusUniqueCode() {
        Double uniqueCode = Double.parseDouble(getUniqueCode());
        Double totalBeforeUniqueCode = Double.parseDouble(TransactionData.getTotalPrice());
        Double totalNow = totalBeforeUniqueCode + uniqueCode;
        Double appearedNow = Double.parseDouble(getTotalPaymentInDetailPembelian());
        assertTrue(Math.abs(appearedNow + totalNow) < 0.1);
        HelperData.setLastActionPage(new DetailPembelianMarketplacePage(iosDriver));
    }

    public String getTotalPaymentInDetailPembelian() {
        String result;
        if (isElementVisible("detail_pembelian_total_tagihan_text")) {
            result = getElementValue("detail_pembelian_total_tagihan_text");
            return result.replaceAll("[^0-9]", "");
        } else {
            return "is not found";
        }
    }

    public String getUniqueCode() {
        String result;
        if (isElementVisible("detail_pembelian_kode_unik_title")) {
            result = getElementValue("detail_pembelian_kode_unik_text");
            return result.replaceAll("[^0-9]", "");
        } else {
            return "0";
        }
    }

    public void verifyPaymentMethod(String arg0) {
        if (isElementExist("dana_no_tagihan", 20)) {
            DANAData.setNoTagihan(getElementLabel("dana_no_tagihan"));
        }
        switch (arg0) {
            case "Indomaret":
                verifyElementExist("detail_pembelian_indomaret_text");
                break;
            case "Alfamart":
                verifyElementExist("detail_pembelian_alfamart_text");
                break;
            case "Transfer ke Virtual Account":
                verifyElementExist("detail_pembelian_transfer_ke_virtual_account_text");
                break;
            case "Pos Indonesia":
                verifyElementExist("detail_pembelian_pos_indonesia_text");
                break;
            case "Mitra Bukalapak":
                verifyElementExist("detail_pembelian_mitra_text");
                break;
            case "BCA KlikPay":
                verifyElementExist("detail_pembelian_bca_klikpay_text");
                break;
            case "LinkAja":
                verifyElementExist("detail_pembelian_linkaja_text");
                break;
            case "CIMB Click":
                verifyElementExist("detail_pembelian_cimb_click_text");
                break;
            case "Transfer Bank":
                verifyElementExist("detail_pembelian_transfer_text");
                break;
            case "Virtual Account":
                verifyElementExist("detail_pembelian_transfer_virtual_account_text");
                break;
            case "DANA - Saldo DANA":
                if (isElementVisible("rating_star")) {
                    try {
                        tapElement("rating_star");
                        waitForVisibilityOf("back_button", 10);
                        tapElement("back_button");
                    } catch (Exception e) {
                        LOGGER.info("Rating not shown up");
                    }
                }
                try {
                    verifyElementExist("DANA_option");
                } catch (Exception e) {
                    tapElement("invoice_detail");
                    verifyElementExist("DANA_option");
                }
                HelperData.setLastActionPage(new DetailPembelianMarketplacePage(iosDriver));
                break;
            case "Transfer Virtual Account":
                verifyElementExist("detail_pembelian_transfer_virtual_account_text");
                break;
            case "Credit Card":
                verifyElementExist("detail_pembelian_credit_card_text");
                break;
            case "BRI EPay":
                verifyElementExist("detail_pembelian_bri_epay_text");
                break;
            case "Kredivo":
                verifyElementExist("detail_pembelian_kredivo_text");
                break;
            case "Akulaku":
                verifyElementExist("detail_pembelian_akulaku_text");
                break;
            case "COD":
                verifyElementExist("detail_pembelian_cod_text");
                verifyElementNotExist("detail_pembelian_ubah_text");
                break;
            case "Saldo":
                verifyElementExist("detail_pembelian_saldo_text");
                break;
            case "BRI Ceria":
                verifyElementExist("detail_pembelian_bri_ceria_text");
                break;
            default:
                LOGGER.error("Payment method " + arg0 + " is not found");
                break;
        }
        HelperData.setLastActionPage(new DetailPembelianMarketplacePage(iosDriver));
    }

    public void tapInformasiTagihan() {
        tapElement("detail_pembelian_informasi_tagihan");
    }

    public void changeAddress() {
        swipeDownToElement("detail_pembalian_alamat_text");
        tapElement("detail_pembelian_ubah_alamat_button");
        allowPopup();
    }

    public void verifyAddressIsExist(String address) {
        swipeToLocator("detail_pembelian_alamat_pengiriman_section");
        verifyElementExist(address);
        HelperData.setLastActionPage(new DetailPembelianMarketplacePage(iosDriver));
    }

    public void verifyCopyFunctionOfBillAndAccountNumber() {
        swipeDownToElement("detail_pembelian_rekening_bca_text");
        copyAndVerify("detail_pembelian_salin_tagihan_text", "detail_pembelian_tagihan_text");
        copyAndVerify("detail_pembelian_salin_rekening_bca_text", "detail_pembelian_no_rekening_bca_text");
        copyAndVerify("detail_pembelian_salin_rekening_bni_text", "detail_pembelian_no_rekening_bni_text");
        copyAndVerify("detail_pembelian_salin_rekening_bri_text", "detail_pembelian_no_rekening_bri_text");
        swipeDownToElement("detail_pembelian_bukabantuan_text");
        copyAndVerify("detail_pembelian_salin_rekening_mandiri_text", "detail_pembelian_no_rekening_mandiri_text");
        copyAndVerify("detail_pembelian_salin_rekening_bsm_text", "detail_pembelian_no_rekening_bsm_text");
    }

    private void copyAndVerify(String copyButtonLocator, String elementToBeCopied) {
        if (isElementVisible(copyButtonLocator)) {
            tapElement(copyButtonLocator);
            verifyCopiedText(getElementValue(elementToBeCopied).replaceAll("[^0-9]", ""));
        }
    }

    public void verifyAdditionalUniqueAmount() {
        Double originalBillAmount = Double.parseDouble(TransactionData.getTotalPrice().replaceAll("[^0-9]", ""));
        Double billWithAdditionalUniqueAmount = Double.parseDouble(getElementLabel("detail_pembelian_total_tagihan_text").replaceAll("[^0-9]", ""));
        assertTrue(originalBillAmount < billWithAdditionalUniqueAmount);
    }

    public void verifyCopyFunctionOfVANumber() {
        swipeUpToElement("detail_pembelian_salin_va_text");
        copyAndVerify("detail_pembelian_salin_va_text", "detail_pembelian_va_text");
    }


    public void verifyJasaPengiriman(String jasaPengiriman) {
        swipeDownToElement("detail_pembelian_lihat_tagihan_pembayaran_button");
        tapElement("detail_pembelian_lihat_tagihan_pembayaran_button");
        swipeDownToElement("detail_pembelian_lihat_detail_text");
        waitForVisibilityOf("detail_pembelian_lihat_detail_text");
        if (isElementVisible("detail_pembelian_not_now_button")) {
            tapElement("detail_pembelian_not_now_button");
        }
        tapElement("detail_pembelian_lihat_detail_text");
        String kurir = getElementValue(constructLocator("detail_pembelian_kurir", jasaPengiriman));
        validateValue().equals(jasaPengiriman, kurir, "Courier is not match");
    }

    public void verifyKodeUnikAmbilSendiri() {
        if (isElementVisible("detail_pembelian_not_now_button")) {
            tapElement("detail_pembelian_not_now_button");
        }
        validateDisplayed(constructLocator("detail_pembelian_kode_unik_ambil_sendiri_text", XPRData.getKodeUnikAmbilSendiri()));
    }

    public void verifyPickupAddress(String pickupAddress) {
        validateDisplayed("detail_pembelian_pickup_address_ambil_sendiri_text");
        validateValue().contains(pickupAddress, getTextFromElement("detail_pembelian_pickup_address_ambil_sendiri_text"));
        XPRData.setPickupLocationAmbilSendiri(pickupAddress);
    }

    public void verifyPetaLokasiPelapak() {
        validateDisplayed("detail_pembelian_peta_lokasi_pelapak_ambil_sendiri_text");
    }

    public void validateStatusPembelianAmbilSendiri(String statusPembelian) {
        waitForVisibilityOf("detail_pembelian_status_pembelian_ambil_sendiri_text", 20);
        validateValue().equals(getTextFromElement("detail_pembelian_status_pembelian_ambil_sendiri_text"), statusPembelian);
        HelperData.setLastActionPage(new DetailPembelianMarketplacePage(iosDriver));
    }

    public void verifyPaidStatusOnInvoice() {
        userOnDetailPembelianPage();
        if (isElementClickable("detail_pembelian_rating_star")) {
            tapElement("detail_pembelian_rating_star");
            waitForVisibilityOf("detail_pembelian_cancel_rating_text", 15);
            tapElement("detail_pembelian_cancel_rating_text");
        }
        if (isElementVisible("detail_pembelian_belum_dibayar_text")) {
            validateValue().contains("MENUNGGU PEMBAYARAN", getElementValue("detail_pembelian_belum_dibayar_text"), "invalid status");
        } else {
            for (int i = 0; i < 10; i++) {
                if (isElementVisible("detail_pembelian_informasi_pesanan")) {
                    tapElement("detail_pembelian_informasi_pesanan");
                    break;
                } else {
                    waitForElementClickable("detail_pembelian_informasi_tagihan", 15);
                    tapElement("detail_pembelian_informasi_tagihan");
                    break;
                }
            }
            if (isElementVisible("detail_pembelian_dibayar_text")) {
                validateValue().contains("Dibayar", getElementValue("detail_pembelian_dibayar_text"), "invalid status");
            }
        }
        HelperData.setLastActionPage(new DetailPembelianMarketplacePage(iosDriver));
    }

    public void clickOnRincianButton() {
        if (isElementVisible("detail_pembelian_rincian_button")) {
            tapElement("detail_pembelian_rincian_button");
        }
    }

    public void validatePriorityCheckoutType(String priorityType) {
        String priorityBuyerPrice = TestInstrument.dotenv.get("PRIO_CROSS_SELLING_PACKAGE_PRICE");
        String maxFreeDelivery = TestInstrument.dotenv.get("PRIO_MAX_PRIORITY_USAGE_PRICE");
        String maxPriorityShippingFeeReduction = TestInstrument.dotenv.get("PRIO_MAX_PRIORITY_USAGE_PRICE");
        assert priorityBuyerPrice != null;
        assert maxFreeDelivery != null;
        assert maxPriorityShippingFeeReduction != null;
        verifyElementExist("detail_pembelian_page_title");
        if (!isElementVisible("detail_pembelian_status_tagihan_text")) {
            tapElement("detail_pembelian_informasi_tagihan");
        }
        if (isElementVisible("detail_pembelian_rincian_button")) {
            tapElement("detail_pembelian_rincian_button");
        }
        if (priorityType.contains("cross-selling")) {
            waitForVisibilityOf("detail_pembelian_harga_priority_text", 20);
            assertTrue(getTextFromElement("detail_pembelian_harga_priority").contains(priorityBuyerPrice),
                    "Cross-selling's Price isn't matched");
        } else {
            verifyElementNotExist("detail_pembelian_harga_priority");
        }
        verifyElementExist("detail_pembelian_benefit_priority_text");
        assertTrue(Math.abs(getIntegerFromTextElement("detail_pembelian_benefit_priority")) <=
                        getIntegerFromTextElement("detail_pembelian_ongkir"),
                "Shipping Fee reduction Price is wrong!");
        assertTrue(Math.abs(getIntegerFromTextElement("detail_pembelian_benefit_priority")) <=
                        parseIntegerFromText(maxFreeDelivery),
                "Shipping Fee reduction Price is more than expected!");
        HelperData.setLastActionPage(new DetailPembelianMarketplacePage(iosDriver));
    }

    private String getErrorPriorityBuyerText() {
        return getText("detail_pembelian_error_priority_buyer");
    }

    public void checkErrorPriorityBuyer(String type, String errorText) {
        if (type.contains("Prioritas")) {
            assertEquals(errorText, getErrorPriorityBuyerText());
        } else {
            Assert.fail("Please check your params!");
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void clickBackFromBukaMart() {
        tapElement("back_from_bukamart");
    }

    public void verifyNoTagihan() {
        waitForVisibilityOf("detail_pembelian_nomor_tagihan_text", 10);
        CSIData.setNomorTagihan(getTextFromElement("detail_pembelian_nomor_tagihan_text").trim());
    }

    public void verifyNoTransaksi() {
        waitForVisibilityOf("detail_pembelian_nomor_transaksi_text2", 10);
        CSIData.setNomorTransaksi(getTextFromElement("detail_pembelian_nomor_transaksi_text2").trim());
    }

    public void userTapTerimaBarangBtn() {
        swipeUpToElement("detail_pembelian_terima_barang_button");
        waitForElementClickable("detail_pembelian_terima_barang_button", 15);
        tapElement("detail_pembelian_terima_barang_button");
    }

    public void userTapUlasBarangBtn() {
        waitForElementClickable("detail_pembelian_ulas_barang_button", 15);
        tapElement("detail_pembelian_ulas_barang_button");
    }

    public void userTapUlasanBtn() {
        waitForElementClickable("ulas_barang_ubah_ulasan_btn", 15);
        tapElement("ulas_barang_ubah_ulasan_btn");
    }

    //Investment
    public void validateAutoInvestAmount() {
        verifyElementExist(constructLocator("detail_pembelian_bukaemas_autoinvest_amount", "Rp" + InvestmentData.getAutoInvestBuyerAmount()));
    }

    public void scrollToAutoInvestSection() {
        for (int i = 0; i < 3; i++) {
            swipeUp(0.8, 0.2);
        }
    }

    public void validateAutoInvestSection() {
        verifyElementExist("detail_pembelian_bukaemas_autoinvest_section_title");
        verifyElementExist("detail_pembelian_bukaemas_autoinvest_section_nominal");
        verifyElementExist("detail_pembelian_bukaemas_autoinvest_section_amount");
        validateValue().equals("Rp" + InvestmentData.getAutoInvestBuyerAmount(), getAutoInvestAmountSection());
    }

    private String getAutoInvestAmountSection() {
        return getText("detail_pembelian_bukaemas_autoinvest_section_amount");
    }

    public void tapAutoInvestSection() {
        tapElement("detail_pembelian_bukaemas_autoinvest_section_amount");
        changeContext().toWebview();
    }

    public void verifyCancelTransactionAndRefunded() {
        userOnDetailPembelianPage();
        swipeDownToElement("detail_pembelian_status_pesanan_text");
        if (isElementVisible("detail_pembelian_dibatalkan_text")) {
            verifyElementExist("detail_pembelian_dibatalkan_text");
        } else {
            verifyElementExist("invoice_detail_status_name");
        }
        HelperData.setLastActionPage(new DetailPembelianMarketplacePage(iosDriver));
    }

    public void verifyTotalPayment() {
        validateValue().equals(TransactionData.getJumlahBayarPlusUniqueAmount(),
                getElementLabel("detail_pembelian_total_tagihan_text").replaceAll("[^0-9]", ""), "Amount doesn't match");
    }

    public void verifyNoPesanan() {
        assertTrue(TransactionData.getInvoiceNo().equals(getElementValue("detail_pembelian_invoice_number_text")));
    }

    public void goToDANACashierPage(String action) {
        String elm = "detail_pembelian_coba_lagi_dana_payment_button";

        if (action.equals("continue payment")) {
            elm = "detail_pembelian_lanjutkan_pembayaran_dana_payment_button";
        }
        for (int i = 0; i < 3; i++) {
            userOnDetailPembelianPage();
            swipeUpToElement(elm);
            tapElement(elm);
            if (isElementVisible("dana_cashier_page_saldo_dana_text", 10)) {
                break;
            } else {
                waitFor(10); //wait loading dana webview
                if (isElementExist("dana_cashier_page_back_button", 10)) {
                    tapElement("dana_cashier_page_back_button");
                } else {
                    tapElement(18, 52);
                }
            }
        }
    }

    public void setInvoiceAddress() {
        TransactionData.setAddress(getElementValue("detail_pembelian_alamat_content_text"));
    }

    public void validateDetailTrxFromCheckout(String flagMultipleProduct, String sectionName) {
        switch (sectionName) {
            case "product name":
                if (flagMultipleProduct == null) {
                    swipeUpToElement("product_name_details_pembelian");
                    assertEquals(CheckoutData.getProductName(),
                            getElementLabel("product_name_details_pembelian"), "product name doesn't match");
                } else {
                    validateMultipleProductOnInvoice(sectionName);
                }
                break;
            case "quantity product":
                if (flagMultipleProduct == null) {
                    swipeUpToElement("product_qty_details_pembelian");
                    assertEquals(CheckoutData.getProductQuantity(),
                            getElementLabel("product_qty_details_pembelian").replace("Jumlah: ", ""), "product quantity doesn't match");
                } else {
                    validateMultipleProductOnInvoice(sectionName);
                }
                break;
            case "product price":
                if (flagMultipleProduct == null) {
                    swipeUpToElement("product_price_details_pembelian");
                    assertEquals(CheckoutData.getProductPrice(),
                            getElementLabel("product_price_details_pembelian"), "product price doesn't match");
                } else {
                    validateMultipleProductOnInvoice(sectionName);
                }
                break;
            case "total payment":
                validateTotalPayment(CheckoutData.getPaymentMethod());
                break;
            case "payment service":
                swipeDownToElement("payment_method_details_pembelian");
                assertEquals(CheckoutData.getPaymentService(),
                        getElementLabel("payment_method_details_pembelian"), "payment method price doesn't match");
                break;
            case "payment method":
                swipeDownToElement("payment_method_details_pembelian");
                assertTextContains(getElementLabel("payment_method_details_pembelian"),
                        CheckoutData.getPaymentMethod(), "payment method price doesn't match");
                break;
            case "address":
                swipeUpToElement("detail_pembelian_alamat_pengiriman_section");
                tapElement("detail_pembelian_alamat_pengiriman_section");
                assertTextContains(CheckoutData.getAddressName().split(" - ")[1],
                        getElementValue("detail_pembelian_alamat_content_text"), "address price doesn't match");
                break;
            default:
                Assert.fail("Invalid section");
                break;
        }
    }

    public void validateInvoiceNumber() {
        assertEquals(TransactionData.getInvoiceNo(),
                getElementValue("detail_pembelian_invoice_number_text"), "invoice number doesn't match");
    }

    public void validatePaymentCode() {
        int expectedPaymentCode = TransactionData.getTotalPayment() - CheckoutData.getTotalPaymentCheckout();
        validateValue().equals(expectedPaymentCode, getPaymentCode());
    }

    private int getPaymentCode() {
        return Integer.parseInt(getElementValue("payment_code_details_pembelian").replaceAll("[^\\d.]", ""));
    }

    private void validateTotalPayment(String paymentMethod) {
        if (paymentMethod.equals("Transfer Bank Manual")) {
            validateValue().equals(CheckoutData.getTotalPaymentCheckout() + getPaymentCode(),
                    getIntFromRp(getElementLabel("total_payment_details_pembelian")), "total payment doesn't match");
        } else {
            swipeDownToElement("total_payment_details_pembelian");
            assertEquals(CheckoutData.getTotalPaymentCheckout(),
                    getIntFromRp(getElementLabel("total_payment_details_pembelian")), "total payment doesn't match");
        }
    }

    private void validateMultipleProductOnInvoice(String sectionName) {
        int sizeProductFromCart = CheckoutData.getCartDetails().size();
        for (int i = 0; i < sizeProductFromCart; i++) {
            swipeDownToElement("detail_pembelian_alamat_pengiriman_section", 5);
            String productName = CheckoutData.getCartDetails().get(i).get("PRODUCT_NAME").toString();
            switch (sectionName) {
                case "product name":
                    String elementProductName = constructLocator("specific_product_name_details_pembelian", productName);
                    swipeUpToElement(elementProductName, 3);
                    validateDisplayed(elementProductName);
                    break;
                case "product price":
                    int productPrice = Integer.parseInt(CheckoutData.getCartDetails().get(i).get("ITEM_PRICE").toString().replaceAll("\\D+", ""));
                    int productQuantity = Integer.parseInt(CheckoutData.getCartDetails().get(i).get("QTY").toString());
                    String elementProductPrice = constructLocator("specific_product_price_details_pembelian", productName);
                    swipeUpToElement(elementProductPrice, 3);
                    validateDisplayed(elementProductPrice);
                    assertEquals(productPrice * productQuantity,
                            getIntegerFromValueElement(elementProductPrice));
                    break;
                case "quantity product":
                    String productQty = CheckoutData.getCartDetails().get(i).get("QTY").toString();
                    String elementProductQty = constructLocator("specific_product_qty_details_pembelian", productName);
                    swipeUpToElement(elementProductQty, 3);
                    validateDisplayed(elementProductQty);
                    assertEquals(productQty, getElementValue(elementProductQty).replaceAll("[^\\d.]", ""));
                    break;
                default:
                    Assert.fail("Invalid section");
                    break;
            }
        }
    }

    public void validateBuyerNotes() {
        swipeDownToElement("detail_pembelian_catatan_pelapak_text");
        assertEquals(CheckoutData.getBuyerNotes(), getElementValue("detail_pembelian_catatan_pelapak_value"), "buyer notes doesn't match");
    }

    public void tapDetailHistoryPengiriman() {
        if (!isElementVisible("detail_pembelian_detail_last_pengiriman_button")) {
            validateDisplayed("detail_pembelian_history_pengiriman_expand_button");
            tapElement("detail_pembelian_history_pengiriman_expand_button");
        }
        validateDisplayed("detail_pembelian_detail_last_pengiriman_button");
        tapElement("detail_pembelian_detail_last_pengiriman_button");
    }

    public void verifyDetailHisotryPengirimanModal(String condition) {
        if (condition == null) {
            validateExist("detail_pembelian_detail_history_pengiriman_label");
            validateExist("detail_pembelian_detail_pengiriman_modal_close_button");
            validateExist("detail_pembelian_last_shipping_state_text");
        } else {
            validateNotExist("detail_pembelian_detail_history_pengiriman_label", 1);
            validateNotExist("detail_pembelian_detail_pengiriman_modal_close_button", 1);
            validateNotExist("detail_pembelian_last_shipping_state_text", 1);
        }
        HelperData.setLastActionPage(new DetailPembelianMarketplacePage(iosDriver));
    }

    public void verifyDetailHisotryPengirimanState(String state) {
        String expectedState = (state.equals("lastShippingHistoryState")) ? XPRData.getLastShippingHistory() : state;
        validateExist("detail_pembelian_last_shipping_state_text");
        validateValue().equals(getText("detail_pembelian_last_shipping_state_text"), expectedState, "state is different");
        HelperData.setLastActionPage(new DetailPembelianMarketplacePage(iosDriver));
    }

    public void verifyAddressInPetaLokasiPelapak() {
        waitForVisibilityOf("detail_pembelian_maps_section", 30);
        verifyElementExist("detail_pembelian_koordinat_alamat_title");
        verifyElementExist("detail_pembelian_bagikan_address_text");
        verifyElementExist("detail_pembelian_address_back_button");
        verifyElementExist(constructLocator("detail_pembelian_address_map_detail_text", XPRData.getPickupLocationAmbilSendiri()));
        HelperData.setLastActionPage(new DetailPembelianMarketplacePage(iosDriver));
    }

    public void validateDropshipperInfo() {
        swipeDownToElement("detail_pembelian_alamat_pengiriman_section");
        tapElement("detail_pembelian_alamat_pengiriman_section");
        swipeDownToElement("detail_pembelian_dropshipper_name_value");
        assertEquals(CheckoutData.getDropshipperName(), getElementValue("detail_pembelian_dropshipper_name_value"), "dropshipper name doesn't match");
        if (CheckoutData.getDropshipperAdditionalInfo().equalsIgnoreCase("No. handphone atau catatan lainnya")) {
            assertEquals(getElement("detail_pembelian_dropshipper_detail_value").getAttribute("value"), null);
        } else {
            assertEquals(CheckoutData.getDropshipperAdditionalInfo(), getElementValue("detail_pembelian_dropshipper_detail_value"), "dropshipper detail info doesn't match");
        }
    }
}
