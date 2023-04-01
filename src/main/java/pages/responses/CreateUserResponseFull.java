package pages.responses;

public class CreateUserResponseFull {

    private boolean success;
    private CreateUserResponseUser user;
    private String accessToken;
    private String refreshToken;

    public CreateUserResponseFull(boolean success, CreateUserResponseUser user, String accessToken, String refreshToken) {
        this.success = success;
        this.user = user;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public boolean isSuccess() {
        return success;
    }

    public CreateUserResponseUser getUser() {
        return user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
