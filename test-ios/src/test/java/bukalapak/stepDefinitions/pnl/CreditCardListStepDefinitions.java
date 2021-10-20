package bukalapak.stepDefinitions.pnl;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class CreditCardListStepDefinitions extends TestInstrument implements En {

    public CreditCardListStepDefinitions() {

        Given("user is in \"credit_card_list\" page", () -> {
            bukalapak.creditCardListPage().userOnCreditCardListPage();
        });

        Given("user is in \"detail_kartu\" page", () -> {
            bukalapak.creditCardDetailPage().userOnCreditCardDetailPage();
        });

        When("user type \"([^\"]*)\" text on cvv field", (String cvv) -> {
            bukalapak.creditCardDetailPage().typeCVV(cvv);
        });

        When("user select cc card with number \"([^\"]*)\" on recurring payment selection page", (String number) -> {
            bukalapak.creditCardListPage().userOnCreditCardListPage();
            bukalapak.creditCardListPage().userSelectCreditCardWithNumber(number);
        });

        When("user tap \"tambah kartu baru\" button", () -> {
            bukalapak.creditCardListPage().tapOnTambahKartuBaruButton();
        });
    }
}
