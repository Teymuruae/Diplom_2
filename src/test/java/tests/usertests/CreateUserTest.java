package tests.usertests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import testmethods.UserMethods;
import utils.Randomizer;

public class CreateUserTest {
   private UserMethods userMethods;

    @Before
    public void setUp() {
        userMethods = new UserMethods();
    }

    @Test
    @DisplayName("create user 200 ok")
    public void createUserSuccessfulTest() {
        ValidatableResponse createUserResponse = userMethods.createUser(HttpStatus.SC_OK);
        Assert.assertTrue(createUserResponse.extract().path("success"));
        String accessToken = createUserResponse.extract().path("accessToken");

        ValidatableResponse deleteUserResponse = userMethods.deleteUser(accessToken, HttpStatus.SC_ACCEPTED);
        Assert.assertTrue(deleteUserResponse.extract().path("success"));
    }

    @Test
    @DisplayName("trying to create existing user")
    public void createExistingUserTest() {
        ValidatableResponse createUserTrueResponse = userMethods.createUser(HttpStatus.SC_OK);
        String email = createUserTrueResponse.extract().path("user.email");
        String name = createUserTrueResponse.extract().path("user.name");
        System.out.printf("email: %s name: %s", email, name);

        ValidatableResponse createUserFalseResponse = userMethods
                .createUser(email, Randomizer.getText(), name, HttpStatus.SC_FORBIDDEN);
        Assert.assertEquals("User already exists", createUserFalseResponse.extract().path("message"));

        ValidatableResponse deleteUserResponse = userMethods
                .deleteUser(createUserTrueResponse.extract().path("accessToken"), HttpStatus.SC_ACCEPTED);
        Assert.assertTrue(deleteUserResponse.extract().path("success"));
    }
}

