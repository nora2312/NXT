import CommonPages.LoginPage;
import TestData.ConfigurationData;
import com.automation.remarks.video.annotations.Video;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {
    LoginPage loginPage;
    @BeforeClass
    public void initialize()
    {
      loginPage = new LoginPage(driver);
    }
    @Test(priority = 0)
    @Video
    public void testLoginWithNoCredentials() {
        logger = report.createTest("Test Login Functionality with No Credentials");
        logger.info("Login Using Empty username & password");
        //TODO change the expected message after fixing the space issue
        assertEquals(loginPage.clickLoginWithoutAnyCredentials(),"Invalid login information, please check and try again.","Something Wrong Happened");
    }
    @Test (priority = 1)
    @Video
    public void testLoginWithWrongCredentials()  {

        logger = report.createTest("Test Login Functionality with invalid Credentials");
        logger.info("Login Using Wrong username & password");
        assertEquals(loginPage.loginWithInvalidCredentials(ConfigurationData.AdminUserName ,ConfigurationData.WrongAdminPassword),"Invalid login information, please check and try again.","Something Wrong Happened");
    }
    @Test (priority = 2)
    @Video
    public void testLoginWithUsernameNotInSystem() {

        logger = report.createTest("Test Login Functionality with Email not in the System");
        logger.info("Login Using Wrong username & password");
        loginPage.clearUsernameAndPassword();
        assertEquals(loginPage.loginWithInvalidCredentials(ConfigurationData.WrongAdminUsername,ConfigurationData.WrongAdminPassword),"Invalid login information, please check and try again.","Something Wrong Happened");
    }
    @Test (priority = 3)
    @Video
    public void testLoginWithVaildCredentials()  {

        logger = report.createTest("Test Login Functionality with valid Credentials");
        logger.info("Login Using Vaild username & password");
       loginPage.loginWithvalidCredentials(ConfigurationData.AdminUserName ,ConfigurationData.AdminPassword);
    //    assertEquals(loginPage.clickSkipButton(),ConfigurationData.AdminUserName,"add skip button failed");
    }

}
