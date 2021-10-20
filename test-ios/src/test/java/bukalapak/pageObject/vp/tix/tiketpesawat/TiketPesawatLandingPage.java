package bukalapak.pageObject.vp.tix.tiketpesawat;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import bukalapak.data.vp.tix.TiketPesawatData;
import bukalapak.pageObject.VpBasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.fail;

public class TiketPesawatLandingPage extends VpBasePage {
    public TiketPesawatLandingPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void skipCoachMark() {
        if (!TiketPesawatData.isCoachMarked()) {
            if (isElementVisible("VP_PESAWAT_ONBOARDING_LANJUT_BUTTON", 15)) {
                tapElement("VP_PESAWAT_ONBOARDING_LANJUT_BUTTON");
                while (isElementVisible("VP_PESAWAT_ONBOARDING_LANJUT_BUTTON")) {
                    tapElement("VP_PESAWAT_ONBOARDING_LANJUT_BUTTON");
                }
                tapElement("VP_PESAWAT_ONBOARDING_SELESAI_BUTTON");
            }
            TiketPesawatData.setCoachMarked(true);
        }
        validateNotDisplayed("VP_PESAWAT_ONBOARDING_SELESAI_BUTTON");
    }

    public void validateOnPage() {
        skipCoachMark();
        waitForVisibilityOf("VP_PESAWAT_FIELD_KEBERANGKATAN", 10);
        verifyElementExist("VP_PESAWAT_FIELD_KEBERANGKATAN");
        verifyElementExist("VP_PESAWAT_FIELD_TUJUAN");
        verifyElementExist("VP_PESAWAT_FIELD_TANGGAL_PERGI");
        verifyElementExist("VP_PESAWAT_FIELD_PENUMPANG");
        verifyElementExist("VP_PESAWAT_TOGGLE_PULANG_PERGI");

        HelperData.setLastActionPage(new TiketPesawatLandingPage(iosDriver));
    }

    public void tapOnDepartureAirportField() {
        tapElement("VP_PESAWAT_FIELD_KEBERANGKATAN");
    }

    public void tapOnArrivalAirportField() {
        tapElement("VP_PESAWAT_FIELD_TUJUAN");
    }

    public void tapOnFieldTanggalPergi() {
        tapElement("VP_PESAWAT_FIELD_TANGGAL_PERGI");
    }

    public void tapOnTogglePulangPergi() {
        tapElement("VP_PESAWAT_TOGGLE_PULANG_PERGI");
    }

    public void tapOnFieldTanggalPulang() {
        waitForVisibilityOf("VP_PESAWAT_FIELD_TANGGAL_PULANG");
        tapElement("VP_PESAWAT_FIELD_TANGGAL_PULANG");
    }

    public void tapOnTextDatePicker(int plusCurrentDate) {
        validateExist("VP_PESAWAT_CALENDAR_DAY");
        setFlightNextDate(plusCurrentDate);
    }

    public void setOnFieldPenumpang(int dewasa, int anak, int bayi) {
        tapElement("VP_PESAWAT_FIELD_PENUMPANG");
        setFlightPassenger(Picker.DEWASA, dewasa);
        setFlightPassenger(Picker.ANAK, anak);
        setFlightPassenger(Picker.BAYI, bayi);
        tapElement("VP_PESAWAT_PILIH_PENUMPANG_LABEL");
    }

    public void tapOnCariPesawatButton() {
        tapElement("VP_PESAWAT_CARI_PESAWAT_BUTTON");
    }

