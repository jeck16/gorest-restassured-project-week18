package com.gorest.userinfo;

import com.gorest.constants.EndPoints;
import com.gorest.model.PostPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class PostsSteps {
    @Step("Creating post with id : {0}, user_id : {1}, title : {2}, body : {3}")
    public ValidatableResponse createPost(String id,String user_id,String title,String body) {
        PostPojo postsPojo = PostPojo.getPostsPojo(id,user_id,title,body);
        return SerenityRest.given()
                .header("Authorization", "Bearer cce0297b38b4f409cd6dc5a94a6115eb7adb27c8d2ee5e0783cfa1c4d7889088")
                .header("Content-Type", "application/json")
                .when()
                .body(postsPojo)
                .post(EndPoints.GET_ALL_POSTS)
                .then();
    }

    @Step("Update post with id : {0},user_id : {1}, title : {2}, body : {3}")
    public ValidatableResponse updatePost(String id,String user_id,String title,String body){

        PostPojo postsPojo = PostPojo.getPostsPojo(id,user_id,title,body);
        return SerenityRest.given()
                .header("Authorization", "Bearer cce0297b38b4f409cd6dc5a94a6115eb7adb27c8d2ee5e0783cfa1c4d7889088")
                .header("Content-Type", "application/json")
                .pathParam("postID", id)
                .when()
                .body(postsPojo)
                .put(EndPoints.UPDATE_POST_BY_ID)
                .then().log().all().statusCode(404);
    }
    @Step("Delete post information with id : {0}")
    public ValidatableResponse deletePostId(String id){
        return SerenityRest.given()
                .header("Authorization", "Bearer cce0297b38b4f409cd6dc5a94a6115eb7adb27c8d2ee5e0783cfa1c4d7889088")
                .header("Content-Type", "application/json")
                .pathParam("postID", id)
                .when()
                .delete(EndPoints.DELETE_POST_BY_ID)
                .then().statusCode(200);
    }

    @Step("Getting store information with storeid : {0}")
    public ValidatableResponse getPostId(String id){
        return SerenityRest.given()
                .pathParam("postID", id)
                .when()
                .get(EndPoints.GET_SINGLE_POST_BY_ID)
                .then();
    }
}
