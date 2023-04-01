package tests.usertests;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import testmethods.UserMethods;
import utils.Randomizer;

@RunWith(Parameterized.class)
public class UpdateUserTest {

    private static String email;
    private static String emailUser2;
    private static String name;
    private int statusCode;
    private static boolean expectedResponseSuccess;
    private static String accessToken;
    private static UserMethods userMethods;
    private ValidatableResponse updateUserResponse;
    private static String accessTokenForDeleteMethod;


    public UpdateUserTest(String email, String name, int statusCode, boolean expectedResponseSuccess, String accessToken) {
        this.email = email;
        this.name = name;
        this.statusCode = statusCode;
        this.expectedResponseSuccess = expectedResponseSuccess;
        this.accessToken = accessToken;
    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        userMethods = new UserMethods();
        ValidatableResponse createUser = userMethods.createUser(HttpStatus.SC_OK);
        ValidatableResponse createUser2 = userMethods.createUser(HttpStatus.SC_OK);
        emailUser2 = createUser2.extract().path("user.email");
        accessToken = createUser.extract().path("accessToken");
        accessTokenForDeleteMethod = createUser.extract().path("accessToken");
        email = createUser.extract().path("user.email");
        name = createUser.extract().path("user.name");
        expectedResponseSuccess = createUser.extract().path("success");
        return new Object[][]
                {
                        {Randomizer.getEmail(), Randomizer.getText(), HttpStatus.SC_OK, true, accessToken},
                        {null, "TestName", HttpStatus.SC_OK, true, accessToken},
                        {Randomizer.getEmail(), null, HttpStatus.SC_OK, true, accessToken},
                        {Randomizer.getEmail(), Randomizer.getText(), HttpStatus.SC_UNAUTHORIZED, false, "accessToken"},
                        {null, "TestName", HttpStatus.SC_UNAUTHORIZED, false, "accessToken"},
                        {Randomizer.getEmail(), null, HttpStatus.SC_UNAUTHORIZED, false, "accessToken"},
                        {emailUser2, null, HttpStatus.SC_FORBIDDEN, false, accessToken}

                };
    }

    @Test
    public void updateAuthorizedUserTest() {
        System.out.println(accessToken);
        System.out.println(accessTokenForDeleteMethod);
        System.out.println(email);
        System.out.println(name);
        updateUserResponse = userMethods.updateUser(email, name, statusCode, accessToken);
        Assert.assertEquals(expectedResponseSuccess, updateUserResponse.extract().path("success"));

    }

    @AfterClass
    public static void clear() {
        userMethods.deleteUser(accessTokenForDeleteMethod, HttpStatus.SC_ACCEPTED);
    }
}

