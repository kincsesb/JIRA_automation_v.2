import CreateIssue.CreateIssue;
import DriverManager.DriverManager;
import IssuePage.IssuePage;
import Login.LoginPage;
import NavBar.NavBar;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.Assert.assertEquals;

public class CreateIssueTest {
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

    @ParameterizedTest
    @CsvFileSource(resources = "/successful_create_task_on_mtp.csv", numLinesToSkip = 0)
    public void SuccessfulCreateTaskOnMTP(String projectName, String issueType, String summary) {
        navBar.clickToCreateButton();
        createIssue.validateTheProject(projectName);
        createIssue.validateTheIssueType(issueType);
        createIssue.setSummaryOnCreateScreen(summary);
        createIssue.clickToSubmitTheCreateIssue();
        createIssue.clickToCreatedIssuePopUp();

        String type = issuePage.getIssueType();
        String project = issuePage.getProject();
        String summ = issuePage.getSummary();

        //Delete the created issue
        issuePage.clickToMoreButton();
        issuePage.clickToDeleteOption();
        issuePage.clickToConfirmDelete();

        assertEquals(type, issueType);
        assertEquals(project, projectName.split(" \\(")[0]);
        assertEquals(summ, summary);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/successful_create_issue_on_jeti.csv", numLinesToSkip = 0)
    public void SuccessfulCreateIssueOnJETI(String projectName, String issueType, String summary) {
        navBar.clickToCreateButton();
        createIssue.validateTheProject(projectName);
        createIssue.validateTheIssueType(issueType);
        createIssue.setSummaryOnCreateScreen(summary);
        createIssue.clickToSubmitTheCreateIssue();
        createIssue.clickToCreatedIssuePopUp();
        String type = issuePage.getIssueType();
        String project = issuePage.getProject();
        String summ = issuePage.getSummary();

        //Delete the created issue
        issuePage.clickToMoreButton();
        issuePage.clickToDeleteOption();
        issuePage.clickToConfirmDelete();

        assertEquals(type, issueType);
        assertEquals(project, projectName.split(" \\(")[0]);
        assertEquals(summ, summary);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/successful_create_issue_on_coala.csv", numLinesToSkip = 0)
    public void SuccessfulCreateIssueOnCOALA(String projectName, String issueType, String summary) {
        navBar.clickToCreateButton();
        createIssue.validateTheProject(projectName);
        createIssue.validateTheIssueType(issueType);
        createIssue.setSummaryOnCreateScreen(summary);
        createIssue.clickToSubmitTheCreateIssue();
        createIssue.clickToCreatedIssuePopUp();
        String type = issuePage.getIssueType();
        String project = issuePage.getProject();
        String summ = issuePage.getSummary();

        //Delete the created issue
        issuePage.clickToMoreButton();
        issuePage.clickToDeleteOption();
        issuePage.clickToConfirmDelete();

        assertEquals(type, issueType);
        assertEquals(project, projectName.split(" \\(")[0]);
        assertEquals(summ, summary);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/successful_create_issue_on_toucan.csv", numLinesToSkip = 0)
    public void SuccessfulCreateIssueOnTOUCAN(String projectName, String issueType, String summary) {
        navBar.clickToCreateButton();
        createIssue.validateTheProject(projectName);
        createIssue.validateTheIssueType(issueType);
        createIssue.setSummaryOnCreateScreen(summary);
        createIssue.clickToSubmitTheCreateIssue();
        createIssue.clickToCreatedIssuePopUp();
        String type = issuePage.getIssueType();
        String project = issuePage.getProject();
        String summ = issuePage.getSummary();

        //Delete the created issue
        issuePage.clickToMoreButton();
        issuePage.clickToDeleteOption();
        issuePage.clickToConfirmDelete();

        assertEquals(type, issueType);
        assertEquals(project, projectName.split(" \\(")[0]);
        assertEquals(summ, summary);
    }

    @Test
    public void CreateTestWithEmptySummary(){
        navBar.clickToCreateButton();
        createIssue.validateTheProject("Main Testing Project (MTP)");
        createIssue.validateTheIssueType("Task");
        createIssue.setSummaryOnCreateScreen("");
        createIssue.clickToSubmitTheCreateIssue();

        String errorMessage = createIssue.getSummaryErrorMessage();

        assertEquals(errorMessage,"You must specify a summary of the issue.");
    }
}
