package CreateIssue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateIssue {

    @FindBy(xpath = "//input[@id='project-field']")
    private WebElement actualProjectNameOnCreateScreen;
    @FindBy(xpath = "//input[@id='project-field']")
    private WebElement projectInputField;
    @FindBy(xpath = "//input[@id='issuetype-field']")
    private WebElement issueTypeInputField;
    @FindBy(xpath = "//div[@role='alert']")
    private WebElement summaryErrorMessageOnCreateScreen;
    @FindBy(xpath = "//input[@id='issuetype-field']")
    private WebElement actualIssueType;
    @FindBy(xpath = "//input[@id='summary']")
    private WebElement summaryInputFieldOnCreateScreen;
    @FindBy(xpath = "//input[@id='create-issue-submit']")
    private WebElement createButtonOnCreateScreen;
    @FindBy(xpath = "//a[@class='issue-created-key issue-link']")
    private WebElement createIssuePopUp;

    private WebDriverWait wait;
    public CreateIssue(WebDriver driver, WebDriverWait wait) {

        this.wait = wait;
        PageFactory.initElements(driver, this);
    }


    public String getActualProjectNameOnCreateScreen() {
        return actualProjectNameOnCreateScreen.getAttribute("value");
    }

    public String getActualIssueTypeOnCreateScreen() {
        return actualIssueType.getAttribute("value");
    }

    public void clickToProjectInputField() {
        projectInputField.click();
    }

    public void setProjectOnCreateScreen(String project) {
        projectInputField.sendKeys(project);
    }

    public void sendBackSpaceToProjectInput() {
        projectInputField.sendKeys(Keys.BACK_SPACE);
    }

    public void sendEnterToProjectInput() {
        projectInputField.sendKeys(Keys.ENTER);
    }

    public String getSummaryErrorMessage() {
       return summaryErrorMessageOnCreateScreen.getText();
    }

    public void clickToIssueTypeInputField() {
        issueTypeInputField.click();
    }

    public void setIssueType(String type) {
        issueTypeInputField.sendKeys(type);
    }

    public void sendBackSpaceToIssueInput() {
        issueTypeInputField.sendKeys(Keys.BACK_SPACE);
    }

    public void sendEnterToIssueInput() {
        issueTypeInputField.sendKeys(Keys.ENTER);
    }

    public void setSummaryOnCreateScreen(String summary) {
        summaryInputFieldOnCreateScreen.sendKeys(summary);
    }

    public void clickToSubmitTheCreateIssue(){
        createButtonOnCreateScreen.click();
    }

    public void clickToCreatedIssuePopUp(){
        createIssuePopUp.click();
    }

    public void validateTheProject(String project){
        wait.until(ExpectedConditions.visibilityOf(actualProjectNameOnCreateScreen));
        if(!getActualProjectNameOnCreateScreen().equals(project)){
            clickToProjectInputField();
            sendBackSpaceToProjectInput();
            setProjectOnCreateScreen(project);
            sendEnterToProjectInput();
        }
    }

    public void validateTheIssueType(String type){
        wait.until(ExpectedConditions.visibilityOf(actualIssueType));
        if(!getActualIssueTypeOnCreateScreen().equals(type)){
            clickToIssueTypeInputField();
            sendBackSpaceToIssueInput();
            setIssueType(type);
            sendEnterToIssueInput();
        }
    }
}
