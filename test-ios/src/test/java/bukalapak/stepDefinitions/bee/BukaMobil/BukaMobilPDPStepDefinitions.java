package bukalapak.stepDefinitions.bee.BukaMobil;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaMobilPDPStepDefinitions extends TestInstrument implements En {

    public BukaMobilPDPStepDefinitions() {
        Then("user redirect to PDP BukaMobil page", () -> {
            bukalapak.bukaMobilPDP().validatePDPBukaMobil();
        });

        When("user choose payment BukaMobil as Tunai", () -> {
            bukalapak.bukaMobilPDP().selectTunaiRadioBtn();
        });

        When("user choose payment BukaMobil as Kredit", () -> {
            bukalapak.bukaMobilPDP().selectKreditRadioBtn();
        });

        When("user tap Lanjutkan button", () -> {
            bukalapak.bukaMobilPDP().tapLanjutkanBtn();
        });

        Then("^user view details schema payment (tunai|kredit)$", (String paymentMethod) -> {
            bukalapak.bukaMobilPDP().validateSchemaPaymentInfo(paymentMethod.equals("tunai"));
        });
    }
}
