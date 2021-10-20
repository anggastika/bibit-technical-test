package bukalapak.stepDefinitions.wow;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class CategoryFashionWanitaStepDefinitions extends TestInstrument implements En {
    public CategoryFashionWanitaStepDefinitions() {
        When("user swipe horizontally on feature item section", () -> {
            bukalapak.categoryFashionWanitaPage().swipeLeftOnFeaturedItemSection();
        });

        When("featured item contain barang diskon, gratis ongkir and barang terbaru", () -> {
            bukalapak.categoryFashionWanitaPage().checkFeaturedItemSection();
        });

        When("user click product at featured item section \"([^\"]*)\"", (String featuredItem) -> {
            bukalapak.categoryFashionWanitaPage().clickProductFeaturedItem(featuredItem);
        });

        When("user is on search page filtered by \"([^\"]*)\"", (String featuredItem) -> {
            bukalapak.categoryFashionWanitaPage().checkFilteringOnSearchPage(featuredItem);
        });

        When("user click lihat semua at featured item section \"([^\"]*)\"", (String featuredItem) -> {
            bukalapak.categoryFashionWanitaPage().clickLihatSemuaFeaturedItem(featuredItem);
        });

        When("user swipe horizontally on feature item subcategory list", () -> {
            bukalapak.categoryFashionWanitaPage().swipeLeftOnSubcategoryList();
        });

        When("user scroll down to subcategory special \"([^\"]*)\"", (String subcategorySpecial) -> {
            bukalapak.categoryFashionWanitaPage().scrollDownToSubcategorySpecial(subcategorySpecial);
        });

        When("subcategory special contains title \"([^\"]*)\"", (String subcategorySpecial) -> {
            bukalapak.categoryFashionWanitaPage().checkSubcategorySpecialTitle(subcategorySpecial);
        });

        When("user swipe horizontally at subcategory special \"([^\"]*)\"", (String subcategorySpecial) -> {
            bukalapak.categoryFashionWanitaPage().swipeLeftOnSubcategorySpecial(subcategorySpecial);
        });

        When("click lihat semua at subcategory special \"([^\"]*)\"", (String subcategorySpecial) -> {
            bukalapak.categoryFashionWanitaPage().clickLihatSemuaSubcategorySpecial(subcategorySpecial);
        });

        When("user is on search page filtered by subcategory special \"([^\"]*)\"", (String subcategorySpecial) -> {
            bukalapak.categoryFashionWanitaPage().checkFilteringSubcategoryOnSearchPage(subcategorySpecial);
        });

        When("user swipe horizontally promo section on fashion wanita page", () -> {
            bukalapak.categoryFashionWanitaPage().swipeLeftOnPromoSection();
        });

        When("user swipe horizontally brand section on fashion wanita page", () -> {
            bukalapak.categoryFashionWanitaPage().swipeLeftOnBrandSection();
        });

        When("user click at product name on subcategory special \"([^\"]*)\"", (String subcategorySpecial) -> {
            bukalapak.categoryFashionWanitaPage().clickProductNameSubcategorySpecial(subcategorySpecial);
        });
    }
}
