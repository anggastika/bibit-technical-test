package bukalapak.pageObject.investment;

import bukalapak.data.HelperData;
import bukalapak.data.InvestmentData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class InvestasikuPage extends BasePage {

    public InvestasikuPage (IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    private void validatePageTitle(String expectedValue) {
        // need delay for page fully rendered
        delay(10000);
        validateExist("REXA_INVESTASIKU_HEADER_TITLE");
        validateValue().equals(expectedValue, getText("REXA_INVESTASIKU_HEADER_TITLE"));
    }

    private void validatePortfolioBalance() {
        changeContext().toWebview();
        validateExist("REXA_INVESTASIKU_CURRENT_INV_VALUE", 10);
        String productPortfolio = getText("REXA_INVESTASIKU_CURRENT_INV_VALUE");
        validateValue().equals(InvestmentData.getProductPortofolioValue(), productPortfolio);
    }

    public void verifyInvestasikuPageDisplayed() {
        validatePageTitle("Investasiku");
        validatePortfolioBalance();
        verifyElementExist("REXA_INVESTASIKU_PORTFOLIO_RETURN");
        verifyElementExist("REXA_INVESTASIKU_PORTFOLIO_PENDING");
        verifyElementExist("REXA_INVESTASIKU_PORTOFOLIO_BTN");
        verifyElementExist("REXA_INVESTASIKU_SUBSCRIPTION_BTN");
        verifyElementExist("REXA_INVESTASIKU_REDEEM_BTN");
        HelperData.setLastActionPage(new InvestasikuPage(iosDriver));
    }

    private void getProductNameText() {
        validateExist("REXA_INVESTASIKU_PRODUCT_NAME");
        InvestmentData.setProductNameBukaReksa(getText("REXA_INVESTASIKU_PRODUCT_NAME"));
    }

    private void getProductTypeText() {
        validateExist("REXA_INVESTASIKU_PRODUCT_TYPE");
        InvestmentData.setProductTypeBukaReksa(getText("REXA_INVESTASIKU_PRODUCT_TYPE"));
    }

    private void getProductNavText() {
        validateExist("REXA_INVESTASIKU_PRODUCT_NAV");
        String nav = getText("REXA_INVESTASIKU_PRODUCT_NAV");
        InvestmentData.setProductNABBukaReksa(String.valueOf(getDoubleFromRp(nav)));
    }

    public void verifyProductCardDisplayed() {
        validateExist("REXA_INVESTASIKU_PRODUCT_CARD", 5);
        getProductNameText();
        getProductTypeText();
        getProductNavText();
        InvestmentData.setFromInvestasikuPage(true);
        HelperData.setLastActionPage(new InvestasikuPage(iosDriver));
    }

    public void tapProductCard() {
        webViewTapOnElement("REXA_INVESTASIKU_PRODUCT_CARD");
        changeContext().toNative();
    }

    private void getValidProductNav() {
        tapProductCard();
        validateExist("PROD_DETAIL_HEADER_PRODUCT_NAB", 10);
        InvestmentData.setProductNABBukaReksa(getText("PROD_DETAIL_HEADER_PRODUCT_NAB"));
        tapElement("PROD_DETAIL_BACK_BTN");
        changeContext().toWebview();
    }

    public void tapOnTransactionBtn(String type) {
        switch (type.toLowerCase()) {
            case "beli":
                webViewTapOnElement("REXA_INVESTASIKU_SUBSCRIPTION_BTN");
                break;
            case "jual instan":
                getValidProductNav();
                webViewTapOnElement("REXA_INVESTASIKU_REDEEM_BTN");
                InvestmentData.setFromInvestasikuPage(true);
                break;
            default:
                Assert.fail(type + "isn't transaction type!");
        }
        changeContext().toNative();
    }

    public void verifyBwrInfoDisplayed() {
        validateExist("REXA_INVESTASIKU_BWR_INFO", 5);
        validateExist("REXA_INVESTASIKU_MULAI_DAFTAR_BTN");
    }

    public void tapOnMulaiDaftarBtn() {
        webViewTapOnElement("REXA_INVESTASIKU_MULAI_DAFTAR_BTN");
        changeContext().toNative();
    }

    public void verifyRegisrationProgressDisplayed() {
        validateExist("REXA_INVESTASIKU_REGISTRATION_PROGRESS");
        validateExist("REXA_INVESTASIKU_UPDATE_INFO_BTN");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
