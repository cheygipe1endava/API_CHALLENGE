package steps;

import controllers.AuthenticationController;
import controllers.TVShowRateController;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class TVShowsRateStep {

    private AuthenticationController authenticationController = new AuthenticationController();
    private TVShowRateController tvShowsRateController = new TVShowRateController();
    private String sessionID, guestSessionID;

    @Given("^the user has a valid session and guest session created$")
    public void theUserHasAValidSessionAndGuestSessionCreated()
    {
        guestSessionID = authenticationController.getGuestSession().getGuest_session_id();
        sessionID = authenticationController.Authenticate().getSession_id();
    }

    @Given("^the TV show \"([^\"]*)\" must be rated \"([^\"]*)\" in TMDB$")
    public void theTVShowMustBeRatedInTMDB(String tvShowID, double rating)
    {
        tvShowsRateController.setTVShowID(tvShowID);
        tvShowsRateController.setRatingController(rating);
        tvShowsRateController.getGuestSessionID(guestSessionID);
        tvShowsRateController.getSessionID(sessionID);
    }

    @When("^the user sends a request to add the TV show rating$")
    public void theUserSendsARequestToAddTheTVShowRating()
    {
        tvShowsRateController.sendRateTVShow();
    }

    @Then("^the service returns a successful TV show rating result$")
    public void theServiceReturnsASuccessfulTVShowRatingResult()
    {
        Assert.assertThat(String.format("Error: The status code returned is not %s or %s", "1", "12"),
                tvShowsRateController.getRateTVShow().getStatus_code(),
                Matchers.anyOf(Matchers.equalTo("1"),Matchers.equalTo("12")));
    }


    @Given("^the TV show \"([^\"]*)\" rating must be deleted from TMDB$")
    public void theTVShowRatingMustBeDeletedFromTMDB(String tvShowID)
    {
        tvShowsRateController.setTVShowID(tvShowID);
        tvShowsRateController.getGuestSessionID(guestSessionID);
        tvShowsRateController.getSessionID(sessionID);
    }

    @When("^the user sends a request to delete the TV show rating$")
    public void theUserSendsARequestToDeleteTheTVShowRating()
    {
        tvShowsRateController.sendDeleteRateTVShow();
    }

    @Then("^the service returns a successful TV show rating elimination$")
    public void theServiceReturnsASuccessfulTVShowRatingElimination()
    {
        Assert.assertEquals("TV show rate deleted successfully","13",
                tvShowsRateController.getDeleteRateTVShow().getStatus_code());
    }
}
