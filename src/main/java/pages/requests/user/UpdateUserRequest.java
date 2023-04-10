package pages.requests.user;

public class UpdateUserRequest {
    private String email;
    private String name;

    public UpdateUserRequest(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
