package shoppersStop;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ShoppersStopChallenge {
	public WebDriver driver;
 
	@Test
	public void cityInShppersStop() throws InterruptedException {
		
		String filePath = System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver";

            driver =new ChromeDriver();
			System.setProperty("webdriver.chrome.driver",filePath); 
			driver.get("https://www.shoppersstop.com/");
			Thread.sleep(3000);
		    driver.manage().window().maximize();
		    Thread.sleep(8000);
			driver.findElement(By.xpath("//div//ul[@class='text-right']//li//a[contains(text(),'All Stores')]")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//div//span//select[@name='cityName']")).click();
			List<WebElement> listOfCities = driver.findElements(By.xpath("//div//span[@class='select-wrapper']//select[@name='cityName']//option"));	    
			
			String[] cities = {"Agra", "Bhopal", "Mysore"};
			System.out.println("list of cities");
			
			for(int i=0;i<listOfCities.size();i++){
				String Cities=listOfCities.get(i).getText();
				System.out.println(Cities);
		    }
			
			for(int i=0; i<cities.length;i++) {
			loop:
			for(WebElement e:listOfCities) {
				if(e.getText().equals(cities[i])) {
					assertEquals(e.getText(), cities[i]);
					break loop;
				}
			 }
		   }
      }
}
