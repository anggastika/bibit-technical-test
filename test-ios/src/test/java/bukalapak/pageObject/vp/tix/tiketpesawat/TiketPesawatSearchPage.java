package bukalapak.pageObject.vp.tix.tiketpesawat;

import bukalapak.data.HelperData;
import bukalapak.data.vp.tix.TiketPesawatData;
import bukalapak.pageObject.VpBasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static org.junit.Assert.fail;

public class TiketPesawatSearchPage extends VpBasePage {
    public TiketPesawatSearchPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void tapOnFilter() {
        tapElement("VP_PESAWAT_FILTER_LABEL");
    }

    public void tapOnSort() {
        tapElement("VP_PESAWAT_URUTKAN_LABEL");
    }

    public void validateSearchResult() {
        validateValue().equals(TiketPesawatData.getDepartureAirport(), getText("VP_PESAWAT_SEARCH_FLIGHT_BANDARA_BERANGKAT", 0));
        validateValue().equals(TiketPesawatData.getArrivalAirport(), getText("VP_PESAWAT_SEARCH_FLIGHT_BANDARA_TUJUAN", 0));
    }

    public void validateOnSearchPage() {
        verifyElementDisplayed("VP_PESAWAT_SEARCH_FIRST_FLIGHT");
        verifyElementDisplayed("VP_PESAWAT_FILTER_LABEL");
        verifyElementDisplayed("VP_PESAWAT_URUTKAN_LABEL");
    }

    public void validateSearchDone(String tripType) {
        if (tripType.equalsIgnoreCase("departure")) {
            waitForVisibilityOf("VP_PESAWAT_PENERBANGAN_PERGI_LABEL", 60);
        } else if (tripType.equalsIgnoreCase("return")) {
            waitForVisibilityOf("VP_PESAWAT_PENERBANGAN_PULANG_LABEL", 60);
        } else {
            fail(String.format("no trip type %s", tripType));
        }
    }

    public void tapOnFilterTerapkanButton() {
        tapElement("VP_PESAWAT_FILTER_TERAPKAN_BUTTON");
    }

    public void tapOnSortingTerapkanButton() {
        tapElement("VP_PESAWAT_SEARCH_SORT_TERAPKAN_BUTTON");
    }

    public void tapOnFirstAirlineFilter() {
        tapElements("VP_PESAWAT_FILTER_AIRLINE_CHECKBOXES", 0);
    }

    public void getAirlineFilterData() {
        waitForVisibilityOf("VP_PESAWAT_FILTER_AIRLINE_CHECKBOXES", 5);
        TiketPesawatData.setAirlineFilter(getText("VP_PESAWAT_FILTER_AIRLINE_NAME").split(" ")[0]);
    }

    public void tapOnFirstSchedule(String tripType) {
        String airline = getText("VP_PESAWAT_SEARCH_FLIGHT_AIRLINE", 0);

        if (tripType.toLowerCase().equals("departure")) {
            setDepartureAirlineData(airline);
        } else if (tripType.toLowerCase().equals("return")) {
            setReturnAirlineData(airline);
        }

        tapElement("VP_PESAWAT_SEARCH_FIRST_FLIGHT");
    }

    public void setDepartureAirlineData(String airline) {
        if (airline.contains("+")) {
            TiketPesawatData.setSelectedDepartureAirline("Multi Maskapai");
        } else {
            TiketPesawatData.setSelectedDepartureAirline(airline);
        }
    }

    public void setReturnAirlineData(String airline) {
        if (airline.contains("+")) {
            TiketPesawatData.setSelectedReturnAirline("Multi Maskapai");
        } else {
            TiketPesawatData.setSelectedReturnAirline(airline);
        }
    }

    public void tapOnCriteriaOrder(String searchCriteria) {
        switch (searchCriteria.toLowerCase()) {
            case "termurah":
                tapElements("VP_PESAWAT_SEARCH_SORT_BY", 0);
                break;
            case "termahal":
                tapElements("VP_PESAWAT_SEARCH_SORT_BY", 1);
                break;
            case "terpagi":
                tapElements("VP_PESAWAT_SEARCH_SORT_BY", 2);
                break;
            case "termalam":
                tapElements("VP_PESAWAT_SEARCH_SORT_BY", 3);
                break;
            case "tercepat":
                tapElements("VP_PESAWAT_SEARCH_SORT_BY", 4);
                break;
            default:
                fail(String.format("%s search criteria is not implemented yet", searchCriteria));
        }

        TiketPesawatData.setSortCriteria(searchCriteria);
    }

