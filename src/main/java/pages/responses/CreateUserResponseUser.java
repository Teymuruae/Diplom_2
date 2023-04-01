package pages.responses;

public class CreateUserResponseUser {
    private String email;
    private String name;

    public CreateUserResponseUser(String email, String name) {
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
