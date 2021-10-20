package bukalapak.stepDefinitions.vp.prepaid;

import bukalapak.TestInstrument;
import bukalapak.data.vp.prepaid.PulsaPrabayarData;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;

/**
 * Created by Ayu Musfita on 24/02/20.
 */
public class PulsaPrabayarStepDefinitions extends TestInstrument implements En {
    public PulsaPrabayarStepDefinitions(){

        Then("^the (transaction|user's) phone number will have pre-filled on the Pulsa Prabayar landing page$", (String flag) -> {
            bukalapak.iOSBasePage().openDeepLink("/bl/pulsa");
            bukalapak.pulsaPrabayarLandingPage().validateOnPage();
            if (flag.equals("transaction")) bukalapak.pulsaPrabayarLandingPage().validateFieldPhoneNumber(PulsaPrabayarData.getPhoneNumber());
            else bukalapak.pulsaPrabayarLandingPage().validateFieldPhoneNumber(dotenv.get("PULSA_PRABAYAR_BUYER_PHONE_NUMBER"));
        });

        When("the Pulsa Prabayar denominations will have displayed with:", (DataTable args) -> {
            List<Map<String, String>> scenarios = args.asMaps(String.class, String.class);

            for (Map<String, String> scenario : scenarios) {
                bukalapak.pulsaPrabayarLandingPage().typeOnFieldPhoneNumber(scenario.get("number"));
                bukalapak.pulsaPrabayarLandingPage().validateFieldPhoneNumber(scenario.get("displayed_number"));
                if (!scenario.get("validation_text").isEmpty()) // element only displayed on negative scenario
                    bukalapak.pulsaPrabayarLandingPage().validateTextFieldValidation(scenario.get("validation_text"));
                bukalapak.pulsaPrabayarLandingPage().validateDenomination(scenario.get("denomination").equals("displayed"));
            }
        });

        Then("the Pulsa Prabayar landing page will have displayed", () -> {
            bukalapak.pulsaPrabayarLandingPage().validateOnPage();
        });

        When("^user imports a number from (contact|user profile) on the Pulsa Prabayar landing page$", (String source) -> {
            if (source.equals("contact")) {
                bukalapak.pulsaPrabayarLandingPage().importContactNumber();
            } else {
                bukalapak.pulsaPrabayarLandingPage().importProfileNumber();
            }
        });
    }
}
