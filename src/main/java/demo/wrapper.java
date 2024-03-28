package demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class wrapper {
    private WebDriver driver;
    private WebDriverWait wait;
    private Duration timeOut;

    public wrapper(WebDriver driver,Duration timeOut){
        this.driver=driver;
        this.timeOut=timeOut;
        wait = new WebDriverWait(driver, timeOut);
    }

    public boolean advanceSendKeys(By locator,String keysToSend){
        try{
            // WebElement elem = wait.until(ExpectedConditions.visibilityOf(locator));
            WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            elem.sendKeys(keysToSend);
            return true;
        }
        catch(Exception e){
            System.out.println("Element is not Present");
            return false;
        }
    }

    public boolean advanceClick(By locator){
        try{
            WebElement elem = wait.until(ExpectedConditions.elementToBeClickable(locator));
            elem.click();
            return true;
        }
        catch(Exception e){
            System.out.println("Element is not clickable or not present");
            return false;
        }
    }
}
