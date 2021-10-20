package bukalapak.stepDefinitions.vp.prepaid;

import bukalapak.TestInstrument;
import bukalapak.data.vp.prepaid.PaketDataData;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

public class PaketDataStepDefinitions extends TestInstrument implements En {

    public PaketDataStepDefinitions() {

        When("the Paket Data denominations will have displayed with:", (DataTable args) -> {
            List<Map<String, String>> scenarios = args.asMaps(String.class, String.class);

            for (Map<String, String> scenario : scenarios) {
                bukalapak.paketDataLandingPage().typeOnFieldNomorTelepon(scenario.get("number"));
                bukalapak.paketDataLandingPage().validateInputtedNumber(scenario.get("displayed_number"));
                if (!scenario.get("validation_text").isEmpty()) // element only displayed on negative scenario
                    bukalapak.paketDataLandingPage().validateTextFieldValidation(scenario.get("validation_text"));
                bukalapak.paketDataLandingPage().validateDenomination(scenario.get("denomination").equals("displayed"));
            }
        });

        Then("^the (transaction|user's) phone number will have pre-filled on the Paket Data landing page$", (String flag) -> {
            bukalapak.iOSBasePage().openDeepLink("/bl/paket-data");
            bukalapak.paketDataLandingPage().validateOnPage();
            if (flag.equals("transaction")) bukalapak.paketDataLandingPage().validateInputtedNumber(PaketDataData.getPhoneNumber());
            else bukalapak.paketDataLandingPage().validateInputtedNumber(dotenv.get("PAKET_DATA_BUYER_PHONE_NUMBER"));
        });

        Then("the Paket Data landing page will have displayed", () -> {
            bukalapak.paketDataLandingPage().validateOnPage();
        });
    }
}
