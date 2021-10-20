package bukalapak.pageObject.investment;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import bukalapak.data.InvestmentData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.Direction;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

public class BukaReksaPortofolioPage extends BasePage {

    public BukaReksaPortofolioPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    //region Main Portofolio Page

    public void isPortfolioPageDisplayed() {
        // if user tap from Investasi button
        if (isElementVisible("REXA_DISCLAIMER_MODAL_BTN")) {
            tapElement("REXA_DISCLAIMER_MODAL_BTN");
        }

        isPortfolioTabSelected();
        isPortfolioHeaderDisplayed();
        isLihatTransaksiBtnDisplayed();
        isReksaDanaKuSectionDisplayed();
        isTotalWealthTxtDisplayed();
        isInitialInvestmentTxtDisplayed();
        isPortfolioProductListDisplayed();
        HelperData.setLastActionPage(new BukaReksaPortofolioPage(iosDriver));
    }

    public void tapOnSeeTransactionBtn() {
        tapElement("PORTFOLIO_SEE_TRANSACTION_BTN");
    }

    public void tapOnSubscriptionBtn() {
        int portfolioProduct = randomPortfolioProduct();
        InvestmentData.setProductNameBukaReksa(getPortfolioProductName(portfolioProduct));
        InvestmentData.setProductTypeBukaReksa(getPortfolioProductType(portfolioProduct));
        tapOnSubscriptionPortfolioBtn(portfolioProduct);
    }

    public void tapOnRedemptionBtn() {
        int portfolioProduct = randomPortfolioProduct();
        InvestmentData.setProductNameBukaReksa(getPortfolioProductName(portfolioProduct));
        InvestmentData.setProductTypeBukaReksa(getPortfolioProductType(portfolioProduct));
        tapOnRedemptionPortfolioBtn(portfolioProduct);
    }

    public void tapOnProductRedemptionBtn(String product) {
        String productName = null;
        switch (product.toLowerCase()) {
            case "non instant product":
                productName = "REXA_PORTFOLIO_NON_INSTANT_PRODUCT";
                break;
            case "instant product":
                productName = "REXA_PORTFOLIO_INSTANT_PRODUCT";
                break;
            default:
                Assert.fail("Invalid product type!");
        }

        String getProduct = TestInstrument.dotenv.get(productName);
        InvestmentData.setProductNameBukaReksa(getPortfolioProductName(getProduct));
        if (!isElementExist(constructLocator("PORTFOLIO_SPECIFIC_PRODUCT_REDEEM_BTN", getProduct))) {
            nativeSwipeUpToElement(constructLocator("PORTFOLIO_SPECIFIC_PRODUCT_REDEEM_BTN", getProduct));
        }
        tapElement(constructLocator("PORTFOLIO_SPECIFIC_PRODUCT_REDEEM_BTN", getProduct));
    }

    /**
     * Compare portfolio total with portfolio summary in Beranda
     */
    public void validatePortfolioTotal() {
        validateValue().equals(InvestmentData.getTotalPortfolio(), getUserPortofolioTotalText(), "Total portfolio doesn't match!");
        HelperData.setLastActionPage(new BukaReksaPortofolioPage(iosDriver));
    }

    /**
     * Compare product portofolio in Portofolio page and Investasi di BukaReksa page
     */
    public void verifyProductPortofolio() {
        validateValue().equals(InvestmentData.getProductPortofolioValue(), getProductPortofolioValueText(), "Total product portofolio value doesn't match!");
    }

    //end region Main Portofolio Page

    //region Subscription from Portofolio Page
    public void tapInvestasiLagi() {
        tapElement("SUBSCRIPTION_OPTION_INVESTASI_LAGI");
    }

    public void tapTransaksiRutin() {
        tapElement("SUBSCRIPTION_OPTION_TRANSAKSI_RUTIN");
    }

    public void tapOnTncDisclaimerCheckBox() {
        String checkbox = InvestmentData.getFromBukaReksaPackage() ? "REXA_PKG_DISCLAIMER_CHECKBOX" : "SUBSCRIPTION_FORM_TNC_CHECKBOX";
        boolean checkbox_state = Boolean.parseBoolean(getElementValue(checkbox));

        if (!checkbox_state) {
            tapElement(checkbox);
        } else {
            Assert.fail("Checkbox already checked!");
        }
    }

