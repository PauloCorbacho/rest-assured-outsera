package stepsdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CommentsSteps extends BaseSteps {

    @When("I send a GET request to {string} with parameter {string} equal to {string}")
    public void iSendAGETRequestToWithParameterEqualTo(String endpoint, String paramName, String paramValue) {
        sendGetRequestWithParam(endpoint, paramName, paramValue);
    }

    @And("the response should contain only comments for post with id {int}")
    public void theResponseShouldContainOnlyCommentsForPostWithId(int postId) {
        List<Map<String, Object>> comments = response.jsonPath().getList("$");
        assertThat(comments.size(), greaterThan(0));

        comments.forEach(comment -> {
            assertThat(comment.get("postId"), is(postId));
            assertThat(comment.get("id"), notNullValue());
            assertThat(comment.get("name"), notNullValue());
            assertThat(comment.get("email"), notNullValue());
            assertThat(comment.get("body"), notNullValue());
        });
    }

    @And("the response should contain comment details with id {int}")
    public void theResponseShouldContainCommentDetailsWithId(int commentId) {
        assertThat(response.jsonPath().getInt("id"), is(commentId));
        assertThat(response.jsonPath().getInt("postId"), notNullValue());
        assertThat(response.jsonPath().getString("name"), not(emptyString()));
        assertThat(response.jsonPath().getString("email"), not(emptyString()));
        assertThat(response.jsonPath().getString("body"), not(emptyString()));
    }
}