package bukalapak.pageObject.vp.insurance;

import bukalapak.data.HelperData;
import bukalapak.pageObject.BasePage;
import bukalapak.pageObject.HomePage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * @Author: Ayu Musfita
 * @Date: 16/07/20, Thu
 **/
public class LeadGeneratorDetailPage extends BasePage {

    public LeadGeneratorDetailPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void validateOnPage() {
        verifyElementExist("LEAD_GENERATOR_DETAIL_BANNER");
        verifyElementExist("LEAD_GENERATOR_DETAIL_REGISTER_BUTTON");
    }

    public void validateSectionDisplayed() {
        String[] allSection = {"Manfaat", "Cara beli", "Ketentuan", "Cara klaim", "Tanya jawab", "Kontak"};

        for (String section: allSection) {
            verifyElementExist(constructLocator("LEAD_GENERATOR_DETAIL_SECTION_TEXT", section));
        }
        HelperData.setLastActionPage(new LeadGeneratorDetailPage(iosDriver));
    }

    public void tapOnRegisterButton() {
        tapElement("LEAD_GENERATOR_DETAIL_REGISTER_BUTTON");
        HelperData.setLastActionPage(new LeadGeneratorDetailPage(iosDriver));
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
