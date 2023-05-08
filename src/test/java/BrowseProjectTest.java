import Browse.BrowseProject;
import Browse.BrowseRepository;
import Login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
    private BrowseProject browseProject;

    private BrowseRepository browseRepository;


    @BeforeEach
    public void SetUp() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginPage = new LoginPage(driver);
        browseProject = new BrowseProject(driver);

        browseRepository = new BrowseRepository();

        loginPage.navigateToTheLoginPage(driver);
        loginPage.successfulLogIn();
        browseRepository.ProjectButton(wait);
    }

    @AfterEach
    public void TearDown() {
        driver.quit();
    }

    @ParameterizedTest
    @CsvSource({
            "https://jira-auto.codecool.metastage.net/projects/MTP/summary, //dd[normalize-space()='MTP'], MTP",
            "https://jira-auto.codecool.metastage.net/projects/COALA/summary, //dd[normalize-space()='COALA'], COALA",
            "https://jira-auto.codecool.metastage.net/projects/JETI/summary, //dd[normalize-space()='JETI'], JETI",
            "https://jira-auto.codecool.metastage.net/projects/TOUCAN/summary, //dd[normalize-space()='TOUCAN'], TOUCAN"
    })public void SuccessfulProjectsBrowse(String link, String xpath, String result) {

        browseRepository.NavigateToProjectLink(driver, link);

        String expectedResult = browseRepository.PathToKeyElement(wait, xpath).getText();

        assertEquals(expectedResult, result);
    }

    @Test
    public void BrowseProjectWhatDoesntExist(){
        browseRepository.NavigateToProjectLink(driver, "https://jira-auto.codecool.metastage.net/projects/MTP2/summary");

        assertEquals(browseRepository.YouCantViewProjectDiv(wait).isDisplayed(),true);
    }

}
