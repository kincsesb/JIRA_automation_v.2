package IssuePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IssuePage {
    @FindBy(css = "#summary-val")
    private WebElement summaryOnCreatedIssue;
    @FindBy(css = "#type-val")
    private WebElement issueTypeOnCreatedIssue;
    @FindBy(xpath = "//a[@id='project-name-val']")
    private WebElement projectOnCreatedIssue;
    @FindBy(xpath = "//span[normalize-space()='More']")
    private WebElement moreButton;
    @FindBy(xpath = "//aui-item-link[@id='delete-issue']//a[@role='menuitem']")
    private WebElement deleteOption;
    @FindBy(xpath = "//input[@id='delete-issue-submit']")
    private WebElement confirmTheDelete;


    private WebDriverWait wait;
    public IssuePage(WebDriver driver, WebDriverWait wait){
        this.wait = wait;
        PageFactory.initElements(driver,this);
    }

    public String getSummary(){
        return summaryOnCreatedIssue.getText();
    }

    public String getIssueType(){
        return issueTypeOnCreatedIssue.getText();
    }

    public String getProject(){
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
}
