package framework;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.core.env.ConfigurableEnvironment;

import constants.EnvironmentProperties;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	protected FluentWait<WebDriver> wait;
	protected JavascriptExecutor js;
	private EnvironmentProperties environmentPropertiesObj;
	protected static ConfigurableEnvironment environmentProperties;
	protected WebDriver driver;


	public BasePage() {

		this.environmentPropertiesObj = new EnvironmentProperties();
		environmentProperties = environmentPropertiesObj.loadProperties();

		if (driver == null) {

			switch ((environmentProperties.getProperty("environments.path.browser"))) {
			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions();
				WebDriverManager.chromedriver().setup();
				//chromeOptions.addArguments("--headless", "--window-size=1366,1366");
				driver = new ChromeDriver(chromeOptions);
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "edge":
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				break;
			case "opera":
				WebDriverManager.operadriver().setup();
				driver = new OperaDriver();
				break;

			}

		}

		wait = new WebDriverWait(driver, 1);
		wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(1))
				.ignoring(Exception.class);
		;

	}

	public void getUrlDriver(String url) {

		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

	}

	public List<WebElement> getElementsByXpath(String xpath) {

		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
	}



	public WebElement getElementByXpath(String xpath) {
		return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}

	public WebElement getElementById(String id) {
		return wait.until(ExpectedConditions.elementToBeClickable(By.id(id)));
	}




	public void quitDriver() {
		if ((environmentProperties.getProperty("environments.path.browser")).equals("firefox")) {
			driver.quit();
		} else {
			driver.close();
			driver.quit();
			
		}

	}
}
