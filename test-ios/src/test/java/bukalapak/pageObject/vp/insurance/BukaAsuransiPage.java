package bukalapak.pageObject.vp.insurance;

import bukalapak.data.InsuranceData;
import bukalapak.pageObject.BasePage;
import bukalapak.data.HelperData;
import bukalapak.pageObject.HomePage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class BukaAsuransiPage extends BasePage {

    public BukaAsuransiPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void validateOnPage() {
        verifyElementExist("BUKAASURANSI_LANDING_PAGE_SELECTION_LIST");
        HelperData.setLastActionPage(new BukaAsuransiPage(iosDriver));
    }

    public void validateSection(String section) {
        switch (section) {
            case "Promo":
                validatePromoSection();
                break;
            case "Pilihan Asuransi":
                validatePilihanAsuransiSection();
                break;
            case "Rekomendasi":
                validateRecommendationSection();
                break;
            case "Panduan Cerdas":
                validatePanduanCerdasAsuransiSection();
                break;
            case "Testimoni":
                validateTestimoniSection();
                break;
            case "Partner":
                validatePartnerSection();
                break;
            case "OJK":
                validateOjkSection();
                break;
            default:
                Assert.fail(section + " Wrong section name");
                break;
        }
    }

    public void validatePromoSection() {
        verifyElementExist("BUKAASURANSI_LANDING_PAGE_LIST_PROMO");
    }

    public void validatePilihanAsuransiSection() {
        verifyElementExist("BUKAASURANSI_LANDING_PAGE_MENU_MOTOR");
        verifyElementExist("BUKAASURANSI_LANDING_PAGE_MENU_BEPERGIAN");
        verifyElementExist("BUKAASURANSI_LANDING_PAGE_MENU_KESEHATAN");
        verifyElementExist("BUKAASURANSI_LANDING_PAGE_MENU_TAMBAHAN");
    }

    public void validateRecommendationSection() {
        verifyElementExist("BUKAASURANSI_RECOMENDATION_SECTION");
        verifyElementExist("BUKAASURANSI_RECOMENDATION_SECTION_TITLE");
        verifyElementExist("BUKAASURANSI_RECOMENDATION_SECTION_IMAGE");
        verifyElementExist("BUKAASURANSI_RECOMENDATION_SECTION_DESCRIPTION");
        verifyElementExist("BUKAASURANSI_RECOMENDATION_SECTION_LIMIT_TITLE");
        verifyElementExist("BUKAASURANSI_RECOMENDATION_SECTION_LIMIT_VALUE");
        verifyElementExist("BUKAASURANSI_RECOMENDATION_SECTION_PREMIUM_TITLE");
        verifyElementExist("BUKAASURANSI_RECOMENDATION_SECTION_PREMIUM_VALUE");
        verifyElementExist("BUKAASURANSI_RECOMENDATION_SECTION_BUTTON_DETAIL");
    }

    public void validatePanduanCerdasAsuransiSection() {
        swipeUpToElement("BUKAASURANSI_LANDING_PARTNER_SECTION_TITLE");
        verifyElementExist("BUKAASURANSI_LANDING_PAGE_PANDUAN_CERDAS_SECTION");
        verifyElementExist("BUKAASURANSI_LANDING_PAGE_PANDUAN_CERDAS_TITLE");
        verifyElementExist("BUKAASURANSI_LANDING_PAGE_PANDUAN_CERDAS_DESCRIPTION");
        verifyElementExist("BUKAASURANSI_LANDING_PAGE_PANDUAN_CERDAS_BUTTON");
        verifyElementExist("BUKAASURANSI_LANDING_PAGE_PANDUAN_CERDAS_LOGO");
    }

    public void validateTestimoniSection() {
        swipeUpToElement("BUKAASURANSI_LANDING_PARTNER_SECTION_TITLE");
        verifyElementExist("BUKAASURANSI_TESTIMONI_TITLE");
        verifyElementExist("BUKAASURANSI_TESTIMONI_SECTION");
        verifyElementExist("BUKAASURANSI_TESTIMONI_DESCRIPTION");
        verifyElementExist("BUKAASURANSI_TESTIMONI_USER_NAME");
        verifyElementExist("BUKAASURANSI_TESTIMONI_USER_DETAIL");
        verifyElementExist("BUKAASURANSI_TESTIMONI_USER_IMAGE");
    }

    public void validatePartnerSection() {
        swipeUpToElement("BUKAASURANSI_LANDING_PAGE_OJK_LOGO");
        verifyElementExist("BUKAASURANSI_LANDING_PARTNER_SECTION_TITLE");
        verifyElementExist("BUKAASURANSI_LANDING_PARTNER_SECTION_TITLE_DETAIL");
        verifyElementExist("BUKAASURANSI_LANDING_PARTNER_LOGO");
    }

    public void validateOjkSection() {
        swipeUpToElement("BUKAASURANSI_LANDING_PAGE_OJK_LOGO");
        verifyElementExist("BUKAASURANSI_LANDING_PAGE_DIAWASI_OLEH_TEXT");
        verifyElementExist("BUKAASURANSI_LANDING_PAGE_OJK_LOGO");
    }

    public void validateAsuransiListMenu(String insuranceName) {
        switch (insuranceName) {
            case "Asuransi Rawat Jalan":
            case "Asuransi Motor":
            case "Asuransi Mobil":
            case "Asuransi Bepergian":
            case "Asuransi Kesehatan":
            case "Asuransi Pengiriman":
            case "Asuransi Tambahan":
            case "Asuransi Produk":
            case "Asuransi Perjalanan":
            case "Asuransi Sepeda":
            case "Asuransi Retur":
                int swipeCount = 0;

                while(swipeCount < 5 && !isElementVisible(constructLocator("BUKAASURANSI_LANDING_PAGE_MENU_TEXT", insuranceName))) {
                    swipeLeftAtSpecifiedLocator("BUKAASURANSI_LANDING_PAGE_MENU_TAP_AREA");
                    swipeCount++;
                }
                verifyElementExist(constructLocator("BUKAASURANSI_LANDING_PAGE_MENU_TEXT", insuranceName));
                verifyElementExist(constructLocator("BUKAASURANSI_LANDING_PAGE_DOPE_ICON", insuranceName));
                break;
            default:
                Assert.fail(insuranceName + "Wrong Insurance Name!");
                break;
        }
    }

    public void validateInsuranceProductDetailPage(String insuranceName) {
        changeContext().toWebview();
        if (insuranceName.equals("Asuransi Perjalanan")) {
            verifyElementExist("BUKAASURANSI_TNC_PAGE_ASURANSI_PERJALANAN");
        } else {
            verifyElementExist("BUKAASURANSI_TNC_PAGE_MANFAAT");
        }
    }

    public void validatePromoLink() {
        if (InsuranceData.isPromoActive()) {
            verifyElementExist("BUKAASURANSI_LANDING_PAGE_LIST_PROMO");
        }
    }

    public void validateFaqPage() {
        changeContext().toWebview();
        verifyElementNotExist("BUKAASURANSI_LANDING_PAGE_LIST_PROMO");
    }

    public void tapOnPromoBanner() {
        if (isElementExist("BUKAASURANSI_LANDING_PAGE_BANNER_IMAGE")) {
            tapElement("BUKAASURANSI_LANDING_PAGE_BANNER_IMAGE");
        } else {
            LogUtil.info("No Promo banner");
            InsuranceData.setPromoActive(false);
        }
    }

    public void tapInsuranceListMenu(String insuranceName) {
        tapElement(constructLocator("BUKAASURANSI_LANDING_PAGE_MENU_TEXT", insuranceName));
    }

    public void tapOnPanduanCerdasAsuransi() {
        tapElement("BUKAASURANSI_LANDING_PAGE_PANDUAN_CERDAS_BUTTON");
    }
}
