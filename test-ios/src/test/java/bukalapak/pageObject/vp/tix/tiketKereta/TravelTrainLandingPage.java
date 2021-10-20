package bukalapak.pageObject.vp.tix.tiketKereta;

import bukalapak.data.HelperData;
import bukalapak.data.vp.tix.TiketKeretaData;
import bukalapak.pageObject.BasePage;
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

/**
 * Created by Ayu Musfita.
 */
public class TravelTrainLandingPage extends BasePage {

    public TravelTrainLandingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void clickTiketKeretaMenu() {
        allowPopup();
        swipeDownToElement("home_lainnya_widget");
        tapElement("home_lainnya_widget");
        swipeDownToElement("train_menu");
        tapElement("train_menu");
    }

    public void validateOnPage() {
        skipCoachMarkTiketKereta();
        verifyElementExist("train_datepicker_departure_button");
    }

    private void tapOnCoachMark() {
        waitForVisibilityOf("train_coach_mark", 15);
        while (isElementClickable("train_coach_mark")) {
            tapElement("train_coach_mark");
            delay(750);
        }
    }

    public void skipCoachMarkTiketKereta() {
        if (!TiketKeretaData.isCoachMarked()) {
            tapOnCoachMark();
            TiketKeretaData.setCoachMarked(true);
        }
    }


    public void skipTravelInfoPopup() {
        if (isElementVisible("train_travel_info_detail_button", 5)) {
            tapOnTravelInfoAgreeButton();
        }
    }

    private void tapOnTravelInfoAgreeButton() {
        verifyElementDisplayed("train_travel_info_agree_button");
        tapElement("train_travel_info_agree_button");
        validateNotDisplayed("train_travel_info_detail_button",
                "Travel info is still displayed");
    }

    public void tapOnOriginStation() {
        tapElement("train_station_origin_button");
        verifyElementExist("train_search_result_station_text");
    }

    public void tapOnDestinationStation() {
        tapElement("train_station_destination_button");
        verifyElementExist("train_search_result_station_text");
    }

    public void searchStation(String tripType, String station) {
        typeAndEnterValue("train_search_station", station);

        if (tripType.equals("origin")) {
            TiketKeretaData.setOriginStation(getTextFromElement("train_search_result_station_text"));
        } else {
            TiketKeretaData.setDestinationStation(getTextFromElement("train_search_result_station_text"));
        }

        tapElement("train_search_result_station_text");
    }

    public void tapOnDatePicker(String tripType) {
        if (tripType.equals("departure")) {
            tapElement("train_datepicker_departure_button");
            verifyElementExist("train_datepicker_header_depart_text");
        } else {
            tapElement("train_roundtrip_switch_button");
            swipeUpToElement("train_datepicker_return_button");
            tapElement("train_datepicker_return_button");
            verifyElementExist("train_datepicker_header_return_text");
        }
    }

    public void chooseTripDate(int dateNumber) {
        setNextDate(dateNumber);
        tapOnChooseDateButton();
    }

    public void tapOnChooseDateButton() {
        tapElement("train_pilih_tanggal_button");
    }

    public void setOnFieldPenumpang(int dewasa, int anak) {
        swipeUpToElement("train_passenger_button");
        tapElement("train_passenger_button");
        waitForVisibilityOf("train_pilih_penumpang_button");

        if (dewasa > 1 && anak > 0) {
            setPassenger(Picker.DEWASA, dewasa);
            setPassenger(Picker.ANAK, anak);
        }

        tapElement("train_pilih_penumpang_button");
    }

    public void tapOnSearchTrain() {
        swipeUpToElement("train_search_for_schedule_button");
        verifyElementDisplayed("train_search_for_schedule_button");
        tapElement("train_search_for_schedule_button");
    }

