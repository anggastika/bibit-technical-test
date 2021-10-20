package bukalapak.pageObject;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static bukalapak.TestInstrument.dotenv;

public class PostpaidBasePage extends VpBasePage {

    public PostpaidBasePage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void isPageDisplayed(String element) {
        assertTrue(isElementVisible(element), "Landing page is not opened yet!");
    }

    public void skipOnboarding(String nextButton, String doneButton) {
        while (isElementVisible(nextButton)) {
            tapElement(nextButton);
            // using delay because there are no other element to wait for and
            // fast transition between clicking onboarding button
            waitFor(1);
        }
        if (isElementVisible(doneButton)) {
            tapElement(doneButton);
        }
    }

    public void skipScenarioIfToggledOff(String element) {
        if (!isElementVisible(element)) {
            //Assume.assumeTrue("Product toggled off!", false);
        }
    }

    public String generatePostpaidNumber(String env) {
        String number;
        String availList = dotenv.get(env);

        if (availList != null) {
            List<String> strAvailList = Arrays.asList(availList.split(","));
            number = strAvailList.get(new Random().nextInt(strAvailList.size()));
        } else {
            throw new NotFoundException("Please define env variable : " + env);
        }

        return number;
    }
}
