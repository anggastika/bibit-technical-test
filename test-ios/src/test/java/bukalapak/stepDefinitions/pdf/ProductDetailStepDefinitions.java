package bukalapak.stepDefinitions.pdf;

import bukalapak.TestInstrument;
import bukalapak.data.STRIPEData;
import bukalapak.data.SubsidiesData;
import cucumber.api.java8.En;

/**
 * Product Details Step Definitions is used by:
 * - PNR
 * - DANA
 * - XPR
 * - SEARCH
 * - MTQ
 * - VP-PREPAID
 * <p>
 * *please add your squad name if you are using this class too*
 */

public class ProductDetailStepDefinitions extends TestInstrument implements En {
    public ProductDetailStepDefinitions() {
        Given("user is in \"product_detail_marketplace\" page", () -> {
            bukalapak.productDetailsPage().userOnProductDetailPage();
        });

        Given("user is in product detail page with deeplink \"([^\"]*)\"", (String deeplink) -> {
            bukalapak.productDetailsPage().userOnProductDetailPageWithDeeplink(deeplink);
        });

        And("^user tap buy now button on product detail revamp$", () -> {
            bukalapak.productListingPage().checkIsOnProductDetail();
            bukalapak.iOSBasePage().tapElement("button_beli_sekarang");
        });

        And("^user back to home from product detail$", () -> {
            bukalapak.productListingPage().backToHome();
        });

        And("^user back to listing from product detail$", () -> {
            bukalapak.productDetailsPage().clickBackToProductListing();
        });

        And("^user should see diskon on product detail$", () -> {
            bukalapak.productDetailsPage().checkDiskonOnProductDetail();
        });

        And("^user verify diskon on product detail$", () -> {
            bukalapak.productDetailsPage().validateDiscount();
        });

        And("^user should see cicilan on product detail$", () -> {
            bukalapak.productDetailsPage().checkCicilanOnProductDetail();
        });

        And("^user should see grosir on product detail$", () -> {
            bukalapak.productDetailsPage().checkGrosirOnProductDetail();
        });

        And("^user validate icon grosir on product detail$", () -> {
            bukalapak.productDetailsPage().validateGrosirIcon();
        });

        And("user see recommendations on product detail", () -> {
            bukalapak.productDetailsPage().checkRecommendationPanelAppear();
        });

        And("^user should check the cheaper price$", () -> {
            bukalapak.productDetailsPage().checkTheCheaperPrice();
        });

        And("^user compare price rentang harga$", () -> {
            bukalapak.productDetailsPage().comparePriceOnProductDetail();
        });

        Then("^user should see \"([^\"]*)\" on product detail$", (String price) -> {
            bukalapak.productDetailsPage().checkPriceOnProductDetail(price);
        });

        And("^user should check the more expensive price$", () -> {
            bukalapak.productDetailsPage().checkTheMoreExpensivePrice();
        });

        And("^user should see list Ulasan Barang$", () -> {
            bukalapak.productDetailsPage().checkUlasanPage();
        });

        And("^user click Bagikan$", () -> {
            bukalapak.productDetailsPage().clickBagikan();
        });

        And("^user should see pop up bagikan icon$", () -> {
            bukalapak.productDetailsPage().checkBagikanPopUp();
        });

        And("^user select location delivery$", () -> {
            bukalapak.productDetailsPage().setLocationDelivery();
        });

        And("^user check price delivery fee$", () -> {
            bukalapak.productDetailsPage().checkDeliveryFee();
        });

        Then("^user changes amount of product$", () -> {
            bukalapak.productDetailsPage().changesAmountProduct();
        });

        Then("^user check amount product$", () -> {
            bukalapak.productDetailsPage().checkAmountProduct();
        });

        And("^user should see info barang page is displayed$", () -> {
            bukalapak.productDetailsPage().checkInfoBarangPage();
        });

        Then("^user click kategori on info barang$", () -> {
            bukalapak.productDetailsPage().clickKategoriOnInfoBarang();
        });

        And("^user scroll down to lihat semua ulasan$", () -> {
            bukalapak.productDetailsPage().scrollToUlasanBarang();
        });

        And("^user scroll down to diskusi barang$", () -> {
            bukalapak.productDetailsPage().scrollToDiskusiBarang();
        });

        Then("^user click \"([^\"]*)\" on product detail$", (String elementName) -> {
            bukalapak.productDetailsPage().clickButtonFromProductDetail(elementName);
        });

        And("^user should see page diskusi barang$", () -> {
            bukalapak.productDetailsPage().checkDiskusiPage();
        });

        And("^user click laporkan$", () -> {
            bukalapak.productDetailsPage().clickLaporkan();
        });

        And("^user should see laporkan page$", () -> {
            bukalapak.productDetailsPage().checkLaporkanPage();
        });

        And("^user should see product name on product detail$", () -> {
            bukalapak.productDetailsPage().checkProductName();
        });

        When("user click catalog info in product detail", () -> {
            bukalapak.productDetailsPage().clickCatalogInfo();
        });

        Then("^user is shown Lapak Terbaik on title product detail$", () -> {
            bukalapak.productDetailsPage().verifyLapakTerbaikBadgeTitle();
        });

        Then("^user is shown Super Seller on title product detail$", () -> {
            bukalapak.productDetailsPage().verifySuperSellerBadgeTitle();
        });

        Then("^user is shown Lapak Terbaik on pelapak section on product detail$", () -> {
            bukalapak.productDetailsPage().verifyLapakTerbaikOnPelapak();
        });

        Then("^user is shown Super Seller on pelapak section on product detail$", () -> {
            bukalapak.productDetailsPage().verifySuperSellerOnPelapak();
        });

        Then("^user verify DANA voucher for \"([^\"]*)\" is visible in PDP$", (String user) -> {
            bukalapak.apiCall().setUserAuthv4(user);
            Integer totalVoucher = bukalapak.apiCall().getDANAPocketTotal();
            bukalapak.productDetailsPage().isDANAVoucherExist(totalVoucher);
        });

        Then("^user tap benefit onboarding on product detail", () -> {
            bukalapak.productDetailsPage().closePopUpBenefitBeliBarang();
        });

        Then("^user tap Beli Sekarang button", () -> {
            bukalapak.productDetailsPage().clickBeliSekarangButton();
        });

        Then("^user is shown voucher lapak in PDP with info \"([^\"]*)\"$", (String title) -> {
            bukalapak.productDetailsPage().verifyVoucherLapakInfo(title);
        });

        Then("^user is shown voucher lapak in PDP with etalase \"([^\"]*)\"$", (String etalaseName) -> {
            bukalapak.productDetailsPage().verifyVoucherLapakEtalase(etalaseName);
        });

        Then("^user is shown voucher lapak on product detail page with min transaksi info \"([^\"]*)\"$", (String minTrx) -> {
            bukalapak.productDetailsPage().verifyVoucherMinTrx(minTrx);
        });

        Then("^user should see product name on product detail popular$", () -> {
            bukalapak.productDetailsPage().verifyProductNamePopular();
        });

        Then("^user should see product name on product detail recommendation$", () -> {
            bukalapak.productDetailsPage().verifyProductNameRecommendation();
        });

        When("^user tap favorite icon in product detail page$", () -> {
            bukalapak.productDetailsPage().tapFavoriteIcon();
        });

        And("^user tap on cart icon header in product detail page$", () -> {
            bukalapak.productDetailsPage().tapCartButton();
        });

        And("user tap on add to cart icon in product detail page", () -> {
            bukalapak.productDetailsPage().tapAddtoCartButton();
        });

        And("^user see pop up add to cart$", () -> {
            bukalapak.productDetailsPage().validatePopUpAddtocart();
        });

        And("^user should see product rating section on product detail page$", () -> {
            bukalapak.productDetailsPage().assertProductRatingSection();
        });

        And("^user validate product name on pop up add to cart$", () -> {
            bukalapak.searchCartPopUpPage().validateProductNameOnPopUp();
        });

        And("^user delete cart item on popup cart$", () -> {
            bukalapak.searchCartPopUpPage().deleteCartItem();
        });

        And("^user (.*) \"([^\"]*)\" tag in pdp revamp$", (String action, String tag) -> {
            bukalapak.productDetailsPage().productDetailTag(action, tag);
        });

        Then("^user should see \"([^\"]*)\" modal$", (String tag) -> {
            bukalapak.productDetailsPage().assertProductDetailTag(tag);
        });

        And("^user close product tag modal$", () -> {
            bukalapak.productDetailsPage().closeTagModal();
        });

        And("^user should see product detail tag modal closed$", () -> {
            bukalapak.productDetailsPage().assertPDPTagModalClosed();
        });

        And("^user scroll to \"([^\"]*)\" section on product detail", (String section) -> {
            bukalapak.productDetailsPage().exploreProductSection(section);
        });

        When("user tap Selengkapnya on Informasi Barang section", () -> {
            bukalapak.productDetailsPage().tapSelengkapnyaProductInfoSection();
        });

        Then("^user is on Barang Terkait product grid page$", () -> {
            bukalapak.productDetailsPage().assertRelatedProductGridPage();
        });

        Then("^user tap on \"([^\"]*)\" product item$", (String section) -> {
            bukalapak.productDetailsPage().tapProductExploreSection(section);
        });

        Then("^user should see video as the first image in the image slider$", () -> {
            bukalapak.productDetailsPage().assertVideoPDP();
        });

        And("^user should see video play button$", () -> {
            bukalapak.productDetailsPage().assertPlayVideoButton();
        });

        And("^user tap voucher section in PDP$", () -> {
            bukalapak.productDetailsPage().tapVoucherSection();
        });

        And("^user tap voucher \"([^\"]*)\"$", (String voucherType) -> {
            bukalapak.productDetailsPage().tapVoucherItem(voucherType);
        });

        And("^user should see voucher section in PDP$", () -> {
            bukalapak.productDetailsPage().validateVoucherSection();
        });

        Then("^user success delete cart item from popup cart$", () -> {
            bukalapak.searchCartPopUpPage().assertEmptyCartItemPopup();
        });

        Then("^user should see correct normal price$", () -> {
            bukalapak.productDetailsPage().validateNormalPrice();
        });

        And("^user should see correct discount price$", () -> {
            bukalapak.productDetailsPage().validateDiscountPrice();
        });

        And("^user should see discount label", () -> {
            bukalapak.productDetailsPage().validateDiscountLabel();
        });

        And("^user should see rating (\\d+) or more", (Integer rating) -> {
            bukalapak.productDetailsPage().validateRatingLabel(rating);
        });

        And("^user should see product info section on product detail$", () -> {
            bukalapak.productDetailsPage().validateProductInformationLabel();
        });

        And("^user tap on \"Selengkapnya\" on product info section$", () -> {
            bukalapak.productDetailsPage().tapDetailProductInfo();
        });

        And("^user should see product info modal on product detail$", () -> {
            bukalapak.productDetailsPage().validateProductInfoModal();
        });

        And("^user should see product info description section displayed$", () -> {
            bukalapak.productDetailsPage().validateDescriptionLabel();
        });

        Then("^user tap link in product info description on product detail$", () -> {
            bukalapak.productDetailsPage().tapDescLink();
        });

        When("^user should see promo overlay banner exist$", () -> {
            bukalapak.productDetailsPage().validatePromoOverlay();
        });

        And("^user tap image in pdp$", () -> {
            bukalapak.productDetailsPage().tapImageinPDP();
        });

        And("^user should see potrait image modal$", () -> {
            bukalapak.productDetailsPage().validatePotraitImageModal();
        });

        And("^user tap close button in potrait image modal$", () -> {
            bukalapak.productDetailsPage().tapCloseButtonInPotraitImage();
        });

        When("^user should see new section wholesale$", () -> {
            bukalapak.productDetailsPage().validateNewWholesaleSection();
        });

        And("^user click dropdown icon on wholesale section$", () -> {
            bukalapak.productDetailsPage().tapDropdownWholesale();
        });

        And("^user should see info detail of wholesale expanded$", () -> {
            bukalapak.productDetailsPage().validateInfoDetailWholesaleSection();
        });

        And("^user should see info detail of wholesale will be hidden$", () -> {
            bukalapak.productDetailsPage().validateInfoDetailWholesaleSectionNotExist();
        });

        And("^user pull to refresh$", () -> {
            bukalapak.productDetailsPage().doPullRefresh(2);
        });

        Then("^user verify detail of wholesale section stay expanded and not collapsed$", () -> {
            bukalapak.productDetailsPage().validateInfoDetailWholesaleSectionStayExpanded();
        });

        And("^user scroll down to the bottom of the page$", () -> {
            bukalapak.productDetailsPage().scrollDownInPDP();
        });

        And("^user scroll up to the new section wholesale$", () -> {
            bukalapak.productDetailsPage().scrollUpInPDP();
        });

        Then("^user not see recommendation on pdp$", () -> {
            bukalapak.productDetailsPage().validateRecommendationNotShowing();
        });

        And("^user click back to homepage from pdp$", () -> {
            bukalapak.productDetailsPage().clickBack();
        });

        And("^user should see \"([^\"]*)\" icon beside product name$", (String badges) -> {
            bukalapak.productDetailsPage().validateBadgeIconBesideProductName(badges);
        });

        Then("^user should see \"([^\"]*)\" icon in pelapak section on product detail$", (String badges) -> {
            bukalapak.productDetailsPage().validateBadgeIconInPelapakSection(badges);
        });

        When("^user tap on three dots button on product detail$", () -> {
            bukalapak.productDetailsPage().tapThreeDotsButton();
        });

        And("^user click Batal$", () -> {
            bukalapak.productDetailsPage().validateBatalOption();
        });

        Then("^user should see empty state of out of stock product on product detail$", () -> {
            bukalapak.productDetailsPage().validateOutOfStockProduct();
        });

        And("^user close pop up cart$", () -> {
            bukalapak.productDetailsPage().closePopUpAddToCart();
        });

        When("^user tap to cart page from product detail page$", () -> {
            bukalapak.productDetailsPage().tapToCartPage();
        });


        When("user reports a product from PDP", () -> {
            bukalapak.reportPage().closePage();
            bukalapak.productDetailsPage().userOnProductDetailPage();
            bukalapak.productDetailsPage().tapThreeDotsButton();
            bukalapak.productDetailsPage().clickLaporkan();
        });

        And("user should see Instant Courier on the list", () -> {
            bukalapak.productDetailsPage().verifyInstantCourier();
        });

        And("user set( \"([^\"]*)\" as)? address on product detail", (String address) -> {
            bukalapak.productDetailsPage().setAddress(address);
        });

        Then("^user click back button on product detail$", () -> {
            bukalapak.productDetailsPage().clickBack();
        });

        And("^user should see correct price in ATC pop up$", () -> {
            bukalapak.productDetailsPage().validatePriceATCPopup();
        });

        Then("^user should see \"([^\"]*)\" on courier list in product detail$", (String courier) -> {
            bukalapak.productDetailsPage().verifyCourier(courier);
        });

        And("^user verify variant \"([^\"]*)\" on product detail$", (String variant) -> {
            bukalapak.productDetailsPage().verifyVariantType(variant);
        });

        And("^user tap on variant \"([^\"]*)\" on product detail$", (String variant) -> {
            bukalapak.productDetailsPage().tapVariantType(variant);
        });

        When("user tap on variant section", () -> {
            bukalapak.productDetailsPage().tapOnVariantSection();
        });

        Then("^user verify variant value \"([^\"]*)\" on dropdown$", (String value) -> {
            bukalapak.productDetailsPage().verifyVariantValue(value);
        });

        Then("^user verify variant \"([^\"]*)\" with option \"([^\"]*)\" exists$", (String variant, String value) -> {
            bukalapak.productDetailsPage().verifyVariant(variant, value);
        });

        And("^user should see Preorder button in product detail$", () -> {
            bukalapak.productDetailsPage().verifyPreorderButton();
        });

        And("^user tap on Preorder button in product detail$", () -> {
            bukalapak.productDetailsPage().tapPreorderButton();
        });

        And("^user see special campaign banner$", () -> {
            bukalapak.productDetailsPage().verifySpecialCampaignBanner();
        });

        Then("user validate SLA on product detail match with input user", () -> {
            bukalapak.productDetailsPage().validateSLA(STRIPEData.getSLAType(), STRIPEData.getSLADuration());
        });

        Then("user see tray add to cart", () -> {
            bukalapak.productDetailsPage().verifyTrayATC();
        });

        And("user close pop up tray atc", () -> {
            bukalapak.productDetailsPage().tapCloseButtonTrayATC();
        });

        And("user \"([^\"]*)\" section recommendation hemat ongkir on tray atc", (String action) -> {
            bukalapak.productDetailsPage().verifySectionHematOngkir(action);
        });

        When("user tap product recommendation hemat ongkir", () -> {
            bukalapak.productDetailsPage().tapProductHematOngkir();
        });

        And("^user (see|not see)? section recommendation dibeli bersamaan on tray atc", (String cases) -> {
            bukalapak.productDetailsPage().verifySectionDibeliBersamaan(cases);
        });

        When("user tap product recommendation dibeli bersamaan", () -> {
            bukalapak.productDetailsPage().tapProductDibeliBersamaan();
        });

        When("user tap Lihat Semua in section hemat ongkir", () -> {
            bukalapak.productDetailsPage().tapLihatSemuaHematOngkir();
        });

        And("user tap Beli on variant product", () -> {
            bukalapak.productDetailsPage().tapButtonBeliOnVariant();
        });

        Then("user validate daily task reward not shown", () -> {
            bukalapak.productDetailsPage().validateRewardNotShown();
        });

        Then("user validate daily task reward shown", () -> {
            bukalapak.productDetailsPage().validateRewardShown();
        });

        And("^user tap on \"([^\"]*)\" button on pop up reward",(String action) -> {
            bukalapak.productDetailsPage().tapOnRewardPopUp(action);
        });

        Then("user validate is on own product detail page", () -> {
            bukalapak.productDetailsPage().validateOwnProductDetail();
        });

        Then("user validate specification \"([^\"]*)\" in Informasi Barang Section", (String specValue) -> {
            bukalapak.productDetailsPage().validateSpecification(specValue);
        });

        When("user should see foto dari pembeli in image section exist", () -> {
            bukalapak.productDetailsPage().validateFotoDariPembeliOnImageSection();
        });

        When("user swipe image in pdp", () -> {
            bukalapak.productDetailsPage().swipeImageOnPdp();
        });

        When("user tap foto dari pembeli in image section", () -> {
            bukalapak.productDetailsPage().tapFotoDariPembeli();
        });

        When("user is in foto dari ulasan page", () -> {
            bukalapak.productDetailsPage().validateOnFotoUlasanPage();
        });

        When("user swipe up and down on foto dari ulasan page", () -> {
            bukalapak.productDetailsPage().swipeUpAndDownOnFotoDariUlasanPage();
        });

        When("user should see sticky navbar on foto dari ulasan page", () -> {
            bukalapak.productDetailsPage().validateStickyNavbar();
        });

        When("user tap back button on foto dari ulasan page", () -> {
            bukalapak.productDetailsPage().tapBackBtnOnUlasanPage();
        });

        And("^user see popular same seller section$", () -> {
            bukalapak.productDetailsPage().verifyPssSection();
        });

        And("^user see the product card of popular same seller section$", () -> {
            bukalapak.productDetailsPage().validatePssProductCard();
        });

        And("^user can swipe left and swipe right popular same seller section$", () -> {
            bukalapak.productDetailsPage().swipePssProduct();
        });

        And("^user can see link to pelapak page$", () -> {
            bukalapak.productDetailsPage().verifyTextLinkPss();
        });

        When("^user tap on text link popular same seller section$", () -> {
            bukalapak.productDetailsPage().tapTextLinkPss();
        });

        Then("^user is redirected to seller page from pdp$", () -> {
            bukalapak.productDetailsPage().redirectToPelapakPage();
        });

        And("^user back tap back button in seller page$", () -> {
            bukalapak.productDetailsPage().tapBackButtonFromSellerPage();
        });

        When("^user tap product on popular same seller section$", () -> {
            bukalapak.productDetailsPage().tapPssProduct();
        });

        And("^user see the product card of recommendations section$", () -> {
            bukalapak.productDetailsPage().validateRecommendationProductCard();
        });

        When("^user tap product on recommendations section$", () -> {
            bukalapak.productDetailsPage().tapRecommendationProduct();
        });

        And("user should see the elements in the hemat ongkir, beli sekalian section", ()->{
            bukalapak.productDetailsPage().verifyElementRecoComplementary();
        });

        And("user tap Selengkapnya on Jualan Lain di Lapak Ini section", ()->{
            bukalapak.productDetailsPage().tapSelengkapnyaPss();
        });

        And("^user not see popular same seller section$", () -> {
            bukalapak.productDetailsPage().verifyPssSectionNotShowing();
        });

        And("^user validate( selection)? variant modal$", (String flag) -> {
            bukalapak.productDetailsPage().validateVariantModal(flag);
        });

        And("^user select( \"([^\"]*)\" as)? variant( \"([^\"]*)\")? on product variant$", (String option, String variant) -> {
            bukalapak.productDetailsPage().selectVariant(variant, option);
        });

        And("user tap Keranjang button on variant modal", () -> {
            bukalapak.productDetailsPage().tapButtonKeranjangOnVariant();
        });

        And("^user should see new voucher section", () -> {
            bukalapak.productDetailsPage().validateNewVoucherSection();
        });

        And("^user can swipe voucher card section", () -> {
            bukalapak.productDetailsPage().swipeOnVoucherSection();
        });

        Then("^user tap chat button from product detail page", () -> {
            bukalapak.productDetailsPage().clickChatButton();
        });

        And("user tap on payment ads section",() -> {
            bukalapak.productDetailsPage().tapPaymentAds();
        });

        And("user is in \"payment_ads_on_pdp\" page", () -> {
            bukalapak.productDetailsPage().checkPaymentAdsPage();
        });

        And("user tap \"Cek Selengkapnya\" and redirected to article ads",() -> {
            bukalapak.productDetailsPage().tapCekSelengkapnya();
        });

        When("user taps the merchandise banner", () -> {
            bukalapak.productDetailsPage().tapMerchandiseBanner();
        });

        When("the merchandise banner will have not displayed", () -> {
            bukalapak.productDetailsPage().verifyBannerNotDisplayed();
        });

        And("^user should see revamp variant section on product detail page$", () -> {
            bukalapak.productDetailsPage().validateRevampVariantSection();
        });

        When("^user tap on variant option in revamp variant section on product detail page$", () -> {
            bukalapak.productDetailsPage().tapRevampVariantOption();
        });

        Then("^user should see selected variant will be automatically applied on revamp variant modal$", () -> {
            bukalapak.productDetailsPage().verifyRevampVariantModalExist();
            bukalapak.productDetailsPage().verifyCTAButtonOnVariantModal();
            bukalapak.productDetailsPage().validateSpecificVariantSelected();
        });

        Then("^user can swipe variant option section on product detail page$", () -> {
            bukalapak.productDetailsPage().swipeOnVariantOptionSection();
        });

        Then("^user should see revamp variant page$", () -> {
            bukalapak.productDetailsPage().verifyRevampVariantModalExist();
        });

        And("^user should see preorder button at the bottom of variant page$", () -> {
            bukalapak.productDetailsPage().verifyPreorderButtonOnVariantModal();
        });

        And("user should see voucher id", () -> {
            bukalapak.productDetailsPage().verifyVoucherId();
        });

        Then("user is on marketplace product detail page", () -> {
            bukalapak.productDetailsPage().goToMarketplacePage();
        });
    }
}
