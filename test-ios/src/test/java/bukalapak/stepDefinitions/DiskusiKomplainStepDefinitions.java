package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class DiskusiKomplainStepDefinitions extends TestInstrument implements En {

    public DiskusiKomplainStepDefinitions() {

        Given("user is in \"Diskusi Komplain\" page", () -> {
            bukalapak.diskusiKomplainPage().userOnDiskusiKomplainPage();
        });

        When("^user fill in \"([^\"]*)\" on chat text field$", (String chatText) -> {
            bukalapak.diskusiKomplainPage().fillChatField(chatText);
        });

        And("^upload attachment picture$", () -> {
            bukalapak.diskusiKomplainPage().uploadAttachment();
        });

        And("^tap on send chat discussion icon$", () -> {
            bukalapak.diskusiKomplainPage().userOnDiskusiKomplainPage();
            bukalapak.diskusiKomplainPage().tapIconOnKomplainPage("send");
        });

        Then("^display chat and picture on discussion column$", () -> {
            bukalapak.diskusiKomplainPage().assertChatColumn();
        });

        Then("^is on attachment page$", () -> {
            bukalapak.diskusiKomplainPage().userIsOnAttachmentPage();
            bukalapak.diskusiKomplainPage().pressBackButton();
        });

        When("user tap on \"Detail\" link komplain", () -> {
            bukalapak.diskusiKomplainPage().tapIconOnKomplainPage("detail");
        });

        When("^user tap on attachment icon$", () -> {
            bukalapak.diskusiKomplainPage().tapIconOnKomplainPage("file");
        });

        And("^user close complaint from Diskusi Komplain$", () -> {
            bukalapak.diskusiKomplainPage().tapIconOnKomplainPage("detail");
            bukalapak.detailKomplainPage().tutupKomplain();
        });

        And("^seller accept solution with item (not\\s)?to be returned$", (String args) -> {
            Boolean item = args == null;

            bukalapak.diskusiKomplainPage().tapTerimaSolution(item);
        });

        Then("display chat and picture on discussion column retur", () -> {
            bukalapak.diskusiKomplainPage().assertChatAfterRetur();
        });

        And("display all of attachment in discussion complaint", () -> {
            bukalapak.diskusiKomplainPage().userIsOnAttachmentPage();
            bukalapak.diskusiKomplainPage().displayAllAttachment();
            bukalapak.diskusiKomplainPage().pressBackButton();
        });

        When("seller accept return solution", () -> {
            bukalapak.diskusiKomplainPage().assertSolutionReturn();
            bukalapak.diskusiKomplainPage().tapActionForSolution("accept");
        });

        When("user is able to \"accept\" return solution", () -> {
            bukalapak.diskusiKomplainPage().assertSolutionReturn();
            bukalapak.diskusiKomplainPage().tapActionForSolution("accept");
        });

        When("user is able to \"change\" return solution", () -> {
            bukalapak.diskusiKomplainPage().assertSolutionReturn();
            bukalapak.diskusiKomplainPage().tapActionForSolution("change");
        });

        And("user is able to tap \"ya, terima solusi\" confirmation at modal terima solusi", () -> {
            bukalapak.diskusiKomplainPage().assertModalTerimaSolusi();
            bukalapak.diskusiKomplainPage().tapActionForConfirmation("ya, terima solusi");
        });

        Then("user is in Diskusi Komplain page with input resi number button", () -> {
            bukalapak.diskusiKomplainPage().tapInputResiButton();
        });

        And("user tap terima barang at diskusi komplain page", () -> {
            bukalapak.diskusiKomplainPage().tapTerimaBarang();
        });

        And("user tap konfirmasi at modal terima barang", () -> {
            bukalapak.diskusiKomplainPage().tapKonfirmasiTerimaBarang();
        });
    }
}
