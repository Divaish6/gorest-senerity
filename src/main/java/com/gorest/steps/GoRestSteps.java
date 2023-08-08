package com.gorest.steps;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class GoRestSteps {
    static final String token = "Bearer 2feef4662388ec0aeb23a14ea7a70888bdfa03b1aee9e415cb6d56ffc29fa9cd";
    @Step("Creating new user record with name: {0}, email: {1}, gender: {2} and status: {3}")
    public ValidatableResponse createUserRecord(String name, String email, String gender, String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        return SerenityRest.given().log().all()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .when()
                .body(userPojo)
                .post("/users")
                .then().log().all().statusCode(201);
    }

    @Step("Getting all users records")
    public ValidatableResponse getUserIDs() {
        return SerenityRest.given().log().all()
                .header("Authorization", token)
                .when()
                .get(EndPoints.GET_USERS)
                .then().log().all().statusCode(200);
    }

    @Step("Updating user record with id: {0}, name: {1}, email: {2}, gender: {3} and status: {4}")
    public void userRecordUpdate(int id, String name, String email, String gender, String status) {
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        SerenityRest.given().log().all()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .pathParam("id", id)
                .body(userPojo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then().log().all().statusCode(200);
    }

    @Step("Getting single record with id: {0}")
    public ValidatableResponse getSingleUser(int id) {
        return SerenityRest.given().log().all()
                .header("Authorization", token)
                .pathParam("id", id)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then().log().all();
    }

    @Step("Deleting  user record with id: {0}")
    public ValidatableResponse deleteUserRecord(int id) {
        return SerenityRest.given().log().all()
                .header("Authorization", token)
                .pathParam("id", id)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then().log().all();
    }
}
