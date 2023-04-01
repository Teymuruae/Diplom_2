package tests.usertests;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
        return new Object[][]{
                {email, ""},
                {"", password}

        };
    }

    @Parameterized.AfterParam
    public static void setUp() {
        userMethods = new UserMethods();
        email = Randomizer.getEmail();
        password = Randomizer.getText();
        ValidatableResponse createUser = userMethods.createUser(email, password, Randomizer.getText(), HttpStatus.SC_OK);
        accessToken = createUser.extract().path("accessToken");
    }

    @Test
    public void loginUserNegativeTest() {
        ValidatableResponse response = userMethods.loginUser(email, password, HttpStatus.SC_UNAUTHORIZED);
        Assert.assertEquals("email or password are incorrect", response.extract().path("message"));

    }

    @After
    public void clear() {
        userMethods.deleteUser(accessToken, HttpStatus.SC_ACCEPTED);
    }

}
