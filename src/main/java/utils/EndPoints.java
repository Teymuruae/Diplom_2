package utils;

public class EndPoints {

    private static String createUser = "/auth/register";
    private static String getUserInfo = "/auth/user";  //используется и для удаления

    private static String doLoginUser = "/auth/login";



    public static String getDoLoginUser() {return doLoginUser;}

    public static String getCreateUser() {
        return createUser;
    }

    public static String getGetUserInfo() {
        return getUserInfo;
    }


}
