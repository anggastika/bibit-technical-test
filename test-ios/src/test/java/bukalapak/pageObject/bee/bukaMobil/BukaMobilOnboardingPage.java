package bukalapak.pageObject.bee.bukaMobil;

import bukalapak.data.BeeData;
import bukalapak.data.BukaMobilData;
import bukalapak.pageObject.BasePage;
import com.bukalapak.salad.util.SwipeSpeed;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import static bukalapak.TestInstrument.dotenv;

public class BukaMobilOnboardingPage extends BasePage {

    public BukaMobilOnboardingPage(IOSDriver<IOSElement> iosDriver) {
        super(iosDriver);
    }

    public void skipOnboarding() {
        if (!BukaMobilData.isOnboarded()) {
            validateDisplayed("BUKAMOBIL_ONBOARDING_CONTENT");
            validateDisplayed("BUKAMOBIL_ONBOARDING_IMG");
            tapLanjutkanButton();
            tapOnPilihDaerahAsal();
            tapOnProvince("Jawa Barat");
            tapOnCity("Bandung");
            BukaMobilData.setOnboarded(true);
        }
    }

    public void tapLanjutkanButton() {
        tapElement("BUKAMOBIL_ONBOARDING_LANJUTKAN_BTN");
    }

    public void tapOnPilihDaerahAsal() {
        tapElement("BUKAMOBIL_ONBOARDING_PILIH_DAERAH_ASAL_BTN");
    }

    public void tapOnProvince(String province) {
        waitForVisibilityOf(constructLocator("BUKAMOBIL_SPECIFIC_LOCATION", province));
        tapElement(constructLocator("BUKAMOBIL_SPECIFIC_LOCATION", province));
    }

    public void tapOnCity(String city) {
        waitForVisibilityOf(constructLocator("BUKAMOBIL_SPECIFIC_LOCATION", city), 3);
        tapElement(constructLocator("BUKAMOBIL_SPECIFIC_LOCATION", city));
        BukaMobilData.setLokasi(city);
    }
    public void selectCoveredLocation(String flag) {
        if (flag.equals("covered")) {
            tapOnProvince("DKI Jakarta");
            tapOnCity("Jakarta Pusat");
        } else {
            nativeSwipeUp();
            tapOnProvince("Aceh");
            tapOnCity("Aceh Barat");
        }
    }
}
