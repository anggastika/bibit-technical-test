package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class SuperSellerOptOutStepDefinitions extends TestInstrument implements En {
    public SuperSellerOptOutStepDefinitions() {
        And("user is in \"Nonaktifkan Super Seller\" page", () -> {
            bukalapak.superSellerOptOutPage().verifyInfoNonaktifkanSuperSeller();
        });
    }
}
