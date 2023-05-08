package Login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginRepository {

    public WebElement userNameField(WebDriverWait wait){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='login-form-username']")));
    }

    public WebElement passwordField(WebDriverWait wait){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='login-form-password']")));
    }

    public WebElement loginButton(WebDriverWait wait){
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='login']")));
    }

    public WebElement avatarIcon(WebDriverWait wait){
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='header-details-user-fullname']")));
    }

    public WebElement profileOption(WebDriverWait wait){
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='view_profile']")));
    }

    public WebElement logOutOption(WebDriverWait wait){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='log_out']")));
    }

    public WebElement logOutMessage(WebDriverWait wait){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'You are now logged out. Any automatic login has al')]")));
    }
    public WebElement userNameOnProfile(WebDriverWait wait){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//dd[@id='up-d-username']")));
    }

    public WebElement errorMessageOnTheLoginPage(WebDriverWait wait){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Sorry, your username and password are incorrect - ')]")));
    }

    public WebElement captchaDivOnLoginPage(WebDriverWait wait){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='captcha']")));
    }
}
