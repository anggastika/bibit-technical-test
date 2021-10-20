package steps;

import config.BaseClass;
import cucumber.api.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber-reports/cucumber.html",
                "json:target/cucumber-reports/cucumber.json"
        },
        features = {"src/test/resources/features"},
        glue = {"steps"},
        tags = "@test"
)

public class TestRunner extends BaseClass {
}
