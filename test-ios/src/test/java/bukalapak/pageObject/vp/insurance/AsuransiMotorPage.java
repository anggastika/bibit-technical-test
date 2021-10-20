package bukalapak.pageObject.vp.insurance;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import bukalapak.data.InsuranceData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.util.NoSuchElementException;

import static org.junit.Assert.fail;

/**
 * Created by Aditya Putra h.p on 27/09/2020.
 */
public class AsuransiMotorPage extends BasePage {

    public AsuransiMotorPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateOnPage() {
        changeContext().toWebview();
        verifyElementExist("ASURANSI_MOTOR_LANDING_PAGE_HEADER_TEXT");
        verifyElementExist("ASURANSI_MOTOR_LANDING_PAGE_SEE_DETAIL_BUTTON");
        verifyElementExist("ASURANSI_MOTOR_LANDING_PAGE_PLAN_CARD");
        verifyElementExist("ASURANSI_MOTOR_LANDING_PAGE_PLAN_NAME_TEXT");
        verifyElementExist("ASURANSI_MOTOR_LANDING_PAGE_PLAN_CLAIM_LIMIT_TEXT");
        verifyElementExist("ASURANSI_MOTOR_LANDING_PAGE_PLAN_INSURED_TIME_TEXT");
        verifyElementExist("ASURANSI_MOTOR_LANDING_PAGE_PLAN_PRICE_TEXT");
        verifyElementExist("ASURANSI_MOTOR_LANDING_PAGE_CHOOSE_BUTTON");
        HelperData.setLastActionPage(new AsuransiMotorPage(iosDriver));
    }

    public void tapOnBuyButton() {
        tapElement("ASURANSI_MOTOR_DETAIL_PAGE_BUY_BUTTON");
    }

    public void validatePopUpModalShown() {
        verifyElementExist("ASURANSI_MOTOR_DETAIL_PAGE_MODAL_TITLE_TEXT");
        verifyElementExist("ASURANSI_MOTOR_DETAIL_PAGE_MODAL_CONTINUE_BUTTON");
    }

    public void validateOnRegistrationPage() {
        verifyElementExist("ASURANSI_MOTOR_REGISTRATION_PAGE_PERSONAL_INFO_TITLE_TEXT");
    }

    public void inputInsuredName() {
        String name = fakerUtil().getFakeName();

        InsuranceData.setInsuredName(name);
        typeValue("ASURANSI_MOTOR_REGISTRATION_PAGE_NAME_INPUT", name);
    }

    public void setInsuredBirthday() {
        tapElement("ASURANSI_MOTOR_REGISTRATION_PAGE_BIRTHDAY_INPUT");
        verifyElementExist("ASURANSI_MOTOR_REGISTRATION_PAGE_BIRTHDAY_PICKER");
        verifyElementExist("ASURANSI_MOTOR_REGISTRATION_PAGE_BIRTHDAY_PICKER_BUTTON");
        tapElement("ASURANSI_MOTOR_REGISTRATION_PAGE_BIRTHDAY_PICKER_BUTTON");
    }

    public void inputInsuredEmail(boolean isValid) {
        String email = isValid? fakerEmail() : "wrongemail@";

        InsuranceData.setInsuredEmail(email);
        typeValue("ASURANSI_MOTOR_REGISTRATION_PAGE_EMAIL_INPUT", email);
    }

    public void inputInsuredPhoneNumber(boolean isValid) {
        String phoneNumber = isValid? fakerPhoneNumber("0812", 8) : "1234";

        InsuranceData.setInsuredPhone(phoneNumber);
        typeValue("ASURANSI_MOTOR_REGISTRATION_PAGE_PHONE_INPUT", phoneNumber);
    }

    public void inputChassisMotor() {
        String chassisMotor = TestInstrument.dotenv.get("MOTOR_NIK_NUMBER");

        InsuranceData.setChassisMotor(chassisMotor);
        typeValue("ASURANSI_MOTOR_REGISTRATION_PAGE_CHASSIS_MOTOR_INPUT", chassisMotor);
    }

