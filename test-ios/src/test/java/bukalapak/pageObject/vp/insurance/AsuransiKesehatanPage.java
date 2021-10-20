package bukalapak.pageObject.vp.insurance;

import bukalapak.data.HelperData;
import bukalapak.pageObject.VpBasePage;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

/**
 * Created by Yuda Baskara h.p on 12/10/2020.
 */
public class AsuransiKesehatanPage extends VpBasePage {
    public AsuransiKesehatanPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnPage() {
        if (isElementExist("ASURANSI_KESEHATAN_COACHMARK_BUTTON", 10)) {
            tapElement("ASURANSI_KESEHATAN_COACHMARK_BUTTON");
        } else {
            delay(3000); //delay animation loading
            validateExist("ASURANSI_KESEHATAN_LANDING_PAGE_HEADER_TEXT", 10, "Landing page can't be loaded");
            validateExist("ASURANSI_KESEHATAN_HISTORY_TRANSACTION_BUTTON");
        }
        HelperData.setLastActionPage(new AsuransiKesehatanPage(iosDriver));
    }

    public void tapOnListOfProductButton() {
        tapElement("ASURANSI_KESEHATAN_LIHAT_PILIHAN_PRODUK_BUTTON");
    }

    public void validateListProduct() {
        verifyElementDisplayed("ASURANSI_KESEHATAN_LIST_PRODUCT");
        verifyElementDisplayed("ASURANSI_KESEHATAN_BUTTON_PRODUCT");
        HelperData.setLastActionPage(new AsuransiKesehatanPage(iosDriver));
    }

    public void tapOnProduct() {
        tapElement("ASURANSI_KESEHATAN_BUTTON_PRODUCT");
    }

    public void tapOnButtonAjukan() {
        tapElement("ASURANSI_KESEHATAN_CONFIRM_BUTTON");
    }

    public void validateButtonAjukanIsExist() {
        validateExist("ASURANSI_KESEHATAN_CONFIRM_BUTTON", 5);
    }

    public void validatePopUpInfo(String validate) {
        verifyElementDisplayed(constructLocator("ASURANSI_KESEHATAN_POP_UP_INFO", validate));
    }

    public void validateOnPengajuanPage() {
        verifyElementExist("ASURANSI_KESEHATAN_INFO_PREMI_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_TIME_PERIOD_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_RADIO_BUTTON");
    }

    public void validateOnBankAccountPage() {
        verifyElementExist("ASURANSI_KESEHATAN_AKUN_BANK_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_SELANJUTNYA_BUTTON");
    }

    public void tapOnButtonSelanjutnya() {
        tapElement("ASURANSI_KESEHATAN_SELANJUTNYA_BUTTON");
    }

    public void typeOnFieldName(String text) {
        String name = generateFullName();

        if (text.equals("clear") || text.equals("edit")) {
            typeAndEnterValue("ASURANSI_KESEHATAN_NAMA_LENGKAP_TEXT", "");
        }

        typeAndEnterValue("ASURANSI_KESEHATAN_NAMA_LENGKAP_TEXT", name);
    }

    public void setOnBirthDate() {
        tapElement("ASURANSI_KESEHATAN_DROPDOWN_TGL_LAHIR");
        tapElement("ASURANSI_KESEHATAN_TERAPKAN_BUTTON");
    }

    public void typeOnFieldIdentity(String text) {
        long identityNumber = generateIdentityNumber();

        if (text.equals("clear") || text.equals("edit")) {
            delay(1000); //stabilize to input form
            typeAndEnterValue("ASURANSI_KESEHATAN_KTP_TEXT", "");
        }

        typeAndEnterValue("ASURANSI_KESEHATAN_KTP_TEXT", String.valueOf(identityNumber));
    }

    public void setOnGender() {
        tapElement("ASURANSI_KESEHATAN_DROPDOWN_GENDER_FIELD");
        tapElement(constructLocator("ASURANSI_KESEHATAN_GENDER_OPTION", generateGender()));
    }

    public void typeOnFieldAddress() {
        if (!isElementExist("ASURANSI_KESEHATAN_SELANJUTNYA_BUTTON")) {
            swipeDownToElement("ASURANSI_KESEHATAN_ADDRESS_FIELD");
            validateDisplayed("ASURANSI_KESEHATAN_ADDRESS_FIELD");
        }
            waitForVisibilityOf("ASURANSI_KESEHATAN_ADDRESS_FIELD", 10);
            tapElement("ASURANSI_KESEHATAN_ADDRESS_FIELD");
            typeAndEnterValue("ASURANSI_KESEHATAN_ADDRESS_FIELD", "Jalan Perjuangan");
    }

