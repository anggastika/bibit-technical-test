package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PantauanSainganStepDefinitions extends TestInstrument implements En {
    public PantauanSainganStepDefinitions() {
        Then("^user is in Pantauan Saingan page with keyword \"([^\"]*)\"$", (String keyword) -> {
            bukalapak.pantauanSainganPage().verifyKeywordTitle(keyword);
        });

        Then("^user is in Pantauan Saingan page with sorter \"([^\"]*)\"$", (String sorter) -> {
            bukalapak.pantauanSainganPage().verifySorter(sorter);
        });

        Given("user is in \"Pantauan Saingan\" page", () -> {
            bukalapak.pantauanSainganPage().userOnPantauanSaingan();
        });

        Then("^user is shown monitoring notification as (.+)$", (String notifyStatus) -> {
            bukalapak.pantauanSainganPage().verifyNotificationToggle(notifyStatus);
        });

        Then("^user is shown last update time of monitoring$", () -> {
            bukalapak.pantauanSainganPage().verifyPerbaruiSection();
        });

        Then("^user is shown list barang saingan on pantauan page$", () -> {
            bukalapak.pantauanSainganPage().verifyListOfBarangSaingan();
        });

        When("^user click barang saingan (\\d+) on pantauan page$", (Integer index) -> {
            bukalapak.pantauanSainganPage().tapBarangSaingan(index);
        });

        Then("^user is shown list barang saya on pantauan page$", () -> {
            bukalapak.pantauanSainganPage().verifyListOfBarangSaya();
        });

        Then("^user is shown promoted push button on list barang saya$", () -> {
            bukalapak.pantauanSainganPage().verifyPromotedPush();
        });

        Then("^user is shown ubah button on list barang saya$", () -> {
            bukalapak.pantauanSainganPage().verifyUbahButton();
        });

        Then("^user is shown pop up push bukadompet on pantauan page$", () -> {
            bukalapak.pantauanSainganPage().verifyPushBukadompetPopup();
        });
    }
}
