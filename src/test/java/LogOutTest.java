import DriverManager.DriverManager;
import LogOutPage.LogOutPage;
import Login.LoginPage;
import NavBar.NavBar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class LogOutTest {

    private LoginPage loginPage;
    private LogOutPage logOutPage;
    private NavBar navBar;
    private DriverManager driverManager;

    @BeforeEach
    public void SetUp(){
        driverManager = new DriverManager();

        loginPage = new LoginPage(driverManager.getDriver(), driverManager.getWait());
        navBar = new NavBar(driverManager.getDriver(), driverManager.getWait());
        logOutPage = new LogOutPage(driverManager.getDriver(),driverManager.getWait());
    }

    @AfterEach
    public void TearDown() {
        driverManager.tearDown();
    }

    @Test
    public void successfulLogOut(){
        loginPage.navigateToTheLoginPage(driverManager.getDriver());
        loginPage.successfulLogIn();

        navBar.clickToAvatarIcon();
        navBar.clickToLogOutOption();

        boolean isLogOutMessageDisplayed = logOutPage.isErrorMessageDisplayed();
        assertEquals(isLogOutMessageDisplayed, true);
    }
}
