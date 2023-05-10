import Login.LoginPage;
import Browse.BrowseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrowseIssueTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;

    private BrowseRepository browseRepository;

    @BeforeEach
    public void SetUp() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginPage = new LoginPage(driver);

        browseRepository = new BrowseRepository(driver,wait);

        loginPage.navigateToTheLoginPage(driver);
        loginPage.successfulLogIn();
        browseRepository.ProjectButton();
    }

    @AfterEach
    public void TearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/issue_data.csv", numLinesToSkip = 0)
    public void SuccessfulIssueBrowse(String url, String issueId) {

        browseRepository.NavigateToProjectLink(url);

        String expectedResult = browseRepository.IssueId().getText();

        assertEquals(expectedResult, issueId);
    }

    @Test
    public void BrowseIssueWithNonExistingId(){

        browseRepository.NavigateToProjectLink("https://jira-auto.codecool.metastage.net/browse/MTP-100000");

        boolean isErrorMessageDisplayed = browseRepository.ErrorMessage().isDisplayed();

        assertEquals(isErrorMessageDisplayed,true);
    }

    @Test
    public void BrowseIssueWithExistingIdButNonExistingProject(){

        browseRepository.NavigateToProjectLink("https://jira-auto.codecool.metastage.net/browse/MTPP-10");

        boolean isErrorMessageDisplayed = browseRepository.ErrorMessage().isDisplayed();

        assertEquals(isErrorMessageDisplayed,true);
    }

    @Test
    public void BrowseDeletedIssue(){

        browseRepository.NavigateToProjectLink("https://jira-auto.codecool.metastage.net/browse/MTP-2");

        boolean isErrorMessageDisplayed = browseRepository.ErrorMessage().isDisplayed();

        assertEquals(isErrorMessageDisplayed,true);
    }
}
