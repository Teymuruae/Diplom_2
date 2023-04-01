package testmethods;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import pages.requests.order.CreateOrderRequest;
import pages.responses.order.getingredients.GetIngredientsResponseMain;
import pages.responses.order.getorder.GetOrderByUserResponseMain;
import utils.EndPoints;
import utils.Spec;

import java.util.List;

public class OrderMethods {

    @Step
    public GetIngredientsResponseMain getIngredientsInfo(int statusCode) {
        Spec.install(statusCode);
        return RestAssured
                .given()
                .get(EndPoints.getAllIngredientsPath())
                .then().extract().as(GetIngredientsResponseMain.class);
    }

    @Step
    public ValidatableResponse createOrderWithAuth(String token, int statusCode, List <String>  ingredients){
        Spec.install(statusCode);
        CreateOrderRequest requestBody = new CreateOrderRequest(ingredients);
       return RestAssured
                .given()
                .header("Authorization", token)
                .body(requestBody)
                .when()
                .post(EndPoints.getOrdersPath())
                .then();
    }

    @Step
    public ValidatableResponse createOrderWithoutAuth(int statusCode, List<String>  ingredients){
        Spec.install(statusCode);
        CreateOrderRequest requestBody = new CreateOrderRequest(ingredients);
        return RestAssured
                .given()
                .body(requestBody)
                .when()
                .post(EndPoints.getOrdersPath()).then();
    }

    @Step
    public GetOrderByUserResponseMain getOrderByAuthUser(String accessToken, int statusCode){
        Spec.install(statusCode);
        return RestAssured
                .given()
                .header("Authorization", accessToken)
                .when()
                .get(EndPoints.getOrdersPath())
                .then().extract().as(GetOrderByUserResponseMain.class);
    }

    @Step
    public ValidatableResponse getOrderByUnauthUser(int statusCode){
        Spec.install(statusCode);
        return RestAssured
                .given()
                .when()
                .get(EndPoints.getOrdersPath())
                .then();
    }


}
