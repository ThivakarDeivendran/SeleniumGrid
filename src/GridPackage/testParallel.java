package GridPackage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class testParallel {
	public static ThreadLocal<RemoteWebDriver> dr =new ThreadLocal<RemoteWebDriver>();
	static RemoteWebDriver driver= null;
	@BeforeMethod
	public void login() throws MalformedURLException {
		DesiredCapabilities capdesire =new DesiredCapabilities().firefox();
		capdesire.setBrowserName("firefox");
		capdesire.setPlatform(Platform.WINDOWS);
		driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capdesire);
		dr.set(driver);
		dr.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	@Test
	public void intialProcessMethod1() throws InterruptedException {

		dr.get().get("https://www.google.com/");
		dr.get().manage().window().maximize();
		dr.get().findElement(By.xpath("//div[@class='KxwPGc iTjxkf']/a[1]")).click();
		Thread.sleep(4000);
		dr.get().findElement(By.xpath("(//a[contains(text(),'Overview')])[1]")).click();
		Thread.sleep(2000);
	}
	@AfterMethod
	public void logoff() {
		dr.get().close();
		dr.set(null);
	}

}
