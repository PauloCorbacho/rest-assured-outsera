package stepsdefinitions;

import core.ApiClient;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CommonSteps {
    private final ApiClient apiClient;

    public CommonSteps(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Given("the API base URL is {string}")
    public void setBaseUrl(String baseUrl) {
        apiClient.setBaseUrl(baseUrl);
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int statusCode) {
        assertThat(apiClient.getLastResponse().getStatusCode(), is(statusCode));
    }
}