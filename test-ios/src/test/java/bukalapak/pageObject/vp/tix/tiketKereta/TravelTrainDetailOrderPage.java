package bukalapak.pageObject.vp.tix.tiketKereta;

import bukalapak.data.vp.tix.TiketKeretaData;
import bukalapak.pageObject.VpBasePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;


public class TravelTrainDetailOrderPage extends VpBasePage {

    public TravelTrainDetailOrderPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage() {
        waitForVisibilityOf("train_detail_pesanan_label", 20);
    }

    public void validateTrainName(String trip) {
        if (trip.equals("departure")) {
            assertEquals(TiketKeretaData.getDepartureTrainName(), getDepartureTrainNameText());
        } else {
            assertEquals(TiketKeretaData.getReturnTrainName(), getReturnTrainNameText());
        }
    }

    private String getDepartureTrainNameText() {
        return getTextFromElement("train_detail_order_departure_train_name_text").trim();
    }

    private String getReturnTrainNameText() {
        return getTextFromElement("train_detail_order_return_train_name_text").trim();
    }

    public void validateTrainClass(String trip) {
        if (trip.equals("departure")) {
            assertEquals(TiketKeretaData.getTrainClassDeparture(), getDepartureTrainClassText());
        } else {
            assertEquals(TiketKeretaData.getTrainClassReturn(), getReturnTrainClassText());
        }
    }

    private String getDepartureTrainClassText() {
        return getTextFromElement("train_detail_order_departure_train_class_text");
    }

    private String getReturnTrainClassText() {
        return getTextFromElement("train_detail_order_return_train_class_text");
    }

    public void validateTrainTime(String trip) {
        if (trip.equals("departure")) {
            assertEquals(TiketKeretaData.getDepartureTimeOrigin(), getTrainDepartureEtdText("time"));
            assertEquals(TiketKeretaData.getDepartureTimeArrival(), getTrainDepartureEtaText("time"));
        } else {
            assertEquals(TiketKeretaData.getReturnTimeOrigin(), getTrainReturnEtdText("time"));
            assertEquals(TiketKeretaData.getReturnTimeArrival(), getTrainReturnEtaText("time"));
        }
    }

    private String getTrainDepartureEtdText(String attribute) {
        int idx = attribute.equals("code") ? 1 : 0;

        return splitStringByParanthesis(getTextFromElement("train_detail_order_departure_etd_text"), idx);
    }

    private String getTrainReturnEtdText(String attribute) {
        int idx = attribute.equals("code") ? 1 : 0;

        return splitStringByParanthesis(getTextFromElement("train_detail_order_return_etd_text"), idx);
    }

    private String getTrainDepartureEtaText(String attribute) {
        int idx = attribute.equals("code") ? 1 : 0;

        return splitStringByParanthesis(getTextFromElement("train_detail_order_departure_eta_text"), idx);
    }

    private String getTrainReturnEtaText(String attribute) {
        int idx = attribute.equals("code") ? 1 : 0;

        return splitStringByParanthesis(getTextFromElement("train_detail_order_return_eta_text"), idx);
    }

    public void validateTrainCode(String trip) {
        if (trip.equals("departure")) {
            assertEquals(TiketKeretaData.getDepartureOriginStationCode(), getTrainDepartureEtdText("code"));
            assertEquals(TiketKeretaData.getDepartureDestinationStationCode(), getTrainDepartureEtaText("code"));
        } else {
            assertEquals(TiketKeretaData.getReturnOriginStationCode(), getTrainReturnEtdText("code"));
            assertEquals(TiketKeretaData.getReturnDestinationStationCode(), getTrainReturnEtaText("code"));
        }
    }

    public void tapOnPassangerDetail() {
        swipeUpToElement("train_detail_order_change_contact_passenger_button");
        tapElement("train_detail_order_change_contact_passenger_button");
    }

