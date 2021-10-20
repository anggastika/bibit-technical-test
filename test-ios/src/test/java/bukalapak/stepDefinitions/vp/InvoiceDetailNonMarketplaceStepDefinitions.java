package bukalapak.stepDefinitions.vp;

import bukalapak.TestInstrument;
import bukalapak.data.TransactionData;
import bukalapak.utils.ChoiceSelector;
import cucumber.api.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.fail;

/**
 * @Author: Ayu Musfita
 * @Date: 28/12/18, Fri
 *
 * IMPORTANT NOTES:
 * Please simplify the test steps (from each page object class) into one line only in each features steps.
 **/
public class InvoiceDetailNonMarketplaceStepDefinitions extends TestInstrument implements En {

    private final static Logger LOGGER = LogManager.getLogger(InvoiceDetailNonMarketplaceStepDefinitions.class);

    public InvoiceDetailNonMarketplaceStepDefinitions() {

        Given("user is in \"detail_tagihan\" page", () -> {
            bukalapak.invoiceDetailNonMarketplacePage().userOnInvoiceDetailPage();
        });

        When("user have a \"([^\"]*)\" invoice number", (String product) -> {
            String invoice = product.replaceAll(" ", "_").toUpperCase();
            TransactionData.setInvoiceNo(dotenv.get(invoice + "_INVOICE_NUMBER"));
        });

        Then("the invoice detail \"([^\"]*)\" bus shown and correct", (String trip) -> {
            bukalapak.invoiceDetailNonMarketplacePage().userOnInvoiceDetailPage();
            bukalapak.invoiceDetailNonMarketplacePage().validateTextInvoiceId(TransactionData.getInvoiceNo());
            bukalapak.invoiceDetailNonMarketplacePage().validateTextTotalTagihan(TransactionData.getTotalPrice());
            bukalapak.iOSBasePage().swipeUpToElement("invoice_non_marketplace_bus_tiket_pergi");
            bukalapak.travelBusInvoiceDetailPage().validateBusBoardingPoint("departure");
            bukalapak.travelBusInvoiceDetailPage().validateBusDroppingPoint("departure");
            bukalapak.travelBusInvoiceDetailPage().validateBusTripTime("departure");
            bukalapak.travelBusInvoiceDetailPage().validateBusName("departure");
            bukalapak.travelBusInvoiceDetailPage().validateSeatNumber("departure");

            if (trip.toLowerCase().equals("round trip")) {
                bukalapak.iOSBasePage().swipeUpToElement("invoice_non_marketplace_bus_detai_pesanan_section");
                bukalapak.travelBusInvoiceDetailPage().validateBusBoardingPoint("return");
                bukalapak.travelBusInvoiceDetailPage().validateBusDroppingPoint("return");
                bukalapak.travelBusInvoiceDetailPage().validateBusTripTime("return");
                bukalapak.travelBusInvoiceDetailPage().validateBusName("return");
                bukalapak.travelBusInvoiceDetailPage().validateSeatNumber("return");
            }
        });

        Then("the invoice detail \"([^\"]*)\" kereta shown and correct with status \"([^\"]*)\"", (String trip, String status) -> {
            bukalapak.invoiceDetailNonMarketplacePage().userOnInvoiceDetailPage();
            bukalapak.invoiceDetailNonMarketplacePage().validateTextInvoiceId(TransactionData.getInvoiceNo());
            bukalapak.travelTrainInvoiceDetailPage().validateTrainTicketTotalPriceText(TransactionData.getTotalPrice());
            bukalapak.invoiceDetailNonMarketplacePage().validateTextInvoiceStatus(status);
            bukalapak.travelTrainInvoiceDetailPage().tapInfoPassengerSection();
            bukalapak.travelTrainInvoiceDetailPage().validateTrainData("departure");
            if (trip.toLowerCase().equals("round trip")) {
                bukalapak.travelTrainInvoiceDetailPage().tapDepartureTripSection();
                bukalapak.travelTrainInvoiceDetailPage().validateTrainData("return");
            }
        });

        Then("new invoice will be created", () -> {
            bukalapak.iOSBasePage().verifyElementExist("tagihan_informasi_tagihan_text");
        });

        Then("new redeem invoice will be created", () -> {
            bukalapak.iOSBasePage().verifyElementExist("tagihan_informasi_penjualan_text");
        });

        Then("user verify \"([^\"]*)\" invoice detail with \"([^\"]*)\" payment method", (String productType, String paymentMethod) -> {
            ChoiceSelector.of(productType)
//                    .when("BukaEmas", () -> bukalapak.bukaEmasPage().verifyBukaEmasInvoiceDetail(paymentMethod))
                    .when("BukaReksa", () -> bukalapak.invoiceDetailNonMarketplacePage().verifyBukaReksaInvoiceDetail(paymentMethod))
                    .orElse(() -> LOGGER.info("Your product type is not implemented yet: " + productType));
        });

        And("^user change payment method to \"([^\"]*)\"$", (String paymentMethod) -> {
            bukalapak.invoiceDetailNonMarketplacePage().clickUbahMetodePembayaran();
            bukalapak.checkoutNonMarketplacePage().choosePaymentMethod(paymentMethod);
            bukalapak.checkoutNonMarketplacePage().tapOnBayarButton();
        });

        And("user change payment method from invoice detail page", () -> {
            bukalapak.invoiceDetailNonMarketplacePage().clickButtonUbah();
        });

        And("user continue payment process using \"([^\"]*)\" from invoice detail", (String paymentMethod) -> {
            bukalapak.invoiceDetailNonMarketplacePage().clickLanjutPembayaran(paymentMethod);
            ChoiceSelector.of(paymentMethod)
                    .when("bcaKlikPay", () -> bukalapak.checkoutNonMarketplacePage().verifyBCAKlikpayWebView())
                    .orElse(() -> LOGGER.info("Your payment method is not implemented yet: " + paymentMethod));
        });

        And("user click Lihat Tagihan Pembayaran button", () -> {
            bukalapak.invoiceDetailNonMarketplacePage().clickLihatTagihanPembayaran();
        });

        And("user check detail tagihan for Priority Buyer from \"([^\"]*)\"", (String tagihanType) -> {
            ChoiceSelector.of(tagihanType)
                    .when("landing page", () -> bukalapak.invoiceDetailNonMarketplacePage().checkPriorityBuyerInvoiceFromLanding())
                    .orElse(() -> LOGGER.info("Please select the available option!"));
        });

        And("user verify total payment in detail tagihan page is match with the one memorized before", () -> {
            bukalapak.invoiceDetailNonMarketplacePage().validateTotalTagihan();
        });

        And("user verify the copy function of virtual account number works properly in detail tagihan page", () -> {
            bukalapak.invoiceDetailNonMarketplacePage().verifyCopyFunctionOfVANumber();
        });

        Then("^(?:the )?invoice should be on \"([^\"]*)\" in (.*) old invoice detail$", (String state, String product) -> {
            bukalapak.invoiceDetailNonMarketplacePage().userOnOldInvoiceDetailPage();
            bukalapak.invoiceDetailNonMarketplacePage().verifyInvoiceStateIsMatch(state);

            ChoiceSelector.of(product.toLowerCase())
                    .when("pajak pbb", () -> {
                        bukalapak.pajakDaerahInvoiceDetailPage().validateCustomerData();
                    })
                    .orElse(() -> fail(String.format("%s not implemented yet", product)));
        });

        Then("^(?:the )?(.*) invoice details data will have equalled by the transaction with status \"([^\"]*)\"$", (String product, String status) -> {
            bukalapak.invoiceDetailNonMarketplacePage().userOnInvoiceDetailPage();
            bukalapak.invoiceDetailNonMarketplacePage().validateTextInvoiceId(TransactionData.getInvoiceNo());
            bukalapak.invoiceDetailNonMarketplacePage().validateTextTotalTagihan(TransactionData.getTotalPrice());
            bukalapak.invoiceDetailNonMarketplacePage().validateTextInvoiceStatus(status);
            ChoiceSelector.of(product.toLowerCase())
                    //TODO validate each product
                    .when("tiket pesawat", () -> {
                        bukalapak.tiketPesawatInvoiceDetailPage().validateTiketPesawatData();
                    })
                    .when("gift card", () -> {
                        bukalapak.giftCardInvoiceDetailPage().validateGiftCardData();
                    })
                    .when("kupon", () -> {
                        bukalapak.kuponInvoiceDetailPage().validateKuponData();
                    })
                    .when("subscription", () -> {
                        bukalapak.travelSubscriptionTransactionDetailPage().validateSubscriptionData();
                    })
                    .when("event", () -> {
                        bukalapak.tiketEventTransactionDetailPage().validateEventData();
                        bukalapak.tiketEventTransactionDetailPage().validateEventBuyer();
                    })
                    .when("asuransi motor", () -> {
                        bukalapak.asuransiMotorPage().validatePurchasedPlan();
                    })
                    .when("gift card", () -> {
                        bukalapak.giftCardInvoiceDetailPage().validateGiftCardData();
                    })
                    .when("gift dana", () -> {
                        bukalapak.giftCardInvoiceDetailPage().validateGiftCardData();
                    })
                    .when("pdam", () -> {
                        bukalapak.pdamInvoiceDetailPage().validatePDAMData();
                    })
                    .when("multifinance", () -> {
                        bukalapak.multifinanceInvoiceDetailPage().validateMultifinanceData();
                    })
                    .when("listrik pascabayar", () -> {
                        bukalapak.listrikPascabayarInvoiceDetailPage().validateListrikPascabayarData();
                    })
                    .when("telkom", () -> {
                        bukalapak.telkomInvoiceDetailPage().validateTelkomData();
                    })
                    .when("bpjs kesehatan", () -> {
                        bukalapak.bpjsInvoiceDetailPage().validateBPJSKesehatanData();
                    })
                    .when("asuransi covid", () -> {
                        bukalapak.asuransiCovidInvoiceDetailPage().validateTransactionData();
                    })
                    .orElse(() -> fail(String.format("%s step not implemented yet", product)));
        });

        Given("user is in \"Detail Tagihan Premium\" page", () -> {
            bukalapak.invoiceDetailNonMarketplacePage().userOnDetailTagihanPremiumPage();
        });

        Then("^(train|flight) insurance details will have displayed on transaction details$", (String product) ->{
            bukalapak.invoiceDetailNonMarketplacePage().validateTravelInsurancePriceCorrect(product);
            bukalapak.invoiceDetailNonMarketplacePage().validateTravelInsuranceSectionShown();
            bukalapak.invoiceDetailNonMarketplacePage().validateTravelInsuranceStatus("Diproses");
            bukalapak.invoiceDetailNonMarketplacePage().tapOnLihatManfaatButton();
            bukalapak.invoiceDetailNonMarketplacePage().validateTravelInsuranceDialogShown();
        });

        Then("user verify payment method vp is \"([^\"]*)\"", (String paymentMethod) -> {
            bukalapak.invoiceDetailNonMarketplacePage().verifyPaymentMethodVP(paymentMethod);
        });

        Then("user validate vp transaction is refunded", () -> {
            bukalapak.invoiceDetailNonMarketplacePage().validateTrxRefunded();
        });

        Then("user tap ubah pembayaran non marketplace", () -> {
            bukalapak.invoiceDetailNonMarketplacePage().tapUbahPembayaran();
        });
    }
}
