package assignment4.sat;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Amazon {
	public static void main(String[] args) throws InterruptedException, IOException {
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.findElement(By.xpath("//input[@class='nav-input nav-progressive-attribute']")).sendKeys("oneplus 9 pro");
		driver.findElement(By.id("nav-search-submit-button")).click();
		WebElement p = driver.findElement(By.xpath("//span[@class='a-price-whole']"));
		String price = p.getText();
		System.out.println("Price of the first product    :" + price);
		WebElement r = driver.findElement(By.xpath("//span[@class='a-size-base s-underline-text']"));
		String rate = r.getText();
		System.out.println("Number of customer ratings for the first displayed product is   :" + rate);
		driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")).click();
		Set<String> next = driver.getWindowHandles();
		List<String> head = new ArrayList<String>(next);
		driver.switchTo().window(head.get(1));
		System.out.println(driver.getTitle());
		File ss = driver.getScreenshotAs(OutputType.FILE);
		File screen = new File("./snaps/submitsnap.png");
		FileUtils.copyFile(ss,screen);
		driver.findElement(By.id("add-to-cart-button")).click();
		Thread.sleep(3000);
		WebElement t = driver.findElement(By.xpath("//span[@class='a-size-base-plus a-color-price a-text-bold']/span"));
		String total = t.getText();
		System.out.println("Cart SubTotal is  :" + total);
		if (total.contains(price)) {
			System.out.println("Cart SubTotal Verified");
		}
		driver.close();
		driver.switchTo().window(head.get(0));
		driver.close();

	}
}
