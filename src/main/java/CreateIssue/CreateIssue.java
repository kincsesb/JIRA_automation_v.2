package CreateIssue;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateIssue {

    private WebDriverWait wait;
    private WebDriver driver;
    public CreateIssueRepository createIssueRepository;

    public CreateIssue(WebDriverWait Wait, WebDriver Driver) {
        this.driver = Driver;
        this.wait = Wait;
        createIssueRepository = new CreateIssueRepository(wait);
    }

    public void setSummary(String summary) {
        createIssueRepository.SummaryInputFieldOnCreateScreen().sendKeys(summary);
    }

    public boolean multipleAssertion(String project, String type, String summary) {

        String[] projectName = project.split(" \\(");

        boolean projectResult = createIssueRepository.ProjectOnCreatedIssue().getText().equals(projectName[0]);
        System.out.print(createIssueRepository.ProjectOnCreatedIssue().getText());
        boolean summaryResult = createIssueRepository.SummaryOnCreatedIssue().getText().equals(summary);
        System.out.print(createIssueRepository.SummaryOnCreatedIssue().getText());
        boolean issueTypeResult = createIssueRepository.IssueTypeOnCreatedIssue().getText().equals(type);
        System.out.print(createIssueRepository.IssueTypeOnCreatedIssue().getText());
        boolean result = projectResult && summaryResult && issueTypeResult;

        return result;
    }

    public boolean successfulCreateIssue(String project, String type, String summary) {
        createIssueRepository.CreateButton().click();


        if (!createIssueRepository.ActualProjectNameOnCreateScreen().getAttribute("value").equals(project)) {
            createIssueRepository.ProjectOptionOnCreateScreen().click();

            //((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", createIssueRepository.ProjectInputField());
            createIssueRepository.ProjectInputField().sendKeys(Keys.BACK_SPACE);

            createIssueRepository.ProjectInputField().sendKeys(project);

            createIssueRepository.ProjectInputField().sendKeys(Keys.ENTER);
        }

        if (!createIssueRepository.ActualIssueType().getAttribute("value").equals(type)) {
            createIssueRepository.IssueTypeOptionOnCreateIssueScreen().click();

            //((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", createIssueRepository.IssueInputField());


            createIssueRepository.IssueInputField().sendKeys(Keys.BACK_SPACE);

            createIssueRepository.IssueInputField().sendKeys(type);

            createIssueRepository.IssueInputField().sendKeys(Keys.ENTER);
        }

        createIssueRepository.SummaryInputFieldOnCreateScreen().sendKeys(summary);

        createIssueRepository.CreateButtonOnCreateScreen().click();

        createIssueRepository.CreateIssuePopUp().click();

        boolean result = multipleAssertion(project, type, summary);

        //Delete the created issue
        createIssueRepository.MoreButton().click();
        createIssueRepository.DeleteOption().click();
        createIssueRepository.ConfirmTheDelete().click();

        return result;
    }

}
