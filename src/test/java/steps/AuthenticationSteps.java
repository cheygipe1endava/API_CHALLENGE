package steps;

import controllers.AuthenticationController;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class AuthenticationSteps {

    AuthenticationController authenticationController = new AuthenticationController();

    @Given("^the user requests a session token$")
    public void theUserRequestsASessionToken()
    {
        authenticationController.getRequestToken();
    }

    @When("^the user requests session with login$")
    public void theUserRequestsSessionWithLogin()
    {
        authenticationController.sessionWithLoginBody();
        authenticationController.sessionWithLogin();
    }

    @Then("^the user creates a valid session$")
    public void theUserCreatesAValidSession()
    {
        authenticationController.createSessionBody();
        Assert.assertEquals("User has a valid session key","true", authenticationController.createSession());
    }
}
