package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PrepaidPhoneCreditStepDefinitions extends TestInstrument implements En {

    public PrepaidPhoneCreditStepDefinitions() {

        When("user buys phone credits with valid data", () -> {
            /*
            Please simplify these 4 methods into a single method in PrepaidPhoneCreditPage class. So it
            cab be invoked as one line method from PrepaidPhoneCreditPage class.
             */
            bukalapak.prepaidPhoneCreditPage().isOnPrepaidPhoneCreditPage();
            bukalapak.prepaidPhoneCreditPage().enterPhoneNumber();
            bukalapak.prepaidPhoneCreditPage().choosePackage();
            bukalapak.prepaidPhoneCreditPage().submitForm();
        });

        When("user DANA buys phone credits with valid data", () -> {
            bukalapak.prepaidPhoneCreditPage().isOnPrepaidPhoneCreditPage();
            bukalapak.prepaidPhoneCreditPage().enterPhoneNumber();
            bukalapak.prepaidPhoneCreditPage().chooseFirstPackage();
            bukalapak.prepaidPhoneCreditPage().submitForm();
        });

        And("user inputs valid phone number on the Pulsa Prabayar landing page", () -> {
            bukalapak.prepaidPhoneCreditPage().isOnPrepaidPhoneCreditPage();
            bukalapak.prepaidPhoneCreditPage().enterPhoneNumber();
        });

        When("user copies promo code on the Pulsa Prabayar landing page", () -> {
            bukalapak.prepaidPhoneCreditPage().tapOnPromoTerbaru();
            bukalapak.prepaidPhoneCreditPage().validatePromoSalin();
            bukalapak.vpBasePage().tapSalinPrepaidPhoneCreditPromo();
            bukalapak.prepaidPhoneCreditPage().tapOnClosePromoTerbaru();
        });

        Then("the selected promo for Pulsa Prabayar will have copied", () -> {
            bukalapak.prepaidPhoneCreditPage().tapOnBayarButton();
            bukalapak.checkoutNonMarketplacePage().tapOnVoucherField();
            bukalapak.checkoutNonMarketplacePage().pasteVoucherCode();
            bukalapak.checkoutNonMarketplacePage().verifyTersalin();
        });

        When("user chooses first denom on the Pulsa Prabayar landing page", () -> {
            bukalapak.prepaidPhoneCreditPage().tapFirstDenom();
        });
        
        When("^user go to Pulsa Prabayar page using deeplink$", () -> {
            bukalapak.iOSBasePage().openDeepLink(dotenv.get("DEEPLINK_PULSA"));
            bukalapak.prepaidPhoneCreditPage().isOnPrepaidPhoneCreditPage();
        });

        When("^user buy Pulsa Prabayar with Indosat provider and amount Rp \"([^\"]*)\"$", (String amount ) -> {
            bukalapak.prepaidPhoneCreditPage().tapOnJumlahPulsaButton(amount);
            bukalapak.prepaidPhoneCreditPage().submitForm();
        });
    }
}
