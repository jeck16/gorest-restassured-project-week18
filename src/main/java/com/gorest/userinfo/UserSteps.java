package com.gorest.userinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class UserSteps {
    @Step("Creating user with name : {0}, email : {1}, gender : {2}, status : {3}")
    public ValidatableResponse createUser(String name, String email, String gender, String status) {
        UserPojo usersPojo = UserPojo.getUsersPojo(name, email, gender, status);
        return SerenityRest.given()
                .header("Authorization", "Bearer cce0297b38b4f409cd6dc5a94a6115eb7adb27c8d2ee5e0783cfa1c4d7889088")
                .header("Content-Type", "application/json")
                .when()
                .body(usersPojo)
                .post(EndPoints.GET_ALL_USERS)
                .then();
    }

    @Step("Getting the user inforamation with firstName : {0}")
    public HashMap<String, Object> getUserInfoByName(String name) {
        String s1 = "findAll{it.name == '";
        String s2 = "'}.get(0)";
        return SerenityRest.given()
                .header("Authorization", "Bearer cce0297b38b4f409cd6dc5a94a6115eb7adb27c8d2ee5e0783cfa1c4d7889088")
                .when()
                .get(EndPoints.GET_ALL_USERS)
                .then().statusCode(200)
                .extract()
                .path(s1 + name + s2);
    }

    @Step("Updating user information with userId : {0}, name : {1}, email : {2}, gender : {3}, status : {4}")
    public ValidatableResponse updateUser(int userId, String name, String email, String gender, String status) {
        UserPojo userPojo = UserPojo.getUsersPojo(name, email, gender, status);
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer cce0297b38b4f409cd6dc5a94a6115eb7adb27c8d2ee5e0783cfa1c4d7889088")
                .pathParam("userID", userId)
                .when()
                .body(userPojo)
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then().statusCode(200);
    }

    @Step("Deleting user information with userId : {0}")
    public ValidatableResponse deleteUser(int userId) {
        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer cce0297b38b4f409cd6dc5a94a6115eb7adb27c8d2ee5e0783cfa1c4d7889088")
                .pathParam("userID", userId)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then().statusCode(204);
    }

    @Step("Getting user information with userId : {0}")
    public ValidatableResponse getUserById(int userId) {
        return SerenityRest.given()
                .pathParam("userID", userId)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then().statusCode(404);
    }
}