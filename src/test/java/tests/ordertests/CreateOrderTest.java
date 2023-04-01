package tests.ordertests;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.responses.order.getingredients.GetIngredientsResponseMain;
import testmethods.OrderMethods;
import testmethods.UserMethods;
import utils.Randomizer;

import java.util.ArrayList;
import java.util.List;

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
        userMethods = new UserMethods();
        createUserResponse = userMethods.createUser(HttpStatus.SC_OK);
    }

    @Parameterized.Parameters
    public static Object[][] setData() {
        orderMethods = new OrderMethods();
        ingredientsResponse = orderMethods.getIngredientsInfo(HttpStatus.SC_OK);

        return new Object[][]{
                {List.of(Randomizer.getText()), HttpStatus.SC_INTERNAL_SERVER_ERROR, false},
                {new ArrayList<>(), HttpStatus.SC_BAD_REQUEST, false},
                {List.of(ingredientsResponse.getData().get(0).get_id()), HttpStatus.SC_OK, true},
                {List.of(ingredientsResponse.getData().get(0).get_id(),
                        ingredientsResponse.getData().get(1).get_id()), HttpStatus.SC_OK, true}
        };
    }

    @Test
    public void createOrderAuthUserTest() {
     token = createUserResponse.extract().path("accessToken");
        ValidatableResponse createOrder = orderMethods.createOrderWithAuth(token, expectedStatusCode, ingredients);

        if (expectedStatusCode != 500) {
            Assert.assertEquals(expectedSuccessResult, createOrder.extract().path("success"));
        }
        if (expectedStatusCode == 200) {
            Assert.assertNotNull(createOrder.extract().path("order._id"));
        }

    }

    @Test
    public void createOrderUnauthUserTest2() {
        ValidatableResponse createOrderUnAuthUser = orderMethods.createOrderWithoutAuth(expectedStatusCode, ingredients);
        if (expectedStatusCode != 500) {
            Assert.assertEquals(expectedSuccessResult, createOrderUnAuthUser.extract().path("success"));
        }

    }

    @AfterClass
    public static void clear(){
        userMethods.deleteUser(token, HttpStatus.SC_ACCEPTED)  ;
    }
}