    public void validateOnPassengerContactPage() {
        waitForVisibilityOf("train_simpan_button", 8);
        verifyElementExist(constructLocator("train_general_label", "Dewasa 1"));
    }

    public void setPassengerName() {
        String fullname = generateFullName();

        TiketKeretaData.setPassangerName(fullname);
        typeAndEnterValue("train_passenger_form_name_input", fullname);
    }

    public void setPassengerIdentity() {
        long identityNumber = generateIdentityNumber();

        TiketKeretaData.setPassangerID(String.valueOf(identityNumber));
        typeAndEnterValue("train_passenger_form_identity_input", String.valueOf(identityNumber));
    }

    public void tapOnLanjutkanPesanan(){
        waitForElementClickable("train_lanjut_pesan_button",5);
        tapElement("train_lanjut_pesan_button");
    }

    public void tapOnSimpan(){
        waitForElementClickable("train_simpan_button",5);
        tapElement("train_simpan_button");
    }

    public void tapOnYaBenar(){
        waitForElementClickable("train_ya_benar_button",5);
        tapElement("train_ya_benar_button");
    }

    public void validatePopUpOrderDisplayed() {
        verifyElementDisplayed("train_detail_pesanan_confirm_msg");
    }

    public void tapOnChangeBuyerContact() {
        swipeUpToElement("train_detail_order_change_contact_buyer_button");
        tapElement("train_detail_order_change_contact_buyer_button");
    }

    public void validateOnBuyerContactPage() {
        verifyElementExist("train_buyer_form_name_input");
        verifyElementExist("train_buyer_form_email_input");
        verifyElementExist("train_buyer_form_phone_input");
    }

    public void setBuyerContactName() {
        String fullname = generateFullName();

        typeAndEnterValue("train_buyer_form_name_input", fullname);
        TiketKeretaData.setBuyerName(fullname);
    }

    public void setBuyerContactEmail() {
        String fullname = generateFullName().replace(" ", "");

        typeAndEnterValue("train_buyer_form_email_input", fullname + "@gmail.com");
        TiketKeretaData.setBuyerEmail(fullname + "@gmail.com");
    }

    public void setBuyerContactPhone() {
        long phoneNumber = generatePhoneNumber();

        typeAndEnterValue("train_buyer_form_phone_input", "08" + phoneNumber);
        TiketKeretaData.setBuyerPhone("08" + phoneNumber);
    }

    public void setTotalAmount() {
        while (!isElementExist("train_lanjut_pesan_button", 10)) {
            swipeUp(0.7, 0.1);
        }

        TiketKeretaData.setTotalAmount(getIntFromRp("train_detail_order_total_amount_text"));
    }

    public void tapOnInsuranceCheckbox(String state) {
        boolean isActive = isInsuranceActive();

        swipeDownToElement("train_detail_order_insurance_checkbox");
        if ((state.equals("ticks") && !isActive) || (state.equals("unticks") && isActive)) {
            tapElement("train_detail_order_insurance_checkbox");
        }
    }

    public void setInsuranceFee() {
        TiketKeretaData.setInsurancePrice(getIntegerFromTextElement("train_detail_order_insurance_price"));
    }

    /**
     * This method is used to validate the insurance checkbox state
     * The attribute checked of the checkbox element is always true, so it can't be used to know the current state
     * The other option is using the insurance price element to indicate the current state
     * If insurance price element exist, it means the insurance state is active and vice versa
     *
     * @return insurance state
     */
    private boolean isInsuranceActive() {
        swipeUpToElement("train_pilih_kursi_button");

        return isElementExist("train_asuransi_label");
    }

    public void tapOnInsuranceDetail() {
        swipeUpToElement("train_manfaat_lain_label");
        tapElement("train_manfaat_lain_label");
    }

    public void validateOnInsuranceBenefit() {
        validateDisplayed("train_manfaat_asuransi_label");
        validateDisplayed("train_pelajari_label");
    }
}
