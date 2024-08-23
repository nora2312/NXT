package Configurations;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.awt.*;
import java.time.Duration;

public class Elements {
    private WebDriver driver;

    FluentWait wait;

    public Elements(WebDriver driver) {
        this.driver = driver;

    }

    public void click(By locator) {
        waitForElementTobeClickable(locator);
        driver.findElement(locator).click();
        LoggerClass.logMessage("Successfully clicked on element with locator '" + locator.toString() + "'");
    }

    private void waitForElementTobeClickable(By locator) {
        try {
            wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(10)).ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (NoSuchElementException | TimeoutException e) {
            LoggerClass.logMessage("Failed to find element with locator " + locator.toString());
        }
    }

    public void write(By locator, String text) {
        waitUntilElementClickableThenSendkeys(locator, text);
   LoggerClass.logMessage("Successfully wrote text '" + text + "' in element with locator '" + locator.toString() + "'");
    }

    public void waitUntilElementClickableThenSendkeys(By locator, String data) {
        // Wait for Element to be visible.
        try {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofSeconds(30))
                    .pollingEvery(Duration.ofSeconds(10))
                    .ignoring(NoSuchElementException.class);

            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            element.sendKeys(data);

        } catch (Exception e) {
            LoggerClass.logMessage( "Element not found - sendkeys"+locator.toString());
        }
    }
    /**
     * Get element text After waiting for it to be visible inside the page
     * @param locator
     * @return text
     */
    public String getElementText(By locator) {
        waitForElementTobeVisible(locator);
        String text = driver.findElement(locator).getText();
    LoggerClass.logMessage("Successfully got text '" + text + "' from element with locator '" + locator.toString() + "'");
        return text;
    }
    /**
     * Wait for the element to be visible inside the page
     * @param locator
     */
    public void waitForElementTobeVisible(By locator) {
        try {
            wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            System.out.println(driver.findElement(locator).getText());
        } catch (NoSuchElementException | TimeoutException e) {
            //LoggerClass.logMessage("Failed to find element with locator " + locator.toString());
        }
    }
    public void clearText(By locator){
        waitForElementTobeVisible(locator);
        driver.findElement(locator).clear();
    }
    public boolean isElementVisible(By elementLocator) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void clickUsingJavaScript (By locator ) {
        waitForElementTobeClickable(locator);

        WebElement element = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

    }
}
