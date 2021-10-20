package bukalapak;

import org.junit.runner.RunWith;

@RunWith(io.cucumber.junit.Cucumber.class)
@io.cucumber.junit.CucumberOptions(
        features = {
                "classpath:features",
        },
        stepNotifications = true,
        plugin = {
                "pretty",
                "rerun:rerun/failed_scenarios.txt",
                "junit:target/report/junit/junit.xml",
                "json:target/json-report/cucumber-json-report.json",
        })
public class TestsRunner {
}