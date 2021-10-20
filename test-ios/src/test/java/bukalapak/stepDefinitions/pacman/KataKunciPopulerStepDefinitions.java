package bukalapak.stepDefinitions.pacman;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class KataKunciPopulerStepDefinitions extends TestInstrument implements En {
    public KataKunciPopulerStepDefinitions() {
        Given("^user is in \"Kata Kunci Populer\" page$", () -> {
            bukalapak.kataKunciPopulerPage().userOnKataKunciPopulerPage();
        });

        Then("user see (\\d+) most populer keyword", (Integer amountPopulerKeyword) -> {
            bukalapak.kataKunciPopulerPage().verifyAmountListKataKunci(amountPopulerKeyword);
        });

        Then("^user see category \"([^\"]*)\" chosen by user is display in kata kunci populer page$", (String categoryMenu) -> {
            bukalapak.kataKunciPopulerPage().verifyCategoryField(categoryMenu);
        });
    }
}
