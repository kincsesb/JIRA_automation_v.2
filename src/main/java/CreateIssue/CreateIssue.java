package CreateIssue;

import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateIssue {

    private WebDriverWait wait;
    public CreateIssueRepository createIssueRepository;

    public CreateIssue(WebDriverWait Wait){
        this.wait = Wait;
        createIssueRepository = new CreateIssueRepository(wait);
    }

    public void setSummary(String summary){
        createIssueRepository.SummaryInputFieldOnCreateScreen().sendKeys(summary);
    }

    public boolean multipleAssertion(String project, String type, String summary){

        String [] projectName = project.split(" \\(");

        boolean projectResult = createIssueRepository.ProjectOnCreatedIssue().getText().equals(projectName[0]);
        System.out.print(createIssueRepository.ProjectOnCreatedIssue().getText());
        boolean summaryResult = createIssueRepository.SummaryOnCreatedIssue().getText().equals(summary);
        System.out.print(createIssueRepository.SummaryOnCreatedIssue().getText());
        boolean issueTypeResult = createIssueRepository.IssueTypeOnCreatedIssue().getText().equals(type);
        System.out.print(createIssueRepository.IssueTypeOnCreatedIssue().getText());
        boolean result = projectResult && summaryResult && issueTypeResult;

        return result;
    }

    public boolean successfulCreateIssue(String project,String type,String summary){
        createIssueRepository.CreateButton().click();


        if(!createIssueRepository.ActualProjectNameOnCreateScreen().getAttribute("value").equals(project)){
            createIssueRepository.ProjectOptionOnCreateScreen().click();
            createIssueRepository.DynamicProject(project).click();
        }

        if(!createIssueRepository.ActualIssueType().getAttribute("value").equals(type)){
            createIssueRepository.IssueTypeOptionOnCreateIssueScreen().click();
            createIssueRepository.DynamicIssueType(type).click();
        }

        createIssueRepository.SummaryInputFieldOnCreateScreen().sendKeys(summary);

        createIssueRepository.CreateButtonOnCreateScreen().click();

        createIssueRepository.CreateIssuePopUp().click();

        boolean result = multipleAssertion(project,type,summary);

        //Delete the created issue
        createIssueRepository.MoreButton().click();
        createIssueRepository.DeleteOption().click();
        createIssueRepository.ConfirmTheDelete().click();

        return result;
    }
}
