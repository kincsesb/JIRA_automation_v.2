import DriverManager.DriverManager;
import Login.LoginPage;
import NavBar.NavBar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    private NavBar navBar;
    private LoginPage loginPage;
    private String errorMessage = "Sorry, your username and password are incorrect - please try again.";
    private DriverManager driverManager;

    @BeforeEach
    public void SetUp(){
        driverManager = new DriverManager();

        loginPage = new LoginPage(driverManager.getDriver(), driverManager.getWait());
        navBar = new NavBar(driverManager.getDriver(), driverManager.getWait());
    }

    @AfterEach
    public void TearDown() {
        driverManager.tearDown();
    }

    @Test
    public void successfulLogin() {
        loginPage.navigateToTheLoginPage(driverManager.getDriver());

        loginPage.successfulLogIn();

        navBar.clickToAvatarIcon();

        navBar.clickToProfileOption();

        assertEquals(loginPage.getUserName(), "automation48");
    }

    @Test
    public void loginWithValidUsernameAndInvalidPassword() {
        String userName = loginPage.configReader.getUsername();
        String password = "asd123";

        loginPage.navigateToTheLoginPage(driverManager.getDriver());
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickToLogInButton();

        String expectedErrorMessage = loginPage.getErrorMessage();

        loginPage.successfulLogIn();

        assertEquals(expectedErrorMessage, errorMessage);
    }

    @Test
    public void loginWithValidPasswordAndInvalidUsername() {
        String userName = "asd123";
        String password = loginPage.configReader.getPassword();

        loginPage.navigateToTheLoginPage(driverManager.getDriver());
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickToLogInButton();

        String expectedErrorMessage = loginPage.getErrorMessage();

        assertEquals(expectedErrorMessage, errorMessage);
    }

    @Test
    public void leaveTheLoginFieldEmpty() {
        loginPage.navigateToTheLoginPage(driverManager.getDriver());
        loginPage.setUserName("");
        loginPage.setPassword("");
        loginPage.clickToLogInButton();

        String expectedErrorMessage = loginPage.getErrorMessage();

        assertEquals(expectedErrorMessage, errorMessage);
    }

    @Test
    public void loginWithValidUsernameAndEmptyPassword() {
        String userName = loginPage.configReader.getUsername();

        loginPage.navigateToTheLoginPage(driverManager.getDriver());
        loginPage.setUserName(userName);
        loginPage.setPassword("");
        loginPage.clickToLogInButton();

        String expectedErrorMessage = loginPage.getErrorMessage();

        loginPage.successfulLogIn();

        assertEquals(expectedErrorMessage, errorMessage);
    }

    @Test
    public void isCaptchaWorksCorrectly(){
        String userName = loginPage.configReader.getUsername();
        String password = "asd123";

        //First try
        loginPage.navigateToTheLoginPage(driverManager.getDriver());
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickToLogInButton();

        //Second try
        loginPage.navigateToTheLoginPage(driverManager.getDriver());
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickToLogInButton();

        //Third try
        loginPage.navigateToTheLoginPage(driverManager.getDriver());
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickToLogInButton();

        boolean isCaptchaDisplayed = loginPage.isCaptchaDisplayed();

        assertEquals(isCaptchaDisplayed,true);
    }
}
