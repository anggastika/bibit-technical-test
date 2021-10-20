package bukalapak.stepDefinitions.wow;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;

/**
 * @author Andi Maryono
 */

public class ProductCatalogDetailStepDefinitions extends TestInstrument implements En {
    public ProductCatalogDetailStepDefinitions() {
        Given("user is in \"product_catalog_detail\" page", () -> {
            bukalapak.productCatalogDetailPage().userOnProductCatalogDetailPage();
        });

        When("user verify catalog detail rating \"([^\"]*)\"$", (String status) -> {
            bukalapak.productCatalogDetailPage().checkCatalogDetailRating(status);
        });

        When("catalog detail displays valid median price", (DataTable arg0) -> {
            bukalapak.productCatalogDetailPage().verifyMedianPrice(arg0);
        });

        When("catalog detail displays valid description", (DataTable arg0) -> {
            bukalapak.productCatalogDetailPage().verifyDescriptionContent(arg0);
        });

        When("catalog detail displays valid content of promoted product", () -> {
            bukalapak.productCatalogDetailPage().verifyPromotedProductContent();
        });

        When("catalog promoted product not displays some contents", (DataTable arg0) -> {
            bukalapak.productCatalogDetailPage().verifyNotVisibleContents(arg0);
        });

        When("catalog promoted product displays some contents", (DataTable arg0) -> {
            bukalapak.productCatalogDetailPage().verifyVisibleContents(arg0);
        });

        When("user verify note of catalog promoted product in checkout marketplace", (DataTable arg0) -> {
            bukalapak.productCatalogDetailPage().verifyNoteInCheckoutMarketplace(arg0);
        });

        When("user verify promoted product of catalog in chat room", () -> {
            bukalapak.productCatalogDetailPage().verifyPromotedProductInChatRoom();
        });

        When("user verify promoted product after proceed to checkout from cart", () -> {
            bukalapak.productCatalogDetailPage().verifyProceedToCheckout();
        });

        When("user swipe up and tap lihat barang lainnya in catalog detail page", () -> {
            bukalapak.productCatalogDetailPage().tapLihatBarangLainnya();
        });

        When("user choose variant (\\d+) in variant option modal of catalog detail", (Integer index) -> {
            bukalapak.productCatalogDetailPage().chooseVariantOption(index);
        });

        When("catalog detail displays valid catalog name \"([^\"]*)\"$", (String catalogName) -> {
            bukalapak.productCatalogDetailPage().verifyCatalogName(catalogName);
        });

        When("user scroll down to ulasan barang in catalog detail page", () -> {
            bukalapak.productCatalogDetailPage().scrollDownToUlasanBarang();
        });

        When("user verify catalog detail review product is \"([^\"]*)\"$", (String status) -> {
            bukalapak.productCatalogDetailPage().verifyReviewVisibility(status);
        });

        When("user click \"([^\"]*)\" at promoted product in catalog detail page$", (String element) -> {
            /*
              Please simplify these two lines of code into one line of method invoke with param.
             */
            bukalapak.productCatalogDetailPage().clickAtPromotedProduct(element);
            bukalapak.productCatalogDetailPage().clickBuyOnVariantProduct();
        });

        Then("^user should see correct \"([^\"]*)\" modal in catalog detail$", (String badge) -> {
            bukalapak.productCatalogDetailPage().verifyBadgeModal(badge);
        });

        And("^user tap on cart icon in catalog detail page$", () -> {
            bukalapak.productCatalogDetailPage().tapCartIcon();
        });

        And("^user should see cart modal on catalog detail page$", () -> {
            bukalapak.productCatalogDetailPage().verifyCartModal();
        });
    }
}
