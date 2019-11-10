package nseindiaExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReadFromExcel {
	
	private Workbook workbook;
	
	public WebDriver driver;

	@BeforeTest
	public void setup() throws InterruptedException {

		String filePath = System.getProperty("user.dir")+"/src/main/resources/drivers/geckodriver";

		System.setProperty("webdriver.gecko.driver", filePath);
		driver = new FirefoxDriver();
	    driver.get("https://www.nseindia.com/");
        driver.manage().window().maximize();
     }

	public List<String> readExcel(String filePath,String fileName,String sheetName) throws IOException{

	    File file =    new File(filePath+""+fileName);
	    FileInputStream inputStream = new FileInputStream(file);
	    workbook = new XSSFWorkbook(inputStream);
	    Sheet sheet = workbook.getSheet(sheetName);
	    int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
	    List<String> companyList = new ArrayList<String>();
	    for (int i = 0; i < rowCount+1; i++) {
	        Row row = sheet.getRow(i);
	        for (int j = 0; j < row.getLastCellNum(); j++) {
	        	companyList.add(row.getCell(j).getStringCellValue());
	        }
	    } 
	    return companyList;
	}  
	
	
	@Test
	public void readingFile() throws IOException, InterruptedException {
	
		String filePath = System.getProperty("user.dir")+"/src/main/resources/excelData/";
    	List<String> companyList = readExcel(filePath,"nseindia.xlsx","sheet1");
    	for(String companyName:companyList) {
    		 driver.findElement(By.xpath("//input[@id='keyword']")).sendKeys(companyName);
    		    Thread.sleep(1000);
    		    driver.findElement(By.xpath("//input[@id='keyword']")).sendKeys(Keys.ENTER);
    		    Thread.sleep(5000);
    		    System.out.println("1.Face Value "+driver.findElement(By.xpath("//span[@id='faceValue']")).getText());
    		    System.out.println("2.52 week high "+driver.findElement(By.xpath("//span[@id='high52']")).getText());
    		    System.out.println("3.52 week low "+driver.findElement(By.xpath("//span[@id='low52']")).getText());
    	}
	}

}
