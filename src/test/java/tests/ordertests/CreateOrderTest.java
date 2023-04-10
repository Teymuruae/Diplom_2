package tests.ordertests;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.responses.order.getingredients.GetIngredientsResponseMain;
import testmethods.OrderMethods;
import testmethods.UserMethods;

import java.util.ArrayList;
import java.util.List;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

@RunWith(Parameterized.class)
public class CreateOrderTest {
    private static OrderMethods orderMethods;
    private static UserMethods userMethods;
    private static ValidatableResponse createUserResponse;
    private static GetIngredientsResponseMain ingredientsResponse;
    private int expectedStatusCode;
    private List<String> ingredients;
    private boolean expectedSuccessResult;
    private static String token;


    public CreateOrderTest(List<String> ingredients, int expectedStatusCode, boolean expectedSuccessResult) {
        this.ingredients = ingredients;
        this.expectedStatusCode = expectedStatusCode;
        this.expectedSuccessResult = expectedSuccessResult;

    }



    @BeforeClass
    public static void setUp() {
        RestAssured.filters(
                new RequestLoggingFilter(), new ResponseLoggingFilter(),
                new AllureRestAssured()
        );
        userMethods = new UserMethods();
        createUserResponse = userMethods.createUser(HttpStatus.SC_OK);
    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        orderMethods = new OrderMethods();
        ingredientsResponse = orderMethods.getIngredientsInfo(HttpStatus.SC_OK);

        return new Object[][]{
                {new ArrayList<>(), HttpStatus.SC_BAD_REQUEST, false},
                {List.of(ingredientsResponse.getData().get(0).get_id()), HttpStatus.SC_OK, true},
                {List.of(ingredientsResponse.getData().get(0).get_id(),
                        ingredientsResponse.getData().get(1).get_id()), HttpStatus.SC_OK, true}
        };
    }

    @Test
    public void createOrderAuthUserTest() {
        token = createUserResponse.extract().path("accessToken");
        ValidatableResponse createOrder = orderMethods.createOrderWithAuth(token, expectedStatusCode, ingredients)
                .body("success", Matchers.equalTo(expectedSuccessResult));

    }

    @Test
    public void createOrderUnauthUserTest2() {
        ValidatableResponse createOrderUnAuthUser = orderMethods.createOrderWithoutAuth(expectedStatusCode, ingredients)
                .body("success", Matchers.equalTo(expectedSuccessResult));
    }



    @AfterClass
    public static void clear() {
        userMethods.deleteUser(token, HttpStatus.SC_ACCEPTED);
    }
}
