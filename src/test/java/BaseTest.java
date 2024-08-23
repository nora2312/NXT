import Configurations.LoggerClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import TestData.ConfigurationData;

public class BaseTest {
    public static WebDriver driver;
    public static ExtentReports report ;
    public static ExtentTest logger;

    @BeforeClass
    public void setup() {
        // below system property is used to record all tests (passed & failed ) not only failed as default
        LoggerClass.logMessage("Starting Test Execution");

        ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir") + "src\\test\\java\\TestReports\\NXT-Web-Automation On "+ ConfigurationData.BrowserName.toLowerCase() +" "+ GetCurrentTime() + " .html"));
        report = new ExtentReports();
        report.attachReporter(extent);

        if (ConfigurationData.BrowserName.equalsIgnoreCase("CHROME")) {

            WebDriverManager.chromedriver().clearDriverCache().setup();
            WebDriverManager.chromedriver().clearResolutionCache().setup();


            //Remove the "enable automation" Bar , also remove save passwords Menu
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            //This line for updated version for chrome
            options.addArguments("--remote-allow-origins=*");
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(options);

        }

        else if (ConfigurationData.BrowserName.equalsIgnoreCase("FIREFOX")){

            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        else if (ConfigurationData.BrowserName.equalsIgnoreCase("Edge"))
        {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();

        }

        else{

         LoggerClass.logMessage("Browser Name is not correct");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(ConfigurationData.NxtPortal);
    }
    @AfterMethod
    public void getResult(ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            logger.fail(result.getThrowable());
            String temp=  Screenshot(driver);
            logger.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED " , ExtentColor.GREEN));
            String temp=  Screenshot(driver);
            logger.pass(result.getMethod().getMethodName(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        }
        else {
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            logger.skip(result.getThrowable());
        }
        report.flush();
    }
    @AfterClass
    public void teardown()
    {

        driver.quit();
        LoggerClass.logMessage("Finishing Test Execution");
    }
    // Method to get currect date & time to help with naming the Extent report file
    public static String GetCurrentTime () {
        DateFormat format = new SimpleDateFormat("dd MMMM YYYY hh.mm.ss");
        Date date=new Date();
        return  format.format(date);
    }
    // Take screenshot when test is failed then attached it to Extent report
    public static String Screenshot(WebDriver driver) {

        TakesScreenshot ts=(TakesScreenshot) driver;

        File src=ts.getScreenshotAs(OutputType.FILE);

        String path=System.getProperty("user.dir")+"\\src\\test\\java\\TestReports\\"+GetCurrentTime()+".jpg";

        File destination=new File(path);

        try
        {
            FileUtils.copyFile(src, destination);
        } catch (IOException e)
        {
            LoggerClass.logMessage("Capture Failed "+e.getMessage());
        }

        return path;
    }

}
