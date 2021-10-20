package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class PotongHargaStepDefinitions extends TestInstrument implements En {

    public PotongHargaStepDefinitions() {

        Then("user is on potong harga Home page", () -> {
            bukalapak.potongHargaPage().isOnPotongHargaPage();
        });

        When("user click icon onboarding on landing page Potong Harga", () -> {
            bukalapak.potongHargaPage().tapOnIconOboardingPotongHarga();
        });

        Then("user will see popup Syarat dan Ketentuan", () -> {
            bukalapak.potongHargaPage().validateOnboardingPopupPotongHarga();
        });

        Then("user will see quick filter category", () -> {
            bukalapak.potongHargaPage().validateFilterCategory();
        });

        When("user tap on Riwayat tab Potong Harga", () -> {
            bukalapak.potongHargaPage().tapOnHistoryTab();
        });

        Then("user will see the empty state on history page potong harga", () -> {
            bukalapak.potongHargaPage().validateEmptyStateHistoryPage();
        });

        When("user click filter \"([^\"]*)\" category", (String category) -> {
            bukalapak.potongHargaPage().tapOnSpecificFilterCategory(category);
        });

        Then("user verify product list will show all products", () -> {
            bukalapak.potongHargaPage().validateTotalProductCardList();
        });

        Then("user verify Product Card potong harga history", () -> {
            bukalapak.potongHargaPage().validateProductCardHistoryPage();
        });

        Then("user verify Product Detail page Potong Harga", () -> {
            bukalapak.potongHargaPage().validateProductDetailPotongHarga();
        });

        When("^user (not )?see product on specific category$", (String flag) -> {
            bukalapak.potongHargaPage().validateProductByCategory(flag);
        });

        When("user tap icon TnC on page Potong Harga", () -> {
            bukalapak.potongHargaPage().tapTncIcon();
        });

        Then("user verify TnC page Potong harga", () -> {
            bukalapak.potongHargaPage().validateTncPage();
        });

        Then("user tap back on TnC Potong Harga", () -> {
            bukalapak.potongHargaPage().tapBackFromTnc();
        });

        Then("user verify product state is slashed", () -> {
            bukalapak.potongHargaPage().validateProductAlreadySlash();
        });
    }
}
