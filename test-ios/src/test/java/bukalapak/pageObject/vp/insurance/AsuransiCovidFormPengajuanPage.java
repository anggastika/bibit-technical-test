package bukalapak.pageObject.vp.insurance;

import bukalapak.data.HelperData;
import bukalapak.data.InsuranceData;
import bukalapak.pageObject.HomePage;
import bukalapak.pageObject.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class AsuransiCovidFormPengajuanPage extends VpBasePage {
    public AsuransiCovidFormPengajuanPage(IOSDriver<IOSElement> iosDriver) {super(iosDriver);}

    public void chooseCovidInsuranceType(String premi) {
        tapElement(constructLocator("ASURANSI_COVID_PRODUCT_BELI_BUTTON", premi));
    }

    public void setInsuranceDataCovidInsurance (String premi) {
        swipeDownToElement("ASURANSI_COVID_PRODUCT_ONE_BELI_BUTTON");
        InsuranceData.setInsuredName("Asuransi Covid");
        InsuranceData.setCovidInsurancedPrice(getElementValue(constructLocator("ASURANSI_COVID_PRODUCT_BIAYA_PREMI_TEXT", premi)).replaceAll("/orang",""));
        InsuranceData.setProductName(getElementValue(constructLocator("ASURANSI_COVID_PRODUCT_TITLE", premi)));
        InsuranceData.setValidityPeriode(premi.toLowerCase());
    }

    public void validateFormPengajuanPage() {
        validateExist("ASURANSI_COVID_MANFAAT", 15);
        validateDisplayed("ASURANSI_COVID_BIAYA_PREMI");
        validateDisplayed("ASURANSI_COVID_LIMIT_KLAIM");
        validateDisplayed("ASURANSI_COVID_PENERIMA_ASURANSI");
        validateDisplayed("ASURANSI_COVID_REMINDER");
        assertEquals("false", getElementValue("ASURANSI_COVID_CHECKBOX"));
    }

    public void choosTypeInsurance(boolean isFamily) {
        if (isFamily) {
            tapElement("ASURANSI_COVID_KELUARGA");
            selectFamilyNumber();
            typeFamilyName();
        } else {
            typeSingleName();
        }
    }

    public void selectFamilyNumber() {
        tapElement("ASURANSI_COVID_DROPDOWN_FAMILY");
        tapElement("ASURANSI_COVID_FAMILY_NUMBER");
    }

    public void typeFamilyName() {
        typeAndEnterValue("ASURANSI_COVID_FAMILY_FIELD", generateFullName());
    }

    public void typeSingleName() {
        getElementPresent("ASURANSI_COVID_SINGLE_FIELD").clear();
        typeAndEnterValue("ASURANSI_COVID_SINGLE_FIELD", generateFullName());
    }

    public void tapCheckBoxCovidInsurance() {
        tapElement("ASURANSI_COVID_CHECKBOX");
    }

    public void tapLanjutkanButtonCovidInsurance() {
        InsuranceData.setCovidInsurancedPrice(getElementValue("ASURANSI_COVID_JUMLAH_BAYAR"));
        tapElement("ASURANSI_COVID_LANJUTKAN_BUTTON");
    }

    public void validateActivePolicy() {
        validateExist("ASURANSI_COVID_USER_NOT_ELIGIBLE", 20);

        HelperData.setLastActionPage(new AsuransiCovidFormPengajuanPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