    public void tapOnContinueBtn() {
        tapElement("SUBSCRIPTION_FORM_LANJUTKAN_BTN");
    }

    public void inputNominalPurchase(String nominal) {
        tapElement("SUBSCRIPTION_FORM_NOMINAL_FIELD", 10);
        clearSubscriptionNominalField();
        typeAndEnterValue("SUBSCRIPTION_FORM_NOMINAL_FIELD", nominal);
        InvestmentData.setNominalPurchase(nominal);
        if (isElementExist("REXA_PKG_BUKAEMAS_SUBSCRIPTION_NOMINAL", 3)){
            InvestmentData.setBukaEmasPackagePurchaseNominal(getText("REXA_PKG_BUKAEMAS_SUBSCRIPTION_NOMINAL"));
        }
    }

    public void checkSubscriptionOptionDisplayed() {
        verifyElementExist("SUBSCRIPTION_OPTION_MODAL");
    }

    public void checkSubscriptionFormNotDisplayed() {
        validateNotDisplayed("SUBSCRIPTION_FORM_LANJUTKAN_BTN");
        HelperData.setLastActionPage(new BukaReksaPortofolioPage(iosDriver));
    }

    public void checkSubscriptionFormDisplayed() {
        if (isElementExist("PROD_DETAIL_USER_CONSENT_MODAL", 3)) {
            tapElement("PROD_DETAIL_LANJUT_BELI_BTN");
        } else if (isElementExist("PROD_DETAIL_CHANGE_PROFILE_RISK_MODAL", 3)) {
            tapElement("PROD_DETAIL_LANJUTKAN_TRANSAKSI_BTN");
        }
        validateExist("SUBSCRIPTION_FORM_MODAL", 10);
        validateValue().equals(InvestmentData.getProductNameBukaReksa(), getProductNameForm(), "Product name doesn't match");
        HelperData.setLastActionPage(new BukaReksaPortofolioPage(iosDriver));
    }

    //end region Subscription from Portofolio Page

    //region Redemption from Portofolio Page
    public void tapJualProdukInvestasiBtn() {
        tapElement("REDEMPTION_CONFIRMATION_JUAL_BTN");
    }

    public void validateRedemptionConfirmationModal() {
        verifyElementExist("REDEMPTION_CONFIRMATION_MODAL_TITLE");
        verifyElementExist("REDEMPTION_CONFIRMATION_MODAL_BODY");
        verifyElementExist("REDEMPTION_CONFIRMATION_JUAL_BTN");
        verifyElementExist("REDEMPTION_CONFIRMATION_BATAL_BTN");
    }

    public void verifyPortofolioRedemptionForm() {
        waitForVisibilityOf("REDEMPTION_FORM_PRODUCT_MODAL");
        validateDisplayed("REDEMPTION_FORM_PRODUCT_REDEEM_BTN");
        validateValue().equals(InvestmentData.getProductNameBukaReksa(), getProductNameForm(), "Product name doesn't match");
        HelperData.setLastActionPage(new BukaReksaPortofolioPage(iosDriver));
    }

    public void validateVoucherCancellationWarning() {
        verifyElementExist("REDEMPTION_VOUCHERS_WARNING");
    }

    private void tapOnLihatSelengkapnyaBtn() {
        tapElement("REDEMPTION_INFO_SELENGKAPNYA_BTN");
    }

    public void verifyImportantSalesInfoPageDisplayed() {
        tapOnLihatSelengkapnyaBtn();
        verifyElementExist("REDEMPTION_IMPORTANT_SALES_INFO_HEADER_TITLE");
        verifyElementExist("REDEMPTION_IMPORTANT_SALES_INFO_DESC");
    }

    public void confirmRedemptionForm() {
        verifyRedemptionConfirmationForm();
        tapElement("REDEMPTION_FORM_PRODUCT_REDEEM_BTN");
    }

    public void processPortfolioRedemption(String amount) {
        inputRedeemNominalValue(amount);
        checkPortfolioAmount(amount);
        tapOnRedemptionCheckbox();
        verifyRedemptionInfo();
        InvestmentData.setNominalRedemptionProduct(amount);
        InvestmentData.setUnitRedemptionProduct(getProductRedemptionUnit());
        tapOnRedemptionContinueBtn();
    }

