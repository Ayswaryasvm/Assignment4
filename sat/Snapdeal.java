package assignment4.sat;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Snapdeal {
	public static void main(String[] args) throws InterruptedException, IOException {
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[@class='catText']")).click();
		driver.findElement(By.linkText("Sports Shoes")).click();
		WebElement c = driver.findElement(By.xpath("//div[@class='child-cat-count ']"));
		String countss=c.getText();
		System.out.println("Count of Sports Shoes are  :"+countss);
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//div[@class='child-cat-name '])[2]")).click();
		driver.findElement(By.xpath("//div[@class='sort-drop clearfix']")).click();
		Thread.sleep(2000);
		 driver.findElement(By.xpath("//li[@class='search-li']")).click();
	     Thread.sleep(3000);
	        List<WebElement> productPrices = driver.findElements(By.xpath("//span[contains(@id,'display-price-')]"));
	        int count = 0;
	        for (int i = 1; i < productPrices.size(); i++) {
	            String lowestPrice = driver.findElement(By.xpath("(//span[contains(@id,'display-price-')])["+i+"]")).getText();
	            //Rs. 599
	            String lp = lowestPrice.replaceAll("[^0-9]", "");
	            String highestPrice = driver.findElement(By.xpath("(//span[contains(@id,'display-price-')])["+(i+1)+"]")).getText();
	            String hp = highestPrice.replaceAll("[^0-9]", "");
	            
	            if(Integer.parseInt(lp)>Integer.parseInt(hp)) {
	                System.out.println("Values are not sorted");
	                count++;
	                break;
	            }
	        }
	        
	        if(count==0) {
	            System.out.println("All the values are sorted from lowest to highest prices");
	        }
		 Thread.sleep(3000);
		 WebElement s = driver.findElement(By.xpath("//span[@class='filter-prod-count']"));
		 Actions scrol=new Actions(driver);
		 scrol.scrollToElement(s).perform();
		 WebElement mi = driver.findElement(By.xpath("//input[@class='input-filter']"));
		 mi.clear();
		 mi.sendKeys("500");
		 WebElement mx = driver.findElement(By.xpath("(//input[@class='input-filter'])[2]"));
		 mx.clear();
		 mx.sendKeys("700");
		 driver.findElement(By.xpath("//div[contains(@class,'price-go-arrow')]")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//label[@for='Color_s-Black']")).click();
		 Thread.sleep(3000);
		 WebElement filter1 = driver.findElement(By.xpath("//div[@class='filters']/div"));
		 String valid1 = filter1.getText();
		 System.out.println(valid1);
		 WebElement filter2 = driver.findElement(By.xpath("(//div[@class='filters']/div)[2]"));
		 String valid2 = filter2.getText();
		 System.out.println(valid2);
		 if(valid1.contains("Rs. 500 - Rs. 700")&& valid2.contains("Black"))
		 {
			 System.out.println("Filter Applied");
		 }
		 else {
			 System.out.println("Filter not Applied");
		 }
		 Thread.sleep(1000);
		 WebElement Quick = driver.findElement(By.xpath("//div[@class='clearfix row-disc']"));
		 Actions builder=new Actions(driver);
		 builder.moveToElement(Quick).click().perform();
		 Thread.sleep(2000);
		System.out.println(driver.getTitle());
		File sn = driver.getScreenshotAs(OutputType.FILE);
		File screens= new File("snaps/submitsnap.png");
		FileUtils.copyFile(sn,screens);
		 WebElement co = driver.findElement(By.xpath("//span[@class='payBlkBig']"));
		 String cost = co.getText();
		 System.out.println("Cost of first product is   :"+ cost);
		 WebElement d = driver.findElement(By.xpath("//span[@class='percent-desc ']"));
		 String discount = d.getText();
		 System.out.println("Discount of first product is   :"+discount);
		driver.close();
	}
	
}

