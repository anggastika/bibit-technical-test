package bukalapak.pageObject.vp.tix.tiketpesawat;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import bukalapak.data.vp.tix.TiketPesawatData;
import bukalapak.pageObject.VpBasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import java.util.Calendar;

public class TiketPesawatBookingPage extends VpBasePage {
    public TiketPesawatBookingPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void tapOnEditBuyerInfo() {
        waitForVisibilityOf("VP_PESAWAT_BOOKING_UBAH_LABEL");
        tapElement("VP_PESAWAT_BOOKING_UBAH_LABEL");
    }

    public void editBuyerName(boolean isValid) {
        if (isValid) {
            String buyerName = generateFullName();

            waitForVisibilityOf("VP_PESAWAT_BOOKING_UBAH_KONTAK_PEMESAN_NAMA_LENGKAP");
            typeAndEnterValue("VP_PESAWAT_BOOKING_UBAH_KONTAK_PEMESAN_NAMA_LENGKAP", buyerName);
            TiketPesawatData.setBuyerName(buyerName);
        } else {
            typeAndEnterValue("VP_PESAWAT_BOOKING_UBAH_KONTAK_PEMESAN_NAMA_LENGKAP", "P@lsk1");
        }
    }

    public void editBuyerEmail(boolean isValid) {
        if (isValid) {
            String buyerEmail = TiketPesawatData.getBuyerName().toLowerCase().replace(" ", "") + "@gmail.com";

            typeAndEnterValue("VP_PESAWAT_BOOKING_UBAH_KONTAK_PEMESAN_EMAIL", buyerEmail);
            TiketPesawatData.setBuyerEmail(buyerEmail);
        }
        else {
            typeAndEnterValue("VP_PESAWAT_BOOKING_UBAH_KONTAK_PEMESAN_EMAIL", "email");
        }
    }

    public void editBuyerPhoneNumber(boolean isValid) {
        if (isValid) {
            typeAndEnterValue("VP_PESAWAT_BOOKING_UBAH_KONTAK_PEMESAN_NOMOR_HANDPHONE", TestInstrument.dotenv.get("TIKET_PESAWAT_BUYER_PHONE_NUMBER"));
            TiketPesawatData.setBuyerPhoneNumber(TestInstrument.dotenv.get("TIKET_PESAWAT_BUYER_PHONE_NUMBER"));
        } else {
            typeAndEnterValue("VP_PESAWAT_BOOKING_UBAH_KONTAK_PEMESAN_NOMOR_HANDPHONE", "123456");
        }
    }

    public void tapOnSaveButton() {
        tapElement("VP_PESAWAT_SIMPAN_LABEL");
    }

    public void validateIncorrectEmailFormatError() {
        waitForVisibilityOf(constructLocator("VP_PESAWAT_GENERAL_CONTAINS_LABEL", TiketPesawatData.invalidEmailFormatError), 3);
        verifyElementExist(constructLocator("VP_PESAWAT_GENERAL_CONTAINS_LABEL", TiketPesawatData.invalidEmailFormatError));
    }

    public void validateInvalidNameFormatError() {
        waitForVisibilityOf(constructLocator("VP_PESAWAT_GENERAL_CONTAINS_LABEL", TiketPesawatData.invalidNameFormatError),3);
        verifyElementExist(constructLocator("VP_PESAWAT_GENERAL_CONTAINS_LABEL", TiketPesawatData.invalidNameFormatError));
    }

    public void validateMinLengthPhoneError() {
        waitForVisibilityOf(constructLocator("VP_PESAWAT_GENERAL_CONTAINS_LABEL", TiketPesawatData.minLengthPhoneError), 3);
        verifyElementExist(constructLocator("VP_PESAWAT_GENERAL_CONTAINS_LABEL", TiketPesawatData.minLengthPhoneError));
        HelperData.setLastActionPage(new TiketPesawatBookingPage(iosDriver));
    }

    public void validateOnPage() {
        waitForVisibilityOf("VP_PESAWAT_DETAIL_PESANAN_LABEL", 10);
        verifyElementExist("VP_PESAWAT_DETAIL_PESANAN_LABEL");
        verifyElementExist("VP_PESAWAT_CONTAIN_SISA_WAKTU_LABEL");
    }

