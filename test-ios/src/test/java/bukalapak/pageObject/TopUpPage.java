package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.DANAData;
import bukalapak.data.HelperData;
import bukalapak.data.InvestmentData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

/**
 * Created by Ferawati.
 */
public class TopUpPage extends BasePage {

    public TopUpPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateVANumber(String vaType) {
        if (isElementVisible("pajak_daerah_pay_button")) {
            LogUtil.info("Selesaikan dulu 5 transaksi sebelumnya");
        } else {
            verifyElementExist(constructLocator("virtual_account_number", TestInstrument.dotenv.get(vaType.toUpperCase())));
        }
        HelperData.setLastActionPage(new TopUpPage(iosDriver));
    }

    public void chooseSaldo() {
        isElementVisible("topup_Saldo_text_view", 5);
        tapElement("topup_Saldo_text_view");
    }

    public void tapBayar() {
        swipeUpToElement("topup_bayar_button");
        tapElement("topup_bayar_button");
    }

    public void tapCloseButton() {
        tapElement("close_button");
    }

    public void chooseIndomaret() {
        isElementVisible("topup_Indomaret_text_view", 5);
        tapElement("topup_Indomaret_text_view");
    }

    public void tapOnBackButton() {
        tapElement("topup_dana_back_button");
    }

    // investment

    public void selectTopUpDANAWithBukaEmas() {
        tapElement("DANA_TOPUP_METODE_LAIN");
        verifyElementExist("DANA_TOPUP_BUKAEMAS_SECTION");
        tapElement("DANA_TOPUP_BUKAEMAS_SECTION");
    }

    public void verifyBukareksaSectionDisplayed() {
        verifyElementExist("DANA_TOPUP_BUKAREKSA_SECTION");
        verifyElementExist("DANA_TOPUP_BUKAREKSA_PRODUCT_NAME");
        verifyElementExist("DANA_TOPUP_BUKAREKSA_BALANCE");
        HelperData.setLastActionPage(new TopUpPage(iosDriver));
    }

    public void verifyBukareksaSectionNotDisplayed() {
        validateNotDisplayed("DANA_TOPUP_BUKAREKSA_SECTION","BukaReksa section displayed");
    }

    public void tapOnBukaReksaSection() {
        tapElement("DANA_TOPUP_BUKAREKSA_SECTION");
    }

    private void topUpDanaWithBukaReksa() {
        if (InvestmentData.getFromInvestasikuPage()) {
            verifyTopUpDanaInvestasikuModalDisplayed();
        } else {
            verifyElementExist("DANA_TOPUP_BUKAREKSA_SHEET_DRAGABLE_TITLE");
            verifyElementExist("DANA_TOPUP_BUKAREKSA_SHEET_DRAGABLE_DESC");
        }
    }

    public void verifyDANATopUpSheetDragableDisplayed(String product) {
        // need delay for waiting sheet dragable display properly
        delay(3000);
        if (product.equals("BukaEmas")) {
            verifyElementExist("DANA_TOPUP_BUKAEMAS_SHEET_DRAGABLE_TITLE");
            verifyElementExist("DANA_TOPUP_BUKAEMAS_SHEET_DRAGABLE_DESC");
        } else if (product.equals("BukaReksa")) {
            topUpDanaWithBukaReksa();
        } else {
            Assert.fail(product + " isn't a product type!");
        }
        verifyInvestmentTotalSaldo();
        HelperData.setLastActionPage(new TopUpPage(iosDriver));
    }

    private void verifyInvestmentTotalSaldo() {
        if (InvestmentData.getFromInvestasikuPage()) {
            verifyElementExist("DANA_TOP_UP_INVESTASIKU_TOTAL_PORTFOLIO");
            InvestmentData.setTotalPortfolio(getText("DANA_TOP_UP_INVESTASIKU_TOTAL_PORTFOLIO"));
        } else {
            verifyElementExist("DANA_TOPUP_SHEET_DRAGABLE_TOTAL_SALDO");
            InvestmentData.setTotalPortfolio(getTotalPortfolio());
            verifyElementExist("DANA_TOPUP_PINDAHKAN_SALDO_BTN");
        }
    }


    private String getTotalPortfolio() {
        return getText("DANA_TOPUP_SHEET_DRAGABLE_TOTAL_SALDO");
    }

    private String getRedeemToDANAAmount() {
        return getText("DANA_TOPUP_INVESTMENT_FORM_NOMINAL_FIELD").replaceAll("[^\\d]", "");
    }

    public void inputNominaltopUpDANAWithInvestmentProduct(String nominal) {
        if (InvestmentData.getFromInvestasikuPage()) {
            inputTopUpAmountfromInvestasiku(nominal);
        } else {
            tapElement("DANA_TOPUP_INVESTMENT_FORM_NOMINAL_FIELD"); // make the topup field active
            typeAndEnterValue("DANA_TOPUP_INVESTMENT_FORM_NOMINAL_FIELD", nominal);
            InvestmentData.setNominalTopUpToDANA(getRedeemToDANAAmount());
        }
    }

