package stepsdefinitions;

import core.ApiClient;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UsersSteps {
    private final ApiClient apiClient;

    public UsersSteps(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @When("I get all users")
    public void getAllUsers() {
        apiClient.get("/users");
    }

    @When("I get user with id {int}")
    public void getUserById(int userId) {
        apiClient.get("/users/" + userId);
    }

    @And("the response should contain a list of users")
    public void verifyUsersList() {
        List<Map<String, Object>> users = apiClient.getLastResponse().jsonPath().getList("$");
        assertThat(users.size(), greaterThan(0));
        users.forEach(this::verifyUserStructure);
    }

    @And("the response should contain valid user details")
    public void verifyUserDetails() {
        verifyUserStructure(apiClient.getLastResponse().jsonPath().getMap("$"));
    }

    @And("the user email should be valid")
    public void verifyEmailFormat() {
        String email = apiClient.getLastResponse().jsonPath().getString("email");
        assertThat(email, matchesPattern("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"));
    }

    private void verifyUserStructure(Map<String, Object> user) {
        assertThat(user.get("id").toString(), not(emptyString()));
        assertThat(user.get("name").toString(), not(emptyString()));
        assertThat(user.get("username").toString(), not(emptyString()));
        assertThat(user.get("email").toString(), not(emptyString()));
        assertThat(user.get("address"), instanceOf(Map.class));
        assertThat(user.get("phone").toString(), not(emptyString()));
        assertThat(user.get("website").toString(), not(emptyString()));
        assertThat(user.get("company"), instanceOf(Map.class));
    }
}