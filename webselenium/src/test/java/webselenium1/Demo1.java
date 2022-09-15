package webselenium1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.expectThrows;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo1 {
	
	WebDriver driver;
	String lastwindow;
	
	@BeforeTest
	public void beforetest()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		//driver.get("https://demo.guru99.com/test/newtours/index.php");
		driver.get("https://demoqa.com/alerts");
		driver.get("https://demoqa.com/browser-windows");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		String expectedtitle = driver.getTitle();
//		System.out.println(expectedtitle);
//		String actualtitle = expectedtitle;
//		Assert.assertEquals(expectedtitle, actualtitle);
//		String expectedurl = driver.getCurrentUrl();
//		System.out.println(expectedurl);
//		String actualurl = expectedurl;
//		Assert.assertEquals(expectedurl, actualurl);
		
		
		
	}
	
	@Test(enabled=false)
	public void testcase() throws InterruptedException
	{
		driver.findElement(By.xpath("//*[@name='userName']")).sendKeys("pavi");
		driver.findElement(By.xpath("//*[@name='password']")).sendKeys("pavi123");
		driver.findElement(By.xpath("//*[@name='submit']")).click();
		String expectedmessage = driver.findElement(By.xpath("//tbody//tr//td//h3")).getText();
		System.out.println(expectedmessage);
		String actualmessage = expectedmessage;
		Assert.assertEquals(expectedmessage, actualmessage);
		driver.findElement(By.linkText("Flights")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@name='tripType' and @value='oneway']")).click();
		Thread.sleep(3000);
		WebElement dd = driver.findElement(By.xpath("//*[@name='passCount']"));
		Select sell = new Select(dd);
		sell.selectByIndex(3);
		Thread.sleep(3000);
		WebElement dd1 = driver.findElement(By.xpath("//*[@name='fromPort']"));
		Select sell1 = new Select(dd1);
		sell1.selectByValue("London");
		Thread.sleep(3000);
		WebElement dd2 = driver.findElement(By.xpath("//*[@name='toPort']"));
		Select sell2= new Select(dd2);
		sell2.deselectByVisibleText("Acapulco");
		//here we are printing the list of values//
		List<WebElement>values = sell2.getOptions();
		System.out.println(values.size());
		for(int i=0;i<values.size();i++)
		{
			System.out.println(values.get(i).getText());
		}
		
}
	
	@Test(enabled=false)
	public void alerts() throws InterruptedException {
		
		driver.findElement(By.id("alertButton")).click();
		Alert alt = driver.switchTo().alert();
		System.out.println(alt.getText());
		alt.accept();
		Thread.sleep(3000);
		
		driver.findElement(By.id("timerAlertButton")).click();
		//WebDriverWait webdwait = new WebDriverWait(driver, Duration.ofSeconds(180));
//		WebDriverWait webdwait = new WebDriverWait(driver, Duration.ofSeconds(180));
//		webdwait.until(ExpectedConditions.alertIsPresent());
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.alertIsPresent());
		Thread.sleep(5000);
	alt.accept();
	Thread.sleep(3000);
	driver.findElement(By.id("confirmButton")).click();
	System.out.println(alt.getText());
	alt.dismiss();
	Thread.sleep(3000);
	
	driver.findElement(By.id("promtButton")).click();
	Thread.sleep(3000);
	alt.sendKeys("pavi");
	System.out.println(alt.getText());
	alt.dismiss();
		
	}
	
	@Test
	public void windowhandle()
	{
	String parentwindow =	driver.getWindowHandle();
	System.out.println(parentwindow);
	WebElement windobtn = driver.findElement(By.id("windowButton"));
	for(int i=0;i<3;i++)
	{
		windobtn.click();
	}
	Set<String>allwindow  = driver.getWindowHandles();
	System.out.println(allwindow);
	for(String windowhandle:allwindow )
	{
		WebDriver window = driver.switchTo().window(windowhandle);
		driver.get("https://www.google.com");
	lastwindow = windowhandle;
		
	}
	
	driver.switchTo().window(parentwindow);
	driver.get("https://www.gmail.com");
	driver.close();
	
	driver.switchTo().window(lastwindow);
	driver.get("https://www.facebook.com");
	}
	
	@AfterTest
	public void aftertest()
	{
		driver.close();
	}

	
}
