import DriverManager.DriverManager;
import IssuePage.IssuePage;
import Login.LoginPage;
import Utility.Utility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrowseIssueTest {

    private LoginPage loginPage;
    private IssuePage issuePage;
    private DriverManager driverManager;
    private Utility utility;

    @BeforeEach
    public void SetUp(){
        driverManager = new DriverManager();
        loginPage = new LoginPage(driverManager.getDriver(), driverManager.getWait());
        issuePage = new IssuePage(driverManager.getDriver(), driverManager.getWait());
        utility = new Utility(driverManager.getDriver(), driverManager.getWait());
        loginPage.navigateToTheLoginPage(driverManager.getDriver());
        loginPage.successfulLogIn();
    }

    @AfterEach
    public void TearDown() {
        driverManager.tearDown();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/issue_data.csv", numLinesToSkip = 0)
    public void SuccessfulIssueBrowse(String url, String expectedIssueId) {

        utility.navigateToUrl(loginPage.BaseUrl + url);

        assertEquals(issuePage.checkIssueId(), expectedIssueId);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/non_existing_issues.csv", numLinesToSkip = 0)
    public void BrowseNonExistingOrDeletedIssue(String url, String expectedError) {

        utility.navigateToUrl(loginPage.BaseUrl + url);

        assertEquals(issuePage.getErrorMessageText(), expectedError);

    }
}
