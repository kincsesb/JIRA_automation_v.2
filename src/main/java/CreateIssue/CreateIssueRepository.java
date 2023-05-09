package CreateIssue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateIssueRepository {

    private WebDriverWait wait;

    public CreateIssueRepository(WebDriverWait Wait){
        this.wait = Wait;
    }

    public WebElement CreateButton(){
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@id='create_link']")));
    }

    public WebElement ProjectOptionOnCreateScreen(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='project-field']")));
    }

    public WebElement ActualProjectNameOnCreateScreen(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='project-field']")));
    }

    public WebElement DynamicProject(String projectName){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[normalize-space()='" + projectName + "']")));
    }

    public WebElement IssueTypeOptionOnCreateIssueScreen(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@for='issuetype-field']")));
    }

    public WebElement ActualIssueType(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='issuetype-field']")));
    }

    public WebElement DynamicIssueType(String type){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[normalize-space()='" + type + "']")));
    }

    public WebElement SummaryInputFieldOnCreateScreen(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='summary']")));
    }

    public WebElement CreateButtonOnCreateScreen(){
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='create-issue-submit']")));
    }

    public WebElement CreateIssuePopUp(){
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='issue-created-key issue-link']")));
    }

    public WebElement SummaryOnCreatedIssue(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#summary-val")));
    }

    public WebElement IssueTypeOnCreatedIssue(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#type-val")));
    }

    public WebElement IssueOwnerOnCreatedIssue(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='reporter-val']")));
    }
    public WebElement ProjectOnCreatedIssue(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='project-name-val']")));
    }

    public WebElement MoreButton(){
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='More']")));
    }

    public WebElement DeleteOption(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//aui-item-link[@id='delete-issue']//a[@role='menuitem']")));
    }

    public WebElement ConfirmTheDelete(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='delete-issue-submit']")));
    }
}
