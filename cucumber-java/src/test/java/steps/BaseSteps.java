package steps;

import cucumber.api.java.en.*;
import static pages.BasePage.*;

public class BaseSteps {

    @Given("user go to homepage stockbit")
    public void hompage() {
        goToHomepage();
    }
}
