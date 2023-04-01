package testmethods;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import pages.requests.user.CreateUserRequest;
import pages.requests.user.LoginUserRequest;
import pages.requests.user.UpdateUserRequest;
import utils.EndPoints;
import utils.Randomizer;
import utils.Spec;

public class UserMethods {

    @Step
    public ValidatableResponse createUser(String email, String password, String name, int statusCode) {
        CreateUserRequest userRequest = new CreateUserRequest(email, password, name);
        Spec.install(statusCode);
        return RestAssured
                .given()
                .body(userRequest)
                .when()
                .post(EndPoints.getCreateUser() ).then();

    }

    @Step
    public ValidatableResponse createUser(int statusCode){
        Spec.install(statusCode);
        return RestAssured
                .given()
                .body(Randomizer.createRandomUser())
                .when()
                .post(EndPoints.getCreateUser()).then();
    }

    @Step
    public ValidatableResponse deleteUser(String accessToken, int statusCode){
        Spec.install(statusCode);
        return RestAssured
                .given()
                .header("Authorization", accessToken)
                .when()
                .delete(EndPoints.getGetUserInfo()).then();
    }

    @Step
    public ValidatableResponse loginUser(String email, String password, int statusCode){
        Spec.install(statusCode);
        LoginUserRequest loginUserBody = new LoginUserRequest(email,password);
        return RestAssured
                .given()
                .body(loginUserBody)
                .when()
                .post(EndPoints.getDoLoginUser())
                .then();
    }

    @Step
    public ValidatableResponse updateUser(String email, String name, int statusCode, String token){
        Spec.install(statusCode);
        UpdateUserRequest updateBody = new UpdateUserRequest(email, name);
        return RestAssured
                .given()
                .header("Authorization", token)
                .body(updateBody)
                .when()
                .patch(EndPoints.getGetUserInfo()).then();
    }
}
