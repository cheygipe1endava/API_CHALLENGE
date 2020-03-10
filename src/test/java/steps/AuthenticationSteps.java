package steps;

import controllers.AuthenticationController;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class AuthenticationSteps {

    private AuthenticationController authenticationController = new AuthenticationController();

    @Given("^the user requests a guest session$")
    public void theUserRequestsAGuestSession()
    {

    }

    @When("^the user sends the requests for a guest session$")
    public void theUserSendsTheRequestsForAGuestSession()
    {
        authenticationController.sendGuestSession();
    }

    @Then("^the user creates a valid guest session id$")
    public void theUserCreatesAValidGuestSessionId()
    {
        authenticationController.getGuestSession().getGuest_session_id();
    }

    @And("^the response contains a successful status$")
    public void theResponseContainsASuccessfulStatus()
    {
        Assert.assertEquals("Guest session successfully created",
                "true", authenticationController.getGuestSession().getSuccess());
    }

    @Given("^the user requests a session token$")
    public void theUserRequestsASessionToken()
    {
        authenticationController.getRequestToken();
    }

    @When("^the user requests session with login$")
    public void theUserRequestsSessionWithLogin()
    {
        authenticationController.sessionWithLogin();
    }

    @Then("^the user creates a valid session$")
    public void theUserCreatesAValidSession()
    {
        Assert.assertEquals("User has a valid session key","true",  authenticationController.createSession().getSuccess());
    }

    @And("^the session is deleted$")
    public void theSessionIsDeleted()
    {
        authenticationController.sendDeleteSession();
        Assert.assertEquals("The session was deleted successfully",
                "true",  authenticationController.getdeleteSession().getSuccess());
    }
}