    public void inputPlateMotor(boolean isValid) {
        String plateMotor = isValid? TestInstrument.dotenv.get("MOTOR_PLATE_NUMBER") : "111";

        InsuranceData.setPlateNumberMotor(plateMotor);
        typeValue("ASURANSI_MOTOR_REGISTRATION_PAGE_PLATE_MOTOR_INPUT", plateMotor);
    }

    public void setBrandMotor() {
        String brandMotor = TestInstrument.dotenv.get("MOTOR_BRAND_NAME");

        InsuranceData.setBrandMotor(brandMotor);
        tapElement("ASURANSI_MOTOR_REGISTRATION_PAGE_BRAND_MOTOR_DROPDOWN");
        tapElement(constructLocator("ASURANSI_MOTOR_REGISTRATION_PAGE_SELECT_OPTION", brandMotor));
    }

    public void setModelMotor() {
        String modelMotor = TestInstrument.dotenv.get("MOTOR_MODEL_NAME");

        InsuranceData.setModelMotor(modelMotor);
        tapElement("ASURANSI_MOTOR_REGISTRATION_PAGE_MODEL_MOTOR_DROPDOWN");
        tapElement(constructLocator("ASURANSI_MOTOR_REGISTRATION_PAGE_SELECT_OPTION", modelMotor));
    }

    public void validateErrorMessageRequiredInput(String field) {
        try {
            assertTextContains("Wajib diisi",getTextFromElement(constructLocator("ASURANSI_MOTOR_REGISTRATION_PAGE_ERROR_MESSAGE_TEXT", field)));
        } catch (NoSuchElementException e) {
            fail("Please check the field " +field+ " , make sure the format is correct (lowercase/uppercase)");
        }
    }

    public void validateInvalidEmail() {
        validateValue().contains("Format email tidak sesuai.",
                getText("ASURANSI_MOTOR_REGISTRATION_PAGE_EMAIL_ERROR_MESSAGE_TEXT"));
    }

    public void validateInvalidPhone() {
        validateValue().contains("Nomor harus terdiri dari min. 10 digit.",
                getText("ASURANSI_MOTOR_REGISTRATION_PAGE_HP_ERROR_MESSAGE_TEXT"));
    }

    public void setPlanPrice() {
        waitForVisibilityOf("ASURANSI_MOTOR_LANDING_PAGE_PLAN_PRICE_TEXT");
        InsuranceData.setInsurancePrice(getIntFromRp(getText("ASURANSI_MOTOR_LANDING_PAGE_PLAN_PRICE_TEXT", 0)));
    }

    public void setPlanName() {
        InsuranceData.setProductName(getText("ASURANSI_MOTOR_LANDING_PAGE_PLAN_NAME_TEXT", 0));
    }

    public void setLimitClaim() {
        InsuranceData.setLimitClaim(getText("ASURANSI_MOTOR_LANDING_PAGE_PLAN_CLAIM_LIMIT_TEXT", 0));
    }

    public void tapOnContinueButton() {
        tapElement("ASURANSI_MOTOR_DETAIL_PAGE_MODAL_CONTINUE_BUTTON");
    }

    public void tapOnChoosePlanButton() {
        tapElement("ASURANSI_MOTOR_LANDING_PAGE_CHOOSE_BUTTON", 0);
    }

    public void validateOnDetailPage() {
        verifyElementExist("ASURANSI_MOTOR_DETAIL_PAGE_HEADER_PLAN_NAME_TEXT");
        verifyElementExist("ASURANSI_MOTOR_DETAIL_PAGE_HEADER_CLAIM_LIMIT_TEXT");
        verifyElementExist("ASURANSI_MOTOR_DETAIL_PAGE_PLAN_PRICE_TEXT");
        verifyElementExist("ASURANSI_MOTOR_DETAIL_PAGE_BUY_BUTTON");
    }

    public void tapOnNextButton() {
        tapElement("ASURANSI_MOTOR_REGISTRATION_PAGE_CONTINUE_BUTTON");
    }

    public void tapOnSeeDetailButton() {
        tapElement("ASURANSI_MOTOR_LANDING_PAGE_SEE_DETAIL_BUTTON");
    }

