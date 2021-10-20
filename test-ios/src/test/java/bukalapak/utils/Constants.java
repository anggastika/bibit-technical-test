package bukalapak.utils;

/**
 * @author Irfan Mauludin, 05/11/18
 * <p>
 * Put any global static variable that can be reused in any class. To invoke the global static variable:
 * Constants.VARIABLE_NAME;
 */
public class Constants {
    public static final int TIMEOUT = 10;
    public static final int FIVE_SECS_TIMEOUT = 5;
    public static final int THREE_SECS_TIMEOUT = 3;
    public static final int THIRTY_SECS_TIMEOUT = 30;
    public static final int DEFAULT_SWIPE_DURATION = 3;
    public static final int DEFAULT_HOLD_DURATION = 3;
    public static final int DEFAULT_SWIPE_COUNT = 5;
    public static final String SRC_TEST_RESOURCES = System.getProperty("user.dir")+"/src/test/resources";
    public static final String SCREENSHOT_PATH = System.getProperty("user.dir")+"/screenshots_failed/";
    public static final String CAPABILITIES = SRC_TEST_RESOURCES + "/capabilities";
    public static final String ELEMENTS = SRC_TEST_RESOURCES + "/elements/";
    public static final String ENV = SRC_TEST_RESOURCES + "/env";
    public static final String APP_NAME = "bl_ios.app";
    private static final String HOME_DIR = System.getProperty("user.home");
    public static final String DEFAULT_APP_PATH = HOME_DIR;
    public static final String APP_PATH = System.getProperty("appPath");
}
