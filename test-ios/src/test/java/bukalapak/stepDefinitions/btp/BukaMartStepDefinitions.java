package bukalapak.stepDefinitions.btp;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class BukaMartStepDefinitions extends TestInstrument implements En {
    public BukaMartStepDefinitions() {
        Given("user go to BukaMart with deeplink", () -> {
            bukalapak.productDetailsPage().openDeepLink("/bukamart-official");
        });

        And("user should be redirect to BukaMart Landing Page", () -> {
            bukalapak.bukamartLandingPage().validateBukamartPage();
        });

        And("^user can swipe on \"([^\"]*)\" section$", (String section) -> {
            bukalapak.bukamartLandingPage().swipeSection(section);
        });

        When("^user tap add to cart product on \"([^\"]*)\" section$", (String section) -> {
            bukalapak.bukamartLandingPage().tapATCProductOnSection(section);
        });

        Then("^user should( not)? see sticky cart Bukamart$", (String flag) -> {
            bukalapak.bukamartLandingPage().validateStickyCart(flag);
        });

        And("user validate Bukamart Onboarding", () -> {
            bukalapak.bukamartLandingPage().validateOnboarding();
        });

        And("user should see Add new address on Address field", () -> {
            bukalapak.bukamartLandingPage().validateNoSavedAddress();
        });

        When("user tap Add New Address on Address field", () -> {
            bukalapak.bukamartLandingPage().tapAddAddrField();
        });

        Then("user should be redirect to Add Address Page", () -> {
            bukalapak.bukamartLandingPage().validateNewAddressPage();
        });

        And("^user should( not)? see triggered coverage area$", (String flag) -> {
            bukalapak.bukamartLandingPage().validateTriggeredCoverageArea(flag);
        });

        And("^user change address to \"([^\"]*)\"$", (String address) -> {
            bukalapak.bukamartLandingPage().changeAddress(address);
        });

        When("user tap Bukamart Profile", () -> {
            bukalapak.bukamartLandingPage().tapBukaMartProfile();
        });

        When("user validate Bukamart Profile", () -> {
            bukalapak.bukamartLandingPage().validateBukamartProfile();

        });

        When("user tap sticky cart Bukamart", () -> {
            bukalapak.bukamartLandingPage().tapStickyCart();
        });

        Then("user validate product on pop up atc is correct", () -> {
            bukalapak.bukamartLandingPage().validateProductOnTrayATC();
        });

        When("^user search product on bukamart landing page with keyword \"([^\"]*)\"$", (String product) -> {
            bukalapak.bukamartLandingPage().searchProduct(product);
        });

        Then("user validate empty state is shown", () -> {
            bukalapak.bukamartLandingPage().verifyEmptyState();
        });

        Then("^user select \"([^\"]*)\" as quick location$", (String location) -> {
            bukalapak.bukamartLandingPage().tapQuickLocation(location);
        });

        Then("user validate product exist on bukamart landing page", () -> {
            bukalapak.bukamartLandingPage().validateProductListExist();
        });

        And("^user should see \"([^\"]*)\" section on bukamart landing page$", (String sectionType) -> {
            bukalapak.bukamartLandingPage().validateSectionOnBukaMart(sectionType);
        });

        And("^user should see Urutkan \"([^\"]*)\" is active$", (String urutkanName) -> {
            bukalapak.bukamartLandingPage().validateUrutkanNameActive(urutkanName);
        });

        And("^user tap on \"([^\"]*)\" section$", (String sectionName) -> {
            bukalapak.bukamartLandingPage().tapOnTabSectionBukaMart(sectionName);
        });

        And("^user click on urutkan \"([^\"]*)\"$", (String urutkanName) -> {
            bukalapak.bukamartLandingPage().tapOnUrutkanNameBukaMart(urutkanName);
        });

        And("user delete keyword on search field bukamart", () -> {
            bukalapak.bukamartLandingPage().clearKeywordOnSearchField();
        });

        And("user should see category tab on BukaMart Landing Page", () -> {
            bukalapak.bukamartLandingPage().verifiyCategoryTab();
        });

        When("user click on category tab on BukaMart Landing Page", () -> {
            bukalapak.bukamartLandingPage().tabOnCategoryTab();
        });
    }
}
