package bukalapak.pageObject;

import bukalapak.TestInstrument;
import bukalapak.data.HelperData;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.junit.Assert;

/**
 * Created by Ferawati.
 */
public class TemanCuanPage extends BasePage {

    public TemanCuanPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void goToPage() {
        openDeepLink("/referral");
    }

    public void userOnTemanCuanPage() {
        waitForVisibilityOf("TEMAN_CUAN_TITLE_TEXT", 50);
        waitForVisibilityOf("CARA_MAIN_TEXT", 50);
        validateDisplayed("TEMAN_CUAN_TITLE_TEXT");
        validateDisplayed("CARA_MAIN_TEXT");
        HelperData.setLastActionPage(new TemanCuanPage(iosDriver));
    }

    public void goToHomePage() {
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }
}