    public void setOnProvince() {
        if (!isElementExist("ASURANSI_KESEHATAN_SELANJUTNYA_BUTTON")) {
            swipeDownToElement("ASURANSI_KESEHATAN_DROPDOWN_PROVINSI_FIELD");
            tapElement("ASURANSI_KESEHATAN_DROPDOWN_PROVINSI_FIELD");
            tapElement("ASURANSI_KESEHATAN_LIST_OF_PROVINCE_OPTION");
        } else {
            tapElement("ASURANSI_KESEHATAN_DROPDOWN_PROVINSI_FIELD");
            tapElement("ASURANSI_KESEHATAN_LIST_OF_PROVINCE_OPTION");
        }
    }

    public void setOnCity() {
        if (!isElementExist("ASURANSI_KESEHATAN_SELANJUTNYA_BUTTON")) {
            swipeDownToElement("ASURANSI_KESEHATAN_DROPDOWN_KOTA_FIELD");
            tapElement("ASURANSI_KESEHATAN_DROPDOWN_KOTA_FIELD");
            tapElement("ASURANSI_KESEHATANN_LIST_OF_CITY_OPTION");
        } else {
            waitForVisibilityOf("ASURANSI_KESEHATAN_DROPDOWN_KOTA_FIELD", 10);
            tapElement("ASURANSI_KESEHATAN_DROPDOWN_KOTA_FIELD");
            tapElement("ASURANSI_KESEHATANN_LIST_OF_CITY_OPTION");
        }
    }

    public void typeOnPostalCode() {
        String postalNumber = fakerZipCode();

        if (!isElementExist("ASURANSI_KESEHATAN_SELANJUTNYA_BUTTON")) {
            swipeDownToElement("ASURANSI_KESEHATAN_KODEPOS_TEXT");
        }
        waitForVisibilityOf("ASURANSI_KESEHATAN_KODEPOS_TEXT", 10);
            tapElement("ASURANSI_KESEHATAN_KODEPOS_TEXT");
            typeAndEnterValue("ASURANSI_KESEHATAN_KODEPOS_TEXT", "");
            typeAndEnterValue("ASURANSI_KESEHATAN_KODEPOS_TEXT", postalNumber);

    }

    public void typeOnPhoneNumber() {
        long phoneNumber = generatePhoneNumber();

        if (!isElementExist("ASURANSI_KESEHATAN_SELANJUTNYA_BUTTON")) {
            swipeDownToElement("ASURANSI_KESEHATAN_HP_TEXT");
            typeAndEnterValue("ASURANSI_KESEHATAN_HP_TEXT", "");
            typeAndEnterValue("ASURANSI_KESEHATAN_HP_TEXT", "08" + phoneNumber);
        } else {
            typeAndEnterValue("ASURANSI_KESEHATAN_HP_TEXT", "");
            typeAndEnterValue("ASURANSI_KESEHATAN_HP_TEXT", "08" + phoneNumber);
        }
    }

    public void setOnBankAccount() {
        tapElement("ASURANSI_KESEHATAN_DROPDOWN_REK_BANK_FIELD");
        tapElement("ASURANSI_KESEHATAN_BANK_NAME_OPTION");
    }

    public void typeOnAccountNumber(String flag) {
        if (flag.equals("valid")) {
            typeAndEnterValue("ASURANSI_KESEHATAN_REKENING_TEXT", "4567801021");
        } else {
            typeAndEnterValue("ASURANSI_KESEHATAN_REKENING_TEXT", "");
        }
    }

    public void typeOnAccountName(String flag) {
        String name = generateFullName();

        if (flag.equals("valid")) {
            typeAndEnterValue("ASURANSI_KESEHATAN_NAMA_REKENING_TEXT", name);
        } else {
            typeAndEnterValue("ASURANSI_KESEHATAN_NAMA_REKENING_TEXT", "");
        }
    }

    public void validateOnIdentityPage() {
        waitForVisibilityOf("ASURANSI_KESEHATAN_IDENTITY_HEADER_TEXT",10);
        validateDisplayed("ASURANSI_KESEHATAN_IDENTITY_HEADER_TEXT");
        validateDisplayed("ASURANSI_KESEHATAN_IDENTITY_INFO_TEXT");
        validateDisplayed("ASURANSI_KESEHATAN_SAVE_BUTTON");
    }

    public void tapOnHistoryButton() {
        tapElement("ASURANSI_KESEHATAN_HISTORY_TRANSACTION_BUTTON");
    }

