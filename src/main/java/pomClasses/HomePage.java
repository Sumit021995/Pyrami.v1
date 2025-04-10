package pomClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class HomePage {
	@FindBy(xpath="//table[@class='hdrTabBg']//a[text()='Leads']") private WebElement leadsLink;
	@FindBy(xpath="//table[@class='hdrTabBg']//a[text()='Contacts']") private WebElement contactsLink;
	@FindBy(xpath="//table[@class='hdrTabBg']//a[text()='Organizations']") private WebElement organizationsLink;
	@FindBy(xpath="//table[@class='hdrTabBg']//a[text()='Products']") private WebElement productsLink;
	@FindBy(id="qccombo") private WebElement quickCreateDropdown;
	@FindBy(xpath="//a[@name='Campaigns']") private WebElement campaignsLink;
	@FindBy(linkText="More") private WebElement moreOptionLink;
	
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getCampaignsLink() {
		return campaignsLink;
	}
	public WebElement getLeadsLink() {
		return leadsLink;
	}

	public WebElement getContactsLink() {
		return contactsLink;
	}

	public WebElement getOrganizationsLink() {
		return organizationsLink;
	}

	public WebElement getProductsLink() {
		return productsLink;
	}

	public WebElement getQuickCreateDropdown() {
		return quickCreateDropdown;
	}

	public WebElement getMoreOptionLink() {
		return moreOptionLink;
	}
	/**
	 * This is a business utility method to click on campaign link by hovering over to more link
	 * @param driver
	 */
	public void clickOnCampaignFromMoreOption(WebDriver driver)
	{
		Actions act = new Actions(driver);
		act.moveToElement(moreOptionLink).perform();
		campaignsLink.click();
	}
	/**
	 * This a business utility method to select from QuickCreate Dropdown
	 * @param valueOfQuickCreateDropdown
	 */
	public void selectFromQuickCreateDropdown(String valueOfQuickCreateDropdown)
	{
		Select s = new Select(quickCreateDropdown);
		s.selectByValue(valueOfQuickCreateDropdown);
	}
}
