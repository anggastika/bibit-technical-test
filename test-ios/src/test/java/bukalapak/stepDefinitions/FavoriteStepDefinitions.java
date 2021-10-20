package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * Favorite Step Definitions is used by:
 * - PNR
 * - DANA
 * <p>
 * *please add your squad name if you are using this class too*
 */

public class FavoriteStepDefinitions extends TestInstrument implements En {
    public FavoriteStepDefinitions() {
        And("user see recommendations on favorite page", () -> {
            bukalapak.favoritePage().checkRecommendationPanelAppear();
        });
        And("user see logout state of favorite page", () -> {
            bukalapak.favoritePage().checkLogoutStateOfFavoritePage();
        });

        And("^user should see product name on favorite page$", () -> {
            bukalapak.favoritePage().checkProductNameIsExist();
        });

        And("^user should not see product name on favorite page$", () -> {
            bukalapak.favoritePage().verifyIsOnFavoritePage();
            bukalapak.favoritePage().checkProductNameIsNotExist();
        });

        And("^user buy the item in favorite list menu$", () -> {
            try {
                bukalapak.iOSBasePage().waitForVisibilityOf("favorite_buy_button",20);
                bukalapak.iOSBasePage().tapElement("favorite_buy_button");
                bukalapak.iOSBasePage().waitForVisibilityOf("favorite_lanjut_bayar_button",20);
                bukalapak.iOSBasePage().tapElement("favorite_lanjut_bayar_button");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        And("^user search \"([^\"]*)\" on favorite list$", (String keyword) -> {
            bukalapak.favoritePage().searchProductOnFavorite(keyword);
        });

        // DANA Section

        And("^user buy item in favorite menu with price > Rp 10.000$", () -> {
            bukalapak.favoritePage().tapProductName("barang_dana_mix_payment");
        });

        And("^user buy item in favorite menu with price < Rp 10.000$", () -> {
            bukalapak.favoritePage().tapProductName("barang_dana_rp100");
        });

        And("^user buy item in favorite menu$", () -> {
            bukalapak.favoritePage().tapProductName("barang_dana_single_payment");
        });

        And("^user buy item DANA for voucher in favorite menu$", () -> {
            bukalapak.favoritePage().tapProductName("barang_dana_single_payment_voucher");
        });

        // End Section

        And("^user is on Barang Favorit menu$", () -> {
            bukalapak.homePage().skipDanaOnboarding();
            bukalapak.favoritePage().verifyIsOnFavoritePage();
        });

        And("^user tap favorite icon element$", () -> {
            bukalapak.favoritePage().tapFavoriteIcon();
        });

        And("^user filter product by \"([^\"]*)\"$", (String keywordType) -> {
            bukalapak.favoritePage().filterProductByKeyword(keywordType);
        });

        Then("^user verify label \"([^\"]*)\" exist$", (String keywordType) -> {
            bukalapak.favoritePage().verifyFilterLabel(keywordType);
        });

        And("^user should see label Stok Habis on out of stock product$", () -> {
            bukalapak.favoritePage().validateOutOfStockLabel();
        });

        And("^user should see label Tidak tersedia on out of stock product$", () -> {
            bukalapak.favoritePage().validateOutOfStockRevampLabel();
        });

        And("^user should see buy button is disable on favorite list$", () -> {
            bukalapak.favoritePage().validateBuyButtonDisable();
        });

        When("^user tap on out of stock product on favorite list$", () -> {
            bukalapak.favoritePage().validateFavouriteProductExist();
            bukalapak.favoritePage().tapFavouriteProductExist();
        });

        And("user tap on favourite product that search before", () -> {
            bukalapak.favoritePage().validateFavouriteProductExist();
            bukalapak.favoritePage().tapFavouriteProductExist();
        });

        And("user validate no ongkir icon appear on favorite product card", () -> {
            bukalapak.favoritePage().validateGratisOngkirIcon();
        });

        And("validate button beli not appear on new revamp favourite page", () -> {
            bukalapak.favoritePage().validateBuyButtonNotExist();
        });

        And("user validate newly added product to favorite is displayed", () -> {
            bukalapak.favoritePage().validateNewlyAddedProduct();
        });
    }
}
