package bukalapak.stepDefinitions.csi;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PengajuanKomplainStepDefinitions extends TestInstrument implements En {

    public PengajuanKomplainStepDefinitions() {
        Given("user is in \"Pengajuan Komplain\" page", () -> {
            bukalapak.pengajuanKomplainPage().userOnPengajuanKomplainPage();
        });

        When("user upload image in Pengajuan Komplain page", () -> {
            bukalapak.pengajuanKomplainPage().uploadImage();
        });

        When("user choose complain reason \"([^\"]*)\" in Pengajuan Komplain page", (String reason) -> {
            bukalapak.pengajuanKomplainPage().chooseReason(reason);
        });

        Then("user see default address in Pengajuan Komplain page", () -> {
            bukalapak.pengajuanKomplainPage().verifyDefaultAddress();
        });
    }
}
