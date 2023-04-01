package tests.usertests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import testmethods.UserMethods;
import utils.Randomizer;

@RunWith(Parameterized.class)
public class CreateUserWithoutOneFieldTest {

    private String email;
    private String password;
    private String name;
    private int statusCode;
    private String expectedMessage;

    public CreateUserWithoutOneFieldTest(String email, String password, String name, int statusCode, String message) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.statusCode = statusCode;
        this.expectedMessage = message;
    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        return new Object[][]{
                {"", Randomizer.getText(), Randomizer.getText(), HttpStatus
                        .SC_FORBIDDEN,"Email, password and name are required fields"},
                {Randomizer.getEmail(), "", Randomizer.getText(), HttpStatus
                        .SC_FORBIDDEN,"Email, password and name are required fields"},
                {Randomizer.getEmail(), Randomizer.getText(), "", HttpStatus
                        .SC_FORBIDDEN,"Email, password and name are required fields"},

        };
    }

    private UserMethods userMethods;
    @Before
    public void setUp(){
        userMethods = new UserMethods();
    }
    @Test
    @DisplayName("trying to create user without one field")
    public void createUserWithoutOneFieldTest(){
      ValidatableResponse createUserResponse = userMethods.createUser(email,password,name,statusCode);
        Assert.assertEquals(expectedMessage, createUserResponse.extract().path("message"));
    }
}
