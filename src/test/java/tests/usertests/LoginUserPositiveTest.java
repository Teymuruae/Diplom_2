package tests.usertests;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import testmethods.UserMethods;
import utils.Randomizer;

public class LoginUserPositiveTest {

    private String email;
    private String password;
    private UserMethods userMethods;
    private ValidatableResponse loginUser;

    @Before
    public void setUp() {
        userMethods = new UserMethods();
        email = Randomizer.getEmail();
        password = Randomizer.getText();
        userMethods.createUser(email, password, Randomizer.getText(), HttpStatus.SC_OK);
    }

    @Test
    public void loginUserTest() {
        loginUser = userMethods.loginUser(email, password, HttpStatus.SC_OK);
        Assert.assertTrue(loginUser.extract().path("success"));

    }

    @After
    public void clear() {
        userMethods.deleteUser(loginUser.extract().path("accessToken"), HttpStatus.SC_ACCEPTED);
    }
}
