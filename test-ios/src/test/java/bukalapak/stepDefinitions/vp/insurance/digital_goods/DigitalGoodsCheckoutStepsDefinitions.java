package bukalapak.stepDefinitions.vp.insurance.digital_goods;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

import static org.junit.Assert.fail;

public class DigitalGoodsCheckoutStepsDefinitions extends TestInstrument implements En {

    public DigitalGoodsCheckoutStepsDefinitions() {

        And("^user (ticks|unticks) the Digital Goods checkbox$", (String option) -> {
            bukalapak.checkoutNonMarketplacePage().userOnCheckoutPage();
            bukalapak.digitalGoodsCheckoutPage().validateDigitalGoodsCheckbox();
            bukalapak.digitalGoodsCheckoutPage().tapOnCheckbox(option);
        });

        And("^the Digital Goods fee on vp checkout page is( not | )displayed$", (String state) -> {
            bukalapak.digitalGoodsCheckoutPage().validateDigitalGoodsCheckoutFee(state);
        });

        And("the Digital Goods card section is displayed", () -> {
            bukalapak.checkoutNonMarketplacePage().userOnCheckoutPage();
            bukalapak.digitalGoodsCheckoutPage().validateDigitalGoodsCardSectionDisplayed();
        });

        Then("^the Digital Goods fee on vp invoice details page will have( not | )displayed$", (String state) -> {
            bukalapak.digitalGoodsCheckoutPage().validateDigitalGoodsInvoiceFee(state);
        });

        And("user taps on benefit button the Digital Goods", () -> {
            bukalapak.checkoutNonMarketplacePage().userOnCheckoutPage();
            bukalapak.digitalGoodsCheckoutPage().validateDigitalGoodsCardSectionDisplayed();
            bukalapak.digitalGoodsCheckoutPage().tapOnBenefitButton();
        });

        And("the Digital Goods benefit section is displayed", () -> {
            bukalapak.digitalGoodsCheckoutPage().validateBenefitSectionDisplayed();
        });

        And("user taps on oke mengerti button on benefit section the Digital Goods", () -> {
            bukalapak.digitalGoodsCheckoutPage().tapOnOkButtonBenefitSection();
        });

        And("the Digital Goods checkbox will have ticked", () -> {
            bukalapak.checkoutNonMarketplacePage().userOnCheckoutPage();
            bukalapak.digitalGoodsCheckoutPage().validateDigitalGoodsCheckboxChecked();
        });

        And("user taps on TnC button the Digital Goods", () -> {
            bukalapak.checkoutNonMarketplacePage().userOnCheckoutPage();
            bukalapak.digitalGoodsCheckoutPage().tapOnTncButton();
        });

        And("the Digital Goods TnC section is displayed", () -> {
            bukalapak.digitalGoodsCheckoutPage().validateTncSectionDisplayed();
            bukalapak.digitalGoodsCheckoutPage().validateAllTabDisplayed();
        });

        And("user taps on oke mengerti button on TnC section the Digital Goods", () -> {
            bukalapak.digitalGoodsCheckoutPage().tapOnOkButtonTncSection();
        });

        Then("^the Digital Goods (.*) insurance detail section will have displayed$", (String insuranceProduct) -> {
            switch (insuranceProduct.toLowerCase()) {
                case "covid":
                    bukalapak.digitalGoodsCheckoutPage().validateOnProductDetailPage();
                    bukalapak.insuranceProductDetailPage().tapOnBantuanTab();
                    bukalapak.insuranceProductDetailPage().tapOnManfaatTab();
                    bukalapak.insuranceProductDetailPage().tapOnKetentuanTab();
                    bukalapak.insuranceProductDetailPage().tapOnCaraKlaimTab();
                    break;
                case "covid stand alone":
                    bukalapak.digitalGoodsCheckoutPage().validateInsuranceDetailStandalone();
                    bukalapak.insuranceProductDetailPage().tapOnBantuanTab();
                    bukalapak.insuranceProductDetailPage().validateButtonBeli();
                    bukalapak.insuranceProductDetailPage().tapOnManfaatTab();
                    bukalapak.insuranceProductDetailPage().validateButtonBeli();
                    bukalapak.insuranceProductDetailPage().tapOnKetentuanTab();
                    bukalapak.insuranceProductDetailPage().validateButtonBeli();
                    bukalapak.insuranceProductDetailPage().tapOnCaraKlaimTab();
                    bukalapak.insuranceProductDetailPage().validateButtonBeli();
                    break;
                case "other":
                    bukalapak.digitalGoodsCheckoutPage().validateOnProductDetailPage();
                    bukalapak.insuranceProductDetailPage().tapOnCaraBeliTab();
                    bukalapak.insuranceProductDetailPage().tapOnTanyaJawabTab();
                    bukalapak.insuranceProductDetailPage().tapOnKontakTab();
                    bukalapak.insuranceProductDetailPage().tapOnManfaatTab();
                    bukalapak.insuranceProductDetailPage().tapOnKetentuanTab();
                    bukalapak.insuranceProductDetailPage().tapOnCaraKlaimTab();
                    break;
                default :
                    fail(String.format("%s step not implemented yet", insuranceProduct));
            }
        });
    }
}
