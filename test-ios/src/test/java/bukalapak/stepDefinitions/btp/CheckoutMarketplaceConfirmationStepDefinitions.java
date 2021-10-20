package bukalapak.stepDefinitions.btp;

import bukalapak.TestInstrument;
import bukalapak.data.MtxData;
import bukalapak.data.PRIOData;
import cucumber.api.java8.En;

public class CheckoutMarketplaceConfirmationStepDefinitions extends TestInstrument implements En {

    public CheckoutMarketplaceConfirmationStepDefinitions() {
        Given("user is in \"Checkout Marketplace Confirmation\" page", () -> {
            bukalapak.checkoutMarketplaceConfirmationPage().userOnCheckoutMarketplaceConfirmationPage();
        });

        And("user click Lihat Tagihan Pembayaran in Checkout Marketplace Confirmation Page", () -> {
            bukalapak.checkoutMarketplaceConfirmationPage().clickLihatTagihanPembayaran();
        });

        And("user verify Invoice Number on Bottom Confirmation Page", () -> {
            bukalapak.checkoutMarketplaceConfirmationPage().validateInvoiceNoOnBottomSection();
        });

        And("user verify total price on Confirmation page with Summary Checkout page", () -> {
            bukalapak.checkoutMarketplaceConfirmationPage().validateTotalTagihan(MtxData.getTotalPaymentCheckout());
        });

        /* This step definitions is used by PNR */
        And("user see recommendations on Payment Confirmation page", () -> {
            bukalapak.checkoutMarketplaceConfirmationPage().checkRecommendationPanelAppear();
        });

        And("user verify total price on Checkout Marketplace Confirmation page with (.*)", (String transactionType) -> {
            bukalapak.checkoutMarketplaceConfirmationPage().validateTotalTagihan(transactionType, PRIOData.getCrossSellingPriorityTotalPayment());
        });

        Then("user is on success payment page", () -> {
            bukalapak.checkoutMarketplaceConfirmationPage().isOnSuccessPaymentPage();
        });

    }
}
