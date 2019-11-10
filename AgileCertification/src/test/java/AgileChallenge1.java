import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AgileChallenge1 {
	public WebDriver driver;
	private SoftAssertions softAssertions;

	@Before
    public void setUp() {
        softAssertions = new SoftAssertions();
    }
	@Test
	public void checkInvaildCertificationURL() throws InterruptedException {
		int respCode = 200;
		HttpURLConnection huc = null;
       
		String filePath = System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver";

		driver =new ChromeDriver();
		System.setProperty("webdriver.chrome.driver",filePath); 
		driver.get("https://agiletestingalliance.org/");
	    driver.manage().window().maximize();
	    Thread.sleep(8000);
		driver.findElement(By.xpath("//li/a[contains(text(),'Certifications')]")).click();
		Thread.sleep(5000);
		List<WebElement> listOfCertifications = driver.findElements(By.xpath("//div[@class='grid_12']/map/area"));	    
		System.out.println("Total "+listOfCertifications.size());
     	System.out.println("list of certifications");
		for(int i=0;i<listOfCertifications.size();i++)
		{
			String URL=listOfCertifications.get(i).getAttribute("href");
			System.out.println(URL);
			
		    try {
                huc = (HttpURLConnection)(new URL(URL).openConnection());
                
                huc.setRequestMethod("HEAD");
                
                huc.connect();
                
                respCode = huc.getResponseCode();
                
                softAssertions.assertThat(respCode).isEqualTo(200);
                    
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
   
                e.printStackTrace();
            }
        }

	}
	
	@After
    public void tearDown() {
        softAssertions.assertAll();
        driver.quit();
    }
 
}


