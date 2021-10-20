package bukalapak.stepDefinitions.prio;

import bukalapak.TestInstrument;
import bukalapak.stepDefinitions.BaseStepDefinitions;
import cucumber.api.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PembeliPrioritasStepDefinitions extends TestInstrument implements En {

    public PembeliPrioritasStepDefinitions() {
        Given("user is in \"Pembeli Prioritas Info\" page", () -> {
            bukalapak.pembeliPrioritasPage().userOnPembeliPrioritasInfoPage();
        });

        Given("user is in \"Pembeli Prioritas Status\" page", () -> {
            bukalapak.pembeliPrioritasPage().userOnPembeliPrioritasStatusPage();
        });

        Given("user is in \"Pembeli Prioritas Tagihan\" page", () -> {
            bukalapak.pembeliPrioritasTagihanPage().userOnPembeliPrioritasTagihanPage();
        });

        Given("user is in \"Pembeli Prioritas Berhenti Berlangganan\" page", () -> {
            bukalapak.pembeliPrioritasBerhentiBerlanggananPage().userOnBerhentiBerlanggananPage();
        });

        When("^user see \"([^\"]*)\" benefits from \"([^\"]*)\"$", (String packageType, String entryPoint) -> {
            bukalapak.pembeliPrioritasPage().checkPriorityBenefit(packageType, entryPoint);
        });

        When("^user verify auto-extend section is (not-)?active$", (String state) -> {
            bukalapak.pembeliPrioritasPage().checkAutoExtendSection(state != null);
        });

        When("^user select reason (.*) to stop auto-extend$", (String reason) -> {
            bukalapak.pembeliPrioritasPage().selectReason(reason);
        });

        When("^user verify cashback section is displayed$", () -> {
            bukalapak.pembeliPrioritasPage().checkCashbackSection();
        });

        When("^user verify cashbacks$", () -> {
            bukalapak.pembeliPrioritasPage().checkCashbacks();
        });

        And("^user verify ongkir benefit is used$", () -> {
            bukalapak.pembeliPrioritasPage().checkFreeShippingUsage();
        });

        And("user see the pending payment for Pembeli Prioritas in (.*) tab", (String priorityTab) -> {
            bukalapak.pembeliPrioritasPage().checkPendingPayment(priorityTab);
        });

        Then("user see Priority Buyer free trial section", () -> {
            bukalapak.pembeliPrioritasPage().checkFreeTrialSection();
        });

        //DANA-Section
        And("user is \"([^\"]*)\" info to join DANA", (String state) -> {
            bukalapak.pembeliPrioritasPage().checkJoinDanaSection(state);
        });

        When("^user taps on Hubungkan DANA button", () -> {
            bukalapak.pembeliPrioritasPage().tapOnHubungkanDanaButton();
        });

        When("^user taps on Top Up DANA button", () -> {
            bukalapak.pembeliPrioritasPage().tapOnTopUpDanaButton();
        });

        And("^user is shown info pay with DANA", () -> {
            bukalapak.pembeliPrioritasPage().checkPayWithDanaSection();
        });

        Then("^user is shown pop up Hubungkan DANA", () -> {
            bukalapak.pembeliPrioritasPage().showPopUpHubungkanDana();
        });
    }
}
