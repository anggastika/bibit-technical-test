package bukalapak.stepDefinitions.noob;
import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class ResurrectionZoneStepDefinitions extends TestInstrument implements En {

    public ResurrectionZoneStepDefinitions() {

        Then("user redirect to buku kenangan page", () -> {
            bukalapak.resurrectionZoneWebPage().validateOnResurrectionZone();
        });

        And("^user go to \"([^\"]*)\" using deeplink$", (String deeplink) -> {
            bukalapak.iOSBasePage().openDeepLink(dotenv.get(deeplink));
        });

        And("user redirect to kado balikan page", () -> {
            bukalapak.resurrectionZoneWebPage().validateOnBukuKenangan();
        });

        And("user click special product cta", () -> {
            bukalapak.resurrectionZoneWebPage().clickCTAProductSpecial();
        });

        Then("user redirect to special product section page", () -> {
            bukalapak.resurrectionZoneWebPage().validateOnProductSpecial();
        });

        And("user click cta product cart", () -> {
            bukalapak.resurrectionZoneWebPage().clickCTACart();
        });

        Then("user redirect to cart section page", () -> {
            bukalapak.resurrectionZoneWebPage().validateOnCartSection();
        });

        And("user click recent product cta", () -> {
            bukalapak.resurrectionZoneWebPage().clickCTARecent();
        });

        Then("user redirect to recent section page", () -> {
            bukalapak.resurrectionZoneWebPage().validateOnRiwayatPage();
        });

        Then("user validate has resurrection voucher", () -> {
            bukalapak.resurrectionZoneWebPage().validateVoucherResurrectionZone();
        });

        Then("user can redirect to search page via search resurrection", () -> {
            bukalapak.resurrectionZoneWebPage().typeSearchResurrectionZone();
            bukalapak.resurrectionZoneWebPage().validateSearchResultPage();
        });

        Then("user tap on salin kode resurrection", () -> {
            bukalapak.resurrectionZoneWebPage().tapOnResurrectionVoucherCode();
        });

        Then("user should see success copy code toast", () -> {
            bukalapak.resurrectionZoneWebPage().verifySuccessCopyCode();
        });

    }
}
