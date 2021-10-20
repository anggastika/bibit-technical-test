package bukalapak.stepDefinitions.homeandcat;

import bukalapak.TestInstrument;
import bukalapak.utils.ChoiceSelector;
import cucumber.api.java8.En;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class DopeStepDefinitions extends TestInstrument implements En {

    private final static Logger LOGGER = LogManager.getLogger(DopeStepDefinitions.class);

    public DopeStepDefinitions(){

        Then("^user should be redirect to mweb virtual product$", () -> {
            bukalapak.dopePage().isOnMwebDopePage();
        });

        Then("^user should be redirect to dope page$", () -> {
            bukalapak.dopePage().isOnDopePage();
        });

        And("^user go to main page dope$", () -> {
            bukalapak.dopePage().scrollDownToDope();
        });

        And("^user should be redirect to Tagihan tab Dope$", () -> {
            bukalapak.dopePage().isTagihanTabExist();
        });

        And("^user should be redirect to Featured Tab$", () -> {
            bukalapak.dopePage().isSemuaMenuExist();
        });

        When("^user click on \"([^\"]*)\"$", (String elementName) -> {
            bukalapak.dopePage().clickBukamart(elementName);
        });

        And("^user should be redirect to bukamart Landing Page$", () -> {
            bukalapak.dopePage().isBukamartPageExist();
        });

        When("^user search \"([^\"]*)\" from Dope Page$", (String keywordType) -> {
            bukalapak.dopePage().searchDopeMenu(keywordType);
        });

        And("^user should see \"([^\"]*)\" on search result$", (String keywordType) -> {
            bukalapak.dopePage().isSearchResultExist(keywordType);
        });

        When("user navigate to \"([^\"]*)\" page via widget", (String arg0) -> {
            ChoiceSelector.of(arg0)
                    .when("data plan", () -> bukalapak.dopePage().goToWidget("widget_paket_data_widget"))
                    .when("Air PDAM", () -> bukalapak.dopePage().goToWidget("widget_pdam_widget"))
                    .when("Buka DANA", () -> bukalapak.dopePage().goToWidget("widget_buka_dana_widget"))
                    .when("Telkom", () -> bukalapak.dopePage().goToWidget("telkom_widget"))
                    .when("BPJS Kesehatan", () -> bukalapak.dopePage().goToWidget("widget_bpjs_widget"))
                    .when("Listrik Pascabayar", () -> bukalapak.dopePage().goToWidget("widget_postpaid_electricity_widget"))
                    .when("Pulsa Pascabayar", () -> bukalapak.dopePage().goToWidget("widget_postpaid_phone_credit_widget"))
                    .when("Tagihan Kartu Kredit", () -> bukalapak.dopePage().goToWidget("widget_credit_card_bill_widget"))
                    .when("Bukareview", () -> bukalapak.dopePage().goToWidget("widget_bukareview_widget"))
                    .when("Promoted Push", () -> bukalapak.dopePage().goToWidget("widget_promoted_widget"))
                    .when("Tiket Kereta", () -> bukalapak.dopePage().goToWidget("widget_tiket_kereta_widget"))
                    .when("Gift Card", () -> bukalapak.dopePage().goToWidget("widget_gift_card_widget"))
                    .when("bukaemas", () -> bukalapak.dopePage().goToWidget("widget_bukaemas"))
                    .when("Vaksin Corona", () -> bukalapak.dopePage().goToWidget("widget_vaksin_corona"))
                    .when("Pph Final", () -> bukalapak.dopePage().goToWidget("widget_bayar_pph_final_widget"))
                    .when("Bayar Denda Tilang", () -> bukalapak.dopePage().goToWidget("widget_bayar_denda_tilang_widget"))
                    .orElse(() -> LOGGER.info("Your page is not implemented yet: " + arg0));
        });

        Then("user get error message DANA frozen", () -> {
            bukalapak.dopePage().validateDANADopeFrozen();
        });

        Then("user get error message DANA frozen at homepage", () -> {
            bukalapak.dopePage().validateDANAHomeFrozen();
        });

        Then("user verify single line dope", () -> {
            bukalapak.dopePage().validateSingleLineDope();
        });

        And("user can swipe left and swipe right dope", () -> {
            bukalapak.dopePage().swipeDopeImage();
        });

        And("user validate menu dope single line maximal icon appears \"([^\"]*)\" and minimal product appears 4", (String totalDopeExpected) -> {
            bukalapak.dopePage().validateDopeSingleLine(Integer.valueOf(totalDopeExpected));
        });

        And("user is on dope revamp page", () -> {
            bukalapak.dopePage().isOnDopeRevampPage();
        });

        And("user tap \"([^\"]*)\" icon from category \"([^\"]*)\" on dope revamp page", (String dopeName, String categoryName) -> {
            bukalapak.dopePage().tapDopeCategoryTab(categoryName);
            bukalapak.dopePage().tapDopeIcon(dopeName);
        });

        And("user tap back button from dope detail page", () -> {
            bukalapak.dopePage().tapBackToDopeRevamp();
        });

        And("user tap category \"([^\"]*)\" on dope revamp page", (String categoryName) -> {
            bukalapak.dopePage().tapDopeCategoryTab(categoryName);
        });

        And("user should see dope menu in dope category changed", () -> {
            bukalapak.dopePage().validateCategorySelected();
        });

        And("user should see the viewed dope exist on Terakhir Dibuka section", () -> {
            bukalapak.dopePage().validateLastViewedDope();
        });

        Then("user should see \"([^\"]*)\" tag on dope menu", (String tagName) -> {
            bukalapak.dopePage().validateTagInDopeMenu(tagName);
        });

        Then("should be in \"([^\"]*)\" category tab", (String categoryName) -> {
            bukalapak.dopePage().validateCategorySelected(categoryName);
        });

        Then("user pull refresh the dope page \"([^\"]*)\" times", (String refreshCount) -> {
            bukalapak.dopePage().doPullRefreshDopePage(Integer.valueOf(refreshCount));
        });

        When("user tap menu \"([^\"]*)\" from dope search result", (String menuName) -> {
            bukalapak.dopePage().tapMenuFromSearchResult(menuName);
        });
    }
}