    public void validateBuyerData() {
        waitForVisibilityOf("VP_PESAWAT_BOOKING_NAMA_PEMESAN", 5);
        validateValue().contains(TiketPesawatData.getBuyerName(), getText("VP_PESAWAT_BOOKING_NAMA_PEMESAN"));
        validateValue().equals(TiketPesawatData.getBuyerEmail(), getText("VP_PESAWAT_BOOKING_EMAIL_PEMESAN"));
        validateValue().equals(TiketPesawatData.getBuyerPhoneNumber(), getText("VP_PESAWAT_BOOKING_NOMOR_HANDPHONE_PEMESAN"));
    }

    public void tapOnPassengerDetail() {
        swipeUpToElement("VP_PESAWAT_BOOKING_DETAIL_PENUMPANG");
        tapElement("VP_PESAWAT_BOOKING_DETAIL_PENUMPANG");
    }

    private void tapOnSameAsBuyerCheckbox() {
        TiketPesawatData.setPassengerName(TiketPesawatData.getBuyerName());
        tapElement("VP_PESAWAT_BOOKING_DETAIL_PENUMPANG_CHECKBOX_SAMAKAN_DENGAN_PEMESAN");
    }

    public void fillPassengerForm(boolean isValid) {
        tapOnSameAsBuyerCheckbox();

        if (isElementExist("VP_PESAWAT_BOOKING_UBAH_DETAIL_PENUMPANG_NOMOR_IDENTITAS_TEXT", 15)) {
            editPassengerBirthday();
            editPassengerIdentityNumber(isValid);
        }
    }

    public void validatePassengerData() {
        swipeUpToElement("VP_PESAWAT_BOOKING_NAMA_PENUMPANG");
        validateValue().contains(TiketPesawatData.getPassengerName(), getText("VP_PESAWAT_BOOKING_NAMA_PENUMPANG"));
    }

    public void tapOnInsuranceCheckbox(boolean doCheck) {
        swipeUpToElement("VP_PESAWAT_RINCIAN_HARGA_LABEL");

        if (isElementExist("VP_PESAWAT_BOOKING_ASURANSI_CHECKBOX", 15)) {
            boolean isChecked = Boolean.parseBoolean(getElementValue("VP_PESAWAT_BOOKING_ASURANSI_CHECKBOX"));

            if ((doCheck && !isChecked) || (!doCheck && isChecked)) {
                tapElement("VP_PESAWAT_BOOKING_ASURANSI_CHECKBOX");
            }
        }

        TiketPesawatData.setUsingFlightInsurance(doCheck);
    }

    public void collectFlightData() {
        swipeUpToElement("VP_PESAWAT_PILIH_METODE_BAYAR_LABEL");
        TiketPesawatData.setTotalPrice(getText("VP_PESAWAT_BOOKING_TOTAL_HARGA_INIT"));
        if (TiketPesawatData.isUsingFlightInsurance()) {
            int biayaAsuransiTotal = getIntFromRp(getText("VP_PESAWAT_BOOKING_TOTAL_ASURANSI"));
            TiketPesawatData.setTotalInsurancePrice(biayaAsuransiTotal);
        } else {
            TiketPesawatData.setTotalInsurancePrice(0);
        }
    }

    public void tapOnSelectPaymentMethod() {
        swipeUpToElement("VP_PESAWAT_PILIH_METODE_BAYAR_LABEL");
        tapElement("VP_PESAWAT_PILIH_METODE_BAYAR_LABEL");
        waitForVisibilityOf("VP_PESAWAT_KONFIRM_DETAIL_BENAR_LABEL", 3);
        tapElement("VP_PESAWAT_KONFIRM_BENAR_LABEL");
    }

