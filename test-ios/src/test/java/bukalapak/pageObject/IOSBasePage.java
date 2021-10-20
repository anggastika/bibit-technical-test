package bukalapak.pageObject;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

/**
 * Created by nurdiansetyawan on 29/01/20.
 * This class acts as page object of base step
 */
public class IOSBasePage extends BasePage {

    public IOSBasePage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

}
