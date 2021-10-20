package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class SebarPromosiStepsDefinitions extends TestInstrument implements En {
    public SebarPromosiStepsDefinitions() {
        Given("user is in \"sebar promosi onboarding text\" page", () -> {
            bukalapak.sebarPromosiOnboardingPage().verifySebarPromosiOnboardingDisplayed();
        });

        And("^user see info for non super seller in onboarding sebar promosi page$", () -> {
            bukalapak.sebarPromosiOnboardingPage().verifySebarPromosiOnboardingNonSuperSeller();
        });

        And("^user tap aktifkan super seller button$", () -> {
            bukalapak.sebarPromosiOnboardingPage().tapAktifkanSuperSeller();
        });

        And("^user is in \"Sebar promosi\" page$", () -> {
            bukalapak.sebarPromosiPage().verifySebarPromosiPageDisplayed();
        });

        And("^user click one of product favorite page$", () -> {
            bukalapak.sebarPromosiPage().tapProductSebarPromosList();
        });

        And("^user have product in favorite page with name and amount of user$", () -> {
            bukalapak.sebarPromosiPage().verifyUserHaveProductFavorite();
        });

        And("^user tap product favorite \"([^\"]*)\" in sebar promosi page$", (String nameProduct) -> {
            bukalapak.sebarPromosiPage().tapProductFavoriteWithName(nameProduct);
        });

        When("^user click row (\\d+) on sebar promosi product list page$", (Integer index) -> {
            bukalapak.sebarPromosiPage().clickProductOnSebarPromosiList(index);
        });
    }
}
