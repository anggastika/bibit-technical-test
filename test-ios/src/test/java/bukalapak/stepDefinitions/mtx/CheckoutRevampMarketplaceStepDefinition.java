package bukalapak.stepDefinitions.mtx;

import bukalapak.TestInstrument;
import bukalapak.data.CheckoutData;
import cucumber.api.java8.En;

public class CheckoutRevampMarketplaceStepDefinition extends TestInstrument implements En {

    public CheckoutRevampMarketplaceStepDefinition() {
        Then("user is on checkout revamp marketplace", () -> {
            bukalapak.checkoutRevampMarketplacePage().isOnCheckoutPage();
        });

        And("^user tap address section on checkout marketplace page$", () -> {
            bukalapak.checkoutRevampMarketplacePage().tapAddressSection();
        });

        And("^user change address with label \"([^\"]*)\" on checkout marketplace page$", (String addressLabel) -> {
            bukalapak.checkoutRevampMarketplacePage().changeAddress(addressLabel);
        });

        And("user can see \"([^\"]*)\" label", (String labelName) -> {
            bukalapak.checkoutRevampMarketplacePage().validateLabel(labelName);
        });

        And("user validate \"([^\"]*)\" match from tray atc", (String sectionName) -> {
            bukalapak.checkoutRevampMarketplacePage().validateSectionFromCartOrTrayATC(sectionName);
        });

        And("^user can set \"([^\"]*)\" as a dropshipper name( and \"([^\"]*)\" as a dropshipper additional info)?$", (String dropshipperName, String dropshipperAdditionalInfo) -> {
            bukalapak.checkoutRevampMarketplacePage().setDropshipper(dropshipperName, dropshipperAdditionalInfo);
            bukalapak.checkoutRevampMarketplacePage().validateDropshipperInfo(dropshipperAdditionalInfo);
        });

        And("^user validate multiple \"([^\"]*)\" match from cart$", (String sectionName) -> {
            bukalapak.checkoutRevampMarketplacePage().validateSectionFromCart(sectionName);
        });

        When("user change address to another address", () -> {
            bukalapak.checkoutRevampMarketplacePage().changeToAnotherAddress();
        });

        When("^user set buyer note with value \"([^\"]*)\" at seller name \"([^\"]*)\" on checkout marketplace page$", (String buyerNote, String sellerName) -> {
            bukalapak.checkoutRevampMarketplacePage().setCatatanPelapak(buyerNote, sellerName);
            bukalapak.checkoutRevampMarketplacePage().validateSuccessfullyAddedCatatanPelapak(sellerName);
        });

        Then("^user (can|cant) see buyer notes info on checkout page at seller name \"([^\"]*)\"$", (String state, String seller) -> {
            bukalapak.checkoutRevampMarketplacePage().validateInfoBuyerInfo(state, seller);
        });

        When("user click pelajari on logistice insurance section for information insurance", () -> {
            bukalapak.checkoutRevampMarketplacePage().tapOnPelajariButton();
            bukalapak.checkoutRevampMarketplacePage().tapOnMengertiButton();
        });

        And("^user (check|uncheck) product insurance on checkout marketplace page$", (String state) -> {
            bukalapak.checkoutRevampMarketplacePage().setProductInsurance(state);
        });

        And("^user (check|uncheck) logistic insurance on checkout marketplace page$", (String state) -> {
            bukalapak.checkoutRevampMarketplacePage().setLogisticInsurance(state);
        });

        And("^user (check|uncheck) seller insurance on checkout marketplace page$", (String state) -> {
            bukalapak.checkoutRevampMarketplacePage().setSellerInsurance(state);
        });

        When("^user (check|uncheck) auto invest on checkout marketplace page$", (String state) -> {
            bukalapak.checkoutRevampMarketplacePage().setAutoInvest(state);
        });

        When("user set courier with \"([^\"]*)\" and \"([^\"]*)\" as courier group", (String courierName, String groupcourierName) -> {
            bukalapak.checkoutRevampMarketplacePage().chooseCourier(groupcourierName, courierName);
            bukalapak.checkoutRevampMarketplacePage().validateSelectedGroupCourier();
            bukalapak.checkoutRevampMarketplacePage().validateSelectedCourier();
        });

        When("^user select \"([^\"]*)\" as a payment method( using \"([^\"]*)\")? on checkout marketplace page$", (String method, String service) -> {
            bukalapak.checkoutRevampMarketplacePage().selectPaymentMethod(method, service);
            bukalapak.checkoutRevampMarketplacePage().usePaymentMethod();
        });

        When("^user validate default payment method is \"([^\"]*)\" on checkout marketplace page$" ,(String paymentName) -> {
            bukalapak.checkoutRevampMarketplacePage().validateDefaultPayment(paymentName);
        });

        When("user tap voucher section on checkout page" ,() -> {
            bukalapak.checkoutRevampMarketplacePage().tapVoucherSection();
        });

        And("user using voucher pelapak \"([^\"]*)\"", (String sellerName) -> {
            bukalapak.checkoutRevampMarketplacePage().setVoucherPelapak(sellerName);
        });

        And("user validate successfully voucher applied" , () -> {
            bukalapak.checkoutRevampMarketplacePage().validateVoucherApplied();
        });

        When("user toggle on mixpayment", () -> {
            bukalapak.checkoutRevampMarketplacePage().toggleOnMixpayment();
        });

        When("user tap atur jumlah button mixpayment", () -> {
            bukalapak.checkoutRevampMarketplacePage().tapAturJumlahMixpayment();
        });

        And("^user (check|uncheck) mixpayment (DANA|Saldo) on mixpayment adjust page$", (String state, String type) -> {
            bukalapak.checkoutRevampMarketplacePage().tapCheckboxMixPayment(state, type);
        });

        And("^user set amount Rp.\"([^\"]*)\" mixpayment using (DANA|Saldo) balance$", (String amount, String type) -> {
            bukalapak.checkoutRevampMarketplacePage().setMixPayment(amount, type);
        });

        And("user tap simpan button on mixpayment adjust page", () -> {
            bukalapak.checkoutRevampMarketplacePage().tapSimpanButtonMixpayment();
        });

        And("user should not see mix payment on payment method section", () -> {
            bukalapak.checkoutRevampMarketplacePage().validateMixpaymentNotAppear();
        });

        Then("^user (can|can't) see tnc insurance on checkout submission section$" , (String state) -> {
            bukalapak.checkoutRevampMarketplacePage().validateTncInsuranceCheckoutSubmission(state);
        } );

        Then("user should see rounded amount on rincian harga Checkout Page", () -> {
            bukalapak.checkoutRevampMarketplacePage().validationPembulatanText();
            bukalapak.checkoutRevampMarketplacePage().setRoundedAmount();
            bukalapak.checkoutRevampMarketplacePage().tapOnPembulatanTooltip();
            bukalapak.checkoutRevampMarketplacePage().validationPembulatanTooltipInfoText();
        });

        When("user should see all amount info on Checkout Page is correct", () -> {
            bukalapak.checkoutRevampMarketplacePage().validateTotalHargaBarang(
                    CheckoutData.getProductPrice() * Integer.parseInt(CheckoutData.getProductQuantity()));
            bukalapak.checkoutRevampMarketplacePage().setTotalBiayaLainnya();
            bukalapak.checkoutRevampMarketplacePage().validationBiayaKirim(CheckoutData.getCourierShippingNominal());
            bukalapak.checkoutRevampMarketplacePage().validationTotalBelanja();
        });

        And("^user tap Bayar sekarang button$", () -> {
            bukalapak.checkoutRevampMarketplacePage().clickBayarButton();
        });

        And("user toggle off dropshipper", () -> {
            bukalapak.checkoutRevampMarketplacePage().toggleOffDropshipper();
        });

        And("user can re-toggle on dropshipper", () -> {
            bukalapak.checkoutRevampMarketplacePage().reToggleOnDropShipper();
            bukalapak.checkoutRevampMarketplacePage().validateDropshipperAfterReToggleOn();
        });

        And("user able to edit dropshipper info with \"([^\"]*)\" as a dropshipper name( and \"([^\"]*)\" as a dropshipper additional info)?", (String dropshipperName, String dropshipperAdditionalInfo) -> {
            bukalapak.checkoutRevampMarketplacePage().editDropshipper(dropshipperName,dropshipperAdditionalInfo);
            bukalapak.checkoutRevampMarketplacePage().validateDropshipperInfo(dropshipperAdditionalInfo);
        });

        When("user back from checkout page", () -> {
            bukalapak.checkoutRevampMarketplacePage().backFromCheckoutPage();
        });
    }
}
