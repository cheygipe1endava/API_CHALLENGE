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
        listController.sendCreateList();
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
        listController.getListID(listID);
    }

    @And("^the list created is erased$")
    public void theListCreatedIsErased()
    {
        Assert.assertEquals("User has successfully deleted the list",
                "12", listController.deleteList().getStatus_code());
    }

    @Given("^a new list must be created in TMDB$")
    public void aNewListMustBeCreatedInTMDB()
    {
        listController.getSessionID(sessionID);
        Assert.assertEquals("User has created a valid list","true", listController.createList().getSuccess());
    }

    @And("^the user wants to add a new movie to the list in TMDB$")
    public void theUserWantsToAddANewMovieToTheListInTMDB()
    {

    }

    @When("^the user sends a request to add a movie$")
    public void theUserSendsARequestToAddAMovie()
    {
        listController.createMovieBody();
        listController.sendAddMovieRequest();
    }

    @Then("^the response contains a status code for the added movie$")
    public void theResponseContainsAStatusCodeForTheAddedMovie()
    {
        listController.getAddMovieResponse();
        Assert.assertEquals("User has added a valid movie to the list",
                "12", listController.getAddMovieResponse().getStatus_code());
    }

    @And("^the details of the list must be shown$")
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
        listController.getListDetailsBody();
        Assert.assertEquals("Successfully got list details",
                "FELIPE_GIRALDO_PEREZ", listController.getListDetailsResponse().getCreated_by());
    }

    @And("^a movie must be inserted in the list$")
    public void aMovieMustBeInsertedInTheList()
    {
        Assert.assertEquals("User has added a valid movie to the list",
                "12", listController.addMovie().getStatus_code());
    }

    @And("^the items contained in the list must be shown$")
    public void theItemsContainedInTheListMustBeShown()
    {

    }

    @When("^the user sends a request to get those items contained in list$")
    public void theUserSendsARequestToGetThoseItemsContainedInList()
    {
        listController.sendItemsInListRequest();
    }

    @Then("^the response contains information of items in the list$")
    public void theResponseContainsInformationOfItemsInTheList()
    {
        Assert.assertEquals("Item in list exists",
                "true", listController.getItemsInListResponse().getItem_present());
    }

    @And("^movies in the list must be removed$")
    public void moviesInTheListMustBeRemoved()
    {
        listController.getSessionID(sessionID);
    }

    @When("^the user send a request to remove movies contained in list$")
    public void theUserSendARequestToRemoveMoviesContainedInList()
    {
        listController.createMovieBody();
        listController.sendItemsRemovalRequest();
    }

    @Then("^the response shows successful remove action of the movie$")
    public void theResponseShowsSuccessfulRemoveActionOfTheMovie()
    {
        listController.getItemsRemovalResponse();
        Assert.assertEquals("User has deleted a valid movie from the list",
                "13", listController.getItemsRemovalResponse().getStatus_code());
    }

    @And("^all records in the list must be cleared$")
    public void allRecordsInTheListMustBeCleared()
    {
        listController.getSessionID(sessionID);
    }

    @When("^the user send a request to clear the list$")
    public void theUserSendARequestToClearTheList()
    {
        listController.sendClearListRequest();
    }

    @Then("^the response shows successful clear of the list$")
    public void theResponseShowsSuccessfulClearOfTheList()
    {
        listController.getClearListResponse();
        Assert.assertEquals("User has successfully cleared the list",
                "12", listController.getItemsRemovalResponse().getStatus_code());
    }

    @And("^the created list must be erased from TMDb$")
    public void theCreatedListMustBeErasedFromTMDb()
    {

    }

    @When("^the user send a request to delete the list$")
    public void theUserSendARequestToDeleteTheList()
    {
        listController.sendDeleteList();
    }

    @Then("^the response shows successful delete of the list$")
    public void theResponseShowsSuccessfulDeleteOfTheList()
    {
        listController.getDeleteList();
        Assert.assertEquals("User has successfully deleted the list",
                "12", listController.getItemsRemovalResponse().getStatus_code());
    }



}
