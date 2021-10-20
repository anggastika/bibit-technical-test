package bukalapak.stepDefinitions.gro;

import bukalapak.TestInstrument;
import io.cucumber.java8.En;

public class NabungDiskonStepDefinitions extends TestInstrument implements En {
    public NabungDiskonStepDefinitions() {

        Then("user is on Nabung Diskon landing page", () -> {
            bukalapak.nabungDiskonPage().validateNabungDiskonPage();
        });

        Then("user verify How to Play Section exist", () -> {
            bukalapak.nabungDiskonPage().validateHowToPlaySection();
        });

        Then("user verify FAQ section on Nabung Diskon", () -> {
            bukalapak.nabungDiskonPage().validateFAQSection();
        });

        Then("user verify product list on nabung diskon homepage", () -> {
            bukalapak.nabungDiskonPage().validateAllProductCard();
            bukalapak.nabungDiskonPage().validateProductList();
        });

        Then("user verify out of stock product is disable on the last list", () -> {
            bukalapak.nabungDiskonPage().validateAllProductCard();
            bukalapak.nabungDiskonPage().validateDisableProductCard();
        });

        Then("user tap mulai nabung button on first product", () -> {
            bukalapak.nabungDiskonPage().validateMulaiNabungButtonProductCard();
            bukalapak.nabungDiskonPage().tapMulaiNabungButtonProductCard();
        });

        Then("user verify nabung diskon product detail page", () -> {
            bukalapak.nabungDiskonPage().validateNabungDiskonPdp();
        });

        Then("user tap back button on nabung diskon pdp", () -> {
            bukalapak.nabungDiskonPage().tapNabungDiskonPdpBackButton();
        });

        When("user tap lihat selengkapnya on pdp nabung diskon", () -> {
            bukalapak.nabungDiskonPage().validateViewMore();
            bukalapak.nabungDiskonPage().tapViewMore();
        });

        Then("user verify lihat selengkapnya sheet on product", () -> {
            bukalapak.nabungDiskonPage().validateProductInformationSheet();
        });

        When("user tap payment method on nabung diskon pdp", () -> {
            bukalapak.nabungDiskonPage().validatePaymentMethodOnPdp();
            bukalapak.nabungDiskonPage().tapPaymentMethodOnPdp();
        });

        Then("user verify all payment method on nabung diskon pdp", () -> {
            bukalapak.nabungDiskonPage().validateAllPaymentMethod();
        });

        Then("user tap mulai nabung button on pdp", () -> {
            bukalapak.nabungDiskonPage().tapMulaiNabungButton();
        });

        Then("user tap setuju on ketentuan nabung diskon sheet", () -> {
            bukalapak.nabungDiskonPage().tapSetujuNabungDiskon();
        });

        Then("user verify confirmation phone sheet at the first time", () -> {
            bukalapak.nabungDiskonPage().validatePhoneConfirmSheet();
        });

        Then("user input phone number on confirmation sheet", () -> {
            bukalapak.nabungDiskonPage().fillPhoneNumberNabungDiskon();
        });

        Then("user tap close icon on phone confirmation sheet", () -> {
            bukalapak.nabungDiskonPage().tapCloseIconSheet();
        });

        Then("user validate phone confirmation sheet disappear", () -> {
            bukalapak.nabungDiskonPage().validatePhoneConfirmSheetDisappear();
        });

        When("user tap riwayat on Nabung Diskon", () -> {
            bukalapak.nabungDiskonPage().tapRiwayatNabungDiskon();
        });

        When("user verify riwayat page Nabung Diskon", () -> {
            bukalapak.nabungDiskonPage().validateRiwayatPage();
        });

        Then("^user verify card with status \"([^\"]*)\"$", (String status) -> {
            bukalapak.nabungDiskonPage().validateStatusCardRiwayat(status);
        });

        When("user choose product that price above 10rb", () -> {
            bukalapak.nabungDiskonPage().tapMulaiNabungButtonProductAvailableVA();
        });

        When("^user choose Virtual Account with bank \"([^\"]*)\" as payment method$", (String bank) -> {
            bukalapak.nabungDiskonPage().chooseVAPaymentMethod();
            bukalapak.nabungDiskonPage().chooseVABank(bank);
            bukalapak.nabungDiskonPage().continueBayarWithVA();
        });

        When("user tap confirm button on pop-up confirm phone", () -> {
            bukalapak.nabungDiskonPage().confirmPhoneToContinueBayar();
        });

        Then("user verify confirmation page Virtual Account", () -> {
            bukalapak.nabungDiskonPage().validatePendingConfirmationCheckoutPage();
        });

        Then("user tap lihat detail on confirmation page Virtual Account", () -> {
            bukalapak.nabungDiskonPage().continueLihatDetailPesanan();
        });

        Then("user verify invoice detail pending status", () -> {
            bukalapak.nabungDiskonPage().validatePendingStatusInvoiceDetail();
        });

        Then("^user verify Metode Pembayaran is \"([^\"]*)\"$", (String paymentMethod) -> {
            bukalapak.nabungDiskonPage().validatePaymentMehodInvoiceDetail(paymentMethod);
        });
    }
}
