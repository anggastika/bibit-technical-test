package bukalapak.pageObject.investment;

import bukalapak.data.HelperData;
import bukalapak.data.InvestmentData;
import bukalapak.data.UserData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class BukaReksaHomePage extends BasePage {

    public BukaReksaHomePage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void chooseHomepageSection(String homepage) {
        switch (homepage.toLowerCase()) {
            case "produk pilihan":
                isRecommendedProductDisplayed();
                break;
            case "pakai fitur investasi":
                verifyPakaiFiturInvestasiSectionDisplayed();
                break;
            case "serba-serbi":
                isSerbaSerbiSectionDisplayed();
                break;
            case "paket":
                verifyReksaPackageSectionDisplayed();
                break;
            case "tujuan investasi":
                isGoalInvestmentSectionDisplayed();
                break;
            default:
                Assert.fail("section not found!");
        }
    }

    public void isBerandaPageDisplayed() {
        validateBukaReksaOnboarding();
        isBerandaTabSelected();
        verifyElementExist("REXA_NAV_PRODUK");
        verifyElementExist("REXA_NAV_PORTOFOLIO");
        verifyElementExist("REXA_NAV_PROFILE");
        delay(5000);
        HelperData.setLastActionPage(new BukaReksaHomePage(iosDriver));
    }

    public void validateBukaReksaOnboarding() {
        LogUtil.info("Disclaimer state: " + InvestmentData.getDisclaimerState());
        LogUtil.info("OnBoarding state: " + InvestmentData.getOnBoardingState());
        if (UserData.isLoggedIn()
                && !InvestmentData.getDisclaimerState()
                && !InvestmentData.getOnBoardingState()) {
            isDisclaimerModalDisplayed();
            completeOnBoarding();
        }
    }

    public void isRecommendedProductDisplayed() {
        if (isElementVisible("REXA_MISSION_SECTION")) {
            swipeUpToElement("REXA_BELI_PRODUCT_BTN");
        } else {
            swipeDownBeranda("REXA_PRODUK_PILIHAN_TITLE");
        }
        validateDisplayed("REXA_PRODUK_PILIHAN_TITLE");
        validateDisplayed("REXA_PRODUK_PILIHAN_NAME");
        validateDisplayed("REXA_PRODUK_PILIHAN_RETURN");
        validateDisplayed("REXA_BELI_PRODUCT_BTN");
    }

    public void verifyPakaiFiturInvestasiSectionDisplayed() {
        if (!isElementExist("REXA_PAKAI_FITUR_INVESTASI_SECTION", 5)) {
            swipeUpToElement("REXA_PAKAI_FITUR_INVESTASI_SECTION");
        }
        validateDisplayed("REXA_PAKAI_FITUR_INVESTASI_TITLE");
        validateDisplayed("REXA_PAKAI_FITUR_INVESTASI_RECURRING");
    }

    public void isSerbaSerbiSectionDisplayed() {
        if (!isElementExist("REXA_SERBA_SERBI_SECTION", 5)) {
            swipeUpToElement("REXA_SERBA_SERBI_TITLE");
        }
        validateExist("REXA_SERBA_SERBI_SECTION", 5);
        validateExist("REXA_SERBA_SERBI_TITLE", 5);
        verifySerbaSerbiSection("Telegram BukaReksa");
        verifySerbaSerbiSection("Lihat video");
        verifySerbaSerbiSection("Pelajari istilah dalam reksa dana");
    }

    public void verifySerbaSerbiSection(String serbaSerbi) {
        int maxSwipe = 4;
        int loop = 0;
        while (!isElementExist(constructLocator("REXA_SERBA_SERBI_CARD", serbaSerbi), 5) && loop < maxSwipe) {
            swipeLeftAtSpecifiedLocator("REXA_SERBA_SERBI_BANNER");
            maxSwipe--;
        }
    }

    public void isGoalInvestmentSectionDisplayed() {
        //for user who have created goal investment
        if(isElementExist("REXA_GOAL_INVESTMENT_SECTION")) {
            swipeDownToElement("REXA_GOAL_INVESTMENT_SECTION_TITLE");
            validateDisplayed("REXA_GOAL_INVESTMENT_SECTION_SUBTITLE");
            InvestmentData.setUserHaveGoalInvestProduct(true);
            //for user who have not created goal investment
        } else {
            swipeDownToElement("REXA_GOAL_INVESTMENT_NEW_SECTION_TITLE");
            validateDisplayed("REXA_GOAL_INVESTMENT_NEW_SECTION");
            InvestmentData.setUserHaveGoalInvestProduct(false);
        }
    }

    public void isRegistrationSectionDisplayed() {
        swipeDownBeranda("REXA_REGIST_TITLE_PAGE");
        validateExist("REXA_REGIST_TITLE_PAGE",2);
        validateExist("REXA_REGIST_SUBTITLE",2);
        validateExist("REXA_BERANDA_REGIST_BUTTON",2);
        validateExist("REXA_BERANDA_REGIST_ICON",3);
    }

    public void isPortofolioSummaryDisplayed() {
        swipeDownBeranda("REXA_BERANDA_PORTFOLIO_SUMMARY");
        validateDisplayed("REXA_BERANDA_PORTFOLIO_SUMMARY");
        validateDisplayed("REXA_BERANDA_PORTFOLIO_TOTAL");
        validateDisplayed("REXA_BERANDA_PORTFOLIO_RETURN_PERCENTAGE");
        validateDisplayed("REXA_BERANDA_PORTFOLIO_RETURN");
        InvestmentData.setTotalPortfolio(getBerandaPortfolioText());
    }

    public void isMissionSectionDisplayed() {
        swipeDownBeranda("REXA_MISSION_TITLE");
        if (isElementExist("REXA_MISSION_SECTION", 5)) {
            validateExist("REXA_MISSION_TITLE", 5);
            verifyBukareksaMission("Uji wawasan");
            verifyBukareksaMission("Kuis Profil");
            verifyBukareksaMission("Beli produk");
            verifyBukareksaMission("Kelola kekayaan");
        }
        LogUtil.info("Mission already closed");
    }

    public void isOnKamusReksaDanaPage() {
        validateDisplayed("KAMUS_REKSA_DANA_PAGE_TITLE");
        validateDisplayed("KAMUS_REKSA_DANA_PAGE");
    }

    public void isOnJenisReksaDanaPage() {
        validateDisplayed("JENIS_REKSA_DANA_PAGE_TITLE");
        validateDisplayed("JENIS_REKSA_DANA_PAGE");
    }

    public void isBukaReksaVideoDisplayed() {
        validateDisplayed("SERBA_SERBI_VIDEO_SECTION");
        validateDisplayed("SERBA_SERBI_VIDEO_TITLE");
    }

    public void isBukaReksaYoutubeChannelDisplayed() {
        waitForVisibilityOf("SERBA_SERBI_BUKAREKSA_YOUTUBE_PAGE");
        validateDisplayed("SERBA_SERBI_BUKAREKSA_YOUTUBE_PAGE");
        validateDisplayed("SERBA_SERBI_YOUTUBE_ICON");
        validateDisplayed("SERBA_SERBI_BROWSER_BOTTOM_TOOLBAR");
    }

    public void isBukaReksaArticleDisplayed() {
        if (!isElementExist("SERBA_SERBI_ARTICLE_SECTION", 5)) {
            swipeUpToElement("SERBA_SERBI_ARTICLE_SECTION");
        }
        validateDisplayed("SERBA_SERBI_ARTICLE_TITLE");
    }

    public void isBukaReksaBlogDisplayed() {
        // waiting for bukareview page fully loaded
        delay(5000);
        validateDisplayed("SERBA_SERBI_REKSA_BUKAREVIEW_PAGE");
        validateDisplayed("SERBA_SERBI_REKSA_BUKAREVIEW_TITLE");
        validateDisplayed("SERBA_SERBI_REKSA_BUKAREVIEW_IMAGE");
    }

    public void isBukaReksaTestimonyDisplayed() {
        swipeUpToElement("SERBA_SERBI_TESTIMONY_SECTION");
        validateDisplayed("SERBA_SERBI_TESTIMONY_TITLE");
    }

    public void verifyBukaReksaBenefitDisplayed() {
        verifyBukareksaBenefit("Mulai Investasi");
        verifyBukareksaBenefit("Transaksi Praktis");
        verifyBukareksaBenefit("Jual Instan");
        verifyBukareksaBenefit("Beli Dulu");
        verifyBukareksaBenefit("Pilih Produk");
        verifyBukareksaBenefit("Gratis Biaya Transaksi");
    }

    public void verifyBukareksaMission(String mission) {
        int maxSwipe = 5;
        int loop = 0;
        while (!isElementExist(constructLocator("REXA_MISSION_CARD_DETAIL", mission), 5) && loop < maxSwipe) {
            swipeLeftAtSpecifiedLocator("REXA_MISSION_SECTION");
            maxSwipe--;
        }
    }

    public void tapOnLihatSemuaSerbaSerbiBtn() {
        tapElement("REXA_SERBA_SERBI_LIHAT_SEMUA_BTN");
    }

    public void verifySerbaSerbiReksaDisplayed() {
        validateDisplayed("SERBA_SERBI_REXA_PAGE");
        HelperData.setLastActionPage(new BukaReksaHomePage(iosDriver));
    }

    public void tapOnDaftarBtn() {
        tapElement("REXA_BERANDA_REGIST_BUTTON");
    }

    public void tapOnPortofolioSummary() {
        tapElement("REXA_BERANDA_PORTFOLIO_CARD");
    }

    public void tapOnTransaksiRutin() {
        tapElement("REXA_PAKAI_FITUR_INVESTASI_RECURRING");
    }

    public void tapOnTujuanInvestasi() {
        tapElement("REXA_PAKAI_FITUR_INVESTASI_GOALS");
    }

    public void tapOnPakaiFiturInvestasi(String subSection) {
        switch (subSection.toLowerCase()) {
            case "transaksi rutin":
                tapOnTransaksiRutin();
                break;
            case "tujuan investasi":
                tapOnTujuanInvestasi();
                InvestmentData.setUserHaveGoalInvestProduct(true);
                changeContext().toWebview();
                break;
            default:
                Assert.fail("section not found!");
        }
    }

    public void tapOnLihatSemuaBtn() {
        tapElement("REXA_LIHAT_SEMUA_BTN");
    }

    public void tapOnBeliProductBtn() {
        swipeUpToElement("REXA_BELI_PRODUCT_BTN");
        int randomRecommendedProduct = randomize().number(getRecommendedProductList());
        InvestmentData.setProductNameBukaReksa(getSelectedRecommendedProduct("REXA_PRODUK_PILIHAN_NAME", randomRecommendedProduct));
        tapSelectedRecommendedProduct(randomRecommendedProduct);
    }

    public void tapOnSerbaSerbiPage(String subSection) {
        switch (subSection.toLowerCase()) {
            case "kamus reksa dana":
                tapOnKamusReksaDana();
                break;
            case "jenis-jenis reksa dana":
                tapOnJenisReksaDana();
                break;
            case "lihat video lainnya":
                tapLihatVideoBtn();
                break;
            case "lihat artikel lainnya":
                tapLihatArtikelBtn();
                break;
            default:
                Assert.fail("section not found!");
        }
    }

    public void tapOnSerbaSerbiBackButton() {
        tapElement("SERBA_SERBI_BACK_BTN");
    }

    public void tapSerbaSerbiYoutubeBackBtn() {
        tapElement("SERBA_SERBI_BUKAREKSA_YOUTUBE_DONE_BTN");
        tapElement("SERBA_SERBI_WEB_BACK_BUTTON");
    }

    public void tapSerbaSerbiBukaReviewBackBtn() {
        tapElement("SERBA_SERBI_WEB_BACK_BUTTON");
    }

    public void swipeDownBeranda(String locator) {
        try {
            validateDisplayed(locator);
        } catch (AssertionError e) {
            nativeSwipeDownToElement(locator);
        }
    }

    public void swipeRecommendedProductCard() {
        nativeSwipeLeftToElement("REXA_PRODUK_PILIHAN_NAME");
    }

    private String getBerandaPortfolioText() {
        return getText("REXA_BERANDA_PORTFOLIO_TOTAL");
    }

    private int getRecommendedProductList() {
        return getElementsPresent("REXA_PRODUK_PILIHAN_NAME").size();
    }

    private String getSelectedRecommendedProduct(String locator, int index) {
        return getElementsPresent(locator).get(index).getText();
    }

    private void tapSelectedRecommendedProduct(int index) {
        if (!isElementExist(constructLocator("REXA_BELI_PRODUCT_BTN", index))) {
            swipeLeftAtSpecifiedLocator(constructLocator("REXA_BELI_PRODUCT_BTN", index));
        }
        tapElements("REXA_BELI_PRODUCT_BTN", index);
    }

    private void tapOnKamusReksaDana(){
        tapElement("SERBA_SERBI_KAMUS_REXA");
    }

    private void tapOnJenisReksaDana(){
        tapElement("SERBA_SERBI_JENIS_REXA");
    }

    private void tapLihatVideoBtn(){
        tapElement("SERBA_SERBI_LIHAT_VIDEO_BTN");
    }

    private void tapLihatArtikelBtn(){
        //need to manually scroll due to cannot scroll in Serba-serbi page using available function
        int scroll = 0;
        do {
            swipeUp(0.8, 0.3);
            scroll++;
        } while (!isElementVisible("SERBA_SERBI_LIHAT_ARTIKEL_BTN") && scroll < 3);
        tapElement("SERBA_SERBI_LIHAT_ARTIKEL_BTN");
    }

    private void tapDisclaimerModalBtn() {
        tapElement("REXA_DISCLAIMER_MODAL_BTN");
    }

    private void tapOnBoardingBtn() {
        tapElement("REXA_ONBOARDING_MODAL_BTN");
    }

    private void verifyBukareksaBenefit(String benefit) {
        try {
            validateDisplayed("REXA_BENEFIT_SECTION");
            validateDisplayed("REXA_BENEFIT_TITLE");
            validateExist(constructLocator("REXA_BENEFIT_CARD", benefit), 5, benefit + " benefit doesn't exist!");
        } catch (AssertionError e) {
            swipeLeftAtSpecifiedLocator("REXA_BENEFIT_SECTION");
        }
    }

    private void completeOnBoarding() {
        startOnBoardingTour();
        followingOnBoardingTour();
    }

    private void startOnBoardingTour() {
        isOnBoardingViewDisplayed();
        tapOnBoardingBtn();
    }

    private void isOnBoardingViewDisplayed(){
        validateExist("REXA_ONBOARDING_MODAL_BTN", 30);
    }

    private boolean isDisclaimerDisplayed() {
        return isElementExist("REXA_DISCLAIMER_MODAL", 5);
    }

    private void isDisclaimerModalDisplayed() {
        if (isDisclaimerDisplayed()) {
            validateExist("REXA_DISCLAIMER_MODAL", 10);
            validateExist("REXA_DISCLAIMER_MODAL_TITLE", 3, "Disclaimer modal title not displayed");
            tapDisclaimerModalBtn();
            InvestmentData.setDisclaimerState(true);
        } else {
            LogUtil.info("Disclaimer modal not displayed");
        }
    }

    private void isBerandaTabSelected() {
        verifyElementExist("REXA_NAV_BERANDA");
        validateSelected("REXA_NAV_BERANDA");
    }

    private void followingOnBoardingTour() {
        int i = 0;
        while (isElementExist("REXA_ONBOARDING_VIEW", 5) && i < 5) {
            if (isElementExist("LANJUT_TXT", 2)) {
                tapElement("LANJUT_TXT");
            } else if (isElementExist("SELESAI_TXT", 2)) {
                tapElement("SELESAI_TXT");
            }
            i++;
        }
        InvestmentData.setOnBoardingState(true);
    }

    //region registration

    public void verifyRegistrationPage() {
        validateExist("REGISTRATION_PAGE_HEADER", 3, "Registration header not displayed");
        validateExist("REGISTRATION_INFO_PRIBADI_SECTION", 3, "Info pribadi section not displayed");
        validateExist("REGISTRATION_INFO_REKENING_SECTION", 3, "Info rekening section not displayed");
    }

    public void isBannerSectionDisplayed() {
        swipeDownToElement("REXA_BANNER_TITLE");
        if (isElementExist("REXA_BANNER_PROMO", 5)) {
            validateExist("REXA_BANNER_TITLE", 2);
            validateExist("REXA_BANNER_SECTION", 2);
        } else {
            Assert.fail("Promo banner not displayed!");
        }
    }

    public void tapOnReksaBanner() {
        tapElement("REXA_BANNER_PROMO");
    }

    private void verifyPromoDetail() {
        if (!isElementExist("REXA_BANNER_PROMO_PAGE", 10)) {
            swipeUpToElement("REXA_BANNER_PROMO_PAGE");
            validateExist("REXA_BANNER_PROMO_PAGE", 5);
        } else if (isElementExist("REXA_BANNER_PROMO_DETAIL_NOT_FOUND", 5)) {
            Assert.fail("Promo detail not displayed!");
        }
    }

    private void verifyRegistrationPageDisplayed() {
        if (isElementExist("REGISTRATION_PAGE_HEADER", 5)) {
            verifyRegistrationPage();
        } else {
            Assert.fail("Registration page not displayed!");
        }
    }

    public void verifyPromoInfoDisplayed() {
        if (isElementExist("REXA_BANNER_PROMO_DETAIL", 10)) {
            verifyPromoDetail();
        } else {
            verifyRegistrationPageDisplayed();
        }
    }

    public void verifyReksaPackageSectionDisplayed() {
        if (isElementVisible("REXA_MISSION_SECTION")) {
            swipeUpToElement("REXA_PACKAGE_TITLE");
        } else {
            swipeDownBeranda("REXA_PACKAGE_TITLE");
        }
        validateDisplayed("REXA_PACKAGE_SECTION");
    }

    public void validateReksaGames() {
        validateExist("REXA_QUEST_TITLE", 2);
        validateExist("REXA_QUEST_SECTION", 2);
        validateExist("REXA_QUEST_MAINKAN_BTN", 2);
    }

    public void tapGamesMainkanBtn() {
        tapElement("REXA_QUEST_MAINKAN_BTN");
    }

    public void verifyGamesPageDisplayed() {
        changeContext().toWebview();
        validateExist("REXA_QUEST_PAGE", 10);
    }

    public void tapOnExitGamesBtn() {
        verifyElementExist("REXA_QUEST_EXIT_BTN");
        tapElement("REXA_QUEST_EXIT_BTN");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
