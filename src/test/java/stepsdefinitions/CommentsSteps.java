package stepsdefinitions;

import core.ApiClient;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CommentsSteps {
    private final ApiClient apiClient;

    public CommentsSteps(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @When("I get comments for post {int}")
    public void getCommentsForPost(int postId) {
        apiClient.getWithParam("/comments", "postId", postId);
    }

    @When("I get comment with id {int}")
    public void getCommentById(int commentId) {
        apiClient.get("/comments/" + commentId);
    }

    @Then("the response should contain only comments for post {int}")
    public void verifyCommentsForPost(int postId) {
        List<Map<String, Object>> comments = apiClient.getLastResponse().jsonPath().getList("$");
        assertThat(comments.size(), greaterThan(0));

        comments.forEach(comment -> {
            assertThat("Comment should belong to post " + postId,
                    comment.get("postId"), is(postId));
            verifyCommentStructure(comment);
        });
    }

    @Then("the response should contain valid comment details")
    public void verifyCommentDetails() {
        verifyCommentStructure(apiClient.getLastResponse().jsonPath().getMap("$"));
    }

    private void verifyCommentStructure(Map<String, Object> comment) {
        assertThat("Comment ID should not be null", comment.get("id"), notNullValue());
        assertThat("Name should be a non-empty string",
                comment.get("name").toString(),
                allOf(notNullValue(), not(emptyString())));
        assertThat("Email should be a non-empty string",
                comment.get("email").toString(),
                allOf(notNullValue(), not(emptyString())));
        assertThat("Body should be a non-empty string",
                comment.get("body").toString(),
                allOf(notNullValue(), not(emptyString())));
    }
}