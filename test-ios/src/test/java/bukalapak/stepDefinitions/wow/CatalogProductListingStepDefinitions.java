package bukalapak.stepDefinitions.wow;

import bukalapak.TestInstrument;
import bukalapak.data.WOWData;
import cucumber.api.java8.En;

/**
 * @author Andi Maryono
 */

public class CatalogProductListingStepDefinitions extends TestInstrument implements En {
    public CatalogProductListingStepDefinitions() {
        Given("user is in \"catalog_product_listing\" page", () -> {
            bukalapak.catalogProductListingPage().userOnCatalogProductListing();
        });

        Then("^user should see valid (normal|promoted) product basic info$", (String productType) -> {
            bukalapak.catalogProductListingPage().verifyProductInfoExist(productType);
        });

        When("user tap \"([^\"]*)\" product in catalog product listing", (String productType) -> {
            bukalapak.catalogProductListingPage().clickOnProduct(productType);
        });

        When("user tap \"([^\"]*)\" buy button in catalog product listing", (String productType) -> {
            bukalapak.catalogProductListingPage().clickOnProductListingBuyBtn(productType);
        });

        Then("user should see catalog name in navbar after swipe up", () -> {
            bukalapak.catalogProductListingPage().verifyNavbarTitle(WOWData.getCatalogTitle());
        });

        When("catalog product listing is filtered and contain valid product", () -> {
            bukalapak.catalogProductListingPage().verifyProductInfoExist("normal");
            bukalapak.catalogProductListingPage().verifyProductListingByFilter();
        });

        Then("catalog recommendation product should not exist", () -> {
            bukalapak.catalogProductListingPage().verifyCatalogRecoNotExist();
        });

        Then("catalog product listing is sorted and contain valid product", () -> {
            bukalapak.catalogProductListingPage().verifyProductListingBySort();
            bukalapak.catalogProductListingPage().verifyProductInfoExist("normal");
        });

        When("^user click (filter|sort) button in catalog product listing$", (String btn) -> {
            bukalapak.catalogProductListingPage().clickOnBtn(btn);
        });

        When("user click quick filter product in catalog product listing", () -> {
            bukalapak.catalogProductListingPage().clickOnQuickFilter();
        });
    }
}
