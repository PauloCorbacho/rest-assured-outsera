package core;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiClient {
    private Response lastResponse;
    private RequestSpecification requestSpec;
    private String baseUrl;

    public ApiClient() {
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        RestAssured.baseURI = baseUrl;
        this.requestSpec = new RequestSpecBuilder()
                .setContentType("application/json")
                .setAccept("application/json")
                .build();
    }

    public Response get(String endpoint) {
        this.lastResponse = RestAssured.given()
                .spec(requestSpec)
                .when()
                .get(endpoint);
        return this.lastResponse;
    }

    public Response getWithParam(String endpoint, String paramName, Object paramValue) {
        this.lastResponse = RestAssured.given()
                .spec(requestSpec)
                .queryParam(paramName, paramValue)
                .when()
                .get(endpoint);
        return this.lastResponse;
    }

    public Response post(String endpoint, Object body) {
        this.lastResponse = RestAssured.given()
                .spec(requestSpec)
                .body(body)
                .when()
                .post(endpoint);
        return this.lastResponse;
    }

    public Response put(String endpoint, Object body) {
        this.lastResponse = RestAssured.given()
                .spec(requestSpec)
                .body(body)
                .when()
                .put(endpoint);
        return this.lastResponse;
    }

    public Response delete(String endpoint) {
        this.lastResponse = RestAssured.given()
                .spec(requestSpec)
                .when()
                .delete(endpoint);
        return this.lastResponse;
    }

    public Response getLastResponse() {
        return this.lastResponse;
    }
}
