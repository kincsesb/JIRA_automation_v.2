package IssuePage;

import BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IssuePage extends BasePage{
    @FindBy(css = "#summary-val")
    public WebElement summaryOnCreatedIssue;
    @FindBy(css = "#type-val")
    public WebElement issueTypeOnCreatedIssue;
    @FindBy(xpath = "//a[@id='project-name-val']")
    public WebElement projectOnCreatedIssue;
    @FindBy(xpath = "//span[normalize-space()='More']")
    private WebElement moreButton;
    @FindBy(xpath = "//aui-item-link[@id='delete-issue']//a[@role='menuitem']")
    private WebElement deleteOption;
    @FindBy(xpath = "//input[@id='delete-issue-submit']")
    private WebElement confirmTheDelete;
    @FindBy(xpath = "//a[@id='key-val']")
    private WebElement issueId;
    @FindBy(xpath = "//div[@class='issue-error']")
    private WebElement errorMessage;

    public IssuePage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
    }

    public String getText(WebElement element){
        return element.getText();
    }

    public String getSummary(){
        wait.until(ExpectedConditions.visibilityOf(summaryOnCreatedIssue));
        return summaryOnCreatedIssue.getText();
    }

    public String getIssueType(){
        wait.until(ExpectedConditions.visibilityOf(issueTypeOnCreatedIssue));
        return issueTypeOnCreatedIssue.getText();
    }

    public String getProject(){
        wait.until(ExpectedConditions.visibilityOf(projectOnCreatedIssue));
        return projectOnCreatedIssue.getText();
    }

    public void clickToMoreButton(){
        wait.until(ExpectedConditions.visibilityOf(moreButton));
        moreButton.click();
    }

    public void clickToDeleteOption(){
        wait.until(ExpectedConditions.visibilityOf(deleteOption));
        deleteOption.click();
    }

    public void clickToConfirmDelete(){
        wait.until(ExpectedConditions.visibilityOf(confirmTheDelete));
        confirmTheDelete.click();
    }

    public String checkIssueId(){
        wait.until(ExpectedConditions.visibilityOf(issueId));
        return issueId.getText();
    }

    public String getErrorMessageText(){
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText().replace("\n", " ").replace("\r", " ");
    }
}
