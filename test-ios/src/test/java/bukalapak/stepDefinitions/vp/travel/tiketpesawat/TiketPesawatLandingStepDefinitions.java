package bukalapak.stepDefinitions.vp.travel.tiketpesawat;

import bukalapak.TestInstrument;
import bukalapak.data.vp.tix.TiketPesawatData;
import cucumber.api.java8.En;

public class TiketPesawatLandingStepDefinitions extends TestInstrument implements En {
    public TiketPesawatLandingStepDefinitions() {
        When("^user search One Way Domestic flight schedule$", () -> {
            TiketPesawatData.setInternational(false);
            TiketPesawatData.setRoundTrip(false);
            bukalapak.tiketPesawatLandingPage().tapOnFieldTanggalPergi();
            bukalapak.tiketPesawatLandingPage().tapOnTextDatePicker(Integer.parseInt(dotenv.get("TIKET_PESAWAT_DEPARTURE_DATE")));
            bukalapak.tiketPesawatLandingPage().getSearchQueryData();
            bukalapak.tiketPesawatLandingPage().tapOnCariPesawatButton();
            bukalapak.tiketPesawatSearchPage().validateSearchDone("departure");
        });
        And("^user search Round Trip Domestic flight schedule$", () -> {
            TiketPesawatData.setInternational(false);
            TiketPesawatData.setRoundTrip(true);
            bukalapak.tiketPesawatLandingPage().tapOnTogglePulangPergi();
            bukalapak.tiketPesawatLandingPage().tapOnFieldTanggalPergi();
            bukalapak.tiketPesawatLandingPage().tapOnTextDatePicker(Integer.parseInt(dotenv.get("TIKET_PESAWAT_DEPARTURE_DATE")));
            bukalapak.tiketPesawatLandingPage().tapOnFieldTanggalPulang();
            bukalapak.tiketPesawatLandingPage().tapOnTextDatePicker(Integer.parseInt(dotenv.get("TIKET_PESAWAT_RETURN_DATE")));
            bukalapak.tiketPesawatLandingPage().getSearchQueryData();
            bukalapak.tiketPesawatLandingPage().tapOnCariPesawatButton();
        });
        Then("^user is back on flight landing page$", () -> {
            bukalapak.tiketPesawatLandingPage().validateOnPage();
        });
        And("^user search One Way International flight schedule$", () -> {
            TiketPesawatData.setInternational(true);
            TiketPesawatData.setRoundTrip(false);
            bukalapak.tiketPesawatLandingPage().tapOnArrivalAirportField();
            bukalapak.tiketPesawatLandingPage().selectPopularDestinationItem(dotenv.get("TIKET_PESAWAT_POPULAR_DESTINATION"));
            bukalapak.tiketPesawatLandingPage().tapOnFieldTanggalPergi();
            bukalapak.tiketPesawatLandingPage().tapOnTextDatePicker(Integer.parseInt(dotenv.get("TIKET_PESAWAT_DEPARTURE_DATE")));
            bukalapak.tiketPesawatLandingPage().getSearchQueryData();
            bukalapak.tiketPesawatLandingPage().tapOnCariPesawatButton();
            bukalapak.tiketPesawatSearchPage().validateSearchDone("departure");
        });

        When("user copies promo code on the Tiket Pesawat landing page", () -> {
            bukalapak.vpBasePage().tapSalin();
        });

        Then("the selected promo for Tiket Pesawat will have copied", () -> {
            bukalapak.vpBasePage().verifyPromoTersalin();
        });

    }
}
