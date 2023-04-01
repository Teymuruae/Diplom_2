package tests.usertests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import testmethods.UserMethods;
import utils.Randomizer;

public class CreateUserTest2 {
    private UserMethods userMethods;
    private ValidatableResponse createUserResponse;
    private ValidatableResponse deleteUserResponse;
    private String accessToken;

    @Before
    public void setUp() {
        userMethods = new UserMethods();
        createUserResponse = userMethods.createUser(HttpStatus.SC_OK);

    }

    @After
    public void clear() {
        deleteUserResponse = userMethods.deleteUser(accessToken, HttpStatus.SC_ACCEPTED);
        Assert.assertTrue(deleteUserResponse.extract().path("success"));
    }

    @Test
    @DisplayName("create user 200 ok")
    public void createUserSuccessfulTest() {
        Assert.assertTrue(createUserResponse.extract().path("success"));
        accessToken = createUserResponse.extract().path("accessToken");

    }

    @Test
    @DisplayName("trying to create existing user")
    public void createExistingUserTest() {
        String email = createUserResponse.extract().path("user.email");
        String name = createUserResponse.extract().path("user.name");

        ValidatableResponse createUserFalseResponse = userMethods
                .createUser(email, Randomizer.getText(), name, HttpStatus.SC_FORBIDDEN);
        Assert.assertEquals("User already exists", createUserFalseResponse.extract().path("message"));
        accessToken = createUserResponse.extract().path("accessToken");

    }


}

