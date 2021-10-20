package bukalapak.stepDefinitions.vp.postpaid;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PenerimaanNegaraStepDefinitions extends TestInstrument implements En {

    public PenerimaanNegaraStepDefinitions() {
        When("user tap MPNG3 information", () -> {
            bukalapak.penerimaanNegaraLandingPage().tapOnMPNGSection();
        });

        Then("user verify page containing DJP, DJBC, and DJA information", () -> {
            bukalapak.penerimaanNegaraLandingPage().validateMPNGPage();
        });

        When("^user inputs (valid|invalid) billing code on the Penerimaan Negara landing page$", (String billingCodeType) -> {
            bukalapak.penerimaanNegaraLandingPage().tapOnJenisPenerimaanNegaraDropdown();
            bukalapak.penerimaanNegaraLandingPage().chooseJenisPenerimaanNegara(dotenv.get("JENIS_PENERIMAAN_PAJAK"));
            bukalapak.penerimaanNegaraLandingPage().typeOnBillingCodeField(billingCodeType.toLowerCase());
            bukalapak.penerimaanNegaraLandingPage().tapOnGeneralButton("Lanjutkan");
        });

        Then("the error message will have displayed on Penerimaan Negara landing page", () -> {
            bukalapak.penerimaanNegaraLandingPage().validateOverlayInvalidBillCode();
        });
    }
}
