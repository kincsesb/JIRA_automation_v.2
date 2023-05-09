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
public class LogOutTest {

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
    public void successfulLogOut(){
        loginPage.navigateToTheLoginPage(driver);
        loginPage.successfulLogIn();
        repository.avatarIcon(wait).click();
        repository.logOutOption(wait).click();

        boolean isLogOutMessageDisplayed = repository.logOutMessage(wait).isDisplayed();

        assertEquals(isLogOutMessageDisplayed, true);
    }
}
