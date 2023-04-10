package tests.usertests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import testmethods.UserMethods;
import utils.Randomizer;

@RunWith(Parameterized.class)
public class LoginUserNegativeTest {
    private static String email;
    private static String password;
    private static UserMethods userMethods;
    private static String accessToken;

    public LoginUserNegativeTest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        userMethods = new UserMethods();
        email = Randomizer.getEmail();
        password = Randomizer.getText();
        ValidatableResponse createUser = userMethods.createUser(email, password, Randomizer.getText(), HttpStatus.SC_OK);
        accessToken = createUser.extract().path("accessToken");
        return new Object[][]{
                {email, ""},
                {"", password}

        };
    }

    @BeforeClass
    public static void globalSetUp() {
        RestAssured.filters(
                new RequestLoggingFilter(), new ResponseLoggingFilter(),
                new AllureRestAssured()
        );
    }

    @Test
    public void loginUserNegativeTest() {
        ValidatableResponse response = userMethods.loginUser(email, password, HttpStatus.SC_UNAUTHORIZED);
        Assert.assertEquals("email or password are incorrect", response.extract().path("message"));

    }

    @AfterClass
    public static void clear() {
        userMethods.deleteUser(accessToken, HttpStatus.SC_ACCEPTED);
    }

}
