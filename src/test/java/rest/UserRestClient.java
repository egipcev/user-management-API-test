package rest;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import dto.User;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static users.BasicTest.BASE_URI;
import static users.BasicTest.TOKEN;

public class UserRestClient {

    private final RequestSpecification reqSpec;
    private static UserRestClient restClient;

    private UserRestClient() {
        this.reqSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .addHeader("Authorization", "Bearer " + TOKEN)
                .log(LogDetail.ALL)
                .build();
    }

    public static UserRestClient getInstance() {
        if (restClient == null) {
            restClient = new UserRestClient();
        }
        return restClient;
    }

    @Step
    public ValidatableResponse createUser(User user) {
        Map<String, Object> body = new HashMap<>();
        body.put("email", user.getEmail());
        body.put("id", user.getId());
        body.put("password", user.getPassword());
        body.put("username", user.getUsername());
        body.put("profile", user.getProfile());
        return given()
                .spec(reqSpec)
                .body(body)
                .post("Create")
                .then()
                .log()
                .all();

    }

    @Step
    public ValidatableResponse deleteUser(String id) {
        Map<String, String> body = new HashMap<>();
        body.put("id", id);
        return given()
                .spec(reqSpec)
                .body(body)
                .post("Delete")
                .then()
                .log()
                .all();
    }

    @Step
    public ValidatableResponse readUser(String id) {
        Map<String, String> body = new HashMap<>();
        body.put("id", id);
        return given()
                .spec(reqSpec)
                .body(body)
                .post("Read")
                .then()
                .log()
                .all();
    }


}
