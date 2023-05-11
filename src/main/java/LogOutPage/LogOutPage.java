package LogOutPage;

import BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogOutPage extends BasePage {

    @FindBy(xpath = "//strong[contains(text(),'You are now logged out. Any automatic login has al')]")
    private WebElement logOutMessage;

    public LogOutPage(WebDriver driver, WebDriverWait wait){
        super(driver,wait);
    }

    public boolean isErrorMessageDisplayed(){ return logOutMessage.isDisplayed();}
}