    public void verifyMinimumErrorMessageDisplayed() {
        verifyElementExist("DANA_TOPUP_MIN_ERROR_MSG");
    }

    public void verifyMaximumErrorMessageDisplayed() {
        verifyElementExist("DANA_TOPUP_MAX_ERROR_MSG");
    }

    public void verifyMaxInstanRedemption() {
        verifyElementExist("DANA_TOPUP_INSTAN_REDEMPTION_ERROR_MSG");
    }

    public void verifyMaxDailyLimitDANA() {
        verifyElementExist("DANA_TOPUP_MAX_TOPUP_KE_DANA_ERR_MSG");
        InvestmentData.setLimitDANAamount(getText("DANA_TOPUP_MAX_TOPUP_KE_DANA_ERR_MSG")
                .replaceAll("[^\\d]", ""));
    }

    public void verifyNotBindingDanaErrorMessageDisplayed() {
        changeContext().toWebview();
        validateExist("DANA_TOPUP_NOT_BINDING_ERROR_MSG", 3);
        validateValue().equals("Akun kamu belum terhubung ke DANA", getSnackbarText(), "Invalid message");
    }

    private String getSnackbarText() {
        return getText("DANA_TOPUP_NOT_BINDING_ERROR_MSG");
    }

    public void verifyErrorMessageDisplayed(String method) {
        try {
            switch (method.toLowerCase()) {
                case "minimum":
                    verifyMinimumErrorMessageDisplayed();
                    break;
                case "maximum":
                    verifyMaximumErrorMessageDisplayed();
                    break;
                case "daily limit":
                    verifyMaxDailyLimitDANA();
                    break;
                case "max instan redemption":
                    verifyMaxInstanRedemption();
                    break;
                case "investor not binding dana":
                    verifyNotBindingDanaErrorMessageDisplayed();
                    break;
                default:
                    Assert.fail(method + " isn't a method name");
            }
        } catch (AssertionError e) {
            verifyMaxDailyLimitDANA();
            LogUtil.info("DANA max top up balance" + InvestmentData.getLimitDANAamount());
        }
    }

    public void tapOnPindahinKeDANABtn() {
        if (InvestmentData.getFromInvestasikuPage()) {
            tapElement("DANA_TOPUP_INVESTASIKU_REDEEM_BTN");
        } else {
            tapElement("DANA_TOPUP_PINDAHKAN_SALDO_BTN");
        }
    }

    private String getRedemptionPredictionAmount() {
        validateExist("REDEMPTION_FORM_PRODUCT_REDEEM_AMOUNT", 10);
        return getText("REDEMPTION_FORM_PRODUCT_REDEEM_AMOUNT").replaceAll("[^\\d]", "");
    }

    private void verifyRedeemToDANAConfirmationForm() {
        validateValue().equals(InvestmentData.getNominalTopUpToDANA(), getRedemptionPredictionAmount(), "Amount is not equal!");
        validateExist("DANA_TOPUP_BUKAREKSA_CONFIRM_METHOD");
    }

    public void confirmRedeemBukaReksaToDANAForm() {
        verifyRedeemToDANAConfirmationForm();
        tapElement("REDEMPTION_FORM_PRODUCT_REDEEM_BTN");
    }

    public void verifyWaitingProcessedBannerDisplayed(String product) {
        switch (product.toLowerCase()) {
            case "bukaemas":
                verifyWaitingProcessedBukaEmasBannerDisplayed();
                break;
            case "bukareksa":
                verifyWaitingProcessedBukaReksaBannerDisplayed();
                break;
            default:
                Assert.fail("product not found!");
        }
    }

    public void verifyWaitingProcessedBannerNotDisplayed(String product) {
        switch (product.toLowerCase()) {
            case "bukaemas":
                verifyWaitingProcessedBukaEmasBannerNotDisplayed();
                break;
            case "bukareksa":
                verifyWaitingProcessedBukaReksaBannerNotDisplayed();
                break;
            default:
                Assert.fail("product not found!");
        }
    }

    public void verifyWaitingProcessedBukaEmasBannerDisplayed() {
        verifyElementExist("DANA_TOPUP_BUKAEMAS_WAITING_BANNER");
    }

    public void verifyWaitingProcessedBukaReksaBannerDisplayed() {
        verifyElementExist("DANA_TOPUP_BUKAREKSA_WAITING_BANNER");
    }

    public void verifyWaitingProcessedBukaEmasBannerNotDisplayed() {
        int waitLimit = 1;
        while(isElementExist("DANA_TOPUP_BUKAEMAS_WAITING_BANNER")) {
            try {
                LogUtil.info("Still waiting. DANA balance is not updated");
                waitLimit++;
            } catch (Exception e) {
                e.printStackTrace();
                waitLimit++;
            } if(waitLimit > 6) {
                break;
            }
        }
    }

    public void verifyWaitingProcessedBukaReksaBannerNotDisplayed() {
        while(isElementExist("DANA_TOPUP_BUKAREKSA_WAITING_BANNER")) {
            try {
                LogUtil.info("Still waiting. DANA balance is not updated");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void verifyTopUpSuccessMessageDisplayed() {
        // Use timeout 120s for max time redeem successed
        verifyElementExist("DANA_TOPUP_SUCCESS_MESSAGE", 120, "Success message topup to DANA not displayed");
        verifyElementExist("DANA_TOPUP_SUCCESS_MESSAGE");
    }

    public void tapOnBukaReksaTncCheckbox() {
        if (InvestmentData.getFromInvestasikuPage()) {
            tapElement("DANA_TOPUP_INVESTASIKU_CHECKBOX");
        } else {
            tapElement("DANA_TOPUP_BUKAREKSA_TNC_CHECKBOX");
        }
    }

    public int getDanaBalance() {
        if (InvestmentData.getFromInvestasikuPage()) {
            // need delay for waiting DANA balance updated
            delay(50000);
            swipeDownToElement("homepage_dana_balance_text");
            waitForVisibilityOf("homepage_dana_balance_text", 20);
            return Integer.parseInt(getTextFromElement("homepage_dana_balance_text").replaceAll("[^0-9]", ""));
        } else {
            // need delay for waiting DANA balance fully loaded
            delay(1000);
            verifyElementExist("DANA_BALANCE_IN_TOPUP_DANA_PAGE");
            return getIntegerFromValueElement("DANA_BALANCE_IN_TOPUP_DANA_PAGE");
        }
    }

    public void cekDANABalanceText() {
        DANAData.setDanaBalance(getDanaBalance());
    }

    public void verifyDANABalanceUpdated() {
        // need delay for waiting DANA balance updated
        delay(30000);
        if (InvestmentData.getFromInvestasikuPage()) {
            changeContext().toNative();
            int waitLimit = 1;

            while (!isElementExist("home_blhome_tab")) {
                try {
                    tapElement("REXA_INVESTASIKU_BACK_BTN", 5);
                    waitLimit++;
                } catch (Exception e) {
                    e.printStackTrace();
                    waitLimit++;
                } if(waitLimit > 4) {
                    break;
                }
            }
            tapElement("home_blhome_tab");
        } else {
            tapOnBackButton();
            tapElement("checkout_page_dana_option_card_payment_version");
        }
        validateValue().equals(DANAData.getDanaBalance() + parseIntegerFromText(InvestmentData.getNominalTopUpToDANA()), getDanaBalance(),
                "Saldo seharusnya bertambah");
    }

    public void verifyTopUpDanaInvestasikuModalDisplayed() {
        changeContext().toWebview();
        verifyElementExist("DANA_TOPUP_INVESTASIKU_MODAL");
        verifyElementExist("DANA_TOPUP_INVESTASIKU_INPUT_FIELD");
        verifyElementExist("DANA_TOPUP_INVESTASIKU_CHECKBOX");
        verifyElementExist("DANA_TOPUP_INVESTASIKU_REDEEM_BTN");
    }

    private void inputTopUpAmountfromInvestasiku(String amount) {
        tapElement("DANA_TOPUP_INVESTASIKU_INPUT_FIELD");
        getElementPresent("DANA_TOPUP_INVESTASIKU_INPUT_FIELD").sendKeys(amount);
        InvestmentData.setNominalTopUpToDANA(amount);
    }

    private double getEstimationUnit() {
        verifyElementExist("DANA_TOPUP_INVESTASIKU_ESTIMATION_UNIT");
        double topUpAmount = Double.parseDouble(InvestmentData.getNominalTopUpToDANA());
        double nab = getDoubleFromRp(InvestmentData.getProductNABBukaReksa());
        return roundWithPrecision((topUpAmount / nab), 2);
    }

    public void validateRedemptionUnit() {
        validateDisplayed("DANA_TOPUP_INVESTASIKU_ESTIMATION_UNIT");
        String actualUnit = getText("DANA_TOPUP_INVESTASIKU_ESTIMATION_UNIT");
        validateValue().equals(String.valueOf(getEstimationUnit()), getUnitToDecimalFormat(actualUnit));
    }

    public void validateTopUpAmount() {
        int topUpAmount = getIntFromRp(getText("DANA_TOPUP_INVESTASIKU_REDEEM_AMOUNT"));
        validateValue().equals(InvestmentData.getNominalTopUpToDANA(), String.valueOf(topUpAmount));
    }

    public void verifyInvestasikuConfirmationModalDisplayed() {
        validateRedemptionUnit();
        validateTopUpAmount();
        verifyElementExist("DANA_TOPUP_INVESTASIKU_REDEMPTION_FEE");
        verifyElementExist("DANA_TOPUP_INVESTASIKU_DANA_PHONE_NUMBER");
        HelperData.setLastActionPage(new TopUpPage(iosDriver));
    }

    public void verifyInvestasikuConfirmationModalNotDisplayed() {
        validateNotExist("DANA_TOPUP_INVESTASIKU_REDEMPTION_FEE", 5);
        validateNotExist("DANA_TOPUP_INVESTASIKU_DANA_PHONE_NUMBER", 5);
    }

    public void tapOnJualSekarangBtn() {
        tapElement("DANA_TOPUP_INVESTASIKU_REDEEM_CONFIRMATION_BTN");
    }

    // end of top up Dana with Investment Product

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
