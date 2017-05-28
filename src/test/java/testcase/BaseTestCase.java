package testcase;

import java.lang.reflect.Method;

import org.openqa.selenium.Platform;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import utility.WebDriverUtil;
import utility.WebDriverUtil.BrowserType;

/**
 * This is BaseTest Class contains methods for before/after suites, method.
 * All Test Classes will extend this class.
 * 
 * @author Pratik
 *
 */
public class BaseTestCase {

	BrowserType browserType = BrowserType.CHROME;
	Platform platform = Platform.WIN10;

	@BeforeSuite
	public void beforeSuit() {
		System.out.println("....Testing is started....");
	}

	@BeforeMethod
	public void beforeMethod(Method m) {
		System.out.println("Method:'" + m.getName() + "' is executing......");
		WebDriverUtil.createDriver(browserType, platform);
	}

	@AfterMethod
	public void afterMethod() {
		WebDriverUtil.getWebDriver().quit();
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("....Testing is ended....");
	}
}
