package steps;

import builders.URLBuilder;
import controllers.AuthenticationController;
import controllers.ListController;
import cucumber.api.PendingException;
import entities.ListRequests;
import entities.SessionRequests;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.JsonHelper;
import helpers.PropertiesHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Assert;
import java.net.URL;
import entities.ListRequests;

public class ListStep {

    private AuthenticationController authControlInstance;
    private ListController listController;
    private SessionRequests Autorization;
    private ListRequests listRequests;
    private String f, e;
    private Response response, response2;
    private RequestSpecification httpRequestInstance = RestAssured.given().contentType(ContentType.JSON);
    private URL idUrl;
    private ListRequests requests;

    @Given("^The user has a valid session created with its API Key$")
    public void theUserHasAValidSessionCreatedWithItsAPIKey() {

        authControlInstance = new AuthenticationController();
        Autorization = authControlInstance.Authenticate();
        Assert.assertEquals("true", Autorization.getSuccess());
    }


    @Given("^A new list needs to be created in TMDB$")
    public void aNewListNeedsToBeCreatedInTMDB() {
        listController = new ListController();
        listController.ListExr();
        listController.createList();


    }

    @When("^The user send a request to create the list$")
    public void theUserSendARequestToCreateTheList() {
    response = listController.sendCreateListResponse();
    }

    @Then("^The service responds with a success result$")
    public void theServiceRespondsWithASuccessResult() {
        f = listController.getSuccess();
        Assert.assertEquals("true", f);
    }

    @And("^The response contains the new list$")
    public void theResponseContainsTheNewList() {
        requests = JsonHelper.responseToListObj(response);
        String r = requests.getList_id();

    }



}
