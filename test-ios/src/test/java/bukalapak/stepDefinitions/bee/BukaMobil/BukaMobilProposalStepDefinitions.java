package bukalapak.stepDefinitions.bee.BukaMobil;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaMobilProposalStepDefinitions extends TestInstrument implements En {

    public BukaMobilProposalStepDefinitions() {
        Then("user recirect to Proposal BukaMobil page", () -> {
            bukalapak.bukaMobilProposalPage().validateProposalPage();
        });

        Then("user upload KTP", () -> {
            bukalapak.bukaMobilProposalPage().uploadKTP();
        });

        And("user see proposal summary", () -> {
            bukalapak.bukaMobilProposalPage().validateProposalSummary();
        });

        And("user see term and condition modal", () -> {
            bukalapak.bukaMobilProposalPage().validateTermCondition();
        });

        And("user see select all checkbox on term condition modal", () -> {
            bukalapak.bukaMobilProposalPage().selectTermCondictionModal();
        });

        And("user tap ajukan button", () -> {
            bukalapak.bukaMobilProposalPage().tapAjukanBtn();
        });

        And("user see information about proposal was submitted", () -> {
            bukalapak.bukaMobilProposalPage().validateSubmitProposal();
        });

        And("user see tap Bayar Booking Fee button", () -> {
            bukalapak.bukaMobilProposalPage().tapBayarBookingFeeBtn();
        });

        Then("user redirect to Payment Method page", () -> {
            bukalapak.bukaMobilProposalPage().validatePaymentMethod();
        });
    }
}
