import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChildWindow {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\work\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/popup.php");
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();

		String MainWindow = driver.getWindowHandle();
		
		// To handle all new opened window.
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {
			String ChildWindow = i1.next();

			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {

				// Switching to Child window
				driver.switchTo().window(ChildWindow);
				driver.findElement(By.name("emailid")).sendKeys("gaurav.3n@gmail.com");

				driver.findElement(By.name("btnLogin")).click();
				System.out.println(driver.getTitle());
				Thread.sleep(3000);
				System.out.println(driver.findElement(By.cssSelector("h2[style='color:#800080;font-size:28px;font-weight:700;']")).getText());
				// Closing the Child Window.
				driver.close();
			}
		}
		// Switching to Parent window i.e Main Window.
		driver.switchTo().window(MainWindow);
		System.out.println(driver.getTitle());
	}
}
