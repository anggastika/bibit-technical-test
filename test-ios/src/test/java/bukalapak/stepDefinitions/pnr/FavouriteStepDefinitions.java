package bukalapak.stepDefinitions.pnr;

import bukalapak.TestInstrument;
import bukalapak.data.UserData;
import cucumber.api.java8.En;

public class FavouriteStepDefinitions extends TestInstrument implements En{
    public FavouriteStepDefinitions(){
        When("user click on menu Barang Favorit", ()->{
            bukalapak.favoritePage().tapFavoritMenu();
        });

        Then("user is on favourite page",()->{
            bukalapak.favoritePage().verifyPage();
        });

        And("user click button login",()->{
            bukalapak.favoritePage().clickButtonLogin();
        });

        And("user login with username \"([^\"]*)\"", (String user) -> {
            bukalapak.favoritePage().clickButtonLogin();
            bukalapak.loginPage().typeOnUsernameEditText(dotenv.get(user+"_USERNAME"));
            bukalapak.loginPage().clickContinueButton();
        });

        And("user login with password \"([^\"]*)\"", (String pass) -> {
            bukalapak.loginPage().typeOnPasswordEditText(dotenv.get(pass+"_PASSWORD"));
            bukalapak.favoritePage().clickButtonLogin();
        });

        And("user login with credential \"([^\"]*)\" from favorite page", (String username) -> {
            bukalapak.favoritePage().clickButtonLogin();
            bukalapak.loginPage().loginAs(username);
            bukalapak.favoritePage().verifyPage();
            UserData.setLoggedIn(true);
        });

        And("user can see product marked as favourite",()->{
            bukalapak.favoritePage().assertProductFavorite();
        });

        And("user can see recommendation panel",()->{
            bukalapak.favoritePage().assertSectionReco();
        });

        And("^user clicks up button on favorite page$", () -> {
            bukalapak.favoritePage().clickUpButton();
        });

        When("^user clicks button Tambah on recommendation product$", () -> {
            bukalapak.favoritePage().tapButtonTambah();
            bukalapak.productCatalogDetailPage().clickBuyOnVariantProduct();
        });

        Then("^user see pop up add to cart on favorite page$", () -> {
            bukalapak.favoritePage().validatePopUpAtc();
        });

        When("^user click one of product recommendation$", () -> {
            bukalapak.favoritePage().tapProductRecommendation();
        });

        And("^user can see recommendation on grid view$", () -> {
            bukalapak.favoritePage().validateRecommendationGrid();
        });

        Then("^user should see first favorite product$", () -> {
            bukalapak.favoritePage().checkTitleText();
        });

        And("^user should not see recommendation section$", () -> {
            bukalapak.favoritePage().validateRecommendationNotExist();
        });

        Then("^user should see product based on search result$", () -> {
            bukalapak.favoritePage().checkTitleText();
        });

        And("^user should see recommendation carousel on favorite$", () -> {
            bukalapak.favoritePage().validateSectionRecoCarousel();
        });

        And("^user can swipe \"([^\"]*)\" product recommendation carousel on favorite$", (String swipe) -> {
            bukalapak.favoritePage().swipeSectionRecoCarousel(swipe);
        });

        And("^user validate product card \"([^\"]*)\" recommendation carousel on favorite$", (String productCard) -> {
            bukalapak.favoritePage().validateProductCardRecoCarousel(productCard);
        });

        When("^user tap one of product recommendation carousel on favorite$", () -> {
            bukalapak.favoritePage().tapProductRecoCarousel();
        });
    }
}