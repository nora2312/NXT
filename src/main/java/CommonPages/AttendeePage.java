package CommonPages;

import Configurations.Elements;
import TestData.ConfigurationData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class AttendeePage  {
    private WebDriver driver;
    private Elements elements;
    public AttendeePage(WebDriver driver)   {
        this.driver=driver;
        elements=new Elements(driver);
    }
    private By arrowToViewMenu= By.cssSelector("#col-6 > div > div:nth-child(3) > button > span > svg");
    private By registerationLabel=By.xpath("//span[contains(@class, 'Text_root__M6tno') and text()='Registration']");
  //  private By getRegisterationArrow=By.cssSelector("#root > div.flex.flex-col.w-full > div.flex.flex-col.w-full > main > div.relative > aside > div > main > div:nth-child(4) > div:nth-child(1) > div > div.w-4.h-4 > svg > path");
   private By attendanceLabel=By.xpath("//p[contains(@class, 'Text_root__M6tno') and text()='Attendees']");
   private By allUsersLabel=By.xpath("//p[contains(text(),'All Users')]");
   private By addUserButton=By.xpath("//span[contains(text(),'Add User')]");
   private By userDetailsLabel=By.xpath("//h2[contains(text(),'User Details')]");
   private By usernameTxt=By.id("username");
   private By firstNameTxt=By.id("firstName");
   private By lastNameTxt=By.id("lastName");
   private By emailTxt=By.id("email");
   private By createUserBtn=By.xpath("//button[contains(text(),'Create')]");
   private By cancelBtn=By.xpath("//button[contains(text(),'Cancel')]");
    public boolean   goToMenu()
    {
        elements.click(arrowToViewMenu);
       return elements.isElementVisible(registerationLabel);
    }
    public boolean clickRegistrationArrow()  {
            elements.click(registerationLabel);
        return  elements.isElementVisible(attendanceLabel);
    }

    public boolean clickAttendeeItem() {
        elements.click(attendanceLabel);
        return elements.isElementVisible(allUsersLabel);
    }
    public boolean clickAllUsers()
    {
        elements.click(allUsersLabel);
        return elements.isElementVisible(addUserButton);
    }
    public boolean clickAddUser()
    {
        elements.click(addUserButton);
        return elements.isElementVisible(userDetailsLabel);
    }

    public void adduser( ) throws InterruptedException {
        elements.write(firstNameTxt, ConfigurationData.fName);
        elements.write(lastNameTxt,ConfigurationData.lName);
        elements.write(emailTxt,generateRandomEmail());
        elements.clearText(usernameTxt);
        Thread.sleep(2000);
        elements.write(usernameTxt,generateRandomUserName());
        elements.clickUsingJavaScript(createUserBtn);
    }
    public static String generateRandomEmail() {
        // Define the characters allowed in the username and domain parts of the email
        String usernameChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String domainChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        // Define the length of the username and domain parts
        int usernameLength = 8; // You can adjust this as needed
        int domainLength = 5;   // You can adjust this as needed

        // Create random generators
        Random random = new Random();

        // Generate the username part of the email
        StringBuilder usernameBuilder = new StringBuilder();
        for (int i = 0; i < usernameLength; i++) {
            int index = random.nextInt(usernameChars.length());
            char randomChar = usernameChars.charAt(index);
            usernameBuilder.append(randomChar);
        }
        String username = usernameBuilder.toString();

        // Generate the domain part of the email
        StringBuilder domainBuilder = new StringBuilder();
        for (int i = 0; i < domainLength; i++) {
            int index = random.nextInt(domainChars.length());
            char randomChar = domainChars.charAt(index);
            domainBuilder.append(randomChar);
        }
        String domain = domainBuilder.toString();
        String timeStamp = GetCurrentTime();
        // Combine the username and domain to create the email
        return username + timeStamp + "@" + domain + ".com"; // You can replace ".com" with any other domain extension
    }
    public static String generateRandomUserName() {
        // Define the characters allowed in the username and domain parts of the email
        String usernameChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        // Define the length of the username and domain parts
        int usernameLength = 8; // You can adjust this as needed
        // Create random generators
        Random random = new Random();
        // Generate the username part of the email
        StringBuilder usernameBuilder = new StringBuilder();
        for (int i = 0; i < usernameLength; i++) {
            int index = random.nextInt(usernameChars.length());
            char randomChar = usernameChars.charAt(index);
            usernameBuilder.append(randomChar);
        }
        String username = usernameBuilder.toString();

        // Combine the username and domain to create the email
        return username ; // You can replace ".com" with any other domain extension
    }
    public static String GetCurrentTime() {
        DateFormat format = new SimpleDateFormat("hhmmssSSS");
        Date date = new Date();
        return format.format(date);
    }
    public boolean checkUserAdded()
    {
     return    elements.isElementVisible(cancelBtn);
    }
}
