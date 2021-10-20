package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class AlamatStepDefinitions extends TestInstrument implements En {

    public AlamatStepDefinitions() {
        Given("user is in \"Alamat List\" page", () -> {
            bukalapak.alamatPage().userOnAlamatPage();
        });

        Given("^user is in \"Pilih Alamat Utama\" page$", () -> {
            bukalapak.pilihAlamatUtamaPage().userOnPilihAlamatUtamaPage();
        });

        When("user change to second address in the list", () -> {
            bukalapak.alamatPage().changeShippingAddressIndex("2");
        });

        And("user delete address with detail \"([^\"]*)\"", (String arg0) -> {
            bukalapak.alamatPage().deleteAlamat(arg0);
        });

        And("user delete address number \"([^\"]*)\"", (String arg0) -> {
            bukalapak.alamatPage().deleteAlamatIndexKe(arg0);
        });

        And("validate the new address is deleted", () -> {
            bukalapak.alamatPage().validateNewAddressisdeleted();
            bukalapak.alamatPage().userOnAlamatPage();
        });

        When("user edit address with detail \"([^\"]*)\" and label \"([^\"]*)\"", (String arg0, String arg1) -> {
            bukalapak.alamatPage().editAlamat(arg0, arg1);
        });

        Then("validate changed data with detail \"([^\"]*)\" is saved successfully", (String arg0) -> {
            bukalapak.alamatPage().validateModifyAddress(arg0);
        });

        And("validate in Alamat page after change address", () -> {
            bukalapak.alamatPage().userOnAlamatPage();
        });

        And("user select an existing address with detail \"([^\"]*)\" and label \"([^\"]*)\"", (String arg0, String arg1) -> {
            bukalapak.alamatPage().changeShippingAddress(arg0, arg1);
        });

        And("user select (.*) as selected address", (String selectedAddress) -> {
            bukalapak.alamatPage().selectAddress(selectedAddress);
        });

        Then("user validate masking phone number", () -> {
            bukalapak.alamatPage().validateMaskingPhoneNumber();
        });

        Then("^user validate displayed error max address$", () -> {
            bukalapak.alamatPage().validateErrorMaxAddress();
        });

        When("^user tap Set Utama on address with title \"([^\"]*)\"$", (String title) -> {
            bukalapak.alamatPage().tapSetUtamaOnSelectedAddress(title);
        });

        Then("^user \"([^\"]*)\" validate alamat utama title is updated$", (String credential) -> {
            bukalapak.alamatPage().validateAlamatUtamaUpdated(bukalapak.apiCall().getMainAddressTitle(credential));
        });
    }
}
