package bukalapak.stepDefinitions.financing.PembiayaanTunai;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PembiayaanTunaiListPartnerStepDefinitions extends TestInstrument implements En {
    public PembiayaanTunaiListPartnerStepDefinitions() {

        Then("^user is in List Partner Page", () -> {
            bukalapak.pembiayaanTunaiListPartnerPage().validateInListPartnerPage();
            bukalapak.pembiayaanTunaiListPartnerPage().goToHomePage();
        });

        When("^user choose partner \"([^\"]*)\" on Pembiayaan Tunai list installment page", (String partner) -> {
            bukalapak.pembiayaanTunaiListPartnerPage().selectPartner(partner);
            bukalapak.pembiayaanTunaiListPartnerPage().tapOnCheckBox();
            bukalapak.pembiayaanTunaiListPartnerPage().tapOnLanjutIsiForm();
        });
    }
}