package Utils;

import Login.LoginPage;
import BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility extends BasePage {

    public Utility(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
    }

    public void navigateToUrl(String url){
        wait.until(ExpectedConditions.invisibilityOf(LoginPage.loginButton));
        driver.get(url);
    }
}
