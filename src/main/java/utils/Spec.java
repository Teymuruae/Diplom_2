package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Spec {

    private static RequestSpecification request (){
        return new RequestSpecBuilder()
                .setBaseUri("https://stellarburgers.nomoreparties.site/api")
                .setContentType(ContentType.JSON)
                .build();
    }

    private static ResponseSpecification response(int statusCode){
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }

    public static void install(int expectedStatusCode){
        RestAssured.requestSpecification = request();
        RestAssured.responseSpecification = response(expectedStatusCode);
    }
}
