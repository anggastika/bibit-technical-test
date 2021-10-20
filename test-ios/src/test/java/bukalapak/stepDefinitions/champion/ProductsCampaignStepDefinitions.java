package bukalapak.stepDefinitions.champion;

import bukalapak.TestInstrument;
import bukalapak.data.ChampionData;
import cucumber.api.java8.En;

public class ProductsCampaignStepDefinitions extends TestInstrument implements En {

    public ProductsCampaignStepDefinitions() {

        Given("user is in \"promo\" page", () -> {
            bukalapak.promoPage().userOnPromoPage();
        });

        Given("user navigates to a product campaign page", () -> {
            bukalapak.promoPage().goToPromoPage();
            bukalapak.promoPage().clickPromoTab("Barang");
            bukalapak.promoPage().clickFirstProductCampaign();
        });

        Given("user go to campaign detail page with deeplink \"([^\"]*)\"",  (String deeplink) -> {
            bukalapak.productsCampaignPage().goToCampaignPageWithDeeplink(deeplink);
        });

        Then("the product campaign detail page is displayed properly", () -> {
            bukalapak.productsCampaignPage().isOnCampaignProductPage();
        });

        When("user sorts the campaign products with option \"([^\"]*)\"", (String sortMode) -> {
            bukalapak.productsCampaignPage().sortProducts(sortMode);
        });

        Then("the campaign products are sorted by \"([^\"]*)\"", (String sortMode) -> {
            bukalapak.productsCampaignPage().validateSortResult(sortMode);
        });

        When("user searches a keyword in the products campaign detail page", () -> {
            bukalapak.productsCampaignPage().searchProducts();
        });

        Then("relevant products name are listed in the campaign detail page", () -> {
            bukalapak.productsCampaignPage().validateSearchResult(ChampionData.getSearchKeyword(), 5);
        });
    }
}
