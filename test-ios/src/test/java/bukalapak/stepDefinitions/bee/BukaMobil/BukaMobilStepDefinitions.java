package bukalapak.stepDefinitions.bee.BukaMobil;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaMobilStepDefinitions extends TestInstrument implements En {
    public BukaMobilStepDefinitions() {
        Given("user is in \"City Car\" page", () -> {
            bukalapak.bukaMobilPage().onCityCarPage();
        });

        And("user navigate to \"BukaMobil\" page", () -> {
            bukalapak.iOSBasePage().openDeepLink("/bukamobil");
        });

        And("user is in \"BukaMobil\" landing page", () -> {
            bukalapak.bukaMobilPage().onBukaMobilLandingPage();
        });

        When("^user search \"([^\"]*)\" in search field$", (String keyword) -> {
            bukalapak.bukaMobilPage().searchCar(keyword);
        });

        And("^user back to BukaMobil page$", () -> {
            bukalapak.bukaMobilPage().backToBukaMObilPage();
        });

        And("^user tap \"([^\"]*)\" on jenis mobil section$", (String classification) -> {
            bukalapak.bukaMobilPage().tapOnJenisMobil(classification);
        });

        And("^user select one of car from on brand section$", () -> {
            bukalapak.bukaMobilPage().tapOnBrandMobil();
        });

        And("user tap lihat semua on mobil pilihan section", () -> {
            bukalapak.bukaMobilPage().tapOnLihatSemuaMobilPilihan();
        });

        And("^user see brand section (with|without) brand list$", (String flag) -> {
            bukalapak.bukaMobilPage().validateBrandSection(flag);
        });

        And("^user see jenis section (with|without) jenis list$", (String flag) -> {
            bukalapak.bukaMobilPage().validateJenisSection(flag);
        });

        And("^user tap on live chat from landing page bukamobil$", () -> {
            bukalapak.bukaMobilPage().tapOnLiveChat();
        });

        And("user tap ubah location", () -> {
            bukalapak.bukaMobilPage().clickUbahLokasi();
        });

        When("^user tap \"([^\"]*)\" on header$", (String headerMenu) -> {
            bukalapak.bukaMobilPage().selectHeaderMenu(headerMenu);
        });

        Then("user will redirect to BukaMotor onboarding page", () -> {
            bukalapak.bukaMobilPage().validateBukaMotorOnboardingPage();
        });

        Then("user will redirect to Bukalapak homepage", () -> {
            bukalapak.bukaMobilPage().validateBukalapakHomepage();
        });

        When("user tap Lihat Semua on BukaReview section", () -> {
            bukalapak.bukaMobilPage().tapLihatSemuaBukareview();
        });

        Then("user will redirect to BukaReview homepage", () -> {
            bukalapak.bukaMobilPage().validateBukaReviewHomepage();
        });

        When("user tap specific BukaReview", () -> {
            bukalapak.bukaMobilPage().tapArticleBukareview();
        });

        When("user tap Lihat Semua on FAQ section", () -> {
            bukalapak.bukaMobilPage().tapLihatSemuaFAQ();
        });

        Then("user will redirect to FAQ page", () -> {
            bukalapak.bukaMobilPage().validateFAQPage();
        });

        When("user tap Lihat Selengkapnya button", () -> {
            bukalapak.bukaMobilPage().tapLihatSelengkapnyaBtn();
        });

        When("user tap transaction icon on header", () -> {
            bukalapak.bukaMobilPage().tapTransactionIconHeader();
        });
    }
}
