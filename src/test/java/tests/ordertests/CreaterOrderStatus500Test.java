package tests.ordertests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.responses.order.getingredients.GetIngredientsResponseMain;
import testmethods.OrderMethods;
import testmethods.UserMethods;
import utils.Randomizer;

import java.util.List;

@RunWith(Parameterized.class)
public class CreaterOrderStatus500Test {

    private static ValidatableResponse createUserResponse;
    private static UserMethods userMethods;
    private GetIngredientsResponseMain ingredientsResponse;

    private OrderMethods orderMethods;
    private static String token;

    public CreaterOrderStatus500Test(String token) {
        this.token = token;
    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        userMethods = new UserMethods();
        createUserResponse = userMethods.createUser(HttpStatus.SC_OK);
        return new Object[][]{
                {""},
                {createUserResponse.extract().path("accessToken")}
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
    public void createOrderStatus500Test() {
        orderMethods = new OrderMethods();
        ingredientsResponse = orderMethods.getIngredientsInfo(HttpStatus.SC_OK);
        orderMethods.createOrderWithAuth(token, HttpStatus.SC_INTERNAL_SERVER_ERROR,
                List.of(Randomizer.getText()));
    }

    @AfterClass
    public static void clear() {
        userMethods.deleteUser(token, HttpStatus.SC_ACCEPTED);
    }
}
