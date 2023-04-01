package utils;

public class EndPoints {

    private static String createUser = "/auth/register";
    private static String getUserInfo = "/auth/user";  //используется и для удаления

    private static String doLoginUser = "/auth/login";
    private static String allIngredientsPath = "/ingredients";
    private static String ordersPath = "/orders";

    public static String getDoLoginUser() {return doLoginUser;}
    public static String getCreateUser() {
        return createUser;
    }
    public static String getGetUserInfo() {
        return getUserInfo;
    }
    public static String getAllIngredientsPath() {return allIngredientsPath;}
    public static String getOrdersPath() {return ordersPath;}
}