    private int getPortfolioInvestAmount() {
        return Integer.parseInt(getText("REDEMPTION_FORM_INVEST_AMOUNT")
                .replaceAll("[^\\d]", ""));
    }

    private void checkPortfolioAmount(String amount) {
        int redeemAmount = Integer.parseInt(amount);
        if (redeemAmount > getPortfolioInvestAmount()) {
            validateExist("REDEMPTION_FORM_ERROR_MESSAGE");
            Assert.fail("Portfolio tidak mencukupi! Nilai Investasi Kamu: Rp" + getPortfolioInvestAmount());
        }
    }

    //end region Redemption from Portofolio Page

    //region Strong Auth Page Bukareksa
    public void verifyRedemptionStrongAuthPage() {
        validateDisplayed("REDEMPTION_STRONG_AUTH_TITLE_HEADER");
        validateDisplayed("REDEMPTION_STRONG_AUTH_TITLE_FIELD");
        validateDisplayed("REDEMPTION_INPUT_PASSWORD_FIELD");
        validateDisplayed("REDEMPTION_RESET_PASSWORD_BTN");
        validateDisplayed("REDEMPTION_STRONG_AUTH_VERIFIKASI_BTN");
    }

    public void selectPasswordforRedemption(String passwordType) {
        switch (passwordType.toLowerCase()) {
            case "correct":
                typeAndEnterValue("REDEMPTION_INPUT_PASSWORD_FIELD", TestInstrument.dotenv.get("INVESTOR_PASSWORD"));
                break;
            case "bukaemas correct":
                typeAndEnterValue("REDEMPTION_INPUT_PASSWORD_FIELD", TestInstrument.dotenv.get("BUKAEMAS_PASSWORD"));
                break;
            case "incorrect":
                typeAndEnterValue("REDEMPTION_INPUT_PASSWORD_FIELD", TestInstrument.dotenv.get("NON_INVESTOR_PASSWORD"));
                break;
            default:
                Assert.fail(passwordType + " isn't a password type!");
        }
    }

    public void tapVerifikasiButton() {
        tapElement("REDEMPTION_STRONG_AUTH_VERIFIKASI_BTN");
    }
    //end region Strong Auth Page BukaReksa

    /*
     * this method is used to handle case when randomizer returns an out of array size number
     * for example, index 2 from the size of 2 portfolio products will return an out of bound index for tap action
     */
    private int randomPortfolioProduct() {
        int getRandomPortfolioList = randomize().number(getPortfolioList());
        int totalResult = 0;
        int maxList = 3;
        try {
            if (getRandomPortfolioList > maxList) {
                totalResult = getRandomPortfolioList - (getRandomPortfolioList - maxList);
            } else if (getRandomPortfolioList != 0 && getRandomPortfolioList < maxList || getRandomPortfolioList == maxList) {
                totalResult = getRandomPortfolioList;
            }
        } catch (Exception e) {
            Assert.fail("No portfolio product list displayed!");
        }
        return totalResult;
    }

    private void tapOnSubscriptionPortfolioBtn(int index) {
        if (!isElementExist(constructLocator("PORTFOLIO_PRODUCT_PURCHASE_BTN", index))) {
            swipeUpToElement(constructLocator("PORTFOLIO_PRODUCT_PURCHASE_BTN", index));
        }
        tapElements("PORTFOLIO_PRODUCT_PURCHASE_BTN", index, Direction.UP);
    }

    private void tapOnRedemptionPortfolioBtn(int index) {
        tapElements("PORTFOLIO_PRODUCT_REDEEM_BTN", index, Direction.UP);
    }

    private void tapOnRedemptionCheckbox() {
        tapElement("REDEMPTION_FORM_CHECKBOX");
    }

    private void verifyRedemptionInfo() {
        verifyElementExist("REXA_REDEMPTION_INFO");
    }

    private void tapOnRedemptionContinueBtn() {
        tapElement("REDEMPTION_FORM_CONTINUE_BUTTON");
    }