    public void validateDetailModalDisplayed() {
        verifyElementExist("ASURANSI_MOTOR_LANDING_PAGE_DETAIL_TITLE_TEXT");
        verifyElementExist("ASURANSI_MOTOR_LANDING_PAGE_DETAIL_BEBAS_PANIK_TEXT");
        verifyElementExist("ASURANSI_MOTOR_LANDING_PAGE_DETAIL_PENGGANTIAN_TEXT");
        verifyElementExist("ASURANSI_MOTOR_LANDING_PAGE_DETAIL_KLAIM_TEXT");
    }

    public void tapOnSeePackageButton() {
        tapElement("ASURANSI_MOTOR_LANDING_PAGE_DETAIL_SEE_PACKAGE_BUTTON");
    }

    public void validateOnDetailPurchasedPage() {
        verifyElementExist("ASURANSI_MOTOR_DETAIL_PURCHASED_PAGE_PLAN_NAME_TEXT");
        verifyElementExist("ASURANSI_MOTOR_DETAIL_PURCHASED_PAGE_CLAIM_LIMIT_TEXT");
        verifyElementExist("ASURANSI_MOTOR_DETAIL_PURCHASED_PAGE_PLAN_PRICE_TEXT");
    }

    public void validatePlanInfo() {
        assertEquals(InsuranceData.getProductName(),
                getText("ASURANSI_MOTOR_DETAIL_PURCHASED_PAGE_PLAN_NAME_TEXT"));
        assertEquals(InsuranceData.getInsurancePrice(),
                getIntFromRp(getText("ASURANSI_MOTOR_DETAIL_PURCHASED_PAGE_PLAN_PRICE_TEXT")));
        assertEquals(InsuranceData.getLimitClaim(),
                getText("ASURANSI_MOTOR_DETAIL_PURCHASED_PAGE_CLAIM_LIMIT_TEXT"));
    }

    public void validatePersonalInfo() {
        assertEquals(InsuranceData.getInsuredName(),
                getText("ASURANSI_MOTOR_DETAIL_PURCHASED_PAGE_NAMA_TEXT"));
        assertEquals(InsuranceData.getInsuredEmail(),
                getText("ASURANSI_MOTOR_DETAIL_PURCHASED_PAGE_EMAIL_TEXT"));
        assertEquals(InsuranceData.getInsuredPhone(),
                getText("ASURANSI_MOTOR_DETAIL_PURCHASED_PAGE_HP_TEXT"));
    }

    public void validateMotorInfo() {
        assertEquals(InsuranceData.getChassisMotor(),
                getText("ASURANSI_MOTOR_DETAIL_PURCHASED_PAGE_NO_RANGKA_TEXT"));
        assertEquals(InsuranceData.getPlateNumberMotor(),
                getText("ASURANSI_MOTOR_DETAIL_PURCHASED_PAGE_PLAT_TEXT"));
        assertEquals(InsuranceData.getBrandMotor(),
                getText("ASURANSI_MOTOR_DETAIL_PURCHASED_PAGE_MEREK_TEXT"));
        assertEquals(InsuranceData.getModelMotor(),
                getText("ASURANSI_MOTOR_DETAIL_PURCHASED_PAGE_TIPE_TEXT"));
    }

    public void tapOnTNCCheckbox() {
        swipeUp(1);
        tapElement("ASURANSI_MOTOR_DETAIL_PURCHASED_PAGE_TNC_CHECKBOX");
    }

    public void tapOnContinueToPaymentButton() {
        waitForElementClickable("ASURANSI_MOTOR_DETAIL_PURCHASED_PAGE_CONTINUE_TO_PAY_BUTTON", 10);
        tapElement("ASURANSI_MOTOR_DETAIL_PURCHASED_PAGE_CONTINUE_TO_PAY_BUTTON");
    }

    public void validatePurchasedPlan() {
        assertEquals(InsuranceData.getProductName(),
                getText("ASURANSI_MOTOR_INVOICE_DETAIL_PAKET_ASURANSI_TEXT"));
        assertEquals(InsuranceData.getInsuredName(),
                getText("ASURANSI_MOTOR_INVOICE_DETAIL_NAMA_LENGKAP_TEXT"));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}