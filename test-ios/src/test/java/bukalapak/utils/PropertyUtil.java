package bukalapak.utils;

import io.appium.java_client.MobileBy;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.io.*;
import java.net.ServerSocket;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Properties;

/**
 * Created by sekarayukarindra on 25/09/18.
 */
public class PropertyUtil {

    private final static Logger LOGGER = LogManager.getLogger(PropertyUtil.class);
    private static final String LOCATOR_NOT_IN_PROP_FILE = "Locator type '%s' not defined in the prop file!!";
    private static final String LOCATOR_BAD_DEFINED = "Locator %s is BAD DEFINED !!";
    private static String dynamicElementValue = "";
    private static Properties properties = null;
    private static Dotenv dataEnv;

    /*public synchronized void setDynamicElementValue(String value){
        DYNAMIC_ELEMENT_VALUE = value;
    }*/

    public Properties readCapabilitiesProperties() {
        String propertiesFileName = "capabilities/capabilities.properties";
        Properties properties = new Properties();

        try (FileInputStream Master = new FileInputStream(Constants.SRC_TEST_RESOURCES + "/" + propertiesFileName)) {
            properties.load(Master);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Error while read capabilities : " + e.getMessage());
        }
        return properties;
    }

    public Properties readCapabilitiesProperties(String capabilityType){
        String tmpCapabilityType = capabilityType.toLowerCase();
        String propertiesFileName = tmpCapabilityType + "-capabilities.properties";
        Properties properties = new Properties();

        try(FileInputStream capsFile = new FileInputStream(Constants.CAPABILITIES + "/" + tmpCapabilityType + "/" + propertiesFileName)){
            properties.load(capsFile);
        }catch (IOException e){
            throw new ExceptionInInitializerError("Error while read " + tmpCapabilityType + " capabilities : " + e.getMessage());
        }

        return properties;
    }

    public Properties readConfigProperties(String configType){
        String tmpConfigType = configType.toLowerCase();
        String propertiesFileName = tmpConfigType + "-config.properties";
        Properties properties = new Properties();

        try(FileInputStream configFile = new FileInputStream(Constants.CAPABILITIES + "/" + tmpConfigType + "/" + propertiesFileName)){
            properties.load(configFile);
        }catch (IOException e){
            throw new ExceptionInInitializerError("Error while read " + tmpConfigType + " config : " + e.getMessage());
        }

        return properties;
    }

    public Properties readBitbarConfig() {
        String propertiesFileName = "bitbar-config.properties";
        Properties properties = new Properties();

        try (FileInputStream Master = new FileInputStream(Constants.SRC_TEST_RESOURCES + "/capabilities/bitbar/" + propertiesFileName)) {
            properties.load(Master);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Error while read bitbar config : " + e.getMessage());
        }
        return properties;
    }

    public Properties readBitbarCapabilitiesProperties(){
        String propertiesFileName = "bitbar-capabilities.properties";
        Properties properties = new Properties();

        try (FileInputStream Master = new FileInputStream(Constants.SRC_TEST_RESOURCES + "/capabilities/bitbar/" + propertiesFileName)) {
            properties.load(Master);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Error while read bitbar capabilities : " + e.getMessage());
        }
        return properties;
    }

    public Properties readKobitonCapabilitiesProperties() {
        String propertiesFileName = "kobiton-capabilities.properties";
        Properties properties = new Properties();
        try {
            FileInputStream master = new FileInputStream(Constants.SRC_TEST_RESOURCES + "/capabilities/kobiton/" + propertiesFileName);
            properties.load(master);
            master.close();
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Error while read kobiton capabilities : " + e.getMessage());
        }
        return properties;
    }

    public Properties readKobitonConfigProperties() {
        String propertiesFileName = "kobiton-config.properties";
        Properties properties = new Properties();
        try {
            FileInputStream master = new FileInputStream(Constants.SRC_TEST_RESOURCES + "/capabilities/kobiton/" + propertiesFileName);
            properties.load(master);
            master.close();
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Error while read kobiton config : " + e.getMessage());
        }
        return properties;
    }

    public Properties readSaucelabCapabilitiesProperties() {
        String propertiesFileName = "saucelab-capabilities.properties";
        Properties properties = new Properties();
        try {
            FileInputStream master = new FileInputStream(Constants.SRC_TEST_RESOURCES + "/capabilities/saucelab/" + propertiesFileName);
            properties.load(master);
            master.close();
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Error while read sauce lab capabilities : " + e.getMessage());
        }
        return properties;
    }

    public Properties readSaucelabConfigProperties() {
        String propertiesFileName = "saucelab-config.properties";
        Properties properties = new Properties();
        try {
            FileInputStream master = new FileInputStream(Constants.SRC_TEST_RESOURCES + "/capabilities/saucelab/" + propertiesFileName);
            properties.load(master);
            master.close();
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Error while read sauce lab config : " + e.getMessage());
        }
        return properties;
    }

    public Properties readRealDeviceCapabilitiesProperties(){
        String propertiesFileName = "device-capabilities.properties";
        Properties properties = new Properties();
        try{
            FileInputStream propFile = new FileInputStream(Constants.SRC_TEST_RESOURCES + "/capabilities/device/" +propertiesFileName);
            properties.load(propFile);
            propFile.close();
        }catch (IOException e){
            throw new ExceptionInInitializerError("Error while reading device capabilities : "+e.getMessage());
        }
        return properties;
    }

