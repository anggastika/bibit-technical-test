package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

/**
 * Created by sekarayukarindra on 25/09/18.
 */
public class BaseStepDefinitions extends TestInstrument implements En {

    public BaseStepDefinitions() {

        And("user click \"([^\"]*)\" element", (String arg0) -> {
            bukalapak.iOSBasePage().tapElement(arg0);
        });

        And("user input \"([^\"]*)\" field with \"([^\"]*)\" text", (String arg0, String arg1) -> {
            bukalapak.iOSBasePage().typeAndEnterValueWithTimeOut(arg0, arg1);
        });

        And("user verify \"([^\"]*)\" exist", (String arg0) -> {
            bukalapak.iOSBasePage().verifyElementExist(arg0);
        });

        And("user verify \"([^\"]*)\" does not exist", (String arg0) -> {
            bukalapak.iOSBasePage().verifyElementNotExist(arg0);
        });

        Given("user back to home page", () -> {
            bukalapak.iOSBasePage().backToHomePage();
        });

        And("user scroll up to \"([^\"]*)\" element", (String arg0) -> {
            bukalapak.iOSBasePage().swipeDownToElement(arg0);
        });

        And("user scroll down to \"([^\"]*)\" element", (String arg0) -> {
            bukalapak.iOSBasePage().swipeUpToElement(arg0);
        });

        And("user scroll down to \"([^\"]*)\" element using native swipe", (String arg0) -> {
            bukalapak.iOSBasePage().nativeSwipeUpToElement(arg0);
        });

        And("user scroll up to \"([^\"]*)\" element using native swipe", (String arg0) -> {
            bukalapak.iOSBasePage().nativeSwipeDownToElement(arg0);
        });

        When("^user go to page using deeplink \"([^\"]*)\"", (String deeplinkURL) -> {
            bukalapak.iOSBasePage().openDeepLink(deeplinkURL);
        });

        When("^user go to page using deeplink from env \"([^\"]*)\"", (String envVar) -> {
            bukalapak.iOSBasePage().openDeeplinkFromEnv(envVar);
        });

        When("^admin get token for API exclusive", () -> {
            bukalapak.iOSBasePage().getTokenForAPIExclusive();
        });

        And("done", () -> {
            bukalapak.iOSBasePage().scenarioDone();
        });

        When("user do pull to refresh", () -> {
            bukalapak.iOSBasePage().doPullRefresh(1);
        });

        Given("the Bukalapak app had reset", () -> {
            bukalapak.iOSBasePage().resetApp();
        });

        Then("the Bukalapak app had close and app", () -> {
            bukalapak.iOSBasePage().closeApp();
            bukalapak.iOSBasePage().openApp();
        });

        And("user hide LeakLotek", () -> {
            bukalapak.iOSBasePage().hideLeakLotek();
        });

        And("user back to home page use deeplink", () -> {
            bukalapak.iOSBasePage().backToHomePageViaDeeplink();
        });

    }
}
