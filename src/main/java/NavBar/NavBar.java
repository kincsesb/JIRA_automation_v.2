package NavBar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavBar {

    @FindBy(xpath = "//a[@id='header-details-user-fullname']")
    private WebElement avatarIcon;

    @FindBy(xpath = "//a[@id='view_profile']")
    private WebElement profileOption;

    @FindBy(xpath = "//a[@id='log_out']")
    private WebElement logOutOption;

    @FindBy(xpath = "//a[@id='create_link']")
    private WebElement createButton;

    public NavBar(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void clickToAvatarIcon(){avatarIcon.click();}
    public void clickToProfileOption(){profileOption.click();}
    public void clickToLogOutOption(){
        logOutOption.click();
    }
    public void clickToCreateButton(){createButton.click();}
}
