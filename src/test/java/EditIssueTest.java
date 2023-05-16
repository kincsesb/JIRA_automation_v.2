import CreateIssue.CreateIssue;
import DriverManager.DriverManager;
import IssuePage.IssuePage;
import Login.LoginPage;
import NavBar.NavBar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class EditIssueTest {

    private LoginPage loginPage;
    private CreateIssue createIssue;
    private IssuePage issuePage;
    private NavBar navBar;
    private DriverManager driverManager;

    @BeforeEach
    public void SetUp(){
        driverManager = new DriverManager();
        createIssue = new CreateIssue(driverManager.getDriver(),driverManager.getWait());
        loginPage = new LoginPage(driverManager.getDriver(), driverManager.getWait());
        navBar = new NavBar(driverManager.getDriver(), driverManager.getWait());
        issuePage = new IssuePage(driverManager.getDriver(),driverManager.getWait());
        loginPage.navigateToTheLoginPage(driverManager.getDriver());
        loginPage.successfulLogIn();
    }

    @AfterEach
    public void TearDown() {
        driverManager.tearDown();
    }

    @Test
    public void EditIssueFunctionalityTest(){
        navBar.clickToCreateButton();
        createIssue.validateTheProject("Main Testing Project");
        createIssue.validateTheIssueType("Story");
        createIssue.setSummaryOnCreateScreen("superUniqueTest321777987321");
        createIssue.clickToSubmitTheCreateIssue();
        createIssue.clickToCreatedIssuePopUp();

        issuePage.clickToEditButton();
        createIssue.clearTheSummary();
        createIssue.setSummaryOnCreateScreen("asf321group9jwr321");
        createIssue.validateTheIssueType("Bug");

        createIssue.clickToUpdateButton();

        String issueType = issuePage.getIssueType();
        String summary = issuePage.getSummary();

        issuePage.clickToMoreButton();
        issuePage.clickToDeleteOption();
        issuePage.clickToConfirmDelete();

        assertEquals(summary,"asf321group9jwr321");
        assertEquals(issueType,"Bug");
    }
}
