package bukalapak.stepDefinitions.homeandcat;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;
import bukalapak.data.*;

public class HomeStepDefinitions extends TestInstrument implements En {

    public HomeStepDefinitions() {
        When("user go to \"([^\"]*)\" page via widget", (String widget) -> {
            bukalapak.dopePage().goToDopePage();
            bukalapak.dopePage().isOnDopePage();
            bukalapak.dopePage().tapOnWidgetText(widget);
        });

        When("user go to \"([^\"]*)\" from Dope Page", (String widget) -> {
            bukalapak.dopePage().goToDopePage();
            bukalapak.dopePage().isOnDopePage();
            bukalapak.dopePage().searchDopeMenu(widget);
            bukalapak.dopePage().tapSearchResultExist(widget);
        });

        // for search and click widget on dope screen
        When("user click \"([^\"]*)\" from Dope Page", (String widget) -> {
            bukalapak.dopePage().isOnDopePage();
            bukalapak.dopePage().searchDopeMenu(widget);
            bukalapak.dopePage().tapSearchResultExist(widget);
        });

        When("user go to \"([^\"]*)\" page via widget fitur pelapak", (String widget) -> {
            bukalapak.homePage().clickWidgetFiturPelapak(widget);
        });

        And("^user tap SALDO button in Home page$", () -> {
            bukalapak.homePage().tapUserSaldo();
        });

        Then("^user should see \"([^\"]*)\" on new homepage$", (String componentsType) -> {
            switch (componentsType) {
                case "Homepage Shortcuts":
                    bukalapak.homePage().checkIconShorcutsOnHome();
                    break;
                case "Flash Banners":
                    bukalapak.homePage().checkFlashBannersOnHome();
                    break;
                case "Homepage Menus":
                    bukalapak.homePage().checkHomePageMenusOnHome();
                    break;
                case "Secondary Banners":
                    bukalapak.homePage().checkSecondaryBannerOnHome();
                    break;
                case "Categories":
                    bukalapak.homePage().checkCategoriesOnHome();
                    break;
                case "Today Deals":
                    bukalapak.homePage().checkTodaysDealOnHome();
                    break;
                case "New Items":
                    bukalapak.homePage().checkNewItemsOnHome();
                    break;
                case "Hotlists":
                    bukalapak.homePage().checkHotlistsOnHome();
                    break;
                case "Hotlist":
                    bukalapak.homePage().checkHotlistOnHome();
                    break;
                case "Rekomendasi":
                    bukalapak.homePage().checkRecommendationPanelAppear();
                    break;
                case "Highlight":
                    bukalapak.homePage().validateHighlightSection();
                    break;
                case "Category Tab":
                    bukalapak.homePage().checkCategoryTabOnHome();
                    break;
                default:
                    break;
            }
        });

        And("^user should be able to click Homepage Shortcut \"([^\"]*)\"$", (String shortcutName) -> {
            bukalapak.homePage().tapHomepageShortcut(shortcutName);
        });

        And("^user should be redirected from alchemy homepage to \"([^\"]*)\"$", (String shortcutName) -> {
            bukalapak.homePage().homepageRedirectedTo(shortcutName);
        });

        And("^user should be redirected from alchemy homepage to Hotlist Listing$", () -> {
            bukalapak.homePage().checkListHotlistOnHome();
        });

        And("^user should be able to click from list hotlist$", () -> {
            bukalapak.homePage().clickHotlistFromList();
        });

        And("^user should be able to click \"([^\"]*)\" Lihat Semua Button$", (String lihatSemuaType) -> {
            bukalapak.homePage().clickLihatSemua(lihatSemuaType);
        });

        And("^user should not see recommendation panel on homepage$", () -> {
            bukalapak.homePage().checkRecommendationPanelNotAppear();
        });

        Then("^click cart icon from home page$", () -> {
            bukalapak.homePage().clickCartIcon();
        });

        Then("user access cart page via deeplink", () -> {
            bukalapak.homePage().openDeepLink("/cart/carts");
        });

        And("user click \"([^\"]*)\" menu on Fitur Pelapak", (String arg0) -> {
            bukalapak.homePage().fiturPelapakMenu(arg0);
        });

        And("user verify \"([^\"]*)\" icon on Fitur Pelapak Section", (String icon) -> {
            bukalapak.homePage().verifyFiturPelapakMenu(icon);
        });

        And("^user tap Barang Favorit menu in Homepage$", () -> {
            bukalapak.homePage().tapBarangFavoritMenu();
        });

        When("^user open product detail page using app link$", () -> {
            bukalapak.iOSBasePage().openDeepLink(dotenv.get("PRODUCT_DETAIL_APP_LINK"));
        });

        When("^user open floating download app link in product detail page$", () -> {
            bukalapak.iOSBasePage().openDeepLink(dotenv.get("FLOATING_DOWNLOAD_APP_LINK"));
        });

        And("^user should be able to click \"([^\"]*)\"$", (String category) -> {
            bukalapak.homePage().clickCategoryFromHome(category);
        });


        And("^user should see navbar on home page$", () -> {
            bukalapak.homePage().checkNavbar();
        });

        And("^user verify (DANA|Credits) balance$", (String payment) -> {
            if (payment.equals("Credits")) {
                bukalapak.homePage().verifyCredits();
            } else if (payment.equals("DANA")) {
                bukalapak.homePage().verifyDANA();
            }
        });

        When("^user click on reco \"([^\"]*)\" menu", (String type) -> {
            bukalapak.homePage().clickRecoMenu(type);
        });

        When("^user choose \"([^\"]*)\" reco menu", (String menu) -> {
            bukalapak.homePage().chooseRecoMenu(menu);
        });

        When("^user click on cancel discard", () -> {
            bukalapak.homePage().cancelDiscard();
        });

        When("^user send \"([^\"]*)\" reco feedback", (String feedback) -> {
            bukalapak.homePage().sendRecoFeedback(feedback);
        });

        Then("^user should be able to see discard notification", () -> {
            bukalapak.homePage().verifyDiscardReco();
        });

        When("^user go to \"([^\"]*)\" game page using deeplink", (String pageName) -> {
            String pageDeeplink = pageName.replaceAll(" ", "_").toUpperCase() + "_DEEPLINK";
            bukalapak.iOSBasePage().openDeepLink(dotenv.get(pageDeeplink));
        });

        Then("^user tap on pelajari promoted push instant", () -> {
            bukalapak.homePage().clickPromotedInstant();
        });

        Then("^user tap on Lihat Tagihan promoted push instant", () -> {
            bukalapak.homePage().clickTagihanPromotedInstant();
        });

        When("^user should see \"([^\"]*)\" verification$", (String verificationType) -> {
            if (verificationType.equals("email")) {
                bukalapak.homePage().validateMailConfirmationAppear();
            } else {
                bukalapak.homePage().validatePhoneConfirmationAppear();
            }
        });

        Given("user is in \"home\" page", () -> {
            bukalapak.homePage().isOnHomePage();
        });

        When("user navigate to \"home\" page", () -> {
            bukalapak.homePage().backToHome();
        });

        When("user navigate to \"transaction\" page", () -> {
            bukalapak.homePage().clickTransaksiTab();
        });

        When("user navigate to \"inspiration\" page", () -> {
            bukalapak.homePage().navigateToInspirationPage();
        });

        When("user navigate to \"highlight\" page", () -> {
            bukalapak.homePage().navigateToHighlightPage();
        });

        When("user navigate to \"super_secret_ninja\" page", () -> {
            bukalapak.homePage().navigateToSuperSecretNinja();
        });

        When("user navigate to \"category_fashion_wanita\" page", () -> {
            bukalapak.homePage().navigateToCategoryFashionWanita();
        });

        And("user go to serbu seru screen", () -> {
            bukalapak.homePage().tapSerbuSeruShowAll();
            bukalapak.serbuSeruPage().isOnSerbuSeruPage();
        });

        And("^user input nominal for Top Up DANA Rp (.*)$", (String amount) -> {
            bukalapak.homePage().inputTopUpAmount(amount);
        });

        And("^user should see Voucher on Financial Credential$", () -> {
            bukalapak.homePage().verifyVoucherkuFinancial();
        });

        And("^user should be redirect to promo page$", () -> {
            bukalapak.homePage().verifyPromoPage();
        });

        And("^user (has|has no) shipping vouchers$", (String state) -> {
            bukalapak.homePage().verifyNoOngkirPromoPage(state);
        });

        When("^user click Voucher on homepage$", () -> {
            bukalapak.homePage().clickVoucherOnHome();
        });

        When("user click smartcard on homepage", () -> {
            bukalapak.homePage().clickSmartcard();
        });

        And("^user should see smart card verification account$", () -> {
            bukalapak.homePage().verifySmartCardVerification();
        });

        Then("^user should be redirect to pop up smart card$", () -> {
            bukalapak.homePage().verifyPopUpVerification();
        });

        Then("^user should be redirect to verification email page$", () -> {
            bukalapak.homePage().verifyVerificationEmailPage();
        });

        Then("^user should be redirect to verification phone number page$", () -> {
            bukalapak.homePage().verifyVerificationPhonePage();
        });

        Then("^user should see popular brand products$", () -> {
            bukalapak.homePage().verifyPopularBrandProducts();
        });

        When("^user click products from popular brand$", () -> {
            bukalapak.homePage().clickProductPopular();
        });

        When("^user go to serbu seru main page using deeplink$", () -> {
            bukalapak.iOSBasePage().openDeepLink(dotenv.get("SERBU_SERU_MAIN_DEEPLINK"));
        });

        When("^user top up DANA from Product Categories$", () -> {
            bukalapak.homePage().tapTopUpDANA();
        });

        And("^user scroll to popular product section$", () -> {
            bukalapak.homePage().scrollToPopularProductSection();
        });

        When("^user scroll to Today Deals$", () -> {
            bukalapak.homePage().scrollToTodayDeals();
        });

        When("^user click products on popular section$", () -> {
            bukalapak.homePage().verifyPopularProductSection();
            bukalapak.homePage().clickPopularProduct();
        });

        And("^user should see Voucherku Entry Point$", () -> {
            bukalapak.homePage().verifyHasVoucherkuCard();
        });

        Then("^entry point for user who has (zero|any) Voucherku$", (String status) -> {
            if (status.equals("zero")) {
                bukalapak.homePage().verifyEmptyVoucherkuCard();
            } else {
                bukalapak.homePage().verifyHasVoucherkuCard();
            }
        });

        Then("^user can not see recommendations on homepage$", () -> {
            bukalapak.homePage().validateRecoDOH(false);
        });

        Then("^user see recommendations on homepage( with empty state)?$", (String state) -> {
            bukalapak.homePage().validateRecoDOH(true);
            bukalapak.homePage().validateRecoState(state);
        });

        Then("^user see recommendations product$", () -> {
            bukalapak.homePage().validateRecoProduct();
        });

        Then("^user see recommendation floating button$", () -> {
            bukalapak.homePage().validateRecoFloatingBtn();
        });

        When("^user click recommendation floating button$", () -> {
            bukalapak.homePage().clickRecoFloatingBtn();
        });

        When("^user click \"([^\"]*)\" on reco options$", (String option) -> {
            bukalapak.homePage().clickRecoTabOptions(option);
        });

        When("^user click product on recommendation product list", () -> {
            bukalapak.homePage().clickRecoProduct();
        });

        Then("^(login|non login)? user see reco prime on homepage$", (String user) -> {
            bukalapak.homePage().validateRecoPrime(user);
        });

        Then("^user see reco prime product information$", () -> {
            bukalapak.homePage().validateRecoPrimeProductInformation();
        });

        When("^user click on reco prime product", () -> {
            bukalapak.homePage().clickRecoPrimeProduct();
        });

        When("^user open product with no recommendation using app link$", () -> {
            bukalapak.iOSBasePage().openDeepLink(dotenv.get("PRODUCT_NO_RECOMMENDATION_LINK"));
        });

        When("^user scroll to \"([^\"]*)\" Popular Brand Section$", (String flag) -> {
            bukalapak.homePage().scrollToBrandSection(flag);
        });

        Then("user access popular claim page via deeplink", () -> {
            bukalapak.iOSBasePage().openDeepLink(dotenv.get("PRODUCT_EMPTY_POPULAR_LINK"));
        });

        Then("popular claim will show empty state", () -> {
            bukalapak.voucherDetailPage().validateEmptyPopularPage();
        });

        And("^user swipe on Popular Brand product$", () -> {
            bukalapak.homePage().swipeLeftOnPupularBrand();
        });

        And("user tap DANA Icon at homepage", () -> {
            bukalapak.homePage().tapDanaHomepageIcon();
        });

        When("user go to potong harga at homepage", () -> {
            bukalapak.homePage().tapLihatSemuaPotongHarga();
        });

        When("^home page displays change password pop up$", () -> {
            bukalapak.homePage().verifyChangePasswordPopUp();
        });

        When("^user navigate to transaction page$", () -> {
            bukalapak.homePage().clickTransactionPage();
        });

        Then("user validate recommendations section on home", () -> {
            bukalapak.homePage().verifyRecoSection();
        });

        And("user see tab with name Buat Kamu", () -> {
            bukalapak.homePage().validationTabBuatkamu();
        });

        And("user validate tab \"([^\"]*)\" state condition", (String tab) -> {
            bukalapak.homePage().validationDohTab(tab);
        });

        And("user tap bukamart on recommendation section", () -> {
            bukalapak.homePage().clickTabBukamart();
        });

        Then("user verify top five product is diplayed on active event", () -> {
            bukalapak.homePage().validateTopFiveProductActiveEvent();
        });

        When("^user open product which have same seller using app link$", () -> {
            bukalapak.iOSBasePage().openDeepLink(dotenv.get("PRODUCT_SAME_SELLER"));
        });

        When("user tap Product Card on section Potong Harga", () -> {
            bukalapak.homePage().tapProductCardOnPotongHargaSection();
        });

        When("^(login|logout) user scrolls down the homepage$", (String state) -> {
            bukalapak.homePage().scrollToRecoPrime(state);
        });

        Then("^(login|logout) user should see reco prime above serbu seru section$", (String state) -> {
            bukalapak.homePage().validateSectionReco(state);
        });

        And("^user should see reco prime on homepage$", () -> {
            bukalapak.homePage().validateProductReco();
        });

        And("^user can swipe (left|right) product reco prime$", (String swipe) -> {
            bukalapak.homePage().swipeProductReco(swipe);
        });

        And("user tap on mulai potong harga button", () -> {
            bukalapak.homePage().tapMulaiPotongHarga();
        });

        When("^user go to mini DANA from deeplink$", () -> {
            bukalapak.iOSBasePage().openDeepLink(dotenv.get("DANA_DEEPLINK"));
        });

        When("user open product which \"([^\"]*)\" same seller using app link", (String state) -> {
            bukalapak.homePage().openProductSameSeller(state);
        });

        When("user click DANA icon on homepage", () -> {
            bukalapak.homePage().tapHubungkanDanaOnFinancialSection();
        });

        Then("user validate flash deal section exist", () -> {
            bukalapak.homePage().validateFlashDealSection();
        });

        When("user tap a flash deal product", () -> {
            bukalapak.homePage().tapFlashDealProduct();
        });

        Then("user tap \"([^\"]*)\" on Flash Deal", (String componentName) -> {
            if (componentName.equals("Lihat Semua Card")) {
                bukalapak.homePage().tapFlashDealLihatSemuaCard();
            } else {
                bukalapak.homePage().tapFlashDealLihatSemua();
            }
        });

        When("user tap DANA balance on home page", () -> {
            bukalapak.homePage().tapDANABalance();
        });

        And("user scoll to Campaign Banner section", () -> {
            bukalapak.homePage().scrollToCampaignBanner();
        });

        Then("user should see Gapura Campaign Banner section", () -> {
            bukalapak.homePage().verifyGapuraCampaignBannerSection();
        });

        And("open product detail which have no reco complementary using deeplink with url", ()-> {
            bukalapak.iOSBasePage().openDeepLink(dotenv.get("PRODUCT_NO_RECO_COMPLAMENTARY"));
        });

        When("user scoll to Horizontal Reco Section", () -> {
            bukalapak.homePage().scrollToHorizontalRecoHome();
        });

        Then("user should see Horizontal Reco Section", () -> {
            bukalapak.homePage().verifyHorizontalRecoHome();
        });

        And("user swipe to Opsi Lainnya button", () -> {
            bukalapak.homePage().swipeToOpsiLainnyaButton();
        });

        And("user should see Opsi Lainnya button", () -> {
            bukalapak.homePage().verifyOpsiLainnyaButton();
        });

        And("user tap on Opsi Lainnya button", () -> {
            bukalapak.homePage().tapOnOpsiLainnyaButton();
        });

        And("^user do \"([^\"]*)\" times refresh on home page$", (Integer refreshCount) -> {
            bukalapak.homePage().doPullRefreshHome(refreshCount);
        });

        And("user click products from horizontal reco", () -> {
            bukalapak.homePage().clickProductHorizontalReco();
        });

        When("user scroll to Promo Untukmu section", () -> {
            bukalapak.homePage().scrollToPromoUntukmuSection();
        });

        Then("user should see Promo Untukmu section", () -> {
            bukalapak.homePage().verifyPromoUntukmuSection();
        });

        When("user scroll down to Thematic Campaign section", () -> {
            bukalapak.homePage().scrollToThematicCampaignSection();
        });

        Then("user should see Lihat Semua Thematic Campaign", () -> {
            bukalapak.homePage().verifyLihatSemuaThematicCampaign();
        });

        And("user should be redirect to microsite page", () -> {
            bukalapak.homePage().verifyMicrositePage();
        });

        Then("user should see thematic campaign products", () -> {
            bukalapak.homePage().verifyThematicCampaignProducts();
        });

        And("user click products from thematic campaign section", () -> {
            bukalapak.homePage().clickProductThematicCampaign();
        });

        When("user scroll down to Inspirational Section", () -> {
            bukalapak.homePage().scrollToPersonalizedInspiration();
        });

        Then("user should see Ide Lainnya button", () -> {
            bukalapak.homePage().verifyIdeLainnyaButton();
        });

        And("user click on Ide Lainnya button", () -> {
            bukalapak.homePage().clickIdeLainnyaButton();
        });

        And("user should see section item was changes", () -> {
            bukalapak.homePage().verifyProductImagesInspiration();
        });

        And("user click card product inspiration", () -> {
            bukalapak.homePage().clickProductCardInspiration();
        });

        And("user click on Promo card inspiration", () -> {
            bukalapak.homePage().clickPromoCardInspiration();
        });

        And("user scroll down to DOH section", () -> {
           bukalapak.homePage().navigateTabDohSection();
        });

        And("user verify tab product bukamart", () -> {
           bukalapak.homePage().verifyTabBukamart();
        });

        And("user swipe and click tab \"([^\"]*)\" on Doh section", (String tab) -> {
           bukalapak.homePage().swipeTabDoH(tab);
        });

        When("user skip onboarding dana", () -> {
            bukalapak.homePage().skipDanaOnboarding();
        });

        And("user verify show pop up verification phone number", () ->{
            bukalapak.homePage().verifyPopUpVerificationPhone();
        });

        //voucher claim home promo and home reco
        And("user scroll to Home reco section", () -> {
            bukalapak.homePage().scrollToHomeRecoSection();
        });

        And("user see detail voucher \"([^\"]*)\" in home promo section", (String voucherName)-> {
            bukalapak.homePage().validateVoucherHomePromo(voucherName);
        });

        And("user do claim voucher \"([^\"]*)\" in home promo section", (String voucherName)-> {
            bukalapak.homePage().claimVoucherHomePromo(voucherName);
        });

        And("user copy voucher code \"([^\"]*)\" in home promo section", (String voucherName)-> {
            bukalapak.homePage().copyVoucherHomePromo(voucherName);
        });

        //home reco voucher
        And("user validate \"([^\"]*)\" in home reco section", (String voucherName)-> {
            bukalapak.homePage().validateVoucherHomeReco(voucherName);
        });

        And("user do claim voucher \"([^\"]*)\" in home reco section", (String voucherName)-> {
            bukalapak.homePage().claimVoucherHomeReco(voucherName);
        });

        And("user verify button \"([^\"]*)\"", (String buttonName)-> {
            bukalapak.homePage().validateVoucherHomeRecoBtn(buttonName);
        });

        And("user see detail voucher \"([^\"]*)\" in home reco section", (String voucherName)-> {
            bukalapak.homePage().validateVoucherHomeReco(voucherName);
        });

        And("user scroll to Opsi Lainnya button", () -> {
            bukalapak.homePage().scrollToOpsiLainnyaButton();
        });

        And("^user should see bukalive section$", () -> {
            bukalapak.homePage().validateBukaLiveSection();
        });

        When("^user click \"([^\"]*)\" on bukalive section$", (String actionName) -> {
            bukalapak.homePage().tapBukaLiveSection(actionName);
        });

        When("user swipe left to see see more bukalive card button", () -> {
            bukalapak.homePage().swipeToSeeMoreCardButton();
        });

        And("user go to top home page", () -> {
            bukalapak.homePage().goToTopHomePage();
        });

        And("user go to bukamall page", () -> {
            bukalapak.homePage().clickBukaMall();
        });

        And("user verify the braze PN received", () -> {
            bukalapak.homePage().verifyBrazePN();
        });

        And("user click the braze PN", () -> {
            bukalapak.homePage().clickBrazePN();
        });

        And("user tap the first category in category section", () -> {
            bukalapak.homePage().tapOnFirstCategory();
        });

        When("^user tap the \"([^\"]*)\" in Promo Section$", (String actionName) -> {
            bukalapak.homePage().tapOnPromoSection(actionName);
        });

        Then("user should see promo banner", () -> {
            bukalapak.homePage().validatePromoSectionBanner();
        });

        When("user swipe left the promo banner \"([^\"]*)\" times", (Integer maxSwipe) -> {
            bukalapak.homePage().swipeLeftOnPromoSectionBanner(maxSwipe);
        });

        When("user should see load more card", () -> {
            bukalapak.homePage().validatePromoLoadMoreCard();
        });

        Then("user should see the promo", () -> {
            bukalapak.homePage().validatePromoButton();
        });

        When("user click the in app", () -> {
            bukalapak.homePage().clickCampaignPopUp();
        });

        Then("user should not in home page", () -> {
            if(GMTData.getBrazePopupState()) bukalapak.homePage().verifyNotInHomePage();
        });

        Then("user tap the homepage category tab", () -> {
            bukalapak.homePage().tapCategoryTabbing();
        });

        Then("user should be redirect to dope page on mobileview", () -> {
            bukalapak.homePage().verifyDopePageOnMview();
        });

        And("user tap favorite icon on recommendation product cart", () -> {
            bukalapak.homePage().tapFavIconRecoProduct();
        });

        And("^user see favorite icon color is change to \"([^\"]*)\"$", (String colorIcon) -> {
            bukalapak.homePage().validateFavIcon(colorIcon);
        });

        And("user tap new campaign tab", () -> {
            bukalapak.homePage().tapRecoNewCampaignTab();
        });

        And("user validate new campaign tab is active", () -> {
            bukalapak.homePage().validateRecoNewCampaignTabSelected();
        });

        And("^user see \"([^\"]*)\" on new campaign product card", (String component) -> {
            bukalapak.homePage().validateRecoNewCampaignProduct(component);
        });

        And("^user validate \"([^\"]*)\" tab topic$", (String tabOrder) -> {
            bukalapak.homePage().tapTabTopic(tabOrder);
        });

        And("user tap omnisearch in homepage", () -> {
            bukalapak.homePage().searchHomeKeywordSuggestion();
        });

        And("^user navigates to the (unwhitelisted|whitelisted) merchandising product page$", (String categoryType) -> {
            bukalapak.homePage().openDeepLink(dotenv.get("MERCHANDISING_" + categoryType.toUpperCase() + "_PRODUCT_URL"));
        });

        // Digital Widget

        And("user scroll to Digital Widget section in homepage", () -> {
            bukalapak.homePage().goToDigitalWidgetSection();
        });

        And("user should see Digital Widget section in homepage", () -> {
            bukalapak.homePage().validateDigitalWidgetSection();
        });

        And("user tap on \"([^\"]*)\" tab in Digital Widget section", (String tabName) -> {
            bukalapak.homePage().tapOnDigitalWidgetTab(tabName);
        });

        And("user tap on \"([^\"]*)\" Digital Widget Card", (String cardTitle) -> {
            bukalapak.homePage().tapOnDigitalWidgetCard(cardTitle);
        });

        And("user should see \"([^\"]*)\" investment card in Digital Widget section", (String investmentCardType) -> {
            bukalapak.homePage().validateInvestmentCard(investmentCardType);
        });

        And("user tap on \"([^\"]*)\" investment card in Digital Widget section", (String investmentProductType) -> {
            bukalapak.homePage().tapOnInvestmentCard(investmentProductType);
        });

        And("user scroll to Flash Deal on homepage", () -> {
            bukalapak.homePage().validateFlashDealSection();
        });

        And("user should see flash deal banner on homepage", () -> {
            bukalapak.homePage().verifyBannerFlashDeal();
        });

        And("user click flash deal banner on homepage", () -> {
            bukalapak.homePage().clickFlashDealBanner();
        });

        When("user visit Teman Cuan page", () -> {
            bukalapak.akunPage().clickAkunMenu();
            bukalapak.akunPage().clickTemanCuanMenu();
        });

        And("^user should be redirected from promo section", () -> {
            bukalapak.homePage().verifyRedirectedFromPromoSection();
        });

        And("^user back to home page via deeplink$", () -> {
            bukalapak.homePage().backToHomeViaDeeplink();
        });

        When("user click semua menu dope on homepage", () -> {
            bukalapak.dopePage().goToDopeScreen();
        });

        Then("user should see popular brand section on homepage", () -> {
            bukalapak.homePage().validateHomepageBrandSection();
        });

        And("^user tap \"([^\"]*)\" on Homepage Bukamall Section$", (String component) -> {
            bukalapak.homePage().tapOnHomepageBrandSection(component);
        });

        And("^user tap menu \"([^\"]*)\" on dope section$", (String dopeNamme) -> {
            bukalapak.homePage().tapDopeItemOnHomepage(dopeNamme);
        });

        And("^user should see nabung diskon section$", () -> {
            bukalapak.homePage().validateNabungDiskon();
        });

        When("^user click nabung diskon section$", () -> {
            bukalapak.homePage().tapNabungDiskonSection();
        });

        When("^user get unread chat count badge on home$", () -> {
            bukalapak.homePage().getUnreadChatBadge();
        });

        When("^user verify the unread chat badges is reduced$", () -> {
            bukalapak.homePage().unreadChatBadgeShouldBeReduced();
        });

        When("^user tap chat icon in homepage navigation bar$", () -> {
            bukalapak.homePage().tapChatBtnOnHomepage();
        });
    }
}
