package Login;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class LoginPage {

    @FindBy(xpath = "//input[@id='login-form-username']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='login-form-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id='login']")
    private WebElement loginButton;

    @FindBy(xpath = "//dd[@id='up-d-username']")
    private WebElement userNameOnProfile;

    @FindBy(xpath = "//p[contains(text(),'Sorry, your username and password are incorrect - ')]")
    private WebElement errorMessageOnTheLoginPage;

    @FindBy(xpath = "//div[@id='captcha']")
    private WebElement captchaDivOnLoginPage;

    private FileInputStream fis = new FileInputStream(new File("\\F:\\TW3\\Nevtelen_tablazat.xlsx\\"));
    private Workbook workbook = new XSSFWorkbook(fis);
    private Sheet sheet1 = workbook.getSheet("Users");

    private WebDriverWait wait;


    public LoginPage(WebDriver driver, WebDriverWait wait) throws IOException {
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }


    public void navigateToTheLoginPage(WebDriver driver) {
        driver.get("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
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

        String userName = sheet1.getRow(1).getCell(0).toString();
        String password = sheet1.getRow(1).getCell(1).toString();

        setUserName(userName);
        setPassword(password);
        clickToLogInButton();
    }
}
