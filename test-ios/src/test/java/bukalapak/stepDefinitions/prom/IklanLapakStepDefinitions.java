package bukalapak.stepDefinitions.prom;

import bukalapak.TestInstrument;
import bukalapak.data.PROMData;
import bukalapak.data.UserData;
import cucumber.api.java8.En;

public class IklanLapakStepDefinitions extends TestInstrument implements En {

    public IklanLapakStepDefinitions() {
        Given("user is in \"Iklan Lapak\" page", () -> {
            bukalapak.iklanLapakPage().verifyIklanLapakPageDisplayed();
        });

        And("^user fill nama promosi iklan lapak with \"([^\"]*)\"$", (String promotionName) -> {
            bukalapak.iklanLapakPage().inputPromotionName(promotionName);
        });

        And("^user choose category \"([^\"]*)\" for iklan lapak$", (String categoryName) -> {
            bukalapak.iklanLapakPage().chooseCategory(categoryName);
        });

        And("^user tick first product for iklan lapak$", () -> {
            bukalapak.iklanLapakPage().tapElement("first_product_iklan_lapak_settings_cb");
        });

        And("^user will see iklan lapak sucessfully created$", () -> {
            bukalapak.iklanLapakPage().verifySuccessCreateIklanLapak();
        });

        And("^user will see status iklan lapak is \"([^\"]*)\"$", (String status) -> {
            bukalapak.iklanLapakPage().verifyIklanLapakStatus(PROMData.getIklanLapakPromotionName(), status);
        });

        And("^the Iklan Lapak campaign is created with correct description$", () -> {
            bukalapak.iklanLapakPage().validateIklanLapakCampaignDescription();
        });

        And("^user \"([^\"]*)\" delete iklan lapak \"([^\"]*)\" from api$", (String credentialUser, String promotionName) -> {
            bukalapak.apiCall().deleteIklanLapak(UserData.getUsername(), promotionName);
        });

        And("^user delete iklan lapak via api$", () -> {
            bukalapak.apiCall().deleteIklanLapak(UserData.getUsername(), PROMData.getIklanLapakPromotionName());
        });

        When("^user tap on first iklan lapak campaign$", () -> {
            bukalapak.iklanLapakPage().tapFirstCampaignIklanLapak();
        });

        Then("^user will see active product campaign iklan lapak$", () -> {
            bukalapak.iklanLapakPage().verifyActiveProductIklanLapak();
        });

        And("^user tap on delete icon iklan lapak$", () -> {
            bukalapak.iklanLapakPage().deleteIklanLapak();
        });

        And("^user tap on iklan lapak campaign with name \"([^\"]*)\"$", (String campaignName) -> {
            bukalapak.iklanLapakPage().tapOnSelectedIklanLapakCampaign(campaignName);
        });

        And("^user validate iklan lapak with name \"([^\"]*)\" is deleted$", (String campaignName) -> {
            bukalapak.iklanLapakPage().validateIklanLapakIsDeleted(campaignName);
        });

        And("^user is onboarding Iklan lapak$", () -> {
            bukalapak.iklanLapakPage().verifyOnboardingIklanLapak();
        });

        And("^user is in \"Tambah promosi\" page", () -> {
            bukalapak.iklanLapakPage().verifyTambahPromosiPage();
        });

        And("^select \"([^\"]*)\" products for Iklan Lapak$", (Integer products) -> {
            bukalapak.iklanLapakPage().selectIklanLapakCheckbox(products);
        });

        And("^scroll down to the next available Iklan Lapak checkbox$", () -> {
            bukalapak.iklanLapakPage().swipeToNextCheckbox();
        });

        And("^user validate selected products is \"([^\"]*)\"$", (String selectedProduct) -> {
            bukalapak.iklanLapakPage().verifyTotalSelectedProducts(selectedProduct);
        });

        And("^select product for Iklan Lapak$", () -> {
            bukalapak.iklanLapakPage().selectIklanLapakCheckbox();
        });

        Then("^user will see info that iklan lapak has reached maximum limit$", () -> {
            bukalapak.iklanLapakPage().verifyInfoReachMaxLimit();
        });

        And("^user see daftar promosi is \"([^\"]*)\" of \"([^\"]*)\"$", (String items, String maxItems) -> {
            bukalapak.iklanLapakPage().verifyDaftarPromosiLimit(items, maxItems);
        });

        Then("^user will see list of iklan lapak campaigns$", () -> {
            bukalapak.iklanLapakPage().verifyListOfCampaigns();
        });

        And("^user set iklan lapak budget as \"([^\"]*)\"$", (String budget) -> {
            bukalapak.iklanLapakPage().selectBudgetLimitOption(budget);
        });

        And("^user will see error message Iklan Lapak as \"([^\"]*)\"$", (String error) -> {
            bukalapak.iklanLapakPage().verifyErrorMessage(error);
        });

        And("^user is on successful Iklan Lapak confirmation with status (Active|Dormant)$", (String state) -> {
            bukalapak.iklanLapakPage().checkIklanLapakConfirmationPage(state);
        });

        Then("^user will see first iklan lapak campaign status is \"([^\"]*)\"$", (String status) -> {
            bukalapak.iklanLapakPage().checkFirstCampaignStatus(status);
        });

        And("^user tap on (aktifkan|nonaktifkan) promosi button$", (String action) -> {
            bukalapak.iklanLapakPage().tapOnUpdatePromosiButton(action);
        });

        When("user tap on Pelajari button in Iklan Lapak page", () -> {
            bukalapak.iklanLapakPage().tapOnPelajariButton();
        });

        When("user tap on Coba Iklan Lapak button", () -> {
            bukalapak.iklanLapakPage().tapOnCobaIklanLapakButton();
        });

        Then("^budget promoted \"([^\"]*)\" will be reduced by Iklan Lapak cost$", (String username) -> {
            bukalapak.iklanLapakPage().validatePromotedBudgetIklanLapak(username);
        });
    }
}
