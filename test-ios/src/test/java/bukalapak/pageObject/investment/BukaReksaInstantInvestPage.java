package bukalapak.pageObject.investment;

import bukalapak.data.HelperData;
import bukalapak.data.InvestmentData;
import bukalapak.data.TransactionData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class BukaReksaInstantInvestPage extends BasePage {

    public BukaReksaInstantInvestPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void cekTotalProductPortofolioValue() {
        validateExist("INVESTASI_KAMU_TEXT", 10);
        validateExist("PRODUCT_PORTOFOLIO_VALUE", 10);
        getProductPortofolioValue();
    }

    public void choosePaymentMethod(String paymentMethod) {
        String button = (paymentMethod.equals("Saldo")) ? "INV_SALDO_RADIO_BUTTON" : "INV_DANA_RADIO_BUTTON";
        if (!isElementClickable(button)) {
            Assert.fail("Saldo tidak mencukupi!");
        } else {
            tapElement(button);
        }
        TransactionData.setPaymentMethod(paymentMethod);
        InvestmentData.setTrxListStatus("Berhasil Dibayar");
    }

    public void getProductPortofolioValue() {
        String productPortofolioValue = getText("PRODUCT_PORTOFOLIO_VALUE");
        InvestmentData.setProductPortofolioValue(productPortofolioValue);
    }

    public void selectProductItemInstant(String productName) {
        InvestmentData.setProductNameBukaReksa(productName);
        try {
            if (!isElementExist(constructLocator("INV_BTN_PRODUCT_DETAIL_BTN", productName), 10)) {
                swipeLeftAtSpecifiedLocator("INV_BTN_PRODUCT_LIST");
            }
            tapElement(constructLocator("INV_BTN_PRODUCT_NAME", productName));
        } catch (Exception e) {
            Assert.fail("Product not exist!");
        }
        tapElement(constructLocator("INV_BTN_PRODUCT_NAME", productName));
    }

    public void tapBayarDenganChevron() {
        tapElement("INV_BAYAR_DENGAN_CHEVRON", 3);
        InvestmentData.setTrxListType("Pembelian");
    }

    public void tapProductPortofolioValue() {
        tapElement("PRODUCT_PORTOFOLIO_VALUE");
    }

    public void tapBayarBtn() {
        tapElement("INV_BTN_BAYAR");
    }

    public void tapOnDetailButton() {
        tapElement(constructLocator("INV_BTN_PRODUCT_DETAIL_BTN", InvestmentData.getProductNameBukaReksa()));
        InvestmentData.setProductTypeBukaReksa(getDetailProductType());
    }

    public void verifyInvestasiDiBukareksaPage() {
        verifyElementExist("INVESTASI_DI_BUKAREKSA_HEADER");
        verifyElementExist("INVESTASI_DI_BUKAREKSA_DETAIL");
        HelperData.setLastActionPage(new BukaReksaInstantInvestPage(iosDriver));
    }

    public void verifyAppliedInvestorSubscribeErrorDisplayed() {
        verifyElementDisplayed("APPLIED_INVESTOR_SUBSCRIBE_ERROR");
        HelperData.setLastActionPage(new BukaReksaInstantInvestPage(iosDriver));
    }

    public void verifyBWRInvestorSubscribeErrorDisplayed() {
        verifyElementDisplayed("BWR_INVESTOR_SUBSCRIBE_ERROR");
        HelperData.setLastActionPage(new BukaReksaInstantInvestPage(iosDriver));
    }

    private String getDetailProductType() {
        validateExist("PROD_DETAIL_HEADER_PRODUCT_TYPE", 5);
        String[] productDetailArray = getText("PROD_DETAIL_HEADER_PRODUCT_TYPE").split("-", 2);
        return productDetailArray[0].trim();
    }

    public void verifyDANAOptionInactive(){
        tapElement("INV_BAYAR_DENGAN_CHEVRON", 3);
        validateExist("INV_DANA_RADIO_BUTTON_INACTIVE", 5);
        HelperData.setLastActionPage(new BukaReksaInstantInvestPage(iosDriver));
    }

    public void tapTopupDanaButton(){
        tapElement("INV_BAYAR_DENGAN_CHEVRON", 5);
        tapElement("INV_DANA_RADIO_BUTTON_TOPUP", 10);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
