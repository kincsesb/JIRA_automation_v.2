package Browse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowseRepository {
    private WebDriver driver;
    private WebDriverWait wait;


    public BrowseRepository(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void NavigateToProjectLink(String link) {
        driver.get(link);
    }

    public WebElement PathToKeyElement(String xpath){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public WebElement ProjectButton(){
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='browse_link']")));
    }

    public WebElement IssueId(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='key-val' and @class='issue-link']")));
    }

    public WebElement ErrorMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='issue-error']")));
    }
}
