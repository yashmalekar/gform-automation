package demo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases{
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");
        WebDriverManager.chromedriver().timeout(30).setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        // driver.close();
        driver.quit();

    }

    
    public  void testCase01() throws InterruptedException{
        System.out.println("Start Test case: testCase01");
        driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");
        driver.findElement(By.xpath("//input[contains(@class,'whsOnd') and @type='text']")).sendKeys("Yash");//sending Name to Name textBox
        Thread.sleep(1000);
        long epoch = System.currentTimeMillis()/1000;//Calculated epoch time
        driver.findElement(By.tagName("textarea")).sendKeys("I want to be the best QA Engineer! " + epoch);//Sending text to Why are you practing automation
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[contains(@class,'AB7Lab')]")).click(); // Clicking on desired experience
        //Below is the code for selecting what I have learned in Crio.do
        List<WebElement> checkBox = driver.findElements(By.xpath("//div[contains(@class,'uHMk6b')]"));
        int i=1;
        for(WebElement elem1:checkBox){
            driver.executeScript("arguments[0].scrollIntoView(true)", elem1);
            if(i==3){
                i++;
                continue;
            }
            elem1.click();
            i++;
        }
        Thread.sleep(1000);
        //Below is the code for selection of how you should be addressed?
        driver.findElement(By.xpath("//div[contains(@class,'e2CuFe')]")).click();
        Thread.sleep(1000);
        List<WebElement> list = driver.findElements(By.xpath("//span[text()='Mr']"));
        for(WebElement a:list){
            if(a.getText().equals("Mr")){
                a.click();
            }
        }
        Thread.sleep(1000);
        //Below is the code for date 7 days ago
        LocalDate date = LocalDate.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd");
        int fdate = Integer.parseInt(date.format(format));
        fdate = fdate-7;
        driver.findElement(By.xpath("//input[@type='date']")).sendKeys(String.valueOf(fdate));
        driver.findElement(By.xpath("//input[@type='date']")).sendKeys(String.valueOf(date.getMonthValue())); 
        driver.findElement(By.xpath("//input[@type='date']")).sendKeys("2024");
        Thread.sleep(1000);
        //Sending current time
        List<WebElement> times = driver.findElements(By.xpath("//input[@role='combobox']"));
        LocalTime time = LocalTime.now();
        times.get(0).sendKeys(String.valueOf(time.getHour()));
        times.get(1).sendKeys(String.valueOf(time.getMinute()));
        //Submitting the form
        driver.findElement(By.xpath("//span[text()='Submit']")).click();
        WebElement response = driver.findElement(By.xpath("//div[contains(text(),'Thanks for your response')]"));
        System.out.println("TestCase Step: testCase01 :-"+ (response.getText().contains("Thanks")?"passed":"failed"));
        // driver.findElement(By.xpath("//div[@class='MocG8c HZ3kWc mhLiyf LMgvRb KKjvXb']")).click();
        Thread.sleep(4000);
        System.out.println("end Test case: testCase01");
    }
    

}