    public void getSearchQueryData() {
        verifyElementExist("VP_PESAWAT_FIELD_KEBERANGKATAN", 10, "Airport hasn't been selected");
        TiketPesawatData.setDepartureAirport(splitStringByParanthesis(getText("VP_PESAWAT_FIELD_TEXT_KEBERANGKATAN"), 1));
        TiketPesawatData.setArrivalAirport(splitStringByParanthesis(getText("VP_PESAWAT_FIELD_TEXT_TUJUAN"), 1));
        TiketPesawatData.setDepartureDate(getText("VP_PESAWAT_FIELD_TEXT_TANGGAL_PERGI").split(",")[1].substring(1));
        if (TiketPesawatData.isRoundTrip()) {
            TiketPesawatData.setReturnDate(getText("VP_PESAWAT_FIELD_TEXT_TANGGAL_PULANG").split(",")[1].substring(1));
        }
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    private void setFlightNextDate(int plusCurrentDate) {
        Date selectedMonth = null;
        DateFormat monthFormat = new SimpleDateFormat("MMMM yyyy", new Locale("en", "EN"));

        try {
            selectedMonth = monthFormat.parse(getText("VP_PESAWAT_TEXT_CURRENT_MONTH"));
        } catch (ParseException e) {
            Assert.fail("Cant parse the selected date");
        }

        Calendar cExpectedDate = Calendar.getInstance();
        cExpectedDate.add(Calendar.DAY_OF_MONTH, plusCurrentDate);

        Calendar cSelectedMonth = Calendar.getInstance();
        cSelectedMonth.setTime(selectedMonth);
        cSelectedMonth.set(Calendar.DATE, cExpectedDate.get(Calendar.DATE));

        if (cSelectedMonth.get(Calendar.YEAR) < cExpectedDate.get(Calendar.YEAR)) {
            while (cSelectedMonth.get(Calendar.YEAR) < cExpectedDate.get(Calendar.YEAR)) {
                tapElement("VP_PESAWAT_NEXT_MONTH_BUTTON");
                cSelectedMonth.add(Calendar.MONTH, 1);
            }
        } else if (cSelectedMonth.get(Calendar.YEAR) > cExpectedDate.get(Calendar.YEAR)) {
            while (cSelectedMonth.get(Calendar.YEAR) > cExpectedDate.get(Calendar.YEAR)) {
                tapElement("VP_PESAWAT_PREV_MONTH_BUTTON");
                cSelectedMonth.add(Calendar.MONTH, -1);
            }
        }

        if (cSelectedMonth.get(Calendar.MONTH) < cExpectedDate.get(Calendar.MONTH)) {
            while (cSelectedMonth.get(Calendar.MONTH) < cExpectedDate.get(Calendar.MONTH)) {
                tapElement("VP_PESAWAT_NEXT_MONTH_BUTTON");
                cSelectedMonth.add(Calendar.MONTH, 1);
            }
        } else if (cSelectedMonth.get(Calendar.MONTH) > cExpectedDate.get(Calendar.MONTH)) {
            while (cSelectedMonth.get(Calendar.MONTH) > cExpectedDate.get(Calendar.MONTH)) {
                tapElement("VP_PESAWAT_PREV_MONTH_BUTTON");
                cSelectedMonth.add(Calendar.MONTH, -1);
            }
        }
        waitForVisibilityOf(constructLocator("VP_PESAWAT_GENERAL_LABEL",cExpectedDate.get(Calendar.DAY_OF_MONTH)), 20);
        tapElement(constructLocator("VP_PESAWAT_GENERAL_LABEL",cExpectedDate.get(Calendar.DAY_OF_MONTH)));
    }

    private void setFlightPassenger(TiketPesawatLandingPage.Picker picker, int totalPassenger) {
        int index = picker.getIndex();
        verifyElementExist(constructLocator("VP_PESAWAT_PENUMPANG_PICKER", index));
        typeAndEnterValue(constructLocator("VP_PESAWAT_PENUMPANG_PICKER", index), String.valueOf(totalPassenger));
    }

    public void selectPopularDestinationItem(String destination) {
        if (isElementExist(constructLocator("VP_PESAWAT_GENERAL_LABEL", destination), 5)) {
            tapElement(constructLocator("VP_PESAWAT_GENERAL_LABEL", destination));
        } else {
            nativeSwipeLeftToElement(constructLocator("VP_PESAWAT_TUJUAN_POPULER", TestInstrument.dotenv.get("TIKET_PESAWAT_POPULAR_DESTINATION_ANCHOR")));
            if (isElementExist(constructLocator("VP_PESAWAT_GENERAL_LABEL", destination), 5)) {
                tapElement(constructLocator("VP_PESAWAT_GENERAL_LABEL", destination));
            } else {
                fail("destination element not found");
            }
        }
    }
}
