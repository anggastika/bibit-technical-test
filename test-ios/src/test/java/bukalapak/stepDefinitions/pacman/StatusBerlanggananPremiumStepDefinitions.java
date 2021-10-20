package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class StatusBerlanggananPremiumStepDefinitions extends TestInstrument implements En {
    public StatusBerlanggananPremiumStepDefinitions() {
        Then("^user is shown status as Premium (Basic|Professional|Platinum)$", (String packageName) -> {
            bukalapak.statusBerlanggananPremiumPage().verifyPremiumPackage(packageName);
        });

        Then("^user is shown Premium auto extend status as (active|inactive)$", (String status) -> {
            bukalapak.statusBerlanggananPremiumPage().verifyAutoExtendStatus(status.equals("active"));
        });

        Given("user is in \"Status Berlangganan Premium\" page", () -> {
            bukalapak.statusBerlanggananPremiumPage().userOnStatusBerlangganan();
        });

        Given("user is in \"Konfirmasi Berhenti Langganan Premium\" page", () -> {
            bukalapak.statusBerlanggananPremiumPage().userOnKonfirmasiBerhenti();
        });
    }
}
