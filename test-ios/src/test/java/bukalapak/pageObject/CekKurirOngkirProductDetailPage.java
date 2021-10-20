package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.XPRData;
import com.bukalapak.salad.util.Direction;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CekKurirOngkirProductDetailPage extends BasePage {

    private final static Logger LOGGER = LogManager.getLogger(CekKurirOngkirProductDetailPage.class);

    public CekKurirOngkirProductDetailPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void userOnCekKurirOngkirProductDetailPage() {
        waitForVisibilityOf("cek_ongkir_waktu_pengiriman_text", 30);
        if (isElementVisible("cek_ongkir_waktu_pengiriman_text")) {
            XPRData.setIsRevampPDP(true);
            verifyElementExist("cek_kurir_ongkir_title");
        } else {
            XPRData.setIsRevampPDP(false);
            verifyElementExist("cek_kurir_ongkir_nonrevamp_title");
        }
        HelperData.setLastActionPage(new CekKurirOngkirProductDetailPage(iosDriver));
    }

    public String getTextShippingTimeOnCekOngkir(String courier) {
        String resultElement = getTextFromElement("cek_kurir_ongkir_time_" + courierTrim(courier) + "_text");
        return resultElement;
    }

    public String getSLAonCekOngkirPage(String courier) {
        swipeUpToElement(constructLocator("cek_kurir_sla_text", courier));
        return getTextFromElement(constructLocator("cek_kurir_sla_text", courier));
    }

    public String getShippingFeeOnCekOngkirPage(String courier) {
        swipeUpToElement(constructLocator("cek_kurir_ongkir_fee_text", courier));
        String shippingFee = getTextFromElement(constructLocator("cek_kurir_ongkir_fee_text", courier));
        if (shippingFee.contains("Rp")) {
            return String.valueOf(parseIntegerFromText(shippingFee));
        } else {
            return "0";
        }
    }

    private static String courierTrim(String courier) {
        return courier.replace(" ", "");
    }

    public String getTextShippingFeeOnCekOngkir(String courier) {
        try {
            String resultElement = getTextFromElement("cek_kurir_ongkir_fee_" + courierTrim(courier) + "_text");
            if (!resultElement.contains("Rp")) {
                return "0";
            } else {
                String getValueFee = resultElement.replaceAll("[^0-9]", "");
                return getValueFee;
            }
        } catch (Exception e) {
            return getTextShippingTimeOnCekOngkir(courier);
        }
    }

    public void setShippingPriceAndETA(String courier) {
        if (XPRData.isIsRevampPDP()) {
            setShippingEstimationPriceAndTimeCourier(courier);
        } else {
            setShippingEstimationPriceAndETANonRevamp(courier);
        }
    }

    public void setShippingEstimationPriceAndETANonRevamp(String courier) {
        waitForVisibilityOf("cek_kurir_ongkir_nonrevamp_title");
        switch (courier) {
            case "J&T REG":
                XPRData.setShippingEstimationPrice1(getTextShippingFeeOnCekOngkir(courier));
                XPRData.setShippingEstimationTime1(getTextShippingTimeOnCekOngkir(courier));
                break;
            case "GO-SEND Same Day":
                XPRData.setShippingEstimationTime2(getTextShippingTimeOnCekOngkir(courier));
                XPRData.setShippingEstimationPrice2(getTextShippingFeeOnCekOngkir(courier));
                break;
            case "JNE Trucking":
                XPRData.setShippingEstimationTime3(getTextShippingTimeOnCekOngkir(courier));
                XPRData.setShippingEstimationPrice3(getTextShippingFeeOnCekOngkir(courier));
                break;
            default:
                LogUtil.info("User choose another courier");
                break;
        }
        XPRData.setLocationCekOngkirDefault(getElementValue("cek_kurir_ongkir_location_text"));

    }

    public void setShippingEstimationPriceAndTimeCourier(String courier) {
        waitForVisibilityOf("cek_kurir_ongkir_title", 30);

        switch (courier) {
            case "J&T REG":
                XPRData.setShippingEstimationPrice1(getShippingFeeOnCekOngkirPage(courier));
                XPRData.setShippingEstimationTime1(getSLAonCekOngkirPage(courier));
                break;
            case "GO-SEND Same Day":
                XPRData.setShippingEstimationTime2(getSLAonCekOngkirPage(courier));
                XPRData.setShippingEstimationPrice2(getShippingFeeOnCekOngkirPage(courier));
                break;
            case "JNE Trucking":
                XPRData.setShippingEstimationTime3(getSLAonCekOngkirPage(courier));
                XPRData.setShippingEstimationPrice3(getShippingFeeOnCekOngkirPage(courier));
                break;
            default:
                LogUtil.info("User choose another courier");
                break;
        }
        XPRData.setLocationCekOngkirDefault(getElementValue("cek_kurir_ongkir_location1_text", 0));

    }

    public void validateCourierEtaAndPrice(String status, String courier1, String courier2, String courier3) {
        //Validation with 3 courier (J&T, Gojek, and JNE)
        //J&T REG
        waitForVisibilityOf("cek_kurir_ongkir_location_text");
        int price1 = Integer.parseInt(XPRData.getShippingEstimationPrice1());
        //OnDemand
        String price2 = XPRData.getShippingEstimationPrice2();

        switch (status.toLowerCase()) {
            case "increased":
                assertTrue(price1 < Integer.parseInt(getTextShippingFeeOnCekOngkir(courier1)));
                assertNotSame(price2, getTextShippingTimeOnCekOngkir(courier2));
                assertNotSame(XPRData.getShippingEstimationPrice3(), getTextShippingFeeOnCekOngkir(courier3));
                assertEquals(XPRData.getShippingEstimationTime1(), getTextShippingTimeOnCekOngkir(courier1));
                assertNotSame(XPRData.getShippingEstimationTime2(), getTextShippingTimeOnCekOngkir(courier2));
                assertNotSame(XPRData.getShippingEstimationTime3(), getTextShippingTimeOnCekOngkir(courier3));
                break;
            case "changed":
                assertTrue(price1 < Integer.parseInt(getTextShippingFeeOnCekOngkir(courier1)));
                assertNotSame(price2, getTextShippingTimeOnCekOngkir(courier2));
                assertEquals(XPRData.getShippingEstimationPrice3(), getTextShippingFeeOnCekOngkir(courier3));
                assertNotSame(XPRData.getShippingEstimationTime1(), getTextShippingTimeOnCekOngkir(courier1));
                assertNotSame(XPRData.getShippingEstimationTime2(), getTextShippingTimeOnCekOngkir(courier2));
                assertEquals(XPRData.getShippingEstimationTime3(), getTextShippingTimeOnCekOngkir(courier3));
                break;
            case "decreased":
                assertNotSame(price1, Integer.parseInt(getTextShippingFeeOnCekOngkir(courier1)));
                assertNotSame(price2, getTextShippingFeeOnCekOngkir(courier2));
                assertNotSame(XPRData.getShippingEstimationPrice3(), getTextShippingFeeOnCekOngkir(courier3));
                assertEquals(XPRData.getShippingEstimationTime1(), getTextShippingTimeOnCekOngkir(courier1));
                assertNotSame(XPRData.getShippingEstimationTime2(), getTextShippingTimeOnCekOngkir(courier2));
                assertNotSame(XPRData.getShippingEstimationTime3(), getTextShippingTimeOnCekOngkir(courier3));
                break;
            default:
                break;
        }
    }

    public void validateCourierEtaAndPriceRevamp(String status, String courier1, String courier2, String courier3) {
        waitForVisibilityOf(constructLocator("cek_kurir_ongkir_fee_text", courier1));
        int priceRegularCourier = Integer.parseInt(XPRData.getShippingEstimationPrice1());
        int priceOnDemandCourier = Integer.parseInt(XPRData.getShippingEstimationPrice2());
        int priceJNETruckingCourier = Integer.parseInt(XPRData.getShippingEstimationPrice3());

        switch (status.toLowerCase()) {
            case "increased":
                assertTrue(priceRegularCourier < Integer.parseInt(getShippingFeeOnCekOngkirPage(courier1)), "ongkir lebih besar");
                assertTrue(getSLAonCekOngkirPage(courier2).equals("Berat di luar ketentuan"));
                assertTrue(priceOnDemandCourier > Integer.parseInt(getShippingFeeOnCekOngkirPage(courier2)));
                assertTrue(priceJNETruckingCourier < Integer.parseInt(getShippingFeeOnCekOngkirPage(courier3)));
                break;
            case "changed":
                assertTrue(priceRegularCourier < Integer.parseInt(getShippingFeeOnCekOngkirPage(courier1)), "ongkir lebih besar");
                assertTrue(getSLAonCekOngkirPage(courier2).equals("Lokasi di luar service"));
                assertTrue(priceOnDemandCourier > Integer.parseInt(getShippingFeeOnCekOngkirPage(courier2)));
                assertTrue(getSLAonCekOngkirPage(courier3).equalsIgnoreCase("Berat di luar ketentuan"));
                break;
            case "decreased":
                assertTrue(priceRegularCourier > Integer.parseInt(getShippingFeeOnCekOngkirPage(courier1)), "ongkir lebih kecil");
                assertTrue(priceOnDemandCourier < Integer.parseInt(getShippingFeeOnCekOngkirPage(courier2)));
                assertTrue(priceJNETruckingCourier > Integer.parseInt(getShippingFeeOnCekOngkirPage(courier3)));
                assertTrue(getSLAonCekOngkirPage(courier3).equals("Berat di luar ketentuan"));
                break;
            default:
                LogUtil.info("Tidak ada pilihan tersebut");
                break;
        }
    }

    public void validateFeeAndETA(String status, String courier1, String courier2, String courier3) {
        if (XPRData.isIsRevampPDP()) {
            validateCourierEtaAndPriceRevamp(status, courier1, courier2, courier3);
        } else {
            validateCourierEtaAndPrice(status, courier1, courier2, courier3);
        }
    }

    public void changeTotalProductInProducDetail(String number) {
        if (XPRData.isIsRevampPDP()) {
            if (!isElementVisible("cek_kurir_ongkir_input_number_textfield")) {
                for (int i = 0; i < 5 && !isElementVisible("cek_kurir_ongkir_input_number_textfield"); i++) {
                    swipeToDirection(Direction.DOWN);
                    LOGGER.info("testing :" + isElementVisible("cek_kurir_ongkir_input_number_textfield"));
                }
            }
            tapElement("cek_kurir_ongkir_input_number_textfield");
            tapAndHoldElement("cek_kurir_ongkir_input_number_textfield");
            tapElement("select_all_button");
            waitForVisibilityOf("cut_button", 20);
            tapElement("cut_button");
            typeAndEnterValue("cek_kurir_ongkir_input_number_textfield", number);
        } else {
            tapElement("cek_kurir_ongkir_input_number_old_ui_textfield");
            typeAndEnterValue("cek_kurir_ongkir_input_number_old_ui_textfield", number);
        }

    }

    private static void selectElementTable(List<MobileElement> table, String input) {
        for (MobileElement element : table) {
            String temp = element.getText();
            if (temp.equals(input)) {
                element.click();
                break;
            }
        }
    }

    public void chooseElementLocationOnCekOngkir(String arg0) {
        typeAndEnterValueWithTimeOut("cek_kurir_ongkir_search_location_field", arg0);
        List<MobileElement> table;
        table = getElementTable("XCUIElementTypeTable", "XCUIElementTypeStaticText");
        selectElementTable(table, arg0);
    }

    public void chooseElementLocationOnCekOngkirGeocoder(String arg0) {
        typeAndEnterValueWithTimeOut("cek_kurir_ongkir_search_geocoder_field", arg0);
        List<MobileElement> table;
        table = getElementTable("XCUIElementTypeTable", "XCUIElementTypeButton");
        selectElementTable(table, arg0);
    }

    public void validateNameProvinceDefault(String nameDefaultProvince) {
        assertTrue(getElementValue("cek_kurir_ongkir_location1_text", 0).contains(nameDefaultProvince));
        XPRData.setLocationCekOngkirDefault(getElementValue("cek_kurir_ongkir_location1_text", 0));
    }

    public void validateLocationAfterChanged() {
        waitForVisibilityOf("cek_kurir_ongkir_location1_text", 20);
        assertNotSame(XPRData.getLocationCekOngkirDefault(), getElementValue("cek_kurir_ongkir_location1_text"));
    }

    public void validateServiceAreaError(String courier, String warningNotif) {
        assertEquals(warningNotif, getSLAonCekOngkirPage(courier));
    }

    public void clickCekOngkosKirim() {
        tapElement("product_detail_cek_kurir_ongkir_button");
    }

    public void clickUbahButton() {
        if (XPRData.isIsRevampPDP()) {
            if (!isElementVisible("cek_kurir_ongkir_input_number_textfield")) {
                for (int i = 0; i < 5 && !isElementVisible("cek_kurir_ongkir_input_number_textfield"); i++) {
                    swipeToDirection(Direction.DOWN);
                    LOGGER.info("testing :" + isElementVisible("cek_kurir_ongkir_ubahrevamp_button"));
                }
            }
            tapElement("cek_kurir_ongkir_ubahrevamp_button");
        } else {
            tapElement("cek_kurir_ongkir_ubah_button");
        }

    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void validateKurirSection() {
        verifyElementExist(constructLocator("ongkir_ad_section_title"));
    }
}
