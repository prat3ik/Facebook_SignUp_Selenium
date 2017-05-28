package pageobject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.WebDriverUtil;

/**
 * This is Page Objects for Facebook sign up mobile confirmation page
 * 
 * @author Pratik
 *
 */
public class FBSignupConfimationPO {

	public FBSignupConfimationPO() {
		PageFactory.initElements(WebDriverUtil.getWebDriver(), this);
		new WebDriverWait(WebDriverUtil.getWebDriver(), 10).until(ExpectedConditions.visibilityOf(confirmationPage));
	}

	@FindBy(css = ".uiHeaderTitle")
	WebElement confirmationPage;

	public String getConfirmationPageText() {
		return confirmationPage.getText();
	}

/*	@FindBy(css="._3ixn")
	WebElement overlapElement;

	public void clickOnOverlapElement() {
		overlapElement.click();
	}*/
}
