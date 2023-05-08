package Browse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowseRepository {

    public void NavigateToProjectLink(WebDriver driver,String link) {
        driver.get(link);
    }

    public WebElement PathToKeyElement(WebDriverWait wait, String xpath){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public WebElement ProjectButton(WebDriverWait wait){
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='browse_link']")));
    }

    public WebElement YouCantViewProjectDiv(WebDriverWait wait){
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[@class='projects-error-page-heading']")));
    }
}
