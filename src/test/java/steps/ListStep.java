package steps;

import controllers.AuthenticationController;
import controllers.ListController;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;



public class ListStep{

    private ListController listController = new ListController();
    private String sessionID, listID;

    @Given("^the user has a valid session created with its API Key$")
    public void theUserHasAValidSessionCreatedWithItsAPIKey()
    {
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
        listController.getResponseBody();
        Assert.assertEquals("User has created a valid list","true", listController.getResponseBody().getSuccess());
    }

    @And("^the response contains the new list$")
    public void theResponseContainsTheNewList()
    {
        listID = listController.getResponseBody().getList_id();
    }

    @Given("^the user wants to add a new movie to the list in TMDB$")
    public void theUserWantsToAddANewMovieToTheListInTMDB()
    {
        listController.getSessionID(sessionID);
    }

    @When("^the user send a request to add a movie$")
    public void theUserSendARequestToAddAMovie()
    {
        listController.createAddMovieBody();
        listController.sendAddMovieRequest();
    }

    @Then("^the response contains a status code for the added movie$")
    public void theResponseContainsAStatusCodeForTheAddedMovie()
    {
        listController.getAddMovieResponse();
        Assert.assertEquals("User has added a valid movie to the list",
                "12", listController.getAddMovieResponse().getStatus_code());
    }

    @Given("^the details of the list must be shown$")
    public void theDetailsOfTheListMustBeShown()
    {

    }

    @When("^the user send a request to get details of the list$")
    public void theUserSendARequestToGetDetailsOfTheList()
    {
        listController.sendListDetailsRequest();
    }

    @Then("^the response contains details of the list$")
    public void theResponseContainsDetailsOfTheList()
    {
        listController.getListDetailsResponse();
    }


}
