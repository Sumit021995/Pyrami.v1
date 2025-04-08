package pomClasses;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	@FindBy(xpath="") private WebElement usernameTextField;
	@FindBy(xpath="") private WebElement passwordTextField;
	@FindBy(xpath="") private WebElement loginBtn;
	
	public WebElement getUsernameTextField() {
		return usernameTextField;
	}
	public WebElement getPasswordTextField() {
		return passwordTextField;
	}
	public WebElement getLoginBtn() {
		return loginBtn;
	}
}
