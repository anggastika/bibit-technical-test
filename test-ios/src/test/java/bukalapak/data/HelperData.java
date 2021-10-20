package bukalapak.data;

import bukalapak.pageObject.BasePage;
import com.bukalapak.salad.util.LogUtil;

public class HelperData {

    private static BasePage lastActionPage;

    /**
     * Need handling for setLastActionPage that called in different page classes.
     * Since it requires pageObject with iosDriver object constructor.
     *
     * @param lastActionPage is instantiate of page object
     */
    public static void setLastActionPage(Object lastActionPage) {
        if (lastActionPage instanceof BasePage) {
            LogUtil.info("Setting last action page: " + lastActionPage.getClass().getSimpleName());
            HelperData.lastActionPage = (BasePage) lastActionPage;
        }else if(lastActionPage == null){
            HelperData.lastActionPage = null;
        }
    }

    public static BasePage getLastActionPage() {
        return lastActionPage;
    }

    public static void goToHomePage() {
        if (lastActionPage != null) {
            HelperData.lastActionPage.goToHomePage();
        }
    }
}
