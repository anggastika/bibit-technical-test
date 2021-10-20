package bukalapak.stepDefinitions.investment;

import bukalapak.TestInstrument;
import bukalapak.data.InvestmentData;
import cucumber.api.java8.En;

public class BukaReksaHomeStepDefinitions extends TestInstrument implements En {

    public BukaReksaHomeStepDefinitions() {

        //general steps
        When("user scroll to BukaReksa \"([^\"]*)\" section", (String homepage) -> {
            bukalapak.bukaReksaHomePage().chooseHomepageSection(homepage);
        });
        //end of general steps

        //region Daftar jadi Investor
        When("user verify homepage registration section displayed", () -> {
            bukalapak.bukaReksaHomePage().isRegistrationSectionDisplayed();
        });

        When("user tap on daftar button in homepage", () -> {
            bukalapak.bukaReksaHomePage().tapOnDaftarBtn();
        });
        //end region Daftar jadi Investor

        //region Portofolio Summary
        When("user verify portfolio summary in homepage displayed", () -> {
            bukalapak.bukaReksaHomePage().isPortofolioSummaryDisplayed();
        });

        When("user tap on portfolio summary", () -> {
            bukalapak.bukaReksaHomePage().tapOnPortofolioSummary();
        });
        //end region Portofolio Summary

        //region Mission
        When("user verify BukaReksa mission displayed", () -> {
            bukalapak.bukaReksaHomePage().isMissionSectionDisplayed();
        });
        //end region Mission

        //region pakai fitur investasi
        When("user tap on \"([^\"]*)\" in pakai fitur investasi section", (String subSection) -> {
            bukalapak.bukaReksaHomePage().tapOnPakaiFiturInvestasi(subSection);
        });
        //end region Transaksi Rutin/Recurring

        //region Produk Pilihan
        Then("user swipe recommended product card", () -> {
            bukalapak.bukaReksaHomePage().swipeRecommendedProductCard();
        });

        When("user tap on Lihat Semua button in recommended product section", () -> {
            bukalapak.bukaReksaHomePage().tapOnLihatSemuaBtn();
        });

        When("^user tap Beli Produk button on recommended product", () -> {
            bukalapak.bukaReksaHomePage().tapOnBeliProductBtn();
        });
        //end region Produk Pilihan

        //region Serba-serbi
        And("user tap on Lihat Semua button in Serba-Serbi section", () -> {
            bukalapak.bukaReksaHomePage().tapOnLihatSemuaSerbaSerbiBtn();
        });

        Then("user should be able to see Serba-Serbi BukaReksa page", () -> {
            bukalapak.bukaReksaHomePage().verifySerbaSerbiReksaDisplayed();
        });

        When("user tap on \"([^\"]*)\" in Serba-Serbi BukaReksa page", (String subSection) -> {
            bukalapak.bukaReksaHomePage().tapOnSerbaSerbiPage(subSection);
        });

        Then("user should be able to see kamus reksa dana page", () -> {
            bukalapak.bukaReksaHomePage().isOnKamusReksaDanaPage();
        });

        Then("user should be able to see jenis reksa dana page", () -> {
            bukalapak.bukaReksaHomePage().isOnJenisReksaDanaPage();
        });

        When("user tap on Serba-Serbi BukaReksa back button", () -> {
            bukalapak.bukaReksaHomePage().tapOnSerbaSerbiBackButton();
        });

        And("user should be able to see BukaReksa video", () -> {
            bukalapak.bukaReksaHomePage().isBukaReksaVideoDisplayed();
        });

        Then("user should be able to see BukaReksa Youtube Channel", () -> {
            bukalapak.bukaReksaHomePage().isBukaReksaYoutubeChannelDisplayed();
        });

        When("user tap on back button in BukaReksa Youtube Channel", () -> {
            bukalapak.bukaReksaHomePage().tapSerbaSerbiYoutubeBackBtn();
        });

        When("user tap on Serba-Serbi BukaReview back button", () -> {
            bukalapak.bukaReksaHomePage().tapSerbaSerbiBukaReviewBackBtn();
        });

        When("user scroll to BukaReksa article", () -> {
            bukalapak.bukaReksaHomePage().isBukaReksaArticleDisplayed();
        });

        Then("user should be able to see Investment - BukaReview page", () -> {
            bukalapak.bukaReksaHomePage().isBukaReksaBlogDisplayed();
        });

        Then("user should be able to see BukaReksa user's testimony", () -> {
            bukalapak.bukaReksaHomePage().isBukaReksaTestimonyDisplayed();
        });
        //end region Serba-serbi

        //region Keuntungan Bukareksa
        When("user should be able to see keuntungan bukareksa" ,() -> {
            bukalapak.bukaReksaHomePage().verifyBukaReksaBenefitDisplayed();
        });
        //end region Keuntungan Bukareksa

        // region banner
        When("user verify BukaReksa banner displayed", () -> {
            bukalapak.bukaReksaHomePage().isBannerSectionDisplayed();
        });

        When("user tap on BukaReksa banner", () -> {
            bukalapak.bukaReksaHomePage().tapOnReksaBanner();
        });

        When("user should be able to see promo detail", () -> {
            bukalapak.bukaReksaHomePage().verifyPromoInfoDisplayed();
        });

        // games

        When("user should be able to see BukaReksa games banner displayed", () -> {
            bukalapak.bukaReksaHomePage().validateReksaGames();
        });

        When("user tap on mainkan button", () -> {
            bukalapak.bukaReksaHomePage().tapGamesMainkanBtn();
        });

        When("user should be able to see BukaReksa games page", () -> {
            bukalapak.bukaReksaHomePage().verifyGamesPageDisplayed();
        });

        //region Tujuan Investasi
        When("user tap goal investment section in home page", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().tapOnGoalInvestmentSection();
        });

        Then("user can see entry point goals displayed and saldo goals 0", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().validateActiveGoalInvestmentSectionDisplayed();
        });

        Given("user check whether have goal investment in BukaReksa Homepage", () -> {
            bukalapak.bukaReksaGoalInvestmentPage().checkUserHaveGoalInvestment();
        });

        When("user create goal investment for the first time from BukaReksa Homepage with amount \"([^\"]*)\"", (String amount) -> {
            if (!InvestmentData.getUserHaveGoalInvestProduct()){
                bukalapak.bukaReksaGoalInvestmentPage().tapTujuanLainIcon();
                bukalapak.bukaReksaGoalInvestmentPage().verifyApaTujuanInvestasiKamuDisplayed();
                bukalapak.bukaReksaGoalInvestmentPage().chooseGoalInvestmentType();
                bukalapak.bukaReksaGoalInvestmentPage().verifyAturTujuanInvestasiPageDisplayed();
                bukalapak.bukaReksaGoalInvestmentPage().inputTargetAmount(amount);
                bukalapak.bukaReksaGoalInvestmentPage().chooseTargetTime();
                bukalapak.bukaReksaGoalInvestmentPage().verifyInvestPerMonthSuggestion();
                bukalapak.bukaReksaGoalInvestmentPage().tapLanjutLihatSimulasiButton();
                bukalapak.bukaReksaGoalInvestmentPage().verifySuccessCreateGoalInvestmentMessageDisplayed();
                bukalapak.bukaReksaGoalInvestmentPage().verifyMulaiInvestasiPageDisplayed();
            }
        });

        When("user tap on games exit button", () -> {
            bukalapak.bukaReksaHomePage().tapOnExitGamesBtn();
        });
    }
}
