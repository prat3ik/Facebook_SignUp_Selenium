package utility;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * This class represents the creation of thread-safe WebDriver instance
 * 
 * @author Pratik
 *
 */
public class WebDriverUtil {

	/**
	 * ThreadLocal variable which contains the {@link WebDriver} instance which
	 * is used to perform browser interactions with.
	 */
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

	public enum BrowserType {
		FIREFOX, CHROME;
	}

	/**
	 * Constructs a new {@link WebDriver} instance which is configured to use
	 * the capabilities defined by the browser and os parameters.
	 *
	 * @param browser
	 *            Represents the browser to be used as part of the test run.
	 * @param version
	 *            Represents the version of the browser to be used as part of
	 *            the test run.
	 * @param os
	 *            Represents the operating system to be used as part of the test
	 *            run.
	 * @param methodName
	 *            Represents the name of the test case that will be used to
	 *            identify the test on Sauce.
	 * @return
	 * @throws MalformedURLException
	 *             if an error occurs parsing the url
	 */
	public static void createDriver(BrowserType browser, Platform platform) {
		System.out.println("Creating WebDriver instance....");

		DesiredCapabilities dc = new DesiredCapabilities();
		WebDriver driver = null;

		// set desired capabilities to launch appropriate browser
		// capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
		// capabilities.setCapability(CapabilityType.PLATFORM, platform);

		dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		dc.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
		dc.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS, true);
		dc.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
		dc.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
		dc.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, 1);

		switch (browser) {
		case CHROME:
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--disable-web-security");
			options.addArguments("--no-proxy-server");
			options.addArguments("--disable-notifications");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);
			dc.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(dc);
			break;
		case FIREFOX:
			driver = new FirefoxDriver(dc);
			break;
		}
		driver.manage().window().maximize();

		// Launch remote browser and set it as the current thread
		webDriver.set(driver);
	}

	/**
	 * 
	 * @return WebDriver
	 */
	public static WebDriver getWebDriver() {
		if (webDriver == null) {
			throw new RuntimeException("WebDriver instance has not been created");
		} else
			return webDriver.get();
	}

}