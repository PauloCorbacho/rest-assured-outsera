package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class ApiUtils {
    private static RequestSpecification requestSpec;

    public static void setBaseUrl(String baseUrl) {
        RestAssured.baseURI = baseUrl;
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();
    }

    public static Response sendGetRequest(String endpoint) {
        return RestAssured.given()
                .spec(requestSpec)
                .when()
                .get(endpoint);
    }

    public static Response sendGetRequestWithParam(String endpoint, String paramName, Object paramValue) {
        return RestAssured.given()
                .spec(requestSpec)
                .queryParam(paramName, paramValue)
                .when()
                .get(endpoint);
    }

    public static Response sendPostRequest(String endpoint, Map<String, Object> requestBody) {
        return RestAssured.given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post(endpoint);
    }

    public static Response sendPutRequest(String endpoint, Map<String, Object> requestBody) {
        return RestAssured.given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .put(endpoint);
    }

    public static Response sendDeleteRequest(String endpoint) {
        return RestAssured.given()
                .spec(requestSpec)
                .when()
                .delete(endpoint);
    }
}