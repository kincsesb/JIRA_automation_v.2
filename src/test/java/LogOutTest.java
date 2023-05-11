import DriverManager.DriverManager;
import LogOutPage.LogOutPage;
import Login.LoginPage;
import NavBar.NavBar;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LogOutTest {

    private LoginPage loginPage;
    private LogOutPage logOutPage;
    private NavBar navBar;
    private Sheet sheet1;
    private DriverManager driverManager;

    @BeforeEach
    public void SetUp() throws IOException {
        driverManager = new DriverManager();

        loginPage = new LoginPage(driverManager.getDriver(), driverManager.getWait());
        navBar = new NavBar(driverManager.getDriver(), driverManager.getWait());
        logOutPage = new LogOutPage(driverManager.getDriver(),driverManager.getWait());

        FileInputStream fis = new FileInputStream(new File("/Users/kincsesbence/Desktop/TestAutomation_Module/JIRA_automation_v.2/src/main/Névtelen táblázat.xlsx"));
        Workbook workbook = new XSSFWorkbook(fis);
        sheet1 = workbook.getSheet("Users");
    }

    @AfterEach
    public void TearDown() {
        driverManager.tearDown();
    }

    @Test
    public void successfulLogOut(){
        loginPage.navigateToTheLoginPage(driverManager.getDriver());
        loginPage.successfulLogIn();

        boolean isLogOutMessageDisplayed = logOutPage.isErrorMessageDisplayed();
        assertEquals(isLogOutMessageDisplayed, true);
    }
}
