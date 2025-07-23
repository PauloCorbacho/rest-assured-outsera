package stepsdefinitions;

import io.cucumber.java.en.And;
import java.util.List;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UsersSteps extends BaseSteps{

    @And("the response should contain a list of users")
    public void theResponseShouldContainAListOfUsers() {
        List<Map<String, Object>> users = response.jsonPath().getList("$");
        assertThat(users.size(), greaterThan(0));

        users.forEach(user -> {
            assertThat(user.get("id"), notNullValue());
            assertThat(user.get("name"), notNullValue());
            assertThat(user.get("username"), notNullValue());
            assertThat(user.get("email"), notNullValue());
            assertThat(user.get("address"), notNullValue());
            assertThat(user.get("phone"), notNullValue());
            assertThat(user.get("website"), notNullValue());
            assertThat(user.get("company"), notNullValue());
        });
    }

    @And("the response should contain valid user details with id {int}")
    public void theResponseShouldContainValidUserDetailsWithId(int userId) {
        assertThat(response.jsonPath().getInt("id"), is(userId));
        assertThat(response.jsonPath().getString("name"), not(emptyString()));
        assertThat(response.jsonPath().getString("username"), not(emptyString()));
        assertThat(response.jsonPath().getString("email"), not(emptyString()));
        assertThat(response.jsonPath().getMap("address"), notNullValue());
        assertThat(response.jsonPath().getString("phone"), not(emptyString()));
        assertThat(response.jsonPath().getString("website"), not(emptyString()));
        assertThat(response.jsonPath().getMap("company"), notNullValue());
    }

    @And("the email should be in valid format")
    public void theEmailShouldBeInValidFormat() {
        String email = response.jsonPath().getString("email");
        assertThat(email, matchesPattern(".+@.+\\..+"));
    }
}