    public void confirmPriceChange() {
        String totalPriceInit = TiketPesawatData.getTotalPrice();
        if (isElementExist("VP_PESAWAT_HARGA_BERUBAH_LABEL", 80)) {
            TiketPesawatData.setPriceChanged(true);
            TiketPesawatData.setTotalPrice(getText(
                    constructLocator("VP_PESAWAT_BOOKING_TOTAL_HARGA_TERBARU",
                            totalPriceInit, totalPriceInit)));
            tapElement("VP_PESAWAT_LANJUT_BAYAR_LABEL");
        } else {
            TiketPesawatData.setPriceChanged(false);
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    private void tapOnApplyButton() {
        tapElement("VP_PESAWAT_TERAPKAN_LABEL");
    }

    private void tapOnEditPassengerBirthday() {
        swipeUpToElement("VP_PESAWAT_BOOKING_UBAH_DETAIL_PENUMPANG_TANGGAL_LAHIR");
        tapElement("VP_PESAWAT_BOOKING_UBAH_DETAIL_PENUMPANG_TANGGAL_LAHIR");
        waitForVisibilityOf("VP_PESAWAT_TERAPKAN_LABEL", 5);
        waitForVisibilityOf("VP_PESAWAT_BOOKING_UBAH_DETAIL_PENUMPANG_DATE_PICKER", 5);
    }

    private int getCalendarYearDefaultValue(int offset) {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) + offset;
    }

    private void editPassengerBirthday() {
        tapOnEditPassengerBirthday();
        nativeSwipeDown(constructLocator("VP_PESAWAT_GENERAL_VALUE", getCalendarYearDefaultValue(-12)));
        tapOnApplyButton();
    }

    private void tapOnEditPassportExpiry() {
        swipeUpToElement("VP_PESAWAT_BOOKING_UBAH_DETAIL_PENUMPANG_KEDALUWARSA_PASPOR");
        tapElement("VP_PESAWAT_BOOKING_UBAH_DETAIL_PENUMPANG_KEDALUWARSA_PASPOR");
        waitForVisibilityOf("VP_PESAWAT_TERAPKAN_LABEL", 5);
        waitForVisibilityOf("VP_PESAWAT_BOOKING_UBAH_DETAIL_PENUMPANG_DATE_PICKER", 5);
    }

    public void editPassengerPassportExpiry() {
        tapOnEditPassportExpiry();
        nativeSwipeUp(constructLocator("VP_PESAWAT_GENERAL_VALUE", getCalendarYearDefaultValue(1)));
        tapOnApplyButton();
    }

    private void editPassengerIdentityNumber(boolean isValid) {
        waitForVisibilityOf("VP_PESAWAT_BOOKING_UBAH_DETAIL_PENUMPANG_NOMOR_IDENTITAS",3);
        
        if (isValid) {
            String identityNumber = generateAlphanumericString(15);

            typeAndEnterValue("VP_PESAWAT_BOOKING_UBAH_DETAIL_PENUMPANG_NOMOR_IDENTITAS", identityNumber);
            TiketPesawatData.setPassengerIdentityNumber(identityNumber);
        } else {
            typeAndEnterValue("VP_PESAWAT_BOOKING_UBAH_DETAIL_PENUMPANG_NOMOR_IDENTITAS", "!@#$%^&*");
        }
    }

    public void validateInvalidFullname() {
        validateDisplayed("VP_PESAWAT_BOOKING_INVALID_FULLNAME_MESSAGE");
    }

    public void validateInvalidEmail() {
        validateDisplayed("VP_PESAWAT_BOOKING_INVALID_FULLNAME_EMAIL");
    }

    public void validateInvalidPhoneNumber() {
        validateDisplayed("VP_PESAWAT_BOOKING_INVALID_FULLNAME_PHONENUMBER");
        HelperData.setLastActionPage(new TiketPesawatBookingPage(iosDriver));
    }

    public void validateUserIsOnOrderDetailPage() {
        validateDisplayed("VP_PESAWAT_BOOKING_ORDER_DETAIL_PAGE");
        HelperData.setLastActionPage(new TiketPesawatBookingPage(iosDriver));
    }

    public void cancelFlightTransaction() {
        tapElement("checkout_non_marketplace_alchemy_back_button");
        verifyElementExist("VP_PESAWAT_CHECKOUT_CANCEL_BUTTON");
        tapElement("VP_PESAWAT_CHECKOUT_CANCEL_BUTTON");
    }

}
