import Browse.BrowseRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrowseProjectTest {
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
    @CsvFileSource(resources = "/project_summary_data.csv", numLinesToSkip = 0)
    public void SuccessfulProjectsBrowse(String link, String xpath, String result) {

        browseRepository.NavigateToProjectLink(link);

        String expectedResult = browseRepository.PathToKeyElement(xpath).getText();

        assertEquals(expectedResult, result);
    }

    @Test
    public void BrowseProjectWhatDoesntExist(){
        browseRepository.NavigateToProjectLink("https://jira-auto.codecool.metastage.net/projects/MTP2/summary");

        assertEquals(browseRepository.ErrorMessage().isDisplayed(),true);
    }

}
