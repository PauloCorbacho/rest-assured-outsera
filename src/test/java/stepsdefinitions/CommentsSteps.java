package stepsdefinitions;

import core.ApiClient;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.List;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

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
            assertThat(comment.get("postId"), is(postId));
            verifyCommentStructure(comment);
        });
    }

    @Then("the response should contain valid comment details")
    public void verifyCommentDetails() {
        verifyCommentStructure(apiClient.getLastResponse().jsonPath().getMap("$"));
    }

    private void verifyCommentStructure(Map<String, Object> comment) {
        assertNotNull(comment.get("id"), "Comment ID should not be null");

        assertInstanceOf(String.class, comment.get("name"), "Name should be a String");
        assertFalse(((String) comment.get("name")).isEmpty(), "Name should not be empty");

        assertInstanceOf(String.class, comment.get("email"), "Email should be a String");
        assertFalse(((String) comment.get("email")).isEmpty(), "Email should not be empty");

        assertInstanceOf(String.class, comment.get("body"), "Body should be a String");
        assertFalse(((String) comment.get("body")).isEmpty(), "Body should not be empty");
    }
}