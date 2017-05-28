package testcase;

import org.junit.Assert;
import org.testng.annotations.Test;

import pageobject.FBSignupConfimationPO;
import pageobject.FBSignupPO;
import pageobject.FBSignupPO.Gender;

/**
 * This class contains test cases for Facebook Sign Up page
 * 
 * @author Pratik
 */

public class FBSignupTest extends BaseTestCase {
	@Test
	public void createFacebookAccount() {
		final String confirmationMessage = "Enter the code from the SMS message";
		FBSignupPO po = new FBSignupPO();
		po.fillFirstName("Pratik");
		po.fillLastName("Patel");
		po.fillEmailIdOrMobileNo("7405511225");
		po.fillPassword("Test@123");
		po.selectBirthDate("2", "Feb", "1994");
		po.selectMaleOrFemale(Gender.Male);
		FBSignupConfimationPO confirmationPO = po.clickOnCreateAnAccountButton();
		// Assertion for confirmation page
		Assert.assertEquals(confirmationPO.getConfirmationPageText(), confirmationMessage);
	}
}
