package bukalapak.stepDefinitions.btp;

import bukalapak.TestInstrument;
import bukalapak.data.CartData;
import bukalapak.data.UserData;
import bukalapak.data.TransactionData;
import bukalapak.data.MerchantData;
import cucumber.api.java8.En;
import cucumber.api.Scenario;

public class CartStepDefinitions extends TestInstrument implements En {
    public CartStepDefinitions() {
        Given("user is in \"cart\" page", () -> {
            bukalapak.cartPage().userOnCartPage();
        });

        Given("user is in \"search_cart_pop_up\" page", () -> {
            bukalapak.searchCartPopUpPage().userOnSearchCartPopUpPage();
        });

        And("user click Pilih Semua in cart", () -> {
            bukalapak.cartPage().clickPilihSemua();
        });
        And("user click Bayar in cart", () -> {
            bukalapak.cartPage().clickBayar();
        });
        And("user click Back in cart", () -> {
            bukalapak.cartPage().clickBack();
        });
        And("user clear cart", () -> {
            bukalapak.cartPage().clearCart();
        });
        And("user modify quantity in cart page", () -> {
            bukalapak.cartPage().modifyQuantityAndRecheckPrice();
        });
        And("user verify biaya admin in cart and checkout page", () -> {
            bukalapak.cartPage().getBiayaAdmin();
            bukalapak.cartPage().clickBayar();
            bukalapak.checkoutMarketplacePage().validateAdminFeeFromCartAndCheckoutPage();
        });

        And("user verify all items and sellers in cart page", () -> {
            bukalapak.cartPage().userVerifyAllItemsAndSellers();
        });

        And("user remove all items one by one in cart page", () -> {
            bukalapak.cartPage().deleteItemsOneByOne();
        });

        /* This step definitions is used by PNR */
        And("user see recommendations on cart page", () -> {
            bukalapak.cartPage().checkRecommendationPanelAppear();
        });

        And("^user should be redirect to pilih variant page$", () -> {
            bukalapak.cartPage().checkPilihVariantBarangPage();
        });

        And("^user should see variant that user choose$", () -> {
            bukalapak.cartPage().checkInfoVariantProduct();
        });

        And("^user should be redirect to pop up add to cart with variant$", () -> {
            bukalapak.cartPage().checkIsRedirectToPopUpAddToCartVariant();
        });

        Then("^user add product to favorite from pop up add to cart$", () -> {
            bukalapak.cartPage().addToFavoritePage();
        });

        And("^user should be redirect to pop up add to cart with productName$", () -> {
            bukalapak.cartPage().checkPopUpFrom();
        });

        And("user verify that detail product on cart is correct", () -> {
            bukalapak.cartPage().verifyProductDetailOnCart(CartData.getCartDetails());
        });

        And("user verify strikethrough price appear on item", () -> {
            bukalapak.cartPage().verifyDiscountLabel();
        });

        When("user select item \"([^\"]*)\"", (String itemName) -> {
            bukalapak.cartPage().selectItem(itemName);
        });

        Then("user verify only selected item affect total price", () -> {
            bukalapak.cartPage().verifyTotalPrice(CartData.getSelectedItemOnCart());
        });

        When("^user update quantity \"([^\"]*)\" by click (add|substract) button (\\d{1,}) times$", (String productName, String update, String times) -> {
            bukalapak.cartPage().updateItemQuantity(productName, update, Integer.parseInt(times));
        });

        Then("user verify item price is changed", () -> {
            bukalapak.cartPage().validateItemPriceChanged(CartData.getCartDetails());
        });

        When("user tap on \"([^\"]*)\" in cart", (String name) -> {
            bukalapak.cartPage().tapOnName(name);
            MerchantData.setSellerName(name);
        });

        When("user tap on product \"([^\"]*)\" in cart", (String productName) -> {
            bukalapak.cartPage().tapOnProductName(productName);
            MerchantData.setProductName(productName);
        });

        When("user tap on product image \"([^\"]*)\" in cart", (String productName) -> {
            bukalapak.cartPage().tapOnProductImageName(productName);
        });

        When("user tap delete icon on \"([^\"]*)\"", (String itemName) -> {
            bukalapak.cartPage().tapDeleteIconPerItem(itemName);
        });

        And("user delete item \"([^\"]*)\" on cart page", (String itemName) -> {
            bukalapak.cartPage().tapDeletePerItem(itemName);
        });

        When("user verify Hapus Barang pop up", () -> {
            bukalapak.cartPage().verifyPopupHapusBarang();
        });

        When("user tap on Hapus button on Hapus Barang pop up", () -> {
            bukalapak.cartPage().clickHapusBarangButton();
        });

        When("user tap on Hapus & Simpan ke Favorit button on Hapus Barang pop up", () -> {
            bukalapak.cartPage().clickHapusBarangToFavouriteButton();
        });

        Then("user verify \"([^\"]*)\" notification", (String notif) -> {
            bukalapak.cartPage().verifyNotificationSuccess(notif);
        });

        Then("user verify \"([^\"]*)\" item disappear", (String itemName) -> {
            bukalapak.cartPage().verifyDeletedItem(itemName);
        });

        Then("^user verify subtotal on each seller is correct$", () -> {
            bukalapak.cartPage().verifySubTotalPerStore(CartData.getCartDetails(), CartData.getstoreName());
        });

        When("^user update quantity \"([^\"]*)\" by input (\\d) on quantity textfield$", (String productName, String qty) -> {
            bukalapak.cartPage().setItemQuantity(productName, qty);
        });

        When("user verify error message \"([^\"]*)\" is appear", (String msg) -> {
            bukalapak.cartPage().verifyInvalidItemAlertMsg(msg);
        });

        Then("user verify Bayar button not appear", () -> {
            bukalapak.cartPage().verifyBayarButtonNotAppear();
        });

        After(new String[]{"@BTP-5232"}, (Scenario scenario) -> {
            bukalapak.apiCall().setUserAuthv4("BETEPI");
            bukalapak.apiCall().setStoreStatus("BETEPI", "open");
        });

        After(new String[]{"@BTP-5233"}, (Scenario scenario) -> {
            bukalapak.apiCall().setUserAuthv4("BETEPI_STORE");
            bukalapak.apiCall().setQuantityItemOnStore(CartData.getExpectedProductName(), 1000, true);
        });

        After(new String[]{"@BTP-5281"}, (Scenario scenario) -> {
            bukalapak.apiCall().setUserAuthv4("BETEVI");
            bukalapak.apiCall().setStoreStatus("BETEVI", "open");

            bukalapak.apiCall().setUserAuthv4("BETEPI_STORE");
            bukalapak.apiCall().setQuantityItemOnStore(CartData.getExpectedProductName(), 1000, true);
        });

        When("^user verify error message Stok sisa (1|2|3) is appear$", (String amount) -> {
            bukalapak.cartPage().validateLeftoverItem(CartData.getExpectedProductName(), amount);
        });

        Then("^user verify item quantity does not increase", () -> {
            bukalapak.cartPage().validateProductQuantity(CartData.getQuantityItem(), CartData.getExpectedProductName());
        });

        When("^user verify Konfirmasi Pembayaran pop up message appear$", () -> {
            bukalapak.cartPage().validatePopUpKonfirmasiPembayaran();
        });

        When("^user verify amount invalid item$", () -> {
            bukalapak.cartPage().validateInvalidItemQty(CartData.getInvalidItemQty());
        });

        When("^user verify Hapus text appeared$", () -> {
            bukalapak.cartPage().validateHapusText();
        });

        When("user tap Hapus button link in cart", () -> {
            bukalapak.cartPage().tapHapusButtonLink();
        });

        Then("^user verify cart is empty$", () -> {
            bukalapak.cartPage().validateEmptyCart();
        });

        When("^user select seller name \"([^\"]*)\" on cart page", (String sellerName) -> {
            bukalapak.cartPage().selectSeller(sellerName);
        });

        Then("^user verify seller name \"([^\"]*)\" disappear from cart page", (String storeName) -> {
            bukalapak.cartPage().validateDeletedStore(storeName);
        });

        Then("^user should be redirect to cart page$", () -> {
            bukalapak.cartPage().checkIsRedirectToCartPage();
        });

        When("user unselect all in cart", () -> {
            bukalapak.cartPage().unSelectAll();
        });

        And("^user clear cart from pop up add to cart$", () -> {
            bukalapak.cartPage().clearCartPopUp();
        });

        And("^user should see product name on pop up add to cart$", () -> {
            bukalapak.cartPage().checkProductNameOnPopUp();
        });

        When("^user set total price to (above|below) DANA on Cart Page$", (String state) -> {
            bukalapak.cartPage().setTotalPrice(
                    state.equals("above")
                            ? UserData.getOwnedDANA() + TransactionData.roundingCounterAmount
                            : UserData.getOwnedDANA() - TransactionData.roundingCounterAmount,
                    state.equals("above"));
        });

        When("^user set total price to (above|below) DANA plus Credits on Cart Page", (String state) -> {
            bukalapak.cartPage().setTotalPrice(
                    state.equals("above")
                            ? UserData.getOwnedDANA() + UserData.getOwnedCredits() + TransactionData.roundingCounterAmount
                            : UserData.getOwnedDANA() + UserData.getOwnedCredits() - TransactionData.roundingCounterAmount,
                    state.equals("above"));
        });

        When("user navigate to \"cart\" page", () -> {
            bukalapak.cartPage().clickCartMenu();
        });

        And("^user see pelapak name on the top$", () -> {
            bukalapak.cartPage().validatePelapakNAme();
        });

        When("^user see recommendation same seller on cart$", () -> {
            bukalapak.cartPage().scrollToRecoSameSeller();
        });

        When("^user click button Beli on recommendation same seller$", () -> {
            bukalapak.cartPage().clickButtonBeliRecoSameSeller();
        });

        Then("^user see pop up add to cart on cart page$", () -> {
            bukalapak.cartPage().seePopUpATC();
        });

        When("^user clicks product recommendation same seller$", () -> {
            bukalapak.cartPage().clickProductRecoSameSeller();
        });

        And("^user see recommendation on cart$", () -> {
            bukalapak.cartPage().scrollToRecommendation();
        });

        When("^user click button Beli on recommendation$", () -> {
            bukalapak.cartPage().clickButtonBeliRecommendation();
        });

        When("^user clicks product recommendation$", () -> {
            bukalapak.cartPage().clickProductRecommendation();
        });

        When("^user open product sameseller page using app link$", () -> {
            bukalapak.iOSBasePage().openDeepLink(dotenv.get("PRODUCT_SAME_SELLER_ON_CART"));
        });

        And("user should see recommendation section",()->{
            bukalapak.cartPage().validateReco();
        });

        And("^user login with credential \"([^\"]*)\" from cart page$", (String username) -> {
            bukalapak.loginPage().tapOnBoardingLoginBtn();
            bukalapak.loginPage().userOnLoginPage();
            bukalapak.loginPage().loginAs(username);
            bukalapak.cartPage().userOnCartPage();
            UserData.setLoggedIn(true);
        });

        And("user verify recommendation same seller on cart not showing", () -> {
            bukalapak.cartPage().validateRecoSameSellerNotExist();
        });

        Then("user should see discount price in pop up add to cart", () -> {
            bukalapak.cartPage().checkDiscountPrice();
        });

        When("user open cart page via deeplink", () -> {
            bukalapak.cartPage().openCartPage();
        });

        And("user should see recommendation section on cart page", () -> {
            bukalapak.cartPage().validateRecoSection();
        });

        And("user click add to cart", () -> {
            bukalapak.cartPage().addToCartReco();
        });

        When("user click plus to cart on \"([^\"]*)\" product", (String productName) -> {
            bukalapak.cartPage().tapButtonPlusCartPage(productName);
        });

        When("user click minus to cart on \"([^\"]*)\" product", (String productName) -> {
            bukalapak.cartPage().tapButtonMinusCartPage(productName);
        });

        Then("verify subtotal is exist", () -> {
            bukalapak.cartPage().verifySubtotalIsExist();
        });

        When("user choose item \"([^\"]*)\"", (String itemName) -> {
            bukalapak.cartPage().chooseItem(itemName);
        });

        Then("user clear cart automatically", () -> {
            bukalapak.cartPage().autoClearCart();
        });

        And("user verify flash deal label exist", () -> {
            bukalapak.cartPage().flashDealLabelonCart();
        });

        And("user verify price added to cart correct", () -> {
            bukalapak.cartPage().validatePriceAddedCorrect();
        });

        And("user verify cart quantity disable", () -> {
            bukalapak.cartPage().validateFDQuantityDisable();
        });

        And("user tap product image will redirect to flash deal PDP", () -> {
            bukalapak.cartPage().tapFlashDealProductOnCart();
            bukalapak.productDetailFlashDealPage().validateProductDetailFlashDealPageDisplayed();
            bukalapak.flashDealProductListPage().tapFDBackButton();
        });

        And("user tap Bayar button will redirect to checkout page", () -> {
            bukalapak.cartPage().clickBayar();
            bukalapak.checkoutMarketplacePage().userOnCheckoutPage();
            bukalapak.checkoutMarketplacePage().backFromCheckout();
        });

        And("user delete flash deal product from cart", () -> {
            bukalapak.cartPage().deleteFDProductOnCart();
            bukalapak.cartPage().verifyPopupHapusBarang();
            bukalapak.cartPage().clickHapusBarangButton();
        });
    }
}
