package bukalapak.stepDefinitions.vp.deals;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * @Author: Ayu Musfita
 * @Date: 28/05/20, Thu
 **/
public class KuponStepDefinitions extends TestInstrument implements En {

    public KuponStepDefinitions() {

        Given("^user choose Lihat Semua on popular merchant$", () -> {
            bukalapak.kuponLandingPage().tapOnSeeAllMerchant();
        });

        When("^user search  merchant with (.*) keyword$", (String state) -> {
            bukalapak.kuponMerchantListPage().validateOnPage();
            bukalapak.kuponMerchantListPage().typeOnSearchMerchant(state.toLowerCase());
        });

        Then("^the merchant will have( not)? displayed$", (String flag) -> {
            bukalapak.kuponMerchantListPage().validateSearchedMerchant(flag);
        });

        When("^user choose one of coupon popular merchant$", () -> {
            bukalapak.kuponLandingPage().choosePopularMerchant();
        });

        Then("^user will see list of merchants$", () -> {
            bukalapak.kuponMerchantListPage().validateAllMerchant();
        });

        Then("^user will see list of coupon from that merchant$", () -> {
            bukalapak.kuponListPage().validateOnPage();
            bukalapak.kuponListPage().validateCouponWithMerchant();
        });

        When("^user buys coupon$", () -> {
            bukalapak.kuponLandingPage().tapOnSeeAllMerchant();
            bukalapak.kuponMerchantListPage().typeOnSearchValidMerchant(dotenv.get("MERCHANT_NAME"));
            bukalapak.kuponMerchantListPage().validateMerchantDisplayed(dotenv.get("MERCHANT_NAME"));
            bukalapak.kuponMerchantListPage().setMerchantName();
            bukalapak.kuponMerchantListPage().tapOnMerchant();
            bukalapak.kuponListPage().validateOnPage();
            bukalapak.kuponListPage().setCouponName();
            bukalapak.kuponListPage().tapOnFirstCoupon();
            bukalapak.kuponDetailPage().validateOnPage();
            bukalapak.kuponDetailPage().addCouponSKU(2);
            bukalapak.kuponDetailPage().validateCoupon();
            bukalapak.kuponDetailPage().tapOnBuyButton();
        });

        When("^user click back button to cancel the transaction$", () -> {
           bukalapak.checkoutNonMarketplacePage().userOnCheckoutPage();
           bukalapak.kuponCheckoutPage().cancelTransaction();
        });

        Then("^page will have redirected to Kupon detail page$", () -> {
            bukalapak.kuponDetailPage().swipeDownToTitleSection();
            bukalapak.kuponDetailPage().validateOnPage();
        });

        When("^user choose \"([^\"]*)\" on product category$", (String category) -> {
            bukalapak.kuponLandingPage().tapOnCategory(category);
        });

        When("^user search \"([^\"]*)\" on coupon list page$", (String keyword) -> {
            bukalapak.kuponListPage().validateOnPage();
            bukalapak.kuponListPage().searchCoupon(keyword);
        });

        Then("^user will see \"([^\"]*)\" on list coupon$", (String keyword) -> {
            bukalapak.kuponListPage().validateSearchedCoupon(keyword);
        });

        When("^user search \"([^\"]*)\" on coupon landing page$", (String keyword) -> {
            bukalapak.kuponLandingPage().searchCoupon(keyword);
        });

        Then("^coupon with category \"([^\"]*)\" will have displayed$", (String category) -> {
            bukalapak.kuponListPage().validateOnPage();
            bukalapak.kuponListPage().validateCouponByCategory(category);
        });

        When("user filter coupons by \"([^\"]*)\" category", (String category) -> {
            bukalapak.kuponListPage().validateOnPage();
            bukalapak.kuponListPage().tapOnFilterCategory();
            bukalapak.kuponListPage().validateCategoryModalDisplayed();
            bukalapak.kuponListPage().chooseCategory(category);
        });

        When("^user filter coupons by \"([^\"]*)\" merchant$", (String merchant) -> {
            bukalapak.kuponListPage().validateOnPage();
            bukalapak.kuponListPage().tapOnFilterAllButton();
            bukalapak.kuponListPage().validateFilterModalDisplayed();
            bukalapak.kuponListPage().tapOnMerchantFilter();
            bukalapak.kuponListPage().validateMerchantListModalDisplayed();
            bukalapak.kuponListPage().typeOnSearchMerchant(merchant);
            bukalapak.kuponListPage().validateSearchedMerchant(merchant);
            bukalapak.kuponListPage().checkSelectedMerchant();
            bukalapak.kuponListPage().tapOnSaveButton();
            bukalapak.kuponListPage().validateFilterModalDisplayed();
            bukalapak.kuponListPage().tapOnApplyFilter();
        });

        When("^user filter coupons by Rentang Harga between (.*) and (.*)$", (String minPrice, String maxPrice) -> {
            bukalapak.kuponListPage().validateOnPage();
            bukalapak.kuponListPage().tapOnFilterAllButton();
            bukalapak.kuponListPage().validateFilterModalDisplayed();
            bukalapak.kuponListPage().typeOnPriceRange(minPrice, maxPrice);
            bukalapak.kuponListPage().tapOnApplyFilter();
        });

        Then("^user will see list of coupons on that price range$", () -> {
            bukalapak.kuponListPage().validateOnPage();
            bukalapak.kuponListPage().validateOnPriceRange();
        });

        When("^user sorts the coupon by best selling$", () -> {
            bukalapak.kuponListPage().validateOnPage();
            bukalapak.kuponListPage().tapOnSortButton();
            bukalapak.kuponListPage().validateSortModalDisplayed();
            bukalapak.kuponListPage().tapOnSortBestSellerOption();
        });

        Then("^user will see list of best selling coupon$", () -> {
            bukalapak.kuponListPage().validateCouponBestSeller();
        });

        And("user choose Semua Kupon on product category", () -> {
            bukalapak.kuponLandingPage().tapOnSemuaKupon();
        });

        When("user search coupon by invalid keyword", () -> {
            bukalapak.kuponLandingPage().searchInvalidKupon();
        });

        When("user search coupon by invalid keyword from landing page", () -> {
            bukalapak.kuponLandingPage().searchInvalidKuponFromLandingPage();
        });

        Then("the kupon will have not displayed", () -> {
            bukalapak.kuponLandingPage().validateKuponIsNotFound();
        });
    }
}
