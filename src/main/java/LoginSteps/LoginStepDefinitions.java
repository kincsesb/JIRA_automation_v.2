package LoginSteps;

import DriverManager.DriverManager;
import Login.LoginPage;
import NavBar.NavBar;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import io.cucumber.java.Before;
import io.cucumber.java.After;

import java.io.IOException;


public class LoginStepDefinitions {
    private NavBar navBar;
    private LoginPage loginPage;
    private DriverManager driverManager;

    private String userName;
    private String password;

    @Before
    public void SetUp(){
        driverManager = new DriverManager();

        loginPage = new LoginPage(driverManager.getDriver(), driverManager.getWait());
        navBar = new NavBar(driverManager.getDriver(), driverManager.getWait());

        loginPage.navigateToTheLoginPage(driverManager.getDriver());
    }

    @After
    public void TearDown(){
        driverManager.tearDown();
    }

    @Given("the user has valid credentials with username and password")
    public void the_user_has_valid_credentials(){
        userName = loginPage.configReader.getUsername();
        password = loginPage.configReader.getPassword();
    }


    @When("the user enters the username and password")
    public void the_user_enters_the_username_and_password(){
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
    }

    @When("clicks the log in button")
    public void clicks_the_log_in_button() {
        loginPage.clickToLogInButton();
    }

    @When("clicks the avatar icon")
    public void clicks_the_avatar_icon() {
        navBar.clickToAvatarIcon();
    }

    @When("chooses the profile option")
    public void chooses_the_profile_option() {
        navBar.clickToProfileOption();
    }

    @Then("the displayed username should be the same as the one used for log in")
    public void the_displayed_username_should_be_the_same_as_the_one_used_for_log_in() {
        Assert.assertEquals(loginPage.getUserName(), "automation48");
    }
}
