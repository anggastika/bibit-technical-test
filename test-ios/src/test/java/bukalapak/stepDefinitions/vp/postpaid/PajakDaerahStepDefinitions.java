package bukalapak.stepDefinitions.vp.postpaid;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PajakDaerahStepDefinitions extends TestInstrument implements En {

    public PajakDaerahStepDefinitions() {
        When("user validate Pajak PBB data", () -> {
            bukalapak.pajakDaerahPage().validateDetailInfo();
        });

        When("user buys a Pajak PBB product with invalid data", () -> {
            bukalapak.pajakDaerahPage().inputValidProvince();
            bukalapak.pajakDaerahPage().inputValidCity();
            bukalapak.pajakDaerahPage().inputValidSPPT(dotenv.get("PAJAK_DAERAH_YEAR_2019"));
            bukalapak.pajakDaerahPage().inputInvalidNOP();
        });

        When("show error message on the Pajak PBB landing page", () -> {
            bukalapak.pajakDaerahConfirmDataPage().validateInvalidNOP();
        });

        When("user submit Pajak PBB data", () -> {
            bukalapak.pajakDaerahPage().submitDetailInfo();
        });
    }
}
