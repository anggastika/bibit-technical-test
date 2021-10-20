package bukalapak.stepDefinitions.prio;

import bukalapak.TestInstrument;
import bukalapak.stepDefinitions.BaseStepDefinitions;
import cucumber.api.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UlasanInstanKonfirmasiBerhentiStepDefinitions extends TestInstrument implements En {

    public UlasanInstanKonfirmasiBerhentiStepDefinitions() {
        Given("user is in \"Ulasan Instan Konfirmasi Berhenti Berlangganan\" page", () -> {
            bukalapak.ulasanInstanKonfirmasiBerhentiPage().userOnKonfirmasiBerhentiPage();
        });

        When("^user choose stop reason as \"([^\"]*)\"$", (String reason) -> {
            bukalapak.ulasanInstanKonfirmasiBerhentiPage().selectReason(reason);
        });
    }
}