    private void clearSubscriptionNominalField() {
        getElementPresent("SUBSCRIPTION_FORM_NOMINAL_FIELD").clear();
    }

    private void inputRedeemNominalValue(String nominal) {
        waitForVisibilityOf("REDEMPTION_FORM_NOMINAL_FIELD", 3);
        tapElement("REDEMPTION_FORM_NOMINAL_FIELD");
        typeAndEnterValue("REDEMPTION_FORM_NOMINAL_FIELD", nominal);
    }

    private void verifyRedemptionConfirmationForm() {
        // need delay for waiting sheet dragable fully load after back from selling info page
        delay(3000);
        String paymentMethod = "BukaDompet";

        validateValue().equals(InvestmentData.getUnitRedemptionProduct(),getRedemptionFormUnit(),"Unit is not equal!");
        validateValue().equals(InvestmentData.getNominalRedemption(),getRedemptionFormAmount(),"Amount is not equal!");
        validateValue().contains(paymentMethod,getRedemptionFormMethod(),"Method is not equal!");
    }

    private String getUserPortofolioTotalText() {
        double portfolioTotalDouble = getDoubleFromRp(getText("PORTFOLIO_TOTAL_WEALTH"));
        int totalFloor = (int) Math.floor(portfolioTotalDouble);
        return getRpFromNumber(totalFloor);
    }

    private String getProductPortofolioValueText() {
        String productPortfolioName = InvestmentData.getProductNameBukaReksa();
        try {
            swipeUpToElement(constructLocator("PORTFOLIO_PRODUCT_INVESTMENT_VALUE", productPortfolioName));
            return getText(constructLocator("PORTFOLIO_PRODUCT_INVESTMENT_VALUE", productPortfolioName));
        } catch (Exception e) {
            LogUtil.info("Investor don't have portofolio for this product");
            return "Rp0";
        }
    }

    private String getProductRedemptionUnit() {
        return getText("REDEMPTION_FORM_TOTAL_UNIT");
    }

    private String getPortfolioProductName(String productName) {
        return getText(constructLocator("PORTFOLIO_SPECIFIC_PRODUCT_NAME", productName));
    }

    private int getPortfolioList() {
        return getElementsPresent("PORTFOLIO_PRODUCT_LIST").size();
    }

    private String getProductNameForm() {
        return getText("SUBSCRIPTION_FORM_PRODUCT_NAME");
    }

    private String getPortfolioProductName(int index) {
        return getText("PORTFOLIO_PRODUCT_NAME", index);
    }

    private String getPortfolioProductType(int index) {
        return getText("PORTFOLIO_PRODUCT_TYPE", index);
    }

    private String getRedemptionFormUnit() {
        return getText("REDEMPTION_FORM_PRODUCT_REDEEM_UNIT");
    }

    private String getRedemptionFormAmount() {
        return getText("REDEMPTION_FORM_PRODUCT_REDEEM_AMOUNT").replaceAll("[^\\d]", "");
    }

    private String getRedemptionFormMethod() {
        return getText("REDEMPTION_FORM_PRODUCT_REDEEM_METHOD");
    }

    private void isPortfolioTabSelected() {
        verifyElementExist("REXA_NAV_PORTOFOLIO");
        validateSelected("REXA_NAV_PORTOFOLIO", "Portfolio tab not selected");
    }

    private void isPortfolioHeaderDisplayed() {
        validateExist("REXA_PORTFOLIO_HEADER_TITLE", 10, "Portfolio title not displayed!");
    }

    private void isLihatTransaksiBtnDisplayed() {
        validateExist("PORTFOLIO_SEE_TRANSACTION_BTN", 5);
    }

    private void isReksaDanaKuSectionDisplayed() {
        validateExist("PORTFOLIO_REKSA_DANAKU_SECTION", 10, "User don't have portfolio!");
    }

    private void isTotalWealthTxtDisplayed() {
        verifyElementExist("PORTFOLIO_TOTAL_WEALTH_TXT");
    }

    private void isInitialInvestmentTxtDisplayed() {
        verifyElementExist("PORTFOLIO_INITIAL_INVESTMENT_TXT");
    }

    private void isPortfolioProductListDisplayed() {
        verifyElementExist("PORTFOLIO_PRODUCT_LIST");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
