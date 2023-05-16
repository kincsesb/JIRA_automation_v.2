import DriverManager.DriverManager;
import Login.LoginPage;
import ProjectPage.ProjectPage;
import Utility.Utility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class BrowseProjectTest {

    private LoginPage loginPage;
    private DriverManager driverManager;
    private ProjectPage projectPage;
    private Utility utility;

    @BeforeEach
    public void SetUp(){
        driverManager = new DriverManager();
        loginPage = new LoginPage(driverManager.getDriver(), driverManager.getWait());
        projectPage = new ProjectPage(driverManager.getDriver(), driverManager.getWait());
        utility = new Utility(driverManager.getDriver(), driverManager.getWait());
        loginPage.navigateToTheLoginPage(driverManager.getDriver());
        loginPage.successfulLogIn();
    }

    @AfterEach
    public void TearDown() {
        driverManager.tearDown();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/browse_project", numLinesToSkip = 0)
    public void SuccessfulProjectsBrowse(String url, String expectedKey) {

        utility.navigateToUrl(loginPage.BaseUrl + url);
        assertEquals(projectPage.getProjectKey(),expectedKey);
    }

    @Test
    public void BrowseProjectWhatDoesntExist() {
        utility.navigateToUrl(loginPage.BaseUrl + "projects/MTP2/summary");
        assertEquals(projectPage.getErrorMessageText(), "You can't view this project");
    }

}
