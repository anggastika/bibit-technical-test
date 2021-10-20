package bukalapak.pageObject.vp.tix.tiketBus;

import bukalapak.TestInstrument;
import bukalapak.data.BusData;
import bukalapak.data.HelperData;
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
 * @Author: Ayu Musfita
 * @Date: 03/01/19, Thu
 **/
public class TravelBusTicketPage extends BasePage {

    public TravelBusTicketPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }
    
    public void clickTiketBusMenu() {
        allowPopup();
        swipeDownToElement("home_lainnya_widget");
        tapElement("home_lainnya_widget");
        swipeDownToElement("bus_menu");
        tapElement("bus_menu");
    }

    private void tapOnCoachMark() {
        waitForVisibilityOf("bus_coachmark_oke_button", 15);
        while (isElementClickable("bus_coachmark_oke_button")) {
            tapElement("bus_coachmark_oke_button");
            delay(750);
        }
    }

    public void skipCoachMark() {
        if (!BusData.isCoachMarked()) {
            tapOnCoachMark();
            BusData.setCoachMarked(true);
        }
    }

    public void validateOnBusTicketingPage() {
        skipCoachMark();
        validateElementVisible("bus_sekali_jalan_switch_button");
        validateDisplayed("bus_origin_city_dropdown");
        validateDisplayed("bus_destination_city_dropdown");
        validateDisplayed("bus_button_cari");

        HelperData.setLastActionPage(new TravelBusTicketPage(iosDriver));
    }

    public void switchTripType(String tripType) {
        if (tripType.equals("round trip")) {
            tapElement("bus_sekali_jalan_switch_button");
        }
    }

    public void tapDepartureCity() {
        tapElement("bus_origin_city_dropdown");
    }

    public void tapDestinationCity() {
        tapElement("bus_destination_city_dropdown");
    }

    public void validateOnSearchCityPage() {
        waitForVisibilityOf("bus_first_element_terminal", 25);
    }

    public void searchCity(String terminal) {
        validateOnSearchCityPage();

        if (terminal.equals("departure")) {
            swipeUpToElement(constructLocator("bus_list_city_element", TestInstrument.dotenv.get("ORIGIN_TERMINAL_BUS")));
            tapElement(constructLocator("bus_list_city_element", TestInstrument.dotenv.get("ORIGIN_TERMINAL_BUS")));
            setOriginCity();
        } else {
            swipeUpToElement(constructLocator("bus_list_city_element", TestInstrument.dotenv.get("DESTINATION_TERMINAL_BUS")));
            tapElement(constructLocator("bus_list_city_element", TestInstrument.dotenv.get("DESTINATION_TERMINAL_BUS")));
            setDestinationCity();
        }
    }

    public void setOriginCity() {
        validateDisplayed("bus_origin_city");
        BusData.setBusOriginCity(getElementValue("bus_origin_city"));
    }

    public void setDestinationCity() {
        validateDisplayed("bus_destination_city");
        BusData.setBusDestinationCity(getElementValue("bus_destination_city"));
    }

    public void tapDatepicker(String tripType, int plusCurrentDate) {
        if (tripType.equals("departure")) {
            tapElement("bus_tanggal_pergi_datepicker");
        } else {
            waitForVisibilityOf("bus_tanggal_pulang_datepicker", 10);
            tapElement("bus_tanggal_pulang_datepicker");
        }

        verifyElementExist("bus_datepicker_current_month");
        setNextDateForBusTrip(plusCurrentDate);
    }

    public void tapOnPilihKursi(){
        tapElement("bus_button_pilih_kursi");
    }

    public void tapOnPilihButton(){
        tapElement("bus_button_pilih");
    }

    public void tapOnSearchBusButton() {
        waitForVisibilityOf("bus_button_cari", 10);
        tapElement("bus_button_cari");
    }

    protected void setNextDateForBusTrip(int plusCurrentDate) {
        Date selectedMonth = null;
        DateFormat monthFormat = new SimpleDateFormat("MMMM yyyy", new Locale("en", "EN"));

        try {
            verifyElementExist("bus_datepicker_current_month");
            selectedMonth = monthFormat.parse(getTextFromElement("bus_datepicker_current_month"));
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
                tapElement("bus_next_month_calendar");
                cSelectedMonth.add(Calendar.MONTH, 1);
            }
        } else if (cSelectedMonth.get(Calendar.YEAR) > cExpectedDate.get(Calendar.YEAR)) {
            while (cSelectedMonth.get(Calendar.YEAR) > cExpectedDate.get(Calendar.YEAR)) {
                tapElement("bus_prev_month_calendar");
                cSelectedMonth.add(Calendar.MONTH, -1);
            }
        }

        if (cSelectedMonth.get(Calendar.MONTH) < cExpectedDate.get(Calendar.MONTH)) {
            while (cSelectedMonth.get(Calendar.MONTH) < cExpectedDate.get(Calendar.MONTH)) {
                tapElement("bus_next_month_calendar");
                cSelectedMonth.add(Calendar.MONTH, 1);
            }
        } else if (cSelectedMonth.get(Calendar.MONTH) > cExpectedDate.get(Calendar.MONTH)) {
            while (cSelectedMonth.get(Calendar.MONTH) > cExpectedDate.get(Calendar.MONTH)) {
                tapElement("bus_prev_month_calendar");
                cSelectedMonth.add(Calendar.MONTH, -1);
            }
        }

        tapElement(constructLocator("bus_general_name", cExpectedDate.get(Calendar.DAY_OF_MONTH)));
    }

    public void setCountPassenger(int jumlahPenumpang) {
        int count = 1;

        if (jumlahPenumpang > 1 ) {
            while (count < jumlahPenumpang) {
                tapElement("bus_button_add_penumpang");
                count++;
            }
        }

        if (jumlahPenumpang > 3) {
            BusData.setCountPassenger(3);
        } else {
            BusData.setCountPassenger(jumlahPenumpang);
        }
    }

    public void tapBackButtonBus() {
        tapElement("detail_bus_back_button");
    }

    public void validateCountPassenger() {
        assertEquals(BusData.getCountPassenger(), getIntegerFromTextElement("bus_count_passenger_text"));

        HelperData.setLastActionPage(new TravelBusTicketPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
