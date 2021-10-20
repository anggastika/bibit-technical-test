package bukalapak.pageObject.vp.tix.tiketKereta;

import bukalapak.data.HelperData;
import bukalapak.data.vp.tix.TiketKeretaData;
import bukalapak.pageObject.BasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

public class TravelTrainSchedulePage extends BasePage {

    public TravelTrainSchedulePage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage() {
        waitForVisibilityOf("train_schedule_first_card", 30);
    }

    public void validateStation(String tripType) {
        if (tripType.equals("departure")) {
            assertEquals(TiketKeretaData.getOriginStation() + "  " + TiketKeretaData.getDestinationStation(), getTextFromElement("train_schedule_station_name_text"));
        } else {
            assertEquals(TiketKeretaData.getDestinationStation() + "  " + TiketKeretaData.getOriginStation(), getTextFromElement("train_schedule_station_name_text"));
        }
    }

    public void tapOnFilterIcon() {
        tapElement("train_filter_label");
        waitForVisibilityOf("train_terapkan_label", 8);
    }

    public void tapOnFilterOption(String filterName) {
        tapElement(constructLocator("train_general_label", filterName));
    }

    public void tapOnTerapkanButton(){
        tapElement("train_terapkan_button");
    }

    public void checkTrainClassFilter(String trainClass, String trip) {
        waitForVisibilityOf("train_class_ekonomi_label", 5);

        if (trainClass.contains("and") ) {
            if (trip.equals("departure")) {
                tapElement(constructLocator("train_general_label", trainClass.split("and")[0].trim()));
            } else {
                tapElement(constructLocator("train_general_label", trainClass.split("and")[1].trim()));
            }
        } else {
            tapElement(constructLocator("train_general_label", trainClass));
        }
    }

    public void validateScheduleClassDisplayed(String trainClass, String trip) {
        waitForVisibilityOf("train_filter_label", 8);
        if (trainClass.contains("and")) {
            String newTrainClass;
            if (trip.equals("departure")) {
                newTrainClass = trainClass.split("and")[0].trim();
                TiketKeretaData.setTrainClassDeparture(newTrainClass);
            } else {
                newTrainClass = trainClass.split("and")[1].trim();
                TiketKeretaData.setTrainClassReturn(newTrainClass);
            }

            assertTrue(isElementVisible(constructLocator("train_general_label_contain", newTrainClass)));
        } else {
            if (trip.equals("departure")) {
                TiketKeretaData.setTrainClassDeparture(trainClass);
            } else {
                TiketKeretaData.setTrainClassReturn(trainClass);
            }
            assertTrue(isElementVisible(constructLocator("train_general_label_contain", trainClass)));
        }
    }

    public void setTrainName(String trip) {
        if (trip.equals("departure")) {
            TiketKeretaData.setDepartureTrainName(getTrainNameText());
        } else {
            TiketKeretaData.setReturnTrainName(getTrainNameText());
        }
    }

    private String getTrainNameText() {
        return getTextFromElement("train_schedule_first_train_name_text");
    }

    public void setTrainPrice(String trip) {
        if (trip.equals("departure")) {
            TiketKeretaData.setDeparturePrice(getTrainPriceText());
        } else {
            TiketKeretaData.setReturnPrice(getTrainPriceText());
        }
    }

    private int getTrainPriceText() {
        return getIntFromRp(getElementValue("train_schedule_first_train_price_text").split("/")[0].trim());
    }

    public void setTrainTime(String trip) {
        if (trip.equals("departure")) {
            TiketKeretaData.setDepartureTimeOrigin(getTrainDetailText("time", "origin"));
            TiketKeretaData.setDepartureTimeArrival(getTrainDetailText("time", "arrival"));
        } else {
            TiketKeretaData.setReturnTimeOrigin(getTrainDetailText("time", "origin"));
            TiketKeretaData.setReturnTimeArrival(getTrainDetailText("time", "arrival"));
        }
    }

    public void setStationCode(String trip) {
        if (trip.equals("departure")) {
            TiketKeretaData.setDepartureOriginStationCode(getTrainDetailText("code", "origin"));
            TiketKeretaData.setDepartureDestinationStationCode(getTrainDetailText("code", "arrival"));
        } else {
            TiketKeretaData.setReturnOriginStationCode(getTrainDetailText("code", "origin"));
            TiketKeretaData.setReturnDestinationStationCode(getTrainDetailText("code", "arrival"));
        }
    }

    private String getTrainDetailText(String attribute, String tripType) {
        int strIdx = attribute.equals("code") ? 1 : 0;

        if (tripType.equals("arrival")) {
            return splitStringByParanthesis(getTextFromElement("train_schedule_first_train_eta_text"), strIdx);
        } else {
            return splitStringByParanthesis(getTextFromElement("train_schedule_first_train_etd_text"), strIdx);
        }
    }

    public void tapOnScheduleCard() {
        tapElement("train_schedule_first_card");
    }

    public void tapSortingIcon() {
        tapElement("train_schedule_sorting_icon", 20);
    }

    public void tapSortingType(String type) {
        waitForVisibilityOf(constructLocator("train_schedule_sorting_type", type), 5);
        tapElement(constructLocator("train_schedule_sorting_type", type));
        TiketKeretaData.setSortingType(type);
    }

    public void tapTerapkanButton() {
        tapElement("train_schedule_terapkan_button");
    }

    public void validateTrainSorting(String type) {
        waitForVisibilityOf("train_schedule_price_list", 5);

        int allSeatList = getElementsPresent("train_schedule_price_list").size();
        int allEmptySeatList = getElementsPresent("train_schedule_empty_seat_list").size();
        int totalAvailableList = allSeatList - allEmptySeatList;

        for (int index = 1; index < totalAvailableList - 1; index++) {
            swipeUpToElement(constructLocator("train_schedule_price", index + 1));

            if (type.equals("price")) {
                checkPriceSorting(index);
            }
        }

        HelperData.setLastActionPage(new TravelTrainSchedulePage(iosDriver));
    }

    private void checkPriceSorting(int index) {
        int firstPrice = getIntegerFromValueElement(constructLocator("train_schedule_price", index));
        int secondPrice = getIntegerFromValueElement(constructLocator("train_schedule_price", index + 1));

        if (TiketKeretaData.getSortingType().equals("Termurah") && (firstPrice > secondPrice)) {
            Assert.fail("Sorting tiket kereta termurah tidak sesuai");
        } else if (TiketKeretaData.getSortingType().equals("Termahal") && (firstPrice < secondPrice)) {
            Assert.fail("Sorting tiket kereta termahal tidak sesuai");
        }
    }
}
