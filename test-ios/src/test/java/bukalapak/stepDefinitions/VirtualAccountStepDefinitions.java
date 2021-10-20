package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class VirtualAccountStepDefinitions extends TestInstrument implements En {

    public VirtualAccountStepDefinitions() {

        Given("user is in \"virtual_account\" page", () -> {
            bukalapak.virtualAccountPage().userOnVirtualAccountPage();
        });

        Then("user is in \"virtual_account_vp\" page", () -> {
            bukalapak.virtualAccountPage().userOnVirtualAccountVPPage();
        });

        And("user choose \"([^\"]*)\" on transfer bank otomatis", (String bankName) -> {
            bukalapak.virtualAccountPage().isOnVirtualAccountPage();
            bukalapak.virtualAccountPage().chooseBankAutomaticTransfer(bankName);
        });

    }
}
