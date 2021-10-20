package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;

public class PremiumLandingPage extends BasePage {

    public PremiumLandingPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnPremiumLandingPage() {
        verifyElementExist("premium_bulanan_button");
        verifyElementExist("premium_6_bulan_button");
        verifyElementExist("premium_tahunan_button");
        verifyElementExist("premium_price_text");
        HelperData.setLastActionPage(new PremiumLandingPage(iosDriver));
    }

    public void swipeToPackage(String premium_package) {
        double rightAnchor = 0.2;
        double leftAnchor = 0.8;
        double verticalAnchor = 0.4;
        String totalStaff = getTextFromElement("premium_karyawan_lapak_description_text")
                .replaceAll("/[^a-zA-Z]", "");
        switch (premium_package.toLowerCase()) {
            case "basic":
                if (totalStaff.equals(dotenv.get("PROFESSIONAL_KARYAWAN"))) {
                    swipeLeft(rightAnchor, leftAnchor, verticalAnchor);
                } else {
                    swipeLeft(rightAnchor, leftAnchor, verticalAnchor);
                    swipeLeft(rightAnchor, leftAnchor, verticalAnchor);
                }
                break;
            case "professional":
                if (totalStaff.equals(dotenv.get("BASIC_KARYAWAN"))) {
                    swipeRight(rightAnchor, leftAnchor, verticalAnchor);
                } else {
                    swipeLeft(rightAnchor, leftAnchor, verticalAnchor);
                }
                break;
            case "platinum":
                if (totalStaff.equals(dotenv.get("PROFESSIONAL_KARYAWAN"))) {
                    swipeRight(rightAnchor, leftAnchor, verticalAnchor);
                } else {
                    swipeRight(rightAnchor, leftAnchor, verticalAnchor);
                    swipeRight(rightAnchor, leftAnchor, verticalAnchor);
                }
                break;
            default:
                throw new RuntimeException("Unknown package: " + premium_package);
        }
    }

    public void goToHomePage() {
        backToHomePage();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
