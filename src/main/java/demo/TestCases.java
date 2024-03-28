package demo;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(5).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        // driver.close();
        driver.quit();

    }

    public void testCase01() throws InterruptedException {
        System.out.println("Start Test case: testCase01");
        wrapper wrap = new wrapper(driver, Duration.ofSeconds(5));
        driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");
        // Sending Name to Name TextBox
        wrap.advanceSendKeys(By.xpath("//input[contains(@class,'whsOnd') and @type='text']"), "Yash");
        // Sending Message to Why are you practicing Automation? textbox
        long epoch = System.currentTimeMillis() / 1000;
        wrap.advanceSendKeys(By.tagName("textarea"), "I want to be the best QA Engineer! " + epoch);
        // Clicking on 0-2 experience
        wrap.advanceClick(By.xpath("//div[contains(@class,'AB7Lab')]"));
        // Selecting Java,Selenium and TestNG in checkboxes
        List<WebElement> checkBox = driver.findElements(By.xpath("//div[contains(@class,'uHMk6b')]"));
        int i = 1;
        for (WebElement elem1 : checkBox) {
            driver.executeScript("arguments[0].scrollIntoView(true)", elem1);
            if (i == 3) {
                i++;
                continue;
            }
            elem1.click();
            i++;
        }
        // Clicking on How should be addressed? dropdown menu
        wrap.advanceClick(By.xpath("//div[contains(@class,'e2CuFe')]"));
        Thread.sleep(1000);
        // Selecting Mr as option
        List<WebElement> list = driver.findElements(By.xpath("//span[text()='Mr']"));
        for (WebElement elem : list) {
            if (elem.getText().equals("Mr")) {
                elem.click();
            }
        }
        Thread.sleep(500);

        // Sending date by subtracting 7 days
        LocalDate date = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd");
        int fdate = Integer.parseInt(date.format(format));
        fdate = fdate - 7;
        wrap.advanceSendKeys(By.xpath("//input[@type='date']"), String.valueOf(fdate));
        wrap.advanceSendKeys(By.xpath("//input[@type='date']"), String.valueOf(date.getMonthValue()));
        wrap.advanceSendKeys(By.xpath("//input[@type='date']"), String.valueOf(date.getYear()));

        // Sending Current time in time textBox
        LocalTime time = LocalTime.now();
        wrap.advanceSendKeys(By.xpath("//input[@role='combobox' and @aria-label='Hour']"),
                String.valueOf(time.getHour()));
        wrap.advanceSendKeys(By.xpath("//input[@role='combobox' and @aria-label='Minute']"),
                String.valueOf(time.getMinute()));
        // Clicking on AM-PM dropdown menu
        boolean status = wrap
                .advanceClick(By.xpath("//span[(text()='AM' or text()='PM') and contains(@class,'vRMGwf')]"));
        // Selecting AM or PM according to time.
        if (status) {
            if (time.getHour() >= 12) {
                wrap.advanceClick(By.xpath("//span[text()='PM' and contains(@class,'vRMGwf')]"));
            } else {
                wrap.advanceClick(By.xpath("//span[text()='AM' and contains(@class,'vRMGwf')]"));
            }
        }
        // Navigating to amazon Website
        driver.navigate().to("https://www.amazon.in/");
        // Dismissing alert using dismiss method
        driver.switchTo().alert().dismiss();
        // Submitting the form and checking whether it is submitted or not.
        wrap.advanceClick(By.xpath("//span[text()='Submit']"));
        WebElement response = driver.findElement(By.xpath("//div[contains(text(),'Thanks for your response')]"));
        System.out.println(
                "TestCase Step: testCase01 :-" + (response.getText().contains("Thanks") ? "Passed" : "Failed"));
        System.out.println("End Test case: testCase01");
    }

}
