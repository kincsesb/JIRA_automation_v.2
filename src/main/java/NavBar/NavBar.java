package NavBar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavBar {

    private WebDriverWait wait;

    @FindBy(xpath = "//a[@id='header-details-user-fullname']")
    private WebElement avatarIcon;

    @FindBy(xpath = "//a[@id='view_profile']")
    private WebElement profileOption;

    @FindBy(xpath = "//a[@id='log_out']")
    private WebElement logOutOption;

    @FindBy(xpath = "//a[@id='create_link']")
    private WebElement createButton;

    public NavBar(WebDriver driver, WebDriverWait wait) {
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void clickToAvatarIcon() {
        wait.until(ExpectedConditions.visibilityOf(avatarIcon));
        avatarIcon.click();
    }

    public void clickToProfileOption() {
        wait.until(ExpectedConditions.visibilityOf(profileOption));
        profileOption.click();
    }

    public void clickToLogOutOption() {
        wait.until(ExpectedConditions.visibilityOf(logOutOption));
        logOutOption.click();
    }

    public void clickToCreateButton() {
        wait.until(ExpectedConditions.visibilityOf(createButton));
        createButton.click();
    }
}
