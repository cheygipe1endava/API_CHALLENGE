package steps;

import controllers.AuthenticationController;
import cucumber.api.PendingException;
import entities.Requests;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

public class ListStep {

    private AuthenticationController authControlInstance;
    private Requests Autorization;

    @Given("^The user has a valid session created with its API Key$")
    public void theUserHasAValidSessionCreatedWithItsAPIKey() {

        authControlInstance = new AuthenticationController();
        Autorization = authControlInstance.Authenticate();
        Assert.assertEquals("true", Autorization.getSuccess());
    }


    @Given("^A new list needs to be created in TMDB$")
    public void aNewListNeedsToBeCreatedInTMDB() {
    }

    @When("^The user send a request to create the list$")
    public void theUserSendARequestToCreateTheList() {
    }

    @Then("^The service responds with a status code \"([^\"]*)\"$")
    public void theServiceRespondsWithAStatusCode(String arg0) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @And("^The response contains the new list$")
    public void theResponseContainsTheNewList() {
    }
}
