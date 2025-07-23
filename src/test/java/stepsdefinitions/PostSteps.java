package stepsdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostSteps extends BaseSteps {

    @Given("the API base URL is {string}")
    public void theAPIBaseURLIs(String baseUrl) {
        setBaseUrl(baseUrl);
    }

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        sendGetRequest(endpoint);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        validateStatusCode(statusCode);
    }

    @And("the response should contain a list of posts")
    public void theResponseShouldContainAListOfPosts() {
        List<Map<String, Object>> posts = response.jsonPath().getList("$");
        assertThat(posts.size(), greaterThan(0));

        posts.forEach(post -> {
            assertThat(post.get("userId"), notNullValue());
            assertThat(post.get("id"), notNullValue());
            assertThat(post.get("title"), notNullValue());
            assertThat(post.get("body"), notNullValue());
        });
    }

    @And("the response should contain post details with id {int}")
    public void theResponseShouldContainPostDetailsWithId(int expectedId) {
        assertThat(response.jsonPath().getInt("id"), is(expectedId));
        assertThat(response.jsonPath().getString("title"), not(emptyString()));
        assertThat(response.jsonPath().getString("body"), not(emptyString()));
    }

    @Given("I have the following post data:")
    public void iHaveTheFollowingPostData(Map<String, String> data) {
        requestBody.clear();
        requestBody.putAll(data);
    }

    @When("I send a POST request to {string} with the given data")
    public void iSendAPOSTRequestToWithTheGivenData(String endpoint) {
        response = RestAssured.given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post(endpoint);
    }

    @And("the response should contain the created post data")
    public void theResponseShouldContainTheCreatedPostData() {
        assertThat(response.jsonPath().getInt("id"), notNullValue());
        requestBody.forEach((key, value) -> {
            assertThat(response.jsonPath().get(key), is(value));
        });
    }

    @Given("I have the following updated post data:")
    public void iHaveTheFollowingUpdatedPostData(Map<String, String> data) {
        requestBody.clear();
        requestBody.putAll(data);
    }

    @When("I send a PUT request to {string} with the given data")
    public void iSendAPUTRequestToWithTheGivenData(String endpoint) {
        response = RestAssured.given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .put(endpoint);
    }

    @And("the response should contain the updated post data")
    public void theResponseShouldContainTheUpdatedPostData() {
        requestBody.forEach((key, value) -> {
            Object actual = response.jsonPath().get(key);
            assertThat(String.valueOf(actual), is(String.valueOf(value)));
        });
    }

    @When("I send a DELETE request to {string}")
    public void iSendADELETERequestTo(String endpoint) {
        response = RestAssured.given()
                .spec(requestSpec)
                .when()
                .delete(endpoint);
    }

    @And("the response should be empty")
    public void theResponseShouldBeEmpty() {
        assertThat(response.getBody().asString(), is(""));
    }
}