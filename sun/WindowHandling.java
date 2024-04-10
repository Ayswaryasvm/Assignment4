package assignment4.sun;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowHandling {
	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys("Demosalesmanager");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.partialLinkText("CRM")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.linkText("Merge Contacts")).click();
		driver.findElement(By.xpath("//img[@alt='Lookup']")).click();
		Set<String> next = driver.getWindowHandles();
		List<String> head = new ArrayList<String>(next);
		driver.switchTo().window(head.get(1));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		driver.switchTo().window(head.get(0));
		
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		Set<String> next1 = driver.getWindowHandles();
		List<String> head1 = new ArrayList<String>(next1);
		driver.switchTo().window(head1.get(1));
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[2]")).click();
		driver.switchTo().window(head1.get(0));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
		Alert a = driver.switchTo().alert();
		a.accept();
		String title = driver.getTitle();
		System.out.println(title);
		if(title.contains("View Contact"))
		{
			System.out.println("Successfully Merged");
		}
		driver.close();
		
		
	}

}
