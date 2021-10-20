package bukalapak.pageObject.investment;

import bukalapak.data.HelperData;
import bukalapak.data.InvestmentData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class BukaReksaPackagePage extends BasePage {

    public BukaReksaPackagePage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateReksaPackage() {
        validateExist("REXA_PACKAGE_DETAIL");
        validateExist("REXA_PACKAGE_RETURN");
        HelperData.setLastActionPage(new BukaReksaPackagePage(iosDriver));
    }

    public void tapOnPackageName(String pkgName) {
        int swipeCount = 10;
        InvestmentData.setPackageNameBukareksa(pkgName);
        while (!isElementExist(constructLocator("REXA_PACKAGE_NAME", pkgName), 3) && swipeCount > 0) {
            swipeLeftAtSpecifiedLocator("REXA_PACKAGE_SECTION");
            swipeCount--;
        }
        tapElement(constructLocator("REXA_PACKAGE_NAME", pkgName));
    }

    private String getPackageNameText() {
        return getElementValue("REXA_PKG_NAME");
    }

    public void verifyPackageDetailPage() {
        validateValue().contains(InvestmentData.getPackageNameBukareksa(), getPackageNameText());
        validateExist("REXA_PKG_DETAILS", 5);
        validateExist("REXA_PKG_PROD_TITLE", 3);
        validateExist("REXA_PKG_PURCHASE_DETAIL", 2);
        validateExist("REXA_PKG_RETURN_INFO", 2);
        validateExist("REXA_PKG_DISCLAIMER_CHECKBOX", 2);
        InvestmentData.setFromBukaReksaPackage(true);
    }

    public void tapOnLanjutkanButton() {
        swipeUpToElement("REXA_PKG_LANJUTKAN_BTN", 3);
        tapElement("REXA_PKG_LANJUTKAN_BTN");
    }

    public void tapOnBukaemasProduct() {
        tapElement("REXA_PKG_EMAS_PRODUCT");
    }

    public void validateBukaemasWebviewOnlyPage() {
        verifyElementExist("REXA_PKG_BUKAEMAS_VIEW_ONLY_TITLE");
        verifyElementExist("REXA_PKG_BUKAEMAS_VIEW_ONLY_PERKIRAAN_SALDO");
        verifyElementExist("REXA_PKG_BUKAEMAS_VIEW_ONLY_KEUNTUNGAN");
        verifyElementExist("REXA_PKG_BUKAEMAS_VIEW_ONLY_GRAFIK_HARGA_BELI");
        verifyElementExist("REXA_PKG_BUKAEMAS_VIEW_ONLY_GRAFIK_HARGA_JUAL");
        HelperData.setLastActionPage(new BukaReksaPackagePage(iosDriver));
    }

    public void backFromBukaemasWebviewOnly() {
        tapElement("REXA_PKG_BUKAEMAS_VIEW_ONLY_BACK");
        HelperData.setLastActionPage(new BukaReksaPackagePage(iosDriver));
    }

    public void tapOnBukaEmasTransactionDetailLink() {
        tapElement("REXA_PKG_BUKAEMAS_TRANSACTION_LINK");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
