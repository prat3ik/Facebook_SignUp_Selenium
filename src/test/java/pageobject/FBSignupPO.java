package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.WebDriverUtil;

/**
 * This class represents the Page Objects for Facebook sign up page.
 * @author Pratik
 *
 */
public class FBSignupPO {

	final String FB_URL = "https://www.facebook.com/";
	
	public FBSignupPO() {
		PageFactory.initElements(getWebDriver(), this);
		getWebDriver().navigate().to(FB_URL);
		new WebDriverWait(getWebDriver(), 20).until(ExpectedConditions.elementToBeClickable(createAnAccount));
	}

	protected WebDriver getWebDriver() {
		return WebDriverUtil.getWebDriver();
	}

	@FindBy(css = "input[name='firstname']")
	WebElement firstName;

	public WebElement getFirstName() {
		return firstName;
	}

	/**
	 * It will fill First Name
	 * 
	 * @param firstName
	 */
	public void fillFirstName(String firstName) {
		this.firstName.sendKeys(firstName);
	}

	@FindBy(css = "input[name='lastname']")
	WebElement lastName;

	/**
	 * It will fill Last Name
	 * 
	 * @param lastName
	 */
	public void fillLastName(String lastName) {
		this.lastName.sendKeys(lastName);
	}

	public WebElement getLastName() {
		return lastName;
	}

	@FindBy(css = "input[name='reg_email__']")
	WebElement emailOrMobile;

	/**
	 * It will fill Email/MobileNo. Details
	 * 
	 * @param emailOrNo
	 */
	public void fillEmailIdOrMobileNo(String emailOrNo) {
		this.emailOrMobile.sendKeys(emailOrNo);
	}

	public WebElement getEmailOrMobile() {
		return emailOrMobile;
	}

	@FindBy(css = "input[type='password'][aria-label='New password']")
	WebElement password;

	/**
	 * It will fill Password
	 * 
	 * @param password
	 */
	public void fillPassword(String password) {
		this.password.sendKeys(password);
	}

	public WebElement getPassword() {
		return password;
	}

	@FindBy(css = "select[id='day']")
	WebElement daySelector;

	protected void selectDay(String day) {
		new Select(daySelector).selectByVisibleText(day);
	}

	@FindBy(css = "select[id='month']")
	WebElement monthSelector;

	protected void selectMonth(String month) {
		new Select(monthSelector).selectByVisibleText(month);
	}

	@FindBy(css = "select[id='year']")
	WebElement yearSelector;

	protected void selectYear(String year) {
		new Select(yearSelector).selectByValue(year);
	}
	

	/**
	 * Select day in D/DD format like '1', '20' etc..
	 * Select month text in Mmm format as 'Jan','Feb' etc..
	 * Select Year in YYYY format like '2015'.
	 *
	 * @param day
	 * @param month
	 * @param year
	 */
	public void selectBirthDate(String day, String month, String year){
		selectDay(day);
		selectMonth(month);
		selectYear(year);
	}

	@FindBy(css = "input[type='radio'][value='1']")
	WebElement femaleRadioButton;

	@FindBy(css = "input[type='radio'][value='2']")
	WebElement maleRadioButton;

	public enum Gender {
		Male, Female;
	}

	/**
	 * This method will select gender as 'Male'/'Female'
	 * 
	 * @param gender
	 */
	public void selectMaleOrFemale(Gender gender) {
		switch (gender) {
		case Male:
			maleRadioButton.click();
			break;
		case Female:
			femaleRadioButton.click();
			break;
		default:
			maleRadioButton.click();
			break;
		}
	}

	@FindBy(css = "button[type='submit'][name='websubmit']")
	WebElement createAnAccount;

	/**
	 * This method will click on 'Create an account' button
	 */
	public FBSignupConfimationPO clickOnCreateAnAccountButton() {
		createAnAccount.click();
		return new FBSignupConfimationPO();
	}
}