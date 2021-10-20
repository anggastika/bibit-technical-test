package bukalapak.stepDefinitions.vp;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

import static org.junit.Assert.fail;

public class VpCheckOutStepDefinitions extends TestInstrument implements En {

    public VpCheckOutStepDefinitions() {

        When("^user (?:chooses|had chosen) \"([^\"]*)\" payment to continue the (.*) transaction$", (String paymentMethod, String product) -> {
            // TODO: Implement checkout step for each product
            bukalapak.vpCheckOutPage().validateOnPage();
            switch (product.toLowerCase()) {

                // region PREPAID
                case "paket data":
                    bukalapak.paketDataCheckOutPage().validateTransactionData();
                    bukalapak.paketDataCheckOutPage().validatePaymentAmount();
                    break;
                case "listrik prabayar":
                    bukalapak.listrikPrabayarCheckOutPage().validateTransactionData();
                    bukalapak.listrikPrabayarCheckOutPage().validatePaymentAmount();
                    break;
                case "uang elektronik":
                    bukalapak.uangElektronikCheckOutPage().validateTransactionData();
                    bukalapak.uangElektronikCheckOutPage().validatePaymentAmount();
                    break;
                case "pulsa prabayar":
                    bukalapak.pulsaPrabayarCheckOutPage().validateTransactionData();
                    bukalapak.pulsaPrabayarCheckOutPage().validatePaymentAmount();
                    break;
                // endregion

                // region POSTPAID
                case "premi asuransi":
                    bukalapak.premiAsuransiCheckOutPage().validateTransactionData();
                    break;
                case "bpjs kesehatan":
                    bukalapak.bpjsCheckoutPage().validateTransactionData();
                    break;
                case "pajak pbb":
                    bukalapak.pajakDerahCheckoutPage().validateTransactionData();
                    break;
                case "add-on indihome":
                    break;
                case "pulsa pascabayar":
                    bukalapak.pulsaPascabayarCheckoutPage().validateCheckoutPulsaPascabayarData();
                    break;
                case "tv kabel":
                    bukalapak.cableTvCheckoutPage().validateInquireData();
                    break;
                case "pkb":
                case "esamsat":
                case "bukazakat":
                    break;
                case "telkom":
                    bukalapak.telkomCheckoutPage().validateTransactionData();
                    break;
                case "kartu kredit":
                    bukalapak.kartuKreditCheckoutPage().setTransactionData();
                    break;
                case "listrik pascabayar":
                    bukalapak.listrikPascabayarCheckoutPage().validateTransactionData();
                    break;
                case "pdam":
                    bukalapak.pdamCheckoutPage().validateTransactionData();
                    break;
                case "multifinance":
                    break;
                case "addon indihome":
                    bukalapak.addOnIndihomeCheckoutPage().validateCheckoutAddOnIndihomeData();
                    break;
                // endregion

                // region TIX
                case "tiket kereta":
                    bukalapak.travelTrainCheckoutPage().validateCheckoutDetail();
                    break;
                case "kereta bandara":
                case "tiket pesawat":
                    bukalapak.tiketPesawatCheckoutPage().validateTotalPrice();
                    bukalapak.tiketPesawatCheckoutPage().validateBookingDetail();
                    break;
                case "tiket bus":
                    bukalapak.travelBusCheckoutPage().validateCheckoutDetail();
                    break;
                case "gift card":
                    bukalapak.giftCardCheckoutPage().validateCheckoutDetail();
                    break;
                case "subscription":
                    bukalapak.travelSubscriptionCheckoutPage().validateCheckoutDetail();
                    break;
                case "kupon":
                    bukalapak.kuponCheckoutPage().validateCheckoutDetail();
                    break;
                case "event":
                    bukalapak.tiketEventCheckoutPage().validateCheckoutDetail();
                    break;
                // endregion

                // region INSURANCE
                case "asuransi bepergian":
                case "asuransi motor":
                case "asuransi tambahan":
                    break;
                case "asuransi covid":
                    bukalapak.asuransiCovidCheckoutPage().validateTransactionCovid();
                    bukalapak.asuransiCovidCheckoutPage().setDataInsurance();
                    break;
                // endregion

                default:
                    fail(String.format("%s step not implemented yet", product));
            }
            bukalapak.vpCheckOutPage().choosePaymentMethod(paymentMethod);
            bukalapak.vpCheckOutPage().nativeSwipeUp();
            bukalapak.vpCheckOutPage().tapOnButtonBayar();
        });

        When("user navigates to invoice details from the confirmation check out page", () -> {
            bukalapak.vpConfirmationCheckOutPage().validateOnPage();
            bukalapak.vpConfirmationCheckOutPage().setTransactionData();
            bukalapak.vpConfirmationCheckOutPage().tapOnButtonLihatPesanan();
        });

        When("^user apply (.*) (discount|cashback) voucher$", (String product, String voucherType) -> {
            bukalapak.vpCheckOutPage().validateOnPage();
            bukalapak.vpCheckOutPage().applyVoucher(dotenv.get(product.replaceAll(" ", "_").toUpperCase() + "_VOUCHER_" + voucherType.toUpperCase()));
            bukalapak.vpCheckOutPage().validateDiscountVoucher(voucherType.equals("discount"));
        });

        When("^user uses a (discount|cashback) promo code on the virtual product checkout page$", (String voucherType) -> {
            bukalapak.vpCheckOutPage().validateOnPage();
            bukalapak.vpCheckOutPage().setOnCheckBoxMixDana(false);
            bukalapak.vpCheckOutPage().setOnFieldVoucherCode(voucherType.equals("discount") ? dotenv.get("VP_VOUCHER_DISCOUNT") : dotenv.get("VP_VOUCHER_CASHBACK"));
            bukalapak.vpCheckOutPage().validateVoucherValidationText(voucherType.equals("discount") ? "Yay, vouchermu berhasil digunakan" : "Kamu bisa dapet Credits");
            bukalapak.vpCheckOutPage().validateDiscountVoucher(voucherType.equals("discount"));
        });

        When("^user validate (.*) as default payment method$", (String paymentMethod) -> {
            bukalapak.vpCheckOutPage().validateOnPage();
            bukalapak.vpCheckOutPage().validateDefaultPayment(paymentMethod);
        });

        And("user navigate to the top virtual product checkout page", () -> {
            bukalapak.vpBasePage().nativeSwipeDown();
        });

        And("user navigate to the bottom virtual product checkout page", () -> {
            bukalapak.vpBasePage().nativeSwipeUp();
        });
    }
}
