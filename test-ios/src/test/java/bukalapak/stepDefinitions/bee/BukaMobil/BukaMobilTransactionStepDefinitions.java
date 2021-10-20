package bukalapak.stepDefinitions.bee.BukaMobil;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaMobilTransactionStepDefinitions extends TestInstrument implements En {
    public BukaMobilTransactionStepDefinitions() {
        Then("user redirect to Transaction BukaMobil page", () -> {
            bukalapak.bukaMobilTransactionPage().validateTransactionPage();
        });

        And("user see In Progress proposal", () -> {
            bukalapak.bukaMobilTransactionPage().tapInProgressTab();
            bukalapak.bukaMobilTransactionPage().validateInProgressProposal();
        });

        And("user cancel proposal", () -> {
            bukalapak.bukaMobilTransactionPage().cancelProposal();
        });

        And("user tap Batalkan Pengajuan button", () -> {
            bukalapak.bukaMobilTransactionPage().tapBatalkanPegajuan();
            bukalapak.bukaMobilTransactionPage().validateCancelModal();
        });

        And("user tap Cancel checkbox", () -> {
            bukalapak.bukaMobilTransactionPage().tapCancelCheckbox();
        });

        And("user tap Batalkan button on cancel proposal modal", () -> {
            bukalapak.bukaMobilTransactionPage().tapBatalkanBtn();
        });

        And("^user input (\"([^\"]*)\" as )?cancel reason$", (String reason) -> {
            bukalapak.bukaMobilTransactionPage().inputCancelReason(reason);
        });
    }
}
