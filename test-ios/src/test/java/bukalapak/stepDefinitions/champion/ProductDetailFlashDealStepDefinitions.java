package bukalapak.stepDefinitions.champion;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import bukalapak.pageObject.PremiumDashboardPage;
import bukalapak.pageObject.ProductDetailFlashDealPage;
import cucumber.api.java8.En;

public class ProductDetailFlashDealStepDefinitions extends TestInstrument implements En {
    public ProductDetailFlashDealStepDefinitions() {
        Then("user redirected to flash deal PDP", () -> {
            bukalapak.productDetailFlashDealPage().validateProductDetailFlashDealPageDisplayed();
        });

        Then("user verify product name exist", () -> {
            bukalapak.productDetailFlashDealPage().validateFDProductName();
        });

        Then("user verify product stock exist", () -> {
            bukalapak.productDetailFlashDealPage().validateFDStock();
        });

        Then("user verify product info exist", () -> {
            bukalapak.productDetailFlashDealPage().validateProductInfo();
        });

        Then("user verify product info behaviour valid", () -> {
            bukalapak.productDetailFlashDealPage().tapLihatSemuanya();
            bukalapak.productDetailFlashDealPage().validateInformasiBarangPopup();
            bukalapak.productDetailFlashDealPage().tapCloseButtonOnInformasiBarangPopup();
        });

        Then("user verify pelapak info exist", () -> {
            bukalapak.productDetailFlashDealPage().validatePelapakInfo();
        });

        Then("user verify pelapak info behaviour valid", () -> {
            bukalapak.productDetailFlashDealPage().tapPelapakInfoSection();
            bukalapak.merchantPage().userOnMerchantPage();
        });

        Then("user verify Product Review section exist", () -> {
            bukalapak.productDetailFlashDealPage().validateProductReviewSection();
        });

        Then("user verify Product Review behaviour valid", () -> {
            bukalapak.productDetailFlashDealPage().validateProductReviewBehaviour();
        });

        Then("user verify Product Recommendation section exist", () -> {
            bukalapak.productDetailFlashDealPage().validateProductRecommendationSection();
        });

        Then("user verify Product Recommendation behaviour valid", () -> {
            bukalapak.productDetailFlashDealPage().tapBeliButtonOnProductRecommendation();
            bukalapak.productDetailFlashDealPage().tapProductRecommendation();
            bukalapak.productDetailsPage().userOnProductDetailPage();
            bukalapak.productDetailsPage().clickBack();
        });

        Then("user verify Product Related section exist", () -> {
            bukalapak.productDetailFlashDealPage().validateProductRelatedSection();
        });

        Then("user verify Product Related behaviour valid", () -> {
            bukalapak.productDetailFlashDealPage().validateProductRelatedBehaviour();
            bukalapak.productDetailFlashDealPage().tapProductRelated();
            bukalapak.productDetailsPage().userOnProductDetailPage();
            bukalapak.productDetailsPage().clickBack();
        });

        Then("user tap back button on PDP", () -> {
            bukalapak.productDetailFlashDealPage().tapPDPBackButton();
        });

        Then("^user verify flash deal PDP (ongoing|upcoming) timer is exist$", (String timerState) -> {
            bukalapak.productDetailFlashDealPage().validatePDPTimer(timerState);
        });

        Then("^flash deal PDP product (masked|unmasked) price is displayed$", (String priceState) -> {
            bukalapak.productDetailFlashDealPage().validatePDPPrice(priceState);
        });

        Then("user click back from Flash Deal PDP", () -> {
            bukalapak.productDetailFlashDealPage().tapBackButton();
        });

        Then("user click Beli Button on Flash Deal product", () -> {
            bukalapak.productDetailFlashDealPage().tapBeliButton();
        });

        Then("user redirect to Flash Deal Checkout Page", () -> {
            bukalapak.productDetailFlashDealPage().proceedToFDCheckoutPage();
            bukalapak.checkoutMarketplacePage().userOnCheckoutPage();
            bukalapak.productDetailFlashDealPage().userOnFDCheckoutPage();
        });

        Then("user validate flash deal price on detail page and checkout correct", () -> {
            bukalapak.productDetailFlashDealPage().validateFlashDealPriceonCheckout();
        });

        Then("user validate flash deal tag exist", () -> {
            bukalapak.checkoutMarketplacePage().validateFlashDealTag();
        });

        Then("user click add to cart flash deal product", () -> {
            bukalapak.productDetailFlashDealPage().tapAddToCartFD();
        });

        Then("user verify product added to cart correctly", () -> {
            bukalapak.productDetailFlashDealPage().validateATCPopup();
            bukalapak.productDetailFlashDealPage().validateProductAddedToCart();
        });

        Then("user validate flash deal tag on pop up cart exist", () -> {
            bukalapak.productDetailFlashDealPage().validateFlashDealTagOnATC();
        });

        Then("user verify PDP Rating & Review section exist", () -> {
            bukalapak.productDetailFlashDealPage().validateRatingAndReview();
        });

        Then("user tap PDP Rating & Review section", () -> {
            bukalapak.productDetailFlashDealPage().tapRatingAndReview();
        });

        Then("user is on Ulasan Barang Page", () -> {
            bukalapak.productDetailFlashDealPage().isOnUlasanPage();
        });

        And("user verify Shipping Estimation info section exist", () -> {
            bukalapak.productDetailFlashDealPage().validateFDShippingInfo();
        });

        And("user tap Shipping Estimation info section", () -> {
            bukalapak.productDetailFlashDealPage().tapShippingInfo();
        });

        And("user is on Cek Kurir & Ongkos Kirim pop up", () -> {
            bukalapak.productDetailFlashDealPage().validateShippingPopUp();
            bukalapak.productDetailFlashDealPage().tapCloseButtonOnShippingPopup();
        });
    }
}
