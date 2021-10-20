package bukalapak.stepDefinitions;

import bukalapak.TestInstrument;
import cucumber.api.java8.En;

public class NotificationsStepDefinitions extends TestInstrument implements En {

    public NotificationsStepDefinitions() {

        Given("user is in \"notifications\" page", () -> {
            bukalapak.notificationsPage().userOnNotificationsPage();
        });

        Then("User should see notifications page", () -> {
            bukalapak.notificationsPage().userOnNotificationsPage();
        });

        Then("user should see empty state notifications", () -> {
            bukalapak.notificationsPage().verifyEmptyStateNotifications();
        });

        Then("^user should be redirect to notification page$", () -> {
            bukalapak.notificationsPage().checkIsNotifPageExist();
        });

        When("user navigate to \"notification\" page", () -> {
            bukalapak.notificationsPage().clickNotificationMenu();
        });

        When("user tap thumbnail on notification page", () -> {
            bukalapak.notificationsPage().clickFirstThumbnail();
        });

        Then("user will see pop up thumbnail on notification page", () -> {
            bukalapak.notificationsPage().validatePopUpNotificationThumbnail();
        });

        And("user close pop up thumbnail on notification page", () -> {
            bukalapak.notificationsPage().closePopUpNotificationThumbnail();
        });

        And("user tap first notification on notification page", () -> {
            bukalapak.notificationsPage().clickFirstNotification();
        });
    }
}
