package bukalapak.pageObject;

import bukalapak.data.HelperData;
import bukalapak.data.vp.tix.TiketKeretaData;
import com.github.javafaker.Faker;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static bukalapak.TestInstrument.dotenv;

public class VpBasePage extends BasePage {

    public VpBasePage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public String generateFullName() {
        Random rand = new Random();
        String[] firstNameList = {"Marchel", "Aditya", "Ayu", "Ezra", "Salis", "Pramudita"};
        String[] lastNameList = {"Immanuel", "Prakarsa", "Musfita", "Mahmudah", "Santoso"};
        String firstName = firstNameList[rand.nextInt(firstNameList.length)];
        String lastName = lastNameList[rand.nextInt(lastNameList.length)];

        return firstName + " " + lastName;
    }

    public long generatePhoneNumber() {
        long phoneNumber = (long) (Math.random() * Math.pow(10,10));

        return phoneNumber;
    }

    protected enum Picker {
        DEWASA(3),
        ANAK(1),
        BAYI(2);

        private final int index;

        Picker(int index) {
            this.index = index;
        }

        public final int getIndex() {
            return index;
        }
    }

    public String generateAlphanumericString(int requestedLength) {
        String alphanumericChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder temp = new StringBuilder();
        Random rand = new Random();

        while (temp.length() < requestedLength) {
            int index = rand.nextInt(alphanumericChars.length() - 1);
            temp.append(alphanumericChars.charAt(index));
        }

        return temp.toString();
    }

    public String generateElectricityPostpaidNonBillNumber(String env){
        String custnumber;
        String availList = dotenv.get(env);

        if (availList != null) {
            List<String> strAvailList = Arrays.asList(availList.split(","));
            custnumber = strAvailList.get(new Random().nextInt(strAvailList.size()));
        } else {
            throw new NotFoundException("Please define env variable : " + env);
        }

        return custnumber;
    }

    public String fakerCustNumber () {
        return new Faker().number().digits(12);
    }

    public String generateEsamsatRangkaNumber(String env){
        String vehicleFrame;
        String availableList = dotenv.get(env);

        if (availableList != null) {
            List<String> strAvailableList = Arrays.asList(availableList.split(","));
            vehicleFrame = strAvailableList.get(new Random().nextInt(strAvailableList.size()));
        } else {
            throw new NotFoundException("Please define env variable : " + env);
        }

        return vehicleFrame;
    }

    public String fakerRangkaNumber () {
        return new Faker().number().digits(16);
    }

    public long generateIdentityNumber() {
        return (long) (Math.random() * Math.pow(10,16));
    }

    public void verifyGeneralVpPage(String product) {
        while(isElementVisible("virtual_product_general_btn_lanjut", 5)) {
            tapElement("virtual_product_general_btn_lanjut");
        }

        waitForVisibilityOf("virtual_product_general_navbar_title", 10);
        String vpName = getElementValue("virtual_product_general_navbar_title");
        assertEquals(dotenv.get(product), vpName);
        HelperData.setLastActionPage(new VpBasePage(iosDriver));
    }

    public void tapSalin() {
        // hanya bisa ke-scroll kalau coordinate-nya 0.8, 0.2
        swipeUp(0.8, 0.2);
        tapElement("TRAVEL_BASE_SALIN_BUTTON");
        TiketKeretaData.setVoucherCode(getElementLabel("TRAVEL_BASE_PROMO_VOUCHER"));
    }

    public void verifyPromoTersalin() {
        verifyCopiedText(TiketKeretaData.getVoucherCode());
        HelperData.setLastActionPage(new CheckoutNonMarketplacePage(iosDriver));
    }

    public String generateGender() {
        Random rand = new Random();
        String[] allGender = {"Laki - laki", "Perempuan"};
        String listOfGend = allGender[rand.nextInt(allGender.length)];

        return listOfGend;
    }

    public void validateVoucherValidationText(String voucher) {
        swipeUpToElement("promo_terbaru_text");
        validateDisplayed(constructLocator("promo_voucher_code_validation", voucher));
    }

    public void tapSalinPrepaidPhoneCreditPromo() {
        tapElement("prepaid_phone_credit_salin");
    }

}
