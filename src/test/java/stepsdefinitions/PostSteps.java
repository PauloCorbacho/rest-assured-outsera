package stepsdefinitions;

import core.ApiClient;
import core.TestDataBuilder;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostSteps {
    private final ApiClient apiClient;
    private Map<String, Object> postData;

    public PostSteps(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Given("I prepare a new post for user {int}")
    public void prepareNewPost(int userId) {
        this.postData = TestDataBuilder.buildPostData(userId);
    }

    @When("I create the post")
    public void createPost() {
        apiClient.post("/posts", postData);
    }

    @Then("the response should contain the created post data")
    public void verifyCreatedPost() {
        Map<String, Object> responseData = apiClient.getLastResponse().jsonPath().getMap("$");
        assertThat(responseData.get("id"), notNullValue());
        postData.forEach((key, value) -> {
            assertThat(responseData.get(key), equalTo(value));
        });
    }

    @When("I get all posts")
    public void getAllPosts() {
        apiClient.get("/posts");
    }

    @When("I get post with id {int}")
    public void getPostById(int id) {
        apiClient.get("/posts/" + id);
    }

    @When("I get posts for user {int}")
    public void getPostsByUser(int userId) {
        apiClient.getWithParam("/posts", "userId", userId);
    }

    @Then("the response should contain a list of posts")
    public void verifyPostsList() {
        List<Map<String, Object>> posts = apiClient.getLastResponse().jsonPath().getList("$");
        assertThat(posts.size(), greaterThan(0));
        posts.forEach(this::verifyPostStructure);
    }

    @Then("the response should contain post details")
    public void verifyPostDetails() {
        verifyPostStructure(apiClient.getLastResponse().jsonPath().getMap("$"));
    }

    @Then("the response should contain only posts from user {int}")
    public void verifyPostsByUser(int userId) {
        List<Map<String, Object>> posts = apiClient.getLastResponse().jsonPath().getList("$");
        assertThat(posts.size(), greaterThan(0));
        posts.forEach(post -> assertThat(post.get("userId"), is(userId)));
    }

    private void verifyPostStructure(Map<String, Object> post) {
        assertThat(post.get("userId"), notNullValue());
        assertThat(post.get("id"), notNullValue());
        assertThat(post.get("title").toString(), not(isEmptyString()));
        assertThat(post.get("body").toString(), not(isEmptyString()));
    }

    @Then("the response should match the request data")
    public void verifyResponseData() {
        Map<String, Object> responseData = apiClient.getLastResponse().jsonPath().getMap("$");
        assertThat(responseData.get("title"), equalTo(postData.get("title")));
        assertThat(responseData.get("body"), equalTo(postData.get("body")));
    }
}