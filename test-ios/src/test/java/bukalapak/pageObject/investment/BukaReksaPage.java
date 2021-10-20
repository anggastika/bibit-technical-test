package bukalapak.pageObject.investment;

import bukalapak.data.HelperData;
import bukalapak.data.InvestmentData;
import bukalapak.data.TransactionData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import com.bukalapak.salad.util.Direction;
import org.junit.Assert;

public class BukaReksaPage extends BasePage {

    public BukaReksaPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    //region main

    public void tapOnBackButton() {
        tapElement("REXA_BACK_BUTTON");
    }

    public void backToBukaReksaHomePage() {
        boolean backFromWebView = false;

        if (isElementVisible("REXA_GOAL_INV_BACK_BUTTON",5)){
           backFromWebView  = true;
        }

        while(!isElementVisible("REXA_NAV_BERANDA",5)) {
            try {
                if (backFromWebView) {
                    tapElement("REXA_GOAL_INV_BACK_BUTTON");
                    //need to wait until the page is fully loaded. The page have different elements, thus we can't use wait for specific element
                    waitFor(2);
                    if (!isElementVisible("REXA_GOAL_INV_BACK_BUTTON", 5)) {
                        backFromWebView = false;
                        changeContext().toNative();
                    }
                } else {
                    tapElement("REXA_BACK_BUTTON");
                }
            } catch (Exception e) {
                break;
            }
        }
    }

    public void selectNavigation(String option) {
        switch (option.toLowerCase()) {
            case "beranda":
                tapElement("REXA_NAV_BERANDA");
                break;
            case "produk":
                tapElement("REXA_NAV_PRODUK");
                break;
            case "portofolio":
                tapElement("REXA_NAV_PORTOFOLIO");
                break;
            case "profil":
                tapElement("REXA_NAV_PROFILE");
                break;
            default:
                Assert.fail(option + " isn't the option name");
        }
    }

    public void tapOnRexaBackBtn() {
        tapElement("REXA_BACK_BUTTON");
    }
    //end region

    //region product page
    public void verifyBwrPopupDisplayed() {
        validateDisplayed("BWR_POPUP_TITLE");
        validateDisplayed("BWR_POPUP_DESC");
        validateDisplayed("BWR_POPUP_IMG");
        validateDisplayed("BWR_POPUP_BTN");
    }

    public void tapOnBwrRegisterButton() {
        tapElement("BWR_POPUP_BTN");
    }
    //endregion

    //region Alchemy - Product Detail
    public void verifyBWRProductDetailPopupDisplayed() {
        validateExist("BWR_PRODUCT_DETAIL_POPUP_TITLE", 3);
        validateExist("BWR_PRODUCT_DETAIL_POPUP_TEXT", 3);
        validateExist("BWR_PRODUCT_DETAIL_LANGSUNG_BELI_BTN", 3);
        validateExist("BWR_PRODUCT_DETAIL_DAFTAR_SEKARANG_BTN", 3);
    }

    public void tapOnLangsungBeliButton() {
        tapElement("BWR_PRODUCT_DETAIL_LANGSUNG_BELI_BTN");
    }
    //endregion

    // region Registration
    public void verifyBukaReksaRegistrationPage() {
        validateDisplayed("REXA_REGISTER_PAGE_TITLE", "Registration header not displayed");
        validateDisplayed("REXA_REGISTER_INFO_PRIBADI_HEADER", "Info pribadi section not displayed");
        validateDisplayed("REXA_REGISTER_GANTI_NOMOR_LINK", "Ganti nomor section not displayed");
    }
    //endregion

    //region BukaReksa checkout
    public void tapOnBayarButton() {
        nativeSwipeUpToElement("REXA_CHECKOUT_BAYAR_BUTTON");
        tapElement("REXA_CHECKOUT_BAYAR_BUTTON", Direction.UP);
        if (isElementVisible("REXA_CHECKOUT_INSUFFICIENT_WALLET")) {
            Assert.fail("Saldo BukaDompet Tidak Cukup!");
        }
        InvestmentData.setTrxListType("Pembelian");
        String paymentMethod = TransactionData.getPaymentMethod();
        switch (paymentMethod.toLowerCase()) {
            case "bukadompet":
            case "saldo":
            case "dana":
                InvestmentData.setTrxListStatus("Berhasil Dibayar");
                break;
            default:
                InvestmentData.setTrxListStatus("Menunggu pembayaran");
        }
    }

    public void chooseBukareksaPaymentMethod(String payment_method) {
            validateExist("REXA_CHECKOUT_HEADER",10);
            switch (payment_method.toLowerCase()) {
                case "saldo":
                    tapElement("REXA_PAYMENT_SALDO", Direction.UP);
                    break;
                case "transfer":
                    tapElement("REXA_PAYMENT_TRANSFER",Direction.UP);
                    break;
                case "virtual_account":
                    tapElement("REXA_PAYMENT_VIRTUAL_ACCOUNT", Direction.UP);
                    break;
                default:
                    Assert.fail(payment_method.toUpperCase() + " isn't a bukareksa payment method");
            }
            TransactionData.setPaymentMethod(payment_method);
    }

    public void typeBukareksaVoucherCode(String voucherCode) {
        swipeUpToElement("REXA_VOUCHER_CHECKBOX");
        tapElement("REXA_VOUCHER_CHECKBOX");
        tapElement("REXA_VOUCHER_CODE_FIELD");
        typeAndEnterValue("REXA_VOUCHER_CODE_FIELD", voucherCode);
        tapElement("REXA_VOUCHER_GUNAKAN_BUTTON");
    }

    public void checkPaymentPageNotDisplayed() {
        validateNotDisplayed("REXA_CHECKOUT_HEADER");
    }
    //endregion

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    //Digital Widget
    public void validatePortfolioFromDigitalWidget() {
        String digitalWidgetBalance = removeStringAfterComma(InvestmentData.getTotalPortfolio());
        validateValue().contains(digitalWidgetBalance, getUserPortofolioTotalText());
    }

    private String getUserPortofolioTotalText() {
        double portfolioTotalDouble = getDoubleFromRp(getText("PORTFOLIO_TOTAL_WEALTH"));
        int totalFloor = (int) Math.floor(portfolioTotalDouble);
        return getRpFromNumber(totalFloor);
    }
}
