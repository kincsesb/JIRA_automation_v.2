package Login;

import BasePage.BasePage;
import ConfigReader.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='login-form-username']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='login-form-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='login']")
    public static WebElement loginButton;

    @FindBy(xpath = "//dd[@id='up-d-username']")
    private WebElement userNameOnProfile;

    @FindBy(xpath = "//p[contains(text(),'Sorry, your username and password are incorrect - ')]")
    private WebElement errorMessageOnTheLoginPage;

    @FindBy(xpath = "//div[@id='captcha']")
    private WebElement captchaDivOnLoginPage;
    public ConfigReader configReader;
    public LoginPage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
        configReader = new ConfigReader();
    }


    public void navigateToTheLoginPage(WebDriver driver) {
        driver.get(BaseUrl);
    }

    public void setUserName(String userName) {
        wait.until(ExpectedConditions.visibilityOf(userNameField));
        userNameField.sendKeys(userName);
    }

    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.sendKeys(password);
    }

    public void clickToLogInButton() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        loginButton.click();
    }

    public String getUserName() {
        wait.until(ExpectedConditions.visibilityOf(userNameOnProfile));
        return userNameOnProfile.getText();
    }

    public boolean isCaptchaDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(captchaDivOnLoginPage));
        return captchaDivOnLoginPage.isDisplayed();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessageOnTheLoginPage));
        return errorMessageOnTheLoginPage.getText();
    }

    public void successfulLogIn() {

        String userName = configReader.getUsername();
        String password = configReader.getPassword();

        setUserName(userName);
        setPassword(password);
        clickToLogInButton();
    }
}
