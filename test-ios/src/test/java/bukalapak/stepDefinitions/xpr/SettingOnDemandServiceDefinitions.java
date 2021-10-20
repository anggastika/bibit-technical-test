package bukalapak.stepDefinitions.xpr;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;


public class SettingOnDemandServiceDefinitions extends TestInstrument implements En {
    public SettingOnDemandServiceDefinitions() {

        When("user enable \"([^\"]*)\" service", (String arg0) -> {
            bukalapak.jasaPengirimanPage().userEnableOrDisableOnDemandService(arg0);
        });

        When("user disable \"([^\"]*)\" service", (String arg0) -> {
            bukalapak.jasaPengirimanPage().userEnableOrDisableOnDemandService(arg0);
        });

        Then("user will see set location window", () -> {
            bukalapak.jasaPengirimanPage().verifyPopUpWindow();
        });

        And("user close set location window", () -> {
            /*
              Avoid using direct invoke of BasePage methods. Since its been inherited. Use the related page instead.
             */
            bukalapak.jasaPengirimanPage().tapElement("jasa_pengiriaman_set_koordinat_close_button");
            bukalapak.jasaPengirimanPage().userOnJasaPengirimanPage();
        });

        Then("user successfully \"([^\"]*)\" \"([^\"]*)\" service", (String arg1, String arg0) -> {
            bukalapak.jasaPengirimanPage().verifyElementExist("jasa_pengiriman_title");
            bukalapak.jasaPengirimanPage().verifyElementExist("jasa_pengiriman_back_button");
            bukalapak.jasaPengirimanPage().userOnJasaPengirimanPage();
        });

        And("user wait alamat page", () -> {
            bukalapak.alamatPage().waitLoadingBar();
        });

        Then("Main address has not changed", () -> {
            bukalapak.jasaPengirimanPage().verifyElementExist("pilih_alamat_utama_title");
            bukalapak.pilihAlamatUtamaPage().userOnPilihAlamatUtamaPage();
        });

        Then("Main address has changed", () -> {
            bukalapak.alamatPage().userOnAlamatPage();
        });

        And("verify \"([^\"]*)\" service is enable", (String arg0) -> {
            bukalapak.jasaPengirimanPage().verifyOnDemandEnable(arg0);
        });

        And("user save the setting", () -> {
            bukalapak.jasaPengirimanPage().changeTheSettingOnDemand();
        });

        Given("user is in \"jasa_pengiriman\" page", () -> {
            bukalapak.jasaPengirimanPage().userOnJasaPengirimanPage();
        });

        Then("user validate courier luar negeri section", () -> {
            bukalapak.jasaPengirimanPage().validateJanioInfo();
        });

        When("user tap checkbox courier \"([^\"]*)\"", (String courier) -> {
            bukalapak.jasaPengirimanPage().tapCheckboxCourier(courier);
        });

        Then("user validate service \"([^\"]*)\" is \"([^\"]*)\"", (String courier, String status) -> {
            bukalapak.jasaPengirimanPage().validateCheckboxCourier(courier, status);
        });

        And("user validate popup COD on jasa pengiriman page", () -> {
            bukalapak.jasaPengirimanPage().verifyPopUpCOD();
        });

        And("user click COD confirmation button", () -> {
            bukalapak.jasaPengirimanPage().clickPopUpCODButton();
        });

        And("user click set location button on popup jasa pengiriman page", () -> {
            bukalapak.jasaPengirimanPage().verifyPopUpSetLocation();
            bukalapak.jasaPengirimanPage().clickPopUpSetLocationButton();
        });

        And("user verify show maps location", () -> {
            bukalapak.jasaPengirimanPage().allowPopupMapsPermission();
            bukalapak.jasaPengirimanPage().verifyShowMaps();
        });

        And("user back to jasa pengiriman page", () -> {
            bukalapak.jasaPengirimanPage().tapBackToJasaPengirimanPage();
        });

        And("^user validate \"([^\"]*)\" is (available|not available) in the list$", (String courier, String availability) -> {
            bukalapak.jasaPengirimanPage().validateAvailabilityCourier(courier, availability);
        });

        And("user set courier \"([^\"]*)\" in Jasa Pengiriman page", (String courier) -> {
            bukalapak.akunPage().clickAkunMenu();
            bukalapak.akunPage().tapPelapakTab();
            bukalapak.akunPage().clickJasaPengiriman();
            bukalapak.jasaPengirimanPage().userOnJasaPengirimanPage();
            bukalapak.jasaPengirimanPage().tapCheckboxCourier(courier);
            bukalapak.jasaPengirimanPage().changeTheSettingOnDemand();
        });

        And("validate status of courier \"([^\"]*)\" is updated", (String courier) -> {
            bukalapak.jasaPengirimanPage().validateUpdatingCourier(courier);
        });
    }
}
