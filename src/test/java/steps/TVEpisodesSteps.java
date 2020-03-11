package steps;

import controllers.AuthenticationController;
import controllers.TVEpisodesRateController;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class TVEpisodesSteps {

    private AuthenticationController authenticationController = new AuthenticationController();
    private TVEpisodesRateController tvEpisodesRateController = new TVEpisodesRateController();
    private String sessionID, guestSessionID;

    @Given("^the user has a valid session and guest session created with API KEY$")
    public void theUserHasAValidSessionAndGuestSessionCreatedWithAPIKEY()
    {
        guestSessionID = authenticationController.getGuestSession().getGuest_session_id();
        sessionID = authenticationController.Authenticate().getSession_id();
    }

    @Given("^a TV episode must be rated in TMDB$")
    public void aTVEpisodeMustBeRatedInTMDB()
    {
        tvEpisodesRateController.setGuestSessionID(guestSessionID);
        tvEpisodesRateController.setSessionID(sessionID);
    }

    @When("^the user sends a request to add the TV episode rating$")
    public void theUserSendsARequestToAddTheTVEpisodeRating()
    {
        tvEpisodesRateController.sendRateTVEpisode();
    }

    @Then("^the service returns a successful TV episode rating result$")
    public void theServiceReturnsASuccessfulTVEpisodeRatingResult()
    {
        Assert.assertThat(String.format("Error: The status code returned is not %s or %s", "1", "12"),
                tvEpisodesRateController.getRateTVEpisode().getStatus_code(),
                Matchers.anyOf(Matchers.equalTo("1"),Matchers.equalTo("12")));
    }

    @Given("^a TV episode rating must be deleted from TMDB$")
    public void aTVEpisodeRatingMustBeDeletedFromTMDB()
    {
        tvEpisodesRateController.setGuestSessionID(guestSessionID);
        tvEpisodesRateController.setSessionID(sessionID);
    }

    @When("^the user sends a request to delete the TV episode rating$")
    public void theUserSendsARequestToDeleteTheTVEpisodeRating()
    {
        tvEpisodesRateController.sendDeleteRateTVEpisode();
    }

    @Then("^the service returns a successful TV episode rating elimination$")
    public void theServiceReturnsASuccessfulTVEpisodeRatingElimination()
    {
        Assert.assertEquals("TV show rate deleted successfully","13",
                tvEpisodesRateController.getDeleteRateTVEpisode().getStatus_code());
    }
}
