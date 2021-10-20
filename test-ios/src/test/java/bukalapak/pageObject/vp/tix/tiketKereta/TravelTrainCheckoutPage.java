package bukalapak.pageObject.vp.tix.tiketKereta;

import bukalapak.data.vp.tix.TiketKeretaData;
import bukalapak.pageObject.BasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class TravelTrainCheckoutPage extends BasePage {
    public TravelTrainCheckoutPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void validateCheckoutDetail() {
        waitForVisibilityOf("train_pergi_label", 20);
        assertEquals(TiketKeretaData.getDepartureTrainName(), getDepartureTrainNameText());
        assertEquals(TiketKeretaData.getTrainClassDeparture(), getDepartureTrainClassText());
        assertEquals(TiketKeretaData.getDepartureTimeOrigin(), getDepartureTrainTimeEtdText());
        assertEquals(TiketKeretaData.getDepartureTimeArrival(), getDepartureTrainTimeEtaText());

        if (TiketKeretaData.getTrainTripType().equals("round trip")) {
            assertEquals(TiketKeretaData.getReturnTrainName(), getReturnTrainNameText());
            assertEquals(TiketKeretaData.getTrainClassReturn(), getReturnTrainClassText());
            assertEquals(TiketKeretaData.getReturnTimeOrigin(), getReturnTrainTimeEtdText());
            assertEquals(TiketKeretaData.getReturnTimeArrival(), getReturnTrainTimeEtaText());
        }

        swipeUpToElement("train_nomor_id_label");
        assertEquals(TiketKeretaData.getPassangerID(), getIdentityNumberText());
    }

    public void validateTotalPrice() {
        int expectedTotalAmount;
        verifyElementDisplayed("train_detail_checkout_total_amount_value");
        if (TiketKeretaData.getTrainTripType().equals("one way")) {
            expectedTotalAmount = TiketKeretaData.getTotalAmount() + 7500;
        } else {
            expectedTotalAmount = TiketKeretaData.getTotalAmount() + 15000;
        }

        assertEquals(expectedTotalAmount, getIntFromRp("train_detail_checkout_total_amount_text"));
    }

    private String getDepartureTrainNameText() {
        return getTextFromElement("train_detail_checkout_departure_train_name_text");
    }

    private String getReturnTrainNameText() {
        return getTextFromElement("train_detail_checkout_return_train_name_text");
    }

    private String getDepartureTrainClassText() {
        return getTextFromElement("train_detail_checkout_departure_train_class_text");
    }

    private String getReturnTrainClassText() {
        return getTextFromElement("train_detail_checkout_return_class_text");
    }

    private String getDepartureTrainTimeEtdText() {
        return splitStringByParanthesis(getTextFromElement("train_detail_checkout_departure_etd_text"), 0);
    }

    private String getReturnTrainTimeEtdText() {
        return splitStringByParanthesis(getTextFromElement("train_detail_checkout_return_etd_text"), 0);
    }

    private String getDepartureTrainTimeEtaText() {
        return splitStringByParanthesis(getTextFromElement("train_detail_checkout_departure_eta_text"), 0);
    }

    private String getReturnTrainTimeEtaText() {
        return splitStringByParanthesis(getTextFromElement("train_detail_checkout_return_eta_text"), 0);
    }

    private String getIdentityNumberText() {
        return getTextFromElement("train_detail_checkout_identity_number_text");
    }
}