    /**
     * Set date to plus day after current date
     * Each feature has max total plusDay
     * Make sure your screen
     * @param plusCurrentDate
     */
    private void setNextDate(int plusCurrentDate) {
        Date selectedMonth = null;
        DateFormat monthFormat = new SimpleDateFormat("MMMM yyy", new Locale("id", "ID"));

        try {
            selectedMonth = monthFormat.parse(getTextFromElement("TRAVEL_BASE_TEXT_CURRENT_MONTH"));
        } catch (ParseException e) {
            Assert.fail("Cant parse the selected date");
        }

        Calendar cExpectedDate = Calendar.getInstance();
        cExpectedDate.add(Calendar.DAY_OF_MONTH, plusCurrentDate);

        Calendar cSelectedMonth = Calendar.getInstance();
        cSelectedMonth.setTime(selectedMonth);
        cSelectedMonth.set(Calendar.DATE, cExpectedDate.get(Calendar.DATE));

        // Change year then month
        if (cSelectedMonth.get(Calendar.YEAR) < cExpectedDate.get(Calendar.YEAR)) {
            while (cSelectedMonth.get(Calendar.YEAR) < cExpectedDate.get(Calendar.YEAR)) {
                tapElement("TRAVEL_BASE_BUTTON_NEXT_DATE");
                cSelectedMonth.add(Calendar.MONTH, 1);
            }
        } else if (cSelectedMonth.get(Calendar.YEAR) > cExpectedDate.get(Calendar.YEAR)) {
            while (cSelectedMonth.get(Calendar.YEAR) > cExpectedDate.get(Calendar.YEAR)) {
                tapElement("TRAVEL_BASE_BUTTON_PREV_DATE");
                cSelectedMonth.add(Calendar.MONTH, -1);
            }
        }

        if (cSelectedMonth.get(Calendar.MONTH) < cExpectedDate.get(Calendar.MONTH)) {
            while (cSelectedMonth.get(Calendar.MONTH) < cExpectedDate.get(Calendar.MONTH)) {
                tapElement("TRAVEL_BASE_BUTTON_NEXT_DATE");
                cSelectedMonth.add(Calendar.MONTH, 1);
            }
        } else if (cSelectedMonth.get(Calendar.MONTH) > cExpectedDate.get(Calendar.MONTH)) {
            while (cSelectedMonth.get(Calendar.MONTH) > cExpectedDate.get(Calendar.MONTH)) {
                tapElement("TRAVEL_BASE_BUTTON_PREV_DATE");
                cSelectedMonth.add(Calendar.MONTH, -1);
            }
        }
        waitForVisibilityOf(constructLocator("train_landing_page_date_dynamic",cExpectedDate.get(Calendar.DAY_OF_MONTH)), 20);
        tapElement(constructLocator("train_landing_page_date_dynamic",cExpectedDate.get(Calendar.DAY_OF_MONTH)));
    }

    protected enum Picker {
        DEWASA(2),
        ANAK(1),
        BAYI(3);

        private final int index;

        Picker(int index) {
            this.index = index;
        }

        public final int getIndex() {
            return index;
        }
    }

    /**
     * Set picker to totalPassenger
     * picker obtained from Picker enum getIndex
     * e.g Picker.DEWASA.getIndex();
     * @param picker
     * @param totalPassenger
     */
    private void setPassenger(Picker picker, int totalPassenger) {
        int index = picker.getIndex();
        verifyElementExist(constructLocator("train_picker_passenger", index));
        typeAndEnterValue(constructLocator("train_picker_passenger", index), String.valueOf(totalPassenger));
    }

    public void validateTrainStationMustBeDifferent() {
        if (isElementVisible("train_same_station_error_message")) {
            validateOnPage();
        } else {
            Assert.fail("Expected : user can't continue search train schedule because station is the same");
        }

        HelperData.setLastActionPage(new TravelTrainLandingPage(iosDriver));
    }

    public void validateAdultMustBeExceededInfant() {
        if (!isElementVisible("train_datepicker_departure_button")) {
            Assert.fail("Expected : user can't continue search train schedule because infant > adult");
        }

        HelperData.setLastActionPage(new TravelTrainLandingPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

}
