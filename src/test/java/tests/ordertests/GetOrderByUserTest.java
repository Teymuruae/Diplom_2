package tests.ordertests;

import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Assert;
import org.junit.*;
import pages.responses.order.getingredients.GetIngredientsResponseMain;
import pages.responses.order.getorder.GetOrderByUserResponseMain;
import testmethods.OrderMethods;
import testmethods.UserMethods;
import java.util.List;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class GetOrderByUserTest {

    private UserMethods userMethods;
    private OrderMethods orderMethods;
    private String accessToken;
    private ValidatableResponse createOrder;


    @BeforeClass
    public static void globalSetUp() {
        RestAssured.filters(
                new RequestLoggingFilter(), new ResponseLoggingFilter(),
                new AllureRestAssured()
        );
    }

    @Before
    public void setUp() {
        userMethods = new UserMethods();
        orderMethods = new OrderMethods();
        ValidatableResponse createUser = userMethods.createUser(HttpStatus.SC_OK);
        accessToken = createUser.extract().path("accessToken");
        GetIngredientsResponseMain ingredientsResponse = orderMethods.getIngredientsInfo(HttpStatus.SC_OK);
        createOrder = orderMethods.createOrderWithAuth(accessToken, HttpStatus.SC_OK,
                List.of(ingredientsResponse.getData().get(0).get_id(), ingredientsResponse.getData().get(1).get_id()));
    }

    @Test
    public void getOrderByAuthUserTest() {
        GetOrderByUserResponseMain getOrder = orderMethods.getOrderByAuthUser(accessToken, HttpStatus.SC_OK);

        for (int i = 0; i < getOrder.getOrders().get(0).getIngredients().size(); i++) {
            Assert.assertEquals(createOrder.extract().path(String.format("order.ingredients[%d]._id", i)), getOrder.getOrders().get(0).getIngredients().get(i));
        }

    }

    @Test
    public void getOrderByUnAuthUserTest() {
        ValidatableResponse getOrderResponse = orderMethods.getOrderByUnauthUser(HttpStatus.SC_UNAUTHORIZED);
        Assert.assertEquals("You should be authorised", getOrderResponse.extract().path("message"));
    }

    @After
    public void clear() {
        userMethods.deleteUser(accessToken, HttpStatus.SC_ACCEPTED);
    }
}

