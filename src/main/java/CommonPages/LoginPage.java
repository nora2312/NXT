package CommonPages;

import Configurations.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class LoginPage {
    private WebDriver driver;
    private Elements elements;


    public LoginPage(WebDriver driver)   {
        this.driver=driver;
        elements=new Elements(driver);
    }
    private By userName=By.id("username");
    private By password=By.id("password");
    private  By loginButton=By.cssSelector("[class=\"Button_root__0RbKd Button_primary__NGIIN Button_xl__w2v80\"]");
    private By skipLabel=By.cssSelector("[class=\"Button_root__0RbKd Button_unstyled__ze+oA\"]");
private By  alertMessage= By.cssSelector("[class=\"Text_root__M6tno\"]");
private By userNameLabel=By.cssSelector("[class=\"Text_root__M6tno font-normal capitalize hidden sm:inline sm:w-[70px] truncate text-left\"]");
    public String clickLoginWithoutAnyCredentials() {
        elements.click(loginButton);
        return elements.getElementText(alertMessage);
    }
    public String loginWithInvalidCredentials(String AdminUserName,String AdminPassword)
    {
        elements.write(userName,AdminUserName);
        elements.write(password,AdminPassword);
        elements.click(loginButton);
        return elements.getElementText(alertMessage);
    }
    public void loginWithvalidCredentials(String AdminUserName,String AdminPassword)
    {
        elements.clearText(userName);
        elements.clearText(password);
        elements.write(userName,AdminUserName);
        elements.write(password,AdminPassword);
        elements.click(loginButton);
        elements.click(skipLabel);
       // return elements.getElementText(skipLabel);
    }
    public void clearUsernameAndPassword() {
        elements.clearText(userName);
        elements.clearText(password);
    }
    public String clickSkipButton()
    {
        elements.click(skipLabel);
        return elements.getElementText(userNameLabel);
    }
}
