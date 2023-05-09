import Login.LoginPage;
import Login.LoginRepository;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;

    private LoginRepository repository;

    private Sheet sheet1;
    private Sheet sheet2;

    @BeforeEach
    public void SetUp() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginPage = new LoginPage(driver);

        repository = new LoginRepository();

        FileInputStream fis = new FileInputStream(new File("\\F:\\TW3\\Nevtelen_tablazat.xlsx\\"));
        Workbook workbook = new XSSFWorkbook(fis);
        sheet1 = workbook.getSheet("Users");
    }

    @AfterEach
    public void TearDown() {
        driver.quit();
    }

    @Test
    public void succesfulLogin() {
        loginPage.navigateToTheLoginPage(driver);

        loginPage.successfulLogIn();

        repository.avatarIcon(wait).click();

        repository.profileOption(wait).click();

        assertEquals(repository.userNameOnProfile(wait).getText(), "automation48");
    }

    @Test
    public void loginWithValidUsernameAndInvalidPassword() {
        String userName = sheet1.getRow(2).getCell(0).toString();
        String password = sheet1.getRow(2).getCell(1).toString();

        loginPage.navigateToTheLoginPage(driver);
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickToLogInButton();

        boolean isErrorMessageDisplayed = repository.errorMessageOnTheLoginPage(wait).isDisplayed();

        loginPage.successfulLogIn();

        assertEquals(isErrorMessageDisplayed, true);
    }

    @Test
    public void loginWithValidPasswordAndInvalidUsername() {
        String userName = sheet1.getRow(3).getCell(0).toString();
        String password = sheet1.getRow(3).getCell(1).toString();

        loginPage.navigateToTheLoginPage(driver);
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickToLogInButton();

        boolean isErrorMessageDisplayed = repository.errorMessageOnTheLoginPage(wait).isDisplayed();

        assertEquals(isErrorMessageDisplayed, true);
    }

    @Test
    public void leaveTheLoginFieldEmpty() {
        loginPage.navigateToTheLoginPage(driver);
        loginPage.setUserName("");
        loginPage.setPassword("");
        loginPage.clickToLogInButton();

        boolean isErrorMessageDisplayed = repository.errorMessageOnTheLoginPage(wait).isDisplayed();

        assertEquals(isErrorMessageDisplayed, true);
    }

    @Test
    public void loginWithValidUsernameAndEmptyPassword() {
        String userName = sheet1.getRow(4).getCell(0).toString();

        loginPage.navigateToTheLoginPage(driver);
        loginPage.setUserName(userName);
        loginPage.setPassword("");
        loginPage.clickToLogInButton();

        boolean isErrorMessageDisplayed = repository.errorMessageOnTheLoginPage(wait).isDisplayed();

        loginPage.successfulLogIn();

        assertEquals(isErrorMessageDisplayed, true);
    }

    @Test
    public void isCaptchaWorksCorrectly(){
        String userName = sheet1.getRow(2).getCell(0).toString();
        String password = sheet1.getRow(2).getCell(1).toString();

        //First try
        loginPage.navigateToTheLoginPage(driver);
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickToLogInButton();

        //Second try
        loginPage.navigateToTheLoginPage(driver);
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickToLogInButton();

        //Third try
        loginPage.navigateToTheLoginPage(driver);
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickToLogInButton();

        boolean isCaptchaDisplayed = repository.captchaDivOnLoginPage(wait).isDisplayed();

        assertEquals(isCaptchaDisplayed,true);
    }
}
