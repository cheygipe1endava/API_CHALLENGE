package steps;

import controllers.AuthenticationController;
import controllers.ListController;
import entities.ListRequests;
import entities.SessionRequests;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.JsonHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.eclipse.jetty.client.api.Authentication;
import org.junit.Assert;
import org.yecht.Data;

import java.net.URL;
import java.util.List;

public class ListStep{

    private ListController listController = new ListController();
    private SessionRequests Autorization;
    private ListRequests listRequests;
    private String successResult, sessionID, listID;
    private Response response, responseMovie;
    private RequestSpecification httpRequestInstance = RestAssured.given().contentType(ContentType.JSON);
    private URL idUrl;
    private ListRequests requests;

    @Given("^the user has a valid session created with its API Key$")
    public void theUserHasAValidSessionCreatedWithItsAPIKey() {
        AuthenticationController authenticationController = new AuthenticationController();
        sessionID = authenticationController.Authenticate().getSession_id();
    }

    @Given("^a new list needs to be created in TMDB$")
    public void aNewListNeedsToBeCreatedInTMDB()
    {
        listController.getSessionID(sessionID);
        listController.createListBody();
    }

    @When("^the user send a request to create the list$")
    public void theUserSendARequestToCreateTheList()
    {
        listController.sendCreateListResponse();
    }

    @Then("^the service responds with a success result$")
    public void theServiceRespondsWithASuccessResult()
    {
        successResult = listController.getResponseBody().getSuccess();
        Assert.assertEquals("User has created a valid list","true", successResult);
    }

    @And("^the response contains the new list$")
    public void theResponseContainsTheNewList()
    {
        listID = listController.getResponseBody().getList_id();
    }
}
