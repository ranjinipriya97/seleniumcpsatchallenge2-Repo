package nseindiaChallenge;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class nseindiaPrintingNames {
	public WebDriver driver;

	@BeforeTest
	public void setup() throws InterruptedException {
		
		String filePath = System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver";

		System.setProperty("webdriver.gecko.driver", filePath);
		driver = new FirefoxDriver();
	    driver.get("https://www.nseindia.com/");
        driver.manage().window().maximize();
     }
	
	@Test
    public void FindingValue() throws InterruptedException {
    driver.findElement(By.xpath("//input[@id='keyword']")).sendKeys("Eicher Motors Limited");
    Thread.sleep(1000);
    driver.findElement(By.xpath("//input[@id='keyword']")).sendKeys(Keys.ENTER);
    Thread.sleep(1000);
    System.out.println("1.Face Value "+driver.findElement(By.xpath("//li[@id='face']/span")).getText());
    System.out.println("2.52 week high "+driver.findElement(By.xpath("//span[@id='high52']/font")).getText());
    System.out.println("3.52 week low "+driver.findElement(By.xpath("//span[@id='low52']/font")).getText());

    }
	
   @AfterTest
   public void tearDown() {
		  driver.quit();
	}
	
}
