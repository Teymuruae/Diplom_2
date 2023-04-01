package pages.responses.order.getorder;

import java.util.List;

public class GetOrderByUserResponseOrder {

    private String _id;
    private List<String> ingredients;
    private String status;
    private String name;
    private String createdAt;
    private String updatedAt;
    private Integer number;

    public GetOrderByUserResponseOrder(String _id, List<String> ingredients, String status, String name, String createdAt, String updatedAt, Integer number) {
        this._id = _id;
        this.ingredients = ingredients;
        this.status = status;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.number = number;
    }

    public String get_id() {
        return _id;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Integer getNumber() {
        return number;
    }
}
