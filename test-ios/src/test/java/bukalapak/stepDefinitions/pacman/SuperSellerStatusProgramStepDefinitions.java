package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class SuperSellerStatusProgramStepDefinitions extends TestInstrument implements En {
    public SuperSellerStatusProgramStepDefinitions() {
        Given("user is in \"Super Seller Status Program\" page", () -> {
            bukalapak.superSellerStatusProgramPage().userOnSuperSellerStatusProgram();
        });

        When("^user verify Kunjungi Sekarang button display$", () -> {
            bukalapak.superSellerStatusProgramPage().verifyKunjungiDashboardButton();
        });

        And("^user is shown Status Super Seller$", () -> {
            bukalapak.superSellerStatusProgramPage().verifyStatusProgram();
        });

        When("^user click ganti paket in status program$", () -> {
            bukalapak.superSellerStatusProgramPage().clickGantiPaket();
        });

        And("^user( not)? see info badge super seller$", (String status) -> {
            bukalapak.superSellerStatusProgramPage().verifyInfoBadgeSuperSeller(status);
        });

        When("^user tap tutup button in info badge super seller$", () -> {
            bukalapak.superSellerStatusProgramPage().tapTutupInfoBadgeBtn();
        });

        Then("^user will shown opt out confirmation pop up$", () -> {
            bukalapak.superSellerStatusProgramPage().verifyOptOutPopUp();
        });

        And("^user( not)? see label baru in status program super seller page$", (String state) -> {
            bukalapak.superSellerStatusProgramPage().verifyBadgeBaru(state);
        });

        When("user tap mutasi super seller button in status program super seller page", () -> {
            bukalapak.superSellerStatusProgramPage().tapMutasiSuperSeller();
        });
    }
}