    public void validateOnHistoryPage() {
        if (isElementExist("ASURANSI_KESEHATAN_BUTTON_CHOOSE_PRODUCT")) {
            verifyElementDisplayed("ASURANSI_KESEHATAN_BUTTON_CHOOSE_PRODUCT");
        }
        verifyElementDisplayed("ASURANSI_KESEHATAN_HISTORY_INSURANCE_TEXT");
    }

    /**
    * Validate an error message on Asuransi Kesehatan field
    * @param message based on the form title (e.g.: No. Rekening)
    */
    public void validateOnErrorField(String message) {
        validateDisplayed(constructLocator("ASURANSI_KESEHATAN_ERROR_MESSAGE_TEXT", message), "wrong text input");
    }

    public void tapOnButtonLihatPilihanProduct() {
        tapElement("ASURANSI_KESEHATAN_LANDING_PAGE_LIHAT_PILIHAN_PRODUCT_BUTTON");
    }

    public void validateProductListPage(boolean isDisplayed) {
        if (isDisplayed) {
            verifyElementExist("ASURANSI_KESEHATAN_LIST_BELI_BUTTON");
            verifyElementExist("ASURANSI_KESEHATAN_PARTNER_NAME_TEXT");
            verifyElementExist("ASURANSI_KESEHATAN_PRODUCT_NAME_TEXT");
            verifyElementExist("ASURANSI_KESEHATAN_LIMIT_KLAIM_TITLE_TEXT");
            verifyElementExist("ASURANSI_KESEHATAN_LIMIT_KLAIM_PRICE_TEXT");
            verifyElementExist("ASURANSI_KESEHATAN_BIAYA_PREMI_TITLE_TEXT");
            verifyElementExist("ASURANSI_KESEHATAN_BIAYA_PREMI_MONTHLY_TEXT");
            verifyElementExist("ASURANSI_KESEHATAN_BIAYA_PREMI_ANNUALLY_TEXT");
            verifyElementExist("ASURANSI_KESEHATAN_BENEFIT_TITLE_TEXT");
            verifyElementExist("ASURANSI_KESEHATAN_BENEFIT_DESCRIPTION_TEXT");
            verifyElementExist("ASURANSI_KESEHATAN_LIST_LIHAT_DETAIL_BUTTON");
            verifyElementExist("ASURANSI_KESEHATAN_PARTNER_LOGO_IMAGE");

        } else {
            validateNotDisplayed("ASURANSI_KESEHATAN_LIST_BELI_BUTTON","Product List Page have not displayed");
        }
    }

    public void tapOnButtonLihatDetailProduct() {
        tapElement("ASURANSI_KESEHATAN_LIST_LIHAT_DETAIL_BUTTON");
    }

    public void validateProductDetailPageSection() {
        verifyElementExist("ASURANSI_KESEHATAN_PRODUCT_NAME_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_LIMIT_KLAIM_TITLE_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_BIAYA_PREMI_TITLE_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_BENEFIT_TITLE_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_PARTNER_LOGO_IMAGE");
    }

    public void validateDeskripsiProdukSection() {
        verifyElementExist("ASURANSI_KESEHATAN_DETAIL_DESKRIPSI_PRODUK_TITLE_TEXT");
        tapElement("ASURANSI_KESEHATAN_DETAIL_DESKRIPSI_PRODUK_TITLE_TEXT");
    }

    public void validateSyaratKeikutsertaanSection() {
        swipeUpToElement("ASURANSI_KESEHATAN_DETAIL_AJUKAN_BUKA_ASURANSI_BUTTON");
        tapElement("ASURANSI_KESEHATAN_DETAIL_SYARAT_KEIKUTSERTAAN_TITLE_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_DETAIL_SYARAT_KEIKUTSERTAAN_DESC");
    }

    public void validateMasaTungguSection() {
        swipeUpToElement("ASURANSI_KESEHATAN_DETAIL_AJUKAN_BUKA_ASURANSI_BUTTON");
        tapElement("ASURANSI_KESEHATAN_DETAIL_MASA_TUNGGU_TITLE_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_DETAIL_MASA_TUNGGU_TITLE_DESC");
    }

    public void validateKlaimSection() {
        swipeUpToElement("ASURANSI_KESEHATAN_DETAIL_AJUKAN_BUKA_ASURANSI_BUTTON");
        tapElement("ASURANSI_KESEHATAN_DETAIL_KLAIM_TITLE_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_DETAIL_KLAIM_TITLE_DESC");
    }

