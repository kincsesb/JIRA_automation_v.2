import CreateIssue.CreateIssue;
import Login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class CreateIssueTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private CreateIssue createIssue;

    @BeforeEach
    public void SetUp() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        createIssue = new CreateIssue(wait,driver);

        loginPage = new LoginPage(driver);

        loginPage.navigateToTheLoginPage(driver);
        loginPage.successfulLogIn();
    }

    @AfterEach
    public void TearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/successful_create_task_on_mtp.csv", numLinesToSkip = 0)
    public void SuccessfulCreateTaskOnMTP(String projectName, String issueType, String summary) {
        boolean isSuccessful = createIssue.successfulCreateIssue(projectName, issueType, summary);

        assertEquals(isSuccessful, true);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/successful_create_issue_on_jeti.csv", numLinesToSkip = 0)
    public void SuccessfulCreateIssueOnJETI(String projectName, String issueType, String summary) {
        boolean isSuccessful = createIssue.successfulCreateIssue(projectName, issueType, summary);

        assertEquals(isSuccessful, true);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/successful_create_issue_on_coala.csv", numLinesToSkip = 0)
    public void SuccessfulCreateIssueOnCOALA(String projectName, String issueType, String summary) {
        boolean isSuccessful = createIssue.successfulCreateIssue(projectName, issueType, summary);

        assertEquals(isSuccessful, true);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/successful_create_issue_on_toucan.csv", numLinesToSkip = 0)
    public void SuccessfulCreateIssueOnTOUCAN(String projectName, String issueType, String summary) {
        boolean isSuccessful = createIssue.successfulCreateIssue(projectName, issueType, summary);

        assertEquals(isSuccessful, true);
    }

    @Test
    public void CreateTestWithEmptySummary(){
        createIssue.createIssueRepository.CreateButton().click();
        createIssue.createIssueRepository.CreateButtonOnCreateScreen().click();

        boolean isErrorMessageDisplayed = createIssue.createIssueRepository.SummaryErrorMessageOnCreateScreen().isDisplayed();

        assertEquals(isErrorMessageDisplayed,true);
    }
}
