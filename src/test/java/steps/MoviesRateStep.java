package steps;

import controllers.AuthenticationController;
import controllers.MoviesRateController;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.Matchers;
import org.junit.Assert;

public class MoviesRateStep {

    private AuthenticationController authenticationController = new AuthenticationController();
    private MoviesRateController moviesRateController = new MoviesRateController();
    private String sessionID, guestSessionID;

    @Given("^the user has a valid session and guest session created with its API Key$")
    public void theUserHasAValidSessionAndGuestSessionCreatedWithItsAPIKey()
    {
        guestSessionID = authenticationController.getGuestSession().getGuest_session_id();
        sessionID = authenticationController.Authenticate().getSession_id();
    }

    @Given("^the movie \"([^\"]*)\" must be rated as \"([^\"]*)\" in TMDB$")
    public void theMovieMustBeRatedAsInTMDB(String movieID, double rating)
    {
        moviesRateController.setMovieID(movieID);
        moviesRateController.setRatingController(rating);
        moviesRateController.setSessionID(sessionID);
        moviesRateController.setGuestSessionID(guestSessionID);
    }

    @When("^the user sends a request to add the movie rating$")
    public void theUserSendsARequestToAddTheMovieRating()
    {
        moviesRateController.sendRateMovie();
    }

    @Then("^the service returns a successful movie rating result$")
    public void theServiceReturnsASuccessfulMovieRatingResult()
    {
        Assert.assertThat(String.format("Error: The status code returned is not %s or %s", "1", "12"),
                moviesRateController.getRateMovie().getStatus_code(),
                Matchers.anyOf(Matchers.equalTo("1"),Matchers.equalTo("12")));
    }

    @Given("^the movie \"([^\"]*)\" rating must be deleted from TMDB$")
    public void theMovieRatingMustBeDeletedFromTMDB(String movieID)
    {
        moviesRateController.setMovieID(movieID);
        moviesRateController.setSessionID(sessionID);
        moviesRateController.setGuestSessionID(guestSessionID);
    }

    @When("^the user sends a request to delete the rating$")
    public void theUserSendsARequestToDeleteTheRating()
    {
        moviesRateController.sendDeleteMovieRating();
    }

    @Then("^the service returns a successful movie rating elimination$")
    public void theServiceReturnsASuccessfulMovieRatingElimination()
    {
        Assert.assertEquals("Movie rate deleted successfully","13",
                moviesRateController.getDeleteMovieRating().getStatus_code());
    }
}
