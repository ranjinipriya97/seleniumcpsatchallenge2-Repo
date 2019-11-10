import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GainerAndLosers {
	private Workbook workbook;
	
	public WebDriver driver;

	@BeforeTest
	public void setup() throws InterruptedException {

		String filePath = System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver";

		System.setProperty("webdriver.chrome.driver", filePath);
		driver = new ChromeDriver();
	    driver.get("https://www.nseindia.com/");
        driver.manage().window().maximize();

}   
	@Test
	public void topLosserAndGainers() throws InterruptedException, IOException {
		Actions actions = new Actions(driver);

		WebElement target = driver.findElement(By.xpath("//li[@id='main_livemkt']"));

		actions.moveToElement(target).perform();
		
		driver.findElement(By.xpath("//li[@id='main_liveany_ttg']")).click();
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//ul[@class='tab']//li[1]")).click();
		
		List<WebElement>  col = driver.findElements(By.xpath("//table[@id='topGainers']/tbody/tr"));
		
		int count = 1;
		for(WebElement element:col)  {
			String filePath = System.getProperty("user.dir")+"/src/main/resources/excelData/";
	    	writeExcel(filePath,"nseindia.xlsx","Gainers", element.getText().split(" "), count);
	    	count++;
	    }
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//ul[@class='tab']//li[2]")).click();
		
		col = driver.findElements(By.xpath("//table[@id='topGainers']/tbody/tr"));
		count = 1;
		for(WebElement element:col)  {
			String filePath = System.getProperty("user.dir")+"/src/main/resources/excelData/";
	    	writeExcel(filePath,"nseindia.xlsx","Losers", element.getText().split(" "), count);
	    }
	}
	
	 public void writeExcel(String filePath,String fileName,String sheetName,String[] dataToWrite, int count) throws IOException{

        File file =    new File(filePath+"/"+fileName);
        FileInputStream inputStream = new FileInputStream(file);
        workbook = new XSSFWorkbook(inputStream);
	    Sheet sheet = workbook.getSheet(sheetName);
	    Row newRow = sheet.createRow(count);
	    for(int i = 0; i < dataToWrite.length; i++){
	        Cell cell = newRow.createCell(i);
	        cell.setCellValue(dataToWrite[i]);
	    }
	    inputStream.close();
	    FileOutputStream outputStream = new FileOutputStream(file);
	    workbook.write(outputStream);
	    outputStream.close();
		
	    }
}

