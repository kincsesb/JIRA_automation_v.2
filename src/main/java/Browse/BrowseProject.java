package Browse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BrowseProject {
    private WebDriverWait wait;

    private BrowseRepository repository;

    public BrowseProject(WebDriver driver) {

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
}
