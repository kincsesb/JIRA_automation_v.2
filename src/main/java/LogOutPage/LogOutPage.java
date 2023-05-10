package LogOutPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogOutPage {

    @FindBy(xpath = "//strong[contains(text(),'You are now logged out. Any automatic login has al')]")
    private WebElement logOutMessage;

    public LogOutPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public boolean isErrorMessageDisplayed(){ return logOutMessage.isDisplayed();}
}
