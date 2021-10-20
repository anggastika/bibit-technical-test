package bukalapak.stepDefinitions;


import bukalapak.TestInstrument;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import com.bukalapak.salad.util.LogUtil;

public class Hook extends TestInstrument implements En {

    public Hook(){
        Before(0, () -> {
            setUp();
            Runtime.getRuntime().addShutdownHook(new Thread(this::tearDown));
        });

        Before(1, (Scenario scenario) -> {
            LogUtil.info("Running Scenario : " + scenario.getName());
            LogUtil.info("--------------- Beginning of Scenario ---------------");
        });

        After(0, () -> {
            LogUtil.info("--------------- End of scenario ---------------");
        });

        After(1, super::afterScenario);
    }

    @Before
    public static void beforeStep(Scenario mScenario){
        scenario = mScenario;
    }

//    @Before(order = 1)
//    public void beforeScenario(Scenario scenario) {
//        LOGGER.info("********* Automation execution started for scenario:" + scenario.getName());
//        setScenarioName(scenario.getName());
//        try {
//            Assume.assumeTrue(EXECUTE_TEST);
//        } catch (Exception e) {
//            tearDown();
//            throw new AssumptionViolatedException("Rest of the test cases will not be executed due to assumption violated:" + e.fillInStackTrace());
//        }
//    }
//
//    @Before(order = 0)
//    public void init() {
//        setUp();
//        Runtime.getRuntime().addShutdownHook(new Thread(this::tearDown));
//    }
//
//    @After
//    public void afterScenario(Scenario scenario) {
//        try {
//            Assume.assumeTrue(AppiumHelper.EXECUTE_TEST);
//            if (scenario.isFailed()) {
//                LOGGER.error("********* Automation execution error for scenario:" + scenario.getName());
//                AppiumHelper.saveScreenShot(scenario.getName().replace(' ', '_'), scenario);
//
//                resetApp();
//            } else {
//                LOGGER.info("********* Automation execution completed successfully for scenario:" + scenario.getName());
//            }
//        } catch (Exception e) {
//            throw new AssumptionViolatedException("Rest of the test cases will not be executed due to assumption violated:" + e.fillInStackTrace());
//        }
//    }
}
