package bukalapak;

import bukalapak.utils.APICall;
import bukalapak.utils.Constants;
import bukalapak.utils.DataUtil;
import bukalapak.utils.PropertyUtil;
import cucumber.api.Scenario;
import com.bukalapak.salad.Salad;
import com.bukalapak.salad.util.Driver;
import com.bukalapak.salad.util.LogLevel;
import com.bukalapak.salad.util.LogUtil;
import io.appium.java_client.Setting;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author Irfan Mauludin, 26/09/19
 */
public class TestInstrument {

    private static Salad salad;
    private static boolean isRunning = false;
    protected static Bukalapak bukalapak;
    protected static IOSDriver<IOSElement> iosDriver;
    private static PropertyUtil propertyUtil = new PropertyUtil();
    private static String scenarioName;
    public static Scenario scenario;

    private LogLevel appiumLogLevel;
    private static Properties capabilitiesProperties = getCapabilitiesProperties();
    public static final Dotenv dotenv = Dotenv.load();
    private static final APICall API_CALL = new APICall();

    private void setAppiumLogLevel() {
        try {
            appiumLogLevel = LogLevel.valueOf(System.getProperty("logLevel"));
        } catch (Exception ex){
            appiumLogLevel = LogLevel.ERROR;
        }
    }

    private static Properties getCapabilitiesProperties() {
        Properties capabilitiesProperties = new Properties();
        try {
            FileInputStream properties = new FileInputStream("capabilities.properties");
            capabilitiesProperties.load(properties);
            properties.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return capabilitiesProperties;
    }

    private static void updateCapabilitiesFromSystemProp() {
        String value;
        for (Map.Entry<Object, Object> e : capabilitiesProperties.entrySet()) {
            value = (String) e.getValue();

            if (value.contains("{")) {
                value = value.replace('{', ' ');
                value = value.replace('}', ' ');
                value = System.getProperty(value.trim());
                capabilitiesProperties.setProperty((String) e.getKey(), value);
            }
        }
        //add wda port
        String wdaPort = String.valueOf(propertyUtil.getAvailablePort());
        capabilitiesProperties.setProperty(IOSMobileCapabilityType.WDA_LOCAL_PORT, wdaPort);
        LogUtil.info(capabilitiesProperties.toString());
    }

    public void setUp(){
        String elementProperties = Constants.ELEMENTS;
        updateCapabilitiesFromSystemProp();
        setAppiumLogLevel();
        LogUtil.info(capabilitiesProperties.toString());
        API_CALL.setForAPIcall();

        if(!isRunning){
            salad = new Salad(
                    capabilitiesProperties,
                    elementProperties,
                    Driver.XCUITEST,
                    appiumLogLevel);

            salad.start();

            isRunning = true;
            initiateSession();
        } else {
            LogUtil.info("Appium server has been started.");
        }
    }

    private void initiateSession(){
        salad.initiateSession(Driver.XCUITEST);
        iosDriver = salad.getIosDriver();
        iosDriver.setSetting(Setting.BOUND_ELEMENTS_BY_INDEX,true);
        bukalapak = new Bukalapak(iosDriver);
    }

    public void afterScenario(Scenario scenario){
        scenarioName = scenario.getName();

        if (scenario.isFailed()) {
            File srcFile = ((TakesScreenshot) iosDriver).getScreenshotAs(OutputType.FILE);
            byte[] screenshot = ((TakesScreenshot) iosDriver).getScreenshotAs(OutputType.BYTES);
            File imageFile = new File(Constants.SCREENSHOT_PATH + scenarioName + ".png");
            try {
                scenario.embed(screenshot,"image/png");
                FileUtils.copyFile(Objects.requireNonNull(srcFile), imageFile);
                LogUtil.info("Screenshot taken");
            } catch ( Exception e ) {
                LogUtil.error("Exception while taking screenshot ",e);
            } finally {
                iosDriver.resetApp();
                DataUtil.setDataAfterReset();
                LogUtil.info("Reset App");
            }
        }
        DataUtil.setDataAfterScenario();
    }

    public void tearDown(){
        salad.stop(Driver.XCUITEST);

        // Kill Simulator
        String iosSimulatorKillerPath = System.getProperty("user.dir") + "/libs/ios-simulator-killer-1.0.jar";
        String[] killIosSimulatorCommand = { "java","-jar",
                        iosSimulatorKillerPath,
                        capabilitiesProperties.getProperty("deviceName"),
                        capabilitiesProperties.getProperty("platformVersion")
                };
        runCommand(killIosSimulatorCommand);
    }

    private void runCommand(String[] command) {
        try{
            Process process = new ProcessBuilder(command).start();
            process.waitFor(1, TimeUnit.SECONDS);
        } catch (Exception e) {
           LogUtil.error("No command executed!",e);
        }
    }
}
