package bukalapak.stepDefinitions.csi;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class KomplainStepDefinitions extends TestInstrument implements En {

    public KomplainStepDefinitions() {
        Given("user is in \"Komplain\" page", () -> {
            bukalapak.komplainPage().userOnKomplainPage();
        });

        When("user go to komplain page", () -> {
            bukalapak.komplainPage().goToKomplainPageViaDeepLink();
        });

        When("user going back to Komplain page", () -> {
            bukalapak.komplainPage().goToKomplainPageViaDeepLink();
            bukalapak.komplainPage().userOnKomplainPage();
        });

        When("^user go to BukaBantuan from Komplain Page$", () -> {
            bukalapak.akunPage().clickAkunMenu();
            bukalapak.akunPage().scrollAndClickKomplain();
            bukalapak.komplainPage().userOnKomplainPage();
            bukalapak.komplainPage().tapAddKomplain();
        });

        Then("^user validate BukaBantuan CWP Card on Komplain page$", () -> {
            bukalapak.iOSBasePage().doPullRefresh(1);
            bukalapak.komplainPage().tapFirstTransaction();
            bukalapak.diskusiKomplainPage().userOnDiskusiKomplainPage();
            bukalapak.diskusiKomplainPage().tapDetailKomplain();
            bukalapak.detailKomplainPage().userOnDetailKomplainBukaBantuan();
            bukalapak.detailKomplainPage().validateNomorTransaksi("noTransaksi");
        });

        And("^user create komplain \"([^\"]*)\" with problem \"([^\"]*)\"$", (String komplain, String problem) -> {
            bukalapak.komplainPage().chooseKomplain(komplain);
            bukalapak.bukaBantuanWebviewPage().makeComplaintForm(problem);
        });

        And("^user go to latest komplain in Komplain page$", () -> {
            bukalapak.komplainPage().goToKomplainPageViaDeepLink();
            bukalapak.komplainPage().userOnKomplainPage();
            bukalapak.komplainPage().searchTransaction("noTransaksi");
            bukalapak.komplainPage().tapFirstTransaction();
        });

        And("user go to latest komplain in Komplain page as buyer", () -> {
            bukalapak.komplainPage().goToKomplainPageViaDeepLink();
            bukalapak.komplainPage().userOnKomplainPage();
            bukalapak.komplainPage().tapBuyerTab();
            bukalapak.komplainPage().searchTransaction("noTransaksi");
            bukalapak.komplainPage().tapFirstTransaction();
        });

        And("user go to latest komplain in Komplain page as seller", () -> {
            bukalapak.komplainPage().goToKomplainPageViaDeepLink();
            bukalapak.komplainPage().userOnKomplainPage();
            bukalapak.komplainPage().tapSellerTab();
            bukalapak.komplainPage().searchTransaction("noTransaksi");
            bukalapak.komplainPage().tapFirstTransaction();
        });

        And("^user close complaint from last transaction$", () -> {
            bukalapak.komplainPage().goToKomplainPageViaDeepLink();
            bukalapak.komplainPage().userOnKomplainPage();
            bukalapak.komplainPage().searchTransaction("noTransaksi");
            bukalapak.komplainPage().tapFirstTransaction();
            bukalapak.diskusiKomplainPage().userOnDiskusiKomplainPage();
            bukalapak.diskusiKomplainPage().tapIconOnKomplainPage("detail");
            bukalapak.detailKomplainPage().tutupKomplain();
            bukalapak.detailKomplainPage().userOnDetailKomplainBukaBantuan();
            bukalapak.detailKomplainPage().validateStatusKomplain("Komplain Ditutup");
        });

        Then("^complaint is successfully closed$", () -> {
            bukalapak.komplainPage().goToKomplainPageViaDeepLink();
            bukalapak.komplainPage().userOnKomplainPage();
            bukalapak.iOSBasePage().doPullRefresh(1);
            bukalapak.komplainPage().searchTransaction("noTransaksi");
            bukalapak.komplainPage().validateStatusKomplain("Komplain Selesai");
            bukalapak.komplainPage().tapFirstTransaction();
            bukalapak.diskusiKomplainPage().userOnDiskusiKomplainPage();
            bukalapak.diskusiKomplainPage().validateStatusKomplain("selesai");
        });

        When("^user reopen complaint$", () -> {
            bukalapak.diskusiKomplainPage().lanjutkanKomplain();
        });

        Then("^complaint is successfully reopened$", () -> {
            bukalapak.diskusiKomplainPage().userOnDiskusiKomplainPage();
            bukalapak.diskusiKomplainPage().validateStatusKomplain("Komplain Diajukan");
        });

        When("^user taps \"([^\"]*)\" filter on Komplain page$", (String complainState) -> {
            bukalapak.komplainPage().tapFilter(complainState);
        });

        Then("^display complaint list with \"([^\"]*)\" filter$", (String complainState) -> {
            bukalapak.komplainPage().tapFirstTransaction();
            bukalapak.diskusiKomplainPage().userOnDiskusiKomplainPage();
            bukalapak.diskusiKomplainPage().validateStatusKomplain(complainState);
        });

        When("^user search \"([^\"]*)\" on Komplain page$", (String transactionNumber) -> {
            bukalapak.komplainPage().searchTransaction(transactionNumber);
        });

        Then("^display complaint with transaction number \"([^\"]*)\"$", (String transactionNumber) -> {
            bukalapak.komplainPage().tapFirstTransaction();
            bukalapak.diskusiKomplainPage().userOnDiskusiKomplainPage();
            bukalapak.diskusiKomplainPage().tapDetailKomplain();
            bukalapak.detailKomplainPage().userOnDetailKomplainBukaBantuan();
            bukalapak.detailKomplainPage().validateNomorTransaksi(transactionNumber);
        });

        Then("^display complaint with invoice number \"([^\"]*)\"$", (String invoiceNumber) -> {
            bukalapak.komplainPage().tapFirstTransaction();
            bukalapak.diskusiKomplainPage().userOnDiskusiKomplainPage();
            bukalapak.diskusiKomplainPage().tapDetailKomplain();
            bukalapak.detailKomplainPage().userOnDetailKomplainBukaBantuan();
            bukalapak.detailKomplainPage().validateNomorTagihan(invoiceNumber);
        });

        Then("^display complaint with product name \"([^\"]*)\"$", (String productName) -> {
            bukalapak.komplainPage().validateProductnName(productName);
        });

        And("^user check total of unread complaint on komplain badge$", () -> {
            bukalapak.homePage().selectNavigationTab("akun");
            bukalapak.akunPage().checkKomplainBadge();
        });

        And("^user validate total of unread complaint is decreased$", () -> {
            bukalapak.akunPage().validateComplaintDecreased();
        });

        And("user in the discussion compaint page", () -> {
            bukalapak.diskusiKomplainPage().userOnDiskusiKomplainPage();
        });

    }
}