    public void validateFilterResult() {
        validateValue().contains(TiketPesawatData.getAirlineFilter(), getText("VP_PESAWAT_SEARCH_FLIGHT_AIRLINE", 0));
        HelperData.setLastActionPage(new TiketPesawatSearchPage(iosDriver));
    }

    public void validateSchedulePriceSorted() {
        verifyElementExist("VP_PESAWAT_SEARCH_FIRST_FLIGHT");
        if (isElementVisible("VP_PESAWAT_SEARCH_SECOND_FLIGHT_PRICE", 10)) {
            int topMostSchedulePrice = parseIntegerFromText(getText("VP_PESAWAT_SEARCH_FLIGHT_PRICE", 0));
            int secondMostSchedulePrice = parseIntegerFromText(getText("VP_PESAWAT_SEARCH_SECOND_FLIGHT_PRICE"));

            if (TiketPesawatData.getSortCriteria().toLowerCase().equals("termahal")) {
                validateValue().equalsTrue(topMostSchedulePrice >= secondMostSchedulePrice);
            } else if (TiketPesawatData.getSortCriteria().toLowerCase().equals("termurah")) {
                validateValue().equalsTrue(topMostSchedulePrice <= secondMostSchedulePrice);
            } else {
                fail(String.format("%s sort criteria is not implemented yet", TiketPesawatData.getSortCriteria()));
            }
        }

        HelperData.setLastActionPage(new TiketPesawatSearchPage(iosDriver));
    }

    public void validatePopupNotConfirmEmail() {
        waitForVisibilityOf("VP_PESAWAT_VERIFY_EMAIL_LABEL", 5);
        verifyElementExist("VP_PESAWAT_VERIFY_EMAIL_LABEL");
        HelperData.setLastActionPage(new TiketPesawatSearchPage(iosDriver));
    }

    public void tapOnBackButton() {
        tapElement("VP_PESAWAT_SEARCH_BACK_BUTTON");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void tapRentangHargaFilter() {
        waitForVisibilityOf("VP_PESAWAT_FILTER_PRICE");
        tapElement("VP_PESAWAT_FILTER_PRICE");
    }

    public void typeOnMinPriceFilter (String minimumPrice) {
        TiketPesawatData.setMinimumPrice(Integer.parseInt(minimumPrice));
        waitForVisibilityOf("VP_PESAWAT_FILTER_MINIMUM_PRICE");
        tapElement("VP_PESAWAT_FILTER_MINIMUM_PRICE");
        typeAndEnterValue("VP_PESAWAT_FILTER_MINIMUM_PRICE", minimumPrice);
    }

    public void typeOnMaxPriceFilter(String maximumPrice) {
        TiketPesawatData.setMaximumPrice(Integer.parseInt(maximumPrice));
        waitForVisibilityOf("VP_PESAWAT_FILTER_MAKSIMUM_PRICE");
        tapElement("VP_PESAWAT_FILTER_MAKSIMUM_PRICE");
        typeAndEnterValue("VP_PESAWAT_FILTER_MAKSIMUM_PRICE", maximumPrice);
    }

    public void validateOnFilterResultPage() {
        verifyElementDisplayed("VP_PESAWAT_FILTER_LABEL");
        verifyElementDisplayed("VP_PESAWAT_URUTKAN_LABEL");
    }

    public void validateFilterResultPrice() {
        int minimumPrice = TiketPesawatData.getMinimumPrice();
        int maximumPrice = TiketPesawatData.getMaximumPrice();
        int counter = 0;
        int countScheduleFlight = getElements("VP_PESAWAT_VALUE_PRICE").size();

        while (counter < countScheduleFlight) {
            try {
                int elementPriceSchedule = parseIntegerFromText(getElementValue("VP_PESAWAT_VALUE_PRICE", counter));

                assertTrue(elementPriceSchedule >= minimumPrice && elementPriceSchedule <= maximumPrice);
            } catch (Exception elementNotfound) {
                while (isElementVisible("VP_PESAWAT_VALUE_PRICE", counter)) {
                    nativeSwipeUp();
                }
                int elementPriceSchedule = parseIntegerFromText(getElementValue("VP_PESAWAT_VALUE_PRICE", counter));

                assertTrue(elementPriceSchedule >= minimumPrice && elementPriceSchedule <= maximumPrice);
            }
            counter++;
        }
    }

}
