package nseindiaChallenge;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class nseindianMinimumValue {
	public WebDriver driver;
	
	@BeforeTest
	public void setup() {
		String filePath = System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver";

		driver =new ChromeDriver();
		System.setProperty("webdriver.chrome.driver",filePath); 
		driver.get("https://www.nseindia.com/");
	    driver.manage().window().maximize();
		
	}

	@Test
	public void testCase() throws InterruptedException {

    List<WebElement> listOfNames = driver.findElements(By.xpath("//ul[@class='advanceTab']//li"));	    
	System.out.println("list of names and  their numbers from market: ");
	
	HashMap<String, Integer> market = new HashMap<String, Integer>();
	for(int i=0;i<listOfNames.size();i++){
		String id=listOfNames.get(i).findElement(By.tagName("p")).getText();
		String value=listOfNames.get(i).findElement(By.tagName("span")).getText();
		market.put(id, Integer.valueOf(value)); 
	}
	  for(Entry<String, Integer> entry : market.entrySet())
	    {   
	         System.out.println(entry.getKey() + " " +entry.getValue());
	    }
	  
	 
     	
    Integer minValue = Collections.min(market.values()); 
    System.out.println("The Minimum number:"+getKey(market, minValue)+" "+minValue);
    }
	
	public static <K, V> K getKey(Map<K, V> map, V value) {
		return map.keySet()
					   .stream()
					   .filter(key -> value.equals(map.get(key)))
					   .findFirst().get();
	}
	
	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