    public Properties readGridConfigProperties() {
        String propertiesFileName = "grid-config.properties";
        Properties properties = new Properties();
        try {
            FileInputStream master = new FileInputStream(Constants.SRC_TEST_RESOURCES + "/capabilities/grid/" + propertiesFileName);
            properties.load(master);
            master.close();
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Error while read grid config : " + e.getMessage());
        }
        return properties;
    }

    public Properties readGridCapabilitiesProperties() {
        String propertiesFileName = "grid-capabilities.properties";
        Properties properties = new Properties();

        try (FileInputStream Master = new FileInputStream(Constants.SRC_TEST_RESOURCES + "/capabilities/grid/" + propertiesFileName)) {
            properties.load(Master);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Error while read gridcapabilities : " + e.getMessage());
        }
        return properties;
    }

    public static Dotenv getDataEnv() {
        return dataEnv;
    }

    public synchronized void loadPropertyFile() {
        String propertiesFileName = "element.properties";
        String envFile = "data.env";
        String elmtFile = Constants.SRC_TEST_RESOURCES +"/"+ propertiesFileName;
        if (properties == null) {
            properties = new Properties();
            try (FileInputStream Master = new FileInputStream(elmtFile)) {
                properties.load(Master);
                dataEnv = Dotenv.configure().directory(Constants.SRC_TEST_RESOURCES + "/env/").filename(envFile).load();
            } catch (IOException e) {
                throw new ExceptionInInitializerError("Error while load property file : " + e.getMessage());
            }
        }
    }

    public static String getPropertyValue(String key) {
        if (properties.getProperty(key) == null) {
            if (key.startsWith("id_") || key.startsWith("name_") || key.startsWith("label_") || key.startsWith("value_") ||
                    key.startsWith("classname_") || key.startsWith("labelcontain_") || key.startsWith("xpath_")) {
                return key;
            } else {
                throw new NoSuchFieldError("Property key not found in properties file:" + key);
            }
        } else {
            return properties.getProperty(key);
        }
    }

    public By getLocatorFromValue(String value) {
        if (!value.contains("_")) {
            LOGGER.error(String.format(LOCATOR_BAD_DEFINED, value));
            throw new NoSuchElementException(String.format(LOCATOR_BAD_DEFINED, value));
        }
        String[] values = value.split("_");
        String locatorType = values[0].toLowerCase();
        String locatorValue;
        locatorValue = value.substring(value.indexOf("_") + 1);

        if(locatorValue.contains("%s"))
        {
            locatorValue=String.format(locatorValue, dynamicElementValue);
        }

        switch (locatorType) {
            case "id":
                return MobileBy.ByAccessibilityId.AccessibilityId(locatorValue);
            case "name":
                return MobileBy.ByIosNsPredicate.iOSNsPredicateString("name == '" + locatorValue + "'");
            case "label":
                return MobileBy.ByIosNsPredicate.iOSNsPredicateString("label == '" + locatorValue + "'");
            case "value":
                return MobileBy.ByIosNsPredicate.iOSNsPredicateString("value == '" + locatorValue + "'");
            case "classname":
                return By.className(locatorValue);
            case "labelcontain":
                return MobileBy.ByIosNsPredicate.iOSNsPredicateString("label CONTAINS '" + locatorValue + "'");
            case "xpath":
                return By.ByXPath.xpath(locatorValue);
            default:
                throw new NoSuchElementException(String.format(LOCATOR_NOT_IN_PROP_FILE, locatorType));
        }
    }

    /*public By getLocator(String elementName) {
        String elementNameValue;
        try {
            elementNameValue = getPropertyValue(elementName);
            return getLocatorFromValue(elementNameValue);
        } catch (Exception e) {
            throw new NoSuchElementException(e.getMessage());
        }

    }*/

    public int getAvailablePort() {
        ServerSocket serverSocket;
        int avport = 0;
        try {
            serverSocket = new ServerSocket(0);
            avport = serverSocket.getLocalPort();
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("avail port : " + avport);
        return avport;
    }

    private static BufferedOutputStream createAppendableStream(File destination) {
        try {
            return new BufferedOutputStream(new FileOutputStream(destination, false));
        } catch (FileNotFoundException e) {
            throw new ExceptionInInitializerError("File not found!");
        }
    }

    private static void appendFile(OutputStream output, File source) {
        byte[] sep = IOUtils.LINE_SEPARATOR_UNIX.getBytes(StandardCharsets.UTF_8);
        try (InputStream input = new BufferedInputStream(new FileInputStream(source))) {
            IOUtils.copy(input, output);
            IOUtils.write(sep, output);
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Error while copying files!");
        }
    }

    private static void mergeFiles(File destination, File[] sources) {
        try (OutputStream output = createAppendableStream(destination)) {
            for (File source : sources) {
                appendFile(output, source);
            }
        } catch (IOException e) {
            throw new ExceptionInInitializerError("Error while merging files!");
        }
    }

    private static File[] listAllElementFile(String directory) {
        File folder = new File(directory);
        File[] files = folder.listFiles();
        Assert.assertNotNull("Element files are not found!", files);
        return files;
    }

    public void collectElementProperties() {
        String propertiesFileName = "element.properties";
        String elmtFile = Constants.SRC_TEST_RESOURCES + "/" + propertiesFileName;
        String propBaseDir = Constants.SRC_TEST_RESOURCES + "/elements/";
        mergeFiles(new File(elmtFile), listAllElementFile(propBaseDir));
    }
}
