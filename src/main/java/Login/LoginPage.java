package Login;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class LoginPage {

    private FileInputStream fis = new FileInputStream(new File("/Users/kincsesbence/Desktop/TestAutomation_Module/JIRA_automation_v.2/src/main/Névtelen táblázat.xlsx"));
    private Workbook workbook = new XSSFWorkbook(fis);
    private Sheet sheet1 = workbook.getSheet("Users");

    private WebDriverWait wait;
    private LoginRepository repository;

    public LoginPage(WebDriver driver) throws IOException {
        repository = new LoginRepository();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateToTheLoginPage(WebDriver driver){
        driver.get("https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa");
    }
    public void setUserName(String userName){
        repository.userNameField(wait).sendKeys(userName);
    }

    public void setPassword(String password){
        repository.passwordField(wait).sendKeys(password);
    }

    public void clickToLogInButton(){
        repository.loginButton(wait).click();
    }

    public void successfulLogIn(){
        String userName = sheet1.getRow(1).getCell(0).toString();
        String password = sheet1.getRow(1).getCell(1).toString();

        setUserName(userName);
        setPassword(password);
        clickToLogInButton();
    }

    public void succesFulLogOut(){
        repository.avatarIcon(wait).click();
        repository.logOutOption(wait).click();
    }

    public void successFulLogOut(){
        repository.avatarIcon(wait).click();
        repository.logOutOption(wait).click();
    }
}
