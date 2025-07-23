package stepsdefinitions;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BaseSteps {
    protected static Response response;
    protected static RequestSpecification requestSpec;
    protected Map<String, Object> requestBody = new HashMap<>();

    protected void setBaseUrl(String baseUrl) {
        RestAssured.baseURI = baseUrl;
        requestSpec = new RequestSpecBuilder()
                .setContentType("application/json")
                .setAccept("application/json")
                .build();
    }

    protected void sendGetRequest(String endpoint) {
        response = RestAssured.given()
                .spec(requestSpec)
                .when()
                .get(endpoint);
    }

    protected void sendGetRequestWithParam(String endpoint, String paramName, Object paramValue) {
        response = RestAssured.given()
                .spec(requestSpec)
                .queryParam(paramName, paramValue)
                .when()
                .get(endpoint);
    }

    protected void validateStatusCode(int expectedStatusCode) {
        assertThat(response.getStatusCode(), is(expectedStatusCode));
    }
}