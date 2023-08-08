package com.gorest.gorestinfo;

import com.gorest.testbase.TestBase;
import com.gorest.userinfo.UserSteps;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCRUDTest extends TestBase {
    static String name = "Prime" + TestUtils.getRandomValue();
    static String email = "prime" + TestUtils.getRandomValue() + "@gmail.com";
    static String gender = "male";
    static String status = "active";
    static int userId;
    @Steps
    UserSteps usersSteps;

    @Title("This will create new user")
    @Test
    public void test01() {
        ValidatableResponse response = usersSteps.createUser(name, email, gender, status);
        response.log().all().statusCode(201);
    }
    @Title("Verify if the user was added to the application")
    @Test
    public void test02() {
        HashMap<String, Object> userMap = usersSteps.getUserInfoByName(name);
        Assert.assertThat(userMap, hasValue(name));
        userId = (int) userMap.get("id");
    }

    @Title("Update the user information and verify the updated information")
    @Test()
    public void test03() {
        name = name + TestUtils.getRandomValue();
        usersSteps.updateUser(userId, name, email, gender, status);
        HashMap<String, Object> userMap = usersSteps.getUserInfoByName(name);
        Assert.assertThat(userMap, hasValue(name));
    }

    @Title("Delete the user and verify if the user is deleted")
    @Test
    public void test04() {
        usersSteps.deleteUser(userId);
        usersSteps.getUserById(userId);
    }
}
