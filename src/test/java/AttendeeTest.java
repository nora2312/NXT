import CommonPages.AttendeePage;
import CommonPages.LoginPage;
import TestData.ConfigurationData;
import com.automation.remarks.video.annotations.Video;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;


public class AttendeeTest extends BaseTest {
    LoginPage loginPage;
    AttendeePage attendeePage;

    @BeforeClass
    public void initialize() {
        loginPage = new LoginPage(driver);
        attendeePage = new AttendeePage(driver);
        loginPage.loginWithvalidCredentials(ConfigurationData.AdminUserName, ConfigurationData.AdminPassword);
    }

    @Test(priority = 1, enabled = true)
    public void createAttendee( ) throws InterruptedException {
        logger = report.createTest("Go to attendees");
     Assert.assertTrue(attendeePage.goToMenu(),"menu not appear");
     Assert.assertTrue(attendeePage.clickRegistrationArrow(),"can't click on registation arrow");
     Assert.assertTrue(attendeePage.clickAttendeeItem(),"can't click on attendee item");
     Assert.assertTrue(attendeePage.clickAllUsers(),"Can't click on All Users");
     Assert.assertTrue(attendeePage.clickAddUser(),"can't add user");
   attendeePage.adduser ();
   Assert.assertTrue(attendeePage.checkUserAdded(),"user not added");

    }




}
