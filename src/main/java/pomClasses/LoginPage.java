package pomClasses;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	@FindBy(name="user_name") private WebElement usernameTextField;
	@FindBy(name="user_password") private WebElement passwordTextField;
	@FindBy(id="submitButton") private WebElement loginBtn;
	
	public WebElement getUsernameTextField() {
		return usernameTextField;
	}
	public WebElement getPasswordTextField() {
		return passwordTextField;
	}
	public WebElement getLoginBtn() {
		return loginBtn;
	}
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	/**
	 * This is a generic method to login to application
	 * @param username
	 * @param password
	 */
	public void loginToApplication(String username,String password)
	{
		usernameTextField.sendKeys(username,Keys.TAB,password,Keys.ENTER);
	}
}