    public void validatePengecualianProteksiSection() {
        swipeUpToElement("ASURANSI_KESEHATAN_DETAIL_AJUKAN_BUKA_ASURANSI_BUTTON");
        tapElement("ASURANSI_KESEHATAN_DETAIL_PENGECUALIAN_PROTEKSI_TITLE_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_DETAIL_PENGECUALIAN_PROTEKSI_TITLE_DESC");
}

    public void validateKewajibanPemegangPolisSection() {
        swipeUpToElement("ASURANSI_KESEHATAN_DETAIL_AJUKAN_BUKA_ASURANSI_BUTTON");
        tapElement("ASURANSI_KESEHATAN_DETAIL_KEWAJIBAN_PEMEGANG_POLIS_TITLE_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_DETAIL_KEWAJIBAN_PEMEGANG_POLIS_TITLE_DESC");
    }

    public void validateCatatanPentingSection() {
        swipeUpToElement("ASURANSI_KESEHATAN_DETAIL_AJUKAN_BUKA_ASURANSI_BUTTON");
        tapElement("ASURANSI_KESEHATAN_DETAIL_CATATAN_PENTING_TITLE_TEXT");
        swipeUpToElement("ASURANSI_KESEHATAN_DETAIL_AJUKAN_BUKA_ASURANSI_BUTTON");
        verifyElementExist("ASURANSI_KESEHATAN_DETAIL_CATATAN_PENTING_TITLE_DESC");
    }

    public void validateProductSection() {
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_PRODUCT_TITLE_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_PRODUCT_DESCRIPTION_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_PRODUCT_LOGO");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_PARTNER_LOGO");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_LIHAT_PILIHAN_PRODUCT_BUTTON");
    }

    public void validatePromoSection() {
        if (isElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_PROMO_IMAGE")){
        } else {
            verifyElementNotExist("ASURANSI_KESEHATAN_LANDING_PAGE_PROMO_IMAGE");
            LogUtil.info("Promo tidak ada");
        }
    }

    public void validateBenefitSection() {
        swipeUpToElement("ASURANSI_KESEHATAN_LANDING_PAGE_BLOG_HEADER_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_BENEFIT_TITLE_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_BENEFIT_PERTAMA_IMAGE");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_BENEFIT_PERTAMA_TITLE_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_BENEFIT_KEDUA_IMAGE");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_BENEFIT_KEDUA_TITLE_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_BENEFIT_KETIGA_IMAGE");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_BENEFIT_KETIGA_TITLE_TEXT");
    }

    public void validateBlogSection() {
        swipeUpToElement("ASURANSI_KESEHATAN_LANDING_PAGE_LOGO_OJK_IMAGE");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_BLOG_HEADER_TEXT");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_BLOG_PERTAMA_IMAGE");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_BLOG_KEDUA_IMAGE");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_BLOG_KETIGA_IMAGE");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_BLOG_PERTAMA_TITLE");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_BLOG_KEDUA_TITLE");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_BLOG_KETIGA_TITLE");
        verifyElementExist("ASURANSI_KESEHATAN_LANDING_PAGE_LIHAT_ARTIKEL_LAIN_BUTTON");
    }

    public void tapOnImageArtikel() {
       tapElement("ASURANSI_KESEHATAN_LANDING_PAGE_BLOG_PERTAMA_IMAGE");
    }

    public void isOnBukaReviewPage() {
        validateExist("HEALTH_INSURANCE_BUKAREVIEW_BLOG_TITLE_TEXT", 10);
    }

    public void tapOnButtonLihatArtikelLain() {
        swipeUpToElement("ASURANSI_KESEHATAN_LANDING_PAGE_LOGO_OJK_IMAGE");
        tapElement("ASURANSI_KESEHATAN_LANDING_PAGE_LIHAT_ARTIKEL_LAIN_BUTTON");
    }

    public void validateOjkSection() {
        swipeUpToElement("ASURANSI_KESEHATAN_LANDING_PAGE_LOGO_OJK_IMAGE");
        validateExist("ASURANSI_KESEHATAN_LANDING_PAGE_LOGO_OJK_IMAGE", 10);
    }

    public void validateSectionPage(String section){

        switch (section.toLowerCase()) {
            case "product":
                validateProductSection();
                break;
            case "promo":
                validatePromoSection();
                break;
            case "benefit":
                validateBenefitSection();
                break;
            case "blog":
                validateBlogSection();
                tapOnImageArtikel();
                isOnBukaReviewPage();
                break;
            case "artikel":
                tapOnButtonLihatArtikelLain();
                isOnBukaReviewPage();
                break;
            case "ojk":
                validateOjkSection();
                break;
            default:
                Assert.fail("Invalid Section");
                break;
        }
    }
}
