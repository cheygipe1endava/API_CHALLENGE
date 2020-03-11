package steps;

import controllers.AuthenticationController;
import controllers.ListController;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import org.junit.Assert;

public class ListStep{

    private AuthenticationController authenticationController = new AuthenticationController();
    private ListController listController;
    private String sessionID, listID;

    @Given("^the user has a valid session created with its API Key$")
    public void theUserHasAValidSessionCreatedWithItsAPIKey()
    {
        //sessionID = authenticationController.Authenticate().getSession_id();
        listController = new ListController(authenticationController.Authenticate().getSession_id());
    }

    @Given("^a new list needs to be created in TMDB$")
    public void aNewListNeedsToBeCreatedInTMDB()
    {

    }

    @When("^the user sends a request to create the list$")
    public void theUserSendsARequestToCreateTheList()
    {

    }

    @Then("^the service responds with a success result$")
    public void theServiceRespondsWithASuccessResult()
    {
        Assert.assertEquals("User has created a valid list","true", listController.getResponseBody().getSuccess());
    }

    @And("^the response contains the new list$")
    public void theResponseContainsTheNewList()
    {
        listController.setListID(listController.returnListID());
    }

    @And("^the list created is erased$")
    public void theListCreatedIsErased()
    {
        Assert.assertEquals("User has successfully deleted the list",
                "11", listController.deleteList().getStatus_code());
    }

    @And("^the session is erased$")
    public void theSessionIsErased()
    {
        Assert.assertEquals("Session deleted successfully",
                "true",  authenticationController.deleteSession().getSuccess());
    }

    @Given("^a new list must be created in TMDB$")
    public void aNewListMustBeCreatedInTMDB()
    {
        Assert.assertEquals("User has created a valid list","true", listController.createList().getSuccess());
    }

    @And("^the user wants to add a new movie \"([^\"]*)\" to the list in TMDB$")
    public void theUserWantsToAddANewMovieToTheListInTMDB(String movieID)
    {
        listController.setMovieID(movieID);
    }

    @When("^the user sends a request to add a movie$")
    public void theUserSendsARequestToAddAMovie()
    {

    }

    @Then("^the response contains a status code for the added movie$")
    public void theResponseContainsAStatusCodeForTheAddedMovie()
    {
        Assert.assertEquals("User has added a valid movie to the list",
                "12", listController.getAddMovie().getStatus_code());
    }

    @Given("^the details of the list \"([^\"]*)\" must be shown$")
    public void theDetailsOfTheListMustBeShown(String createdListID)
    {
        listController.setListID(createdListID);
    }

    @When("^the user sends a request to get details of the list$")
    public void theUserSendsARequestToGetDetailsOfTheList()
    {

    }

    @Then("^the response contains details of the list$")
    public void theResponseContainsDetailsOfTheList()
    {
        Assert.assertEquals("Successfully got list details",
                "FELIPE_GIRALDO_PEREZ", listController.getListDetails().getCreated_by());
    }

    @And("^a movie must be inserted in the list$")
    public void aMovieMustBeInsertedInTheList()
    {
        Assert.assertEquals("User has added a valid movie to the list",
                "12", listController.addMovie().getStatus_code());
    }

    @Given("^the movie \"([^\"]*)\" contained in the list \"([^\"]*)\" must be shown$")
    public void theMovieContainedInTheListMustBeShown(String movieID, String createdListID)
    {
        listController.setListID(createdListID);
        listController.setMovieID(movieID);
    }

    @When("^the user sends a request to get those items contained in list$")
    public void theUserSendsARequestToGetThoseItemsContainedInList()
    {

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

    }

    @When("^the user sends a request to remove movies contained in list$")
    public void theUserSendsARequestToRemoveMoviesContainedInList()
    {

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

    }

    @When("^the user sends a request to clear the list$")
    public void theUserSendsARequestToClearTheList()
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

    @When("^the user sends a request to delete the list$")
    public void theUserSendsARequestToDeleteTheList()
    {
        listController.sendDeleteList();
    }

    @Then("^the response shows successful delete of the list$")
    public void theResponseShowsSuccessfulDeleteOfTheList()
    {
        listController.getDeleteList();
        Assert.assertEquals("User has successfully deleted the list",
                "11", listController.getItemsRemovalResponse().getStatus_code());
    }

}
