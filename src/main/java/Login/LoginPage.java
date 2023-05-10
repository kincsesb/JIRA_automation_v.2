package Login;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    private FileInputStream fis = new FileInputStream(new File("/Users/kincsesbence/Desktop/TestAutomation_Module/JIRA_automation_v.2/src/main/Névtelen táblázat.xlsx"));
    private Workbook workbook = new XSSFWorkbook(fis);
    private Sheet sheet1 = workbook.getSheet("Users");


    public LoginPage(WebDriver driver) throws IOException {
        PageFactory.initElements(driver, this);
    }


    public void navigateToTheLoginPage(WebDriver driver) {
        driver.get("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
    }

    public void setUserName(String userName) {
        userNameField.sendKeys(userName);
    }

    public void setPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickToLogInButton() {
        loginButton.click();
    }

    public String getUserName() {
        return userNameOnProfile.getText();
    }

    public boolean isCaptchaDisplayed() {
        return captchaDivOnLoginPage.isDisplayed();
    }

    public String getErrorMessage() {
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
