package bukalapak.pageObject;

import bukalapak.data.HelperData;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class NotificationsPage extends BasePage {

    public NotificationsPage(IOSDriver<IOSElement> iosDriver){
        super(iosDriver);
    }

    public void userOnNotificationsPage() {
        verifyElementExist("nofif_header");
        verifyElementExist("semua_category_link");
        verifyElementExist("pembeli_category_link");
        verifyElementExist("penjual_category_link");
        HelperData.setLastActionPage(new NotificationsPage(iosDriver));
    }

    public void verifyEmptyStateNotifications() {
        verifyElementExist("empty_notif_title");
        verifyElementExist("reset_filter_button");
        HelperData.setLastActionPage(new NotificationsPage(iosDriver));
    }

    public void checkIsNotifPageExist() {
        assertTrue(isElementVisible("notification_page"), "Tidak Berhasil Redirect ke Notif Page");
    }

    public void goToHomePage() {
        backToHomePageViaDeeplink();
        HelperData.setLastActionPage(new HomePage(iosDriver));
    }

    public void clickNotificationMenu() {
        waitForVisibilityOf("notif_icon");
        tapCenterOfElement("notif_icon");
        HelperData.setLastActionPage(new ChatListPage(iosDriver));
    }

    public void clickFirstThumbnail() {
        waitForVisibilityOf("notification_first_thumbnail",10);
        tapElement("notification_first_thumbnail");
    }

    public void validatePopUpNotificationThumbnail() {
        validateEnabled("notification_pop_up_thumbnail");
    }

    public void closePopUpNotificationThumbnail() {
        tapElement("notification_close_pop_up_thumbnail");
    }

    public void clickFirstNotification() {
        waitForVisibilityOf("notification_first_list",10);
        tapElement("notification_first_list");
    }
}
