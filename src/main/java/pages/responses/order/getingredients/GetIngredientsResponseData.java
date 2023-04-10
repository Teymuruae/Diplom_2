package pages.responses.order.getingredients;

public class GetIngredientsResponseData {
    private String _id;
    private String name;
    private String type;
    private Integer proteins;
    private Integer fat;
    private Integer carbohydrates;
    private Integer calories;
    private Integer price;
    private String image;
    private String image_mobile;
    private String image_large;
    private Integer __v;



    public GetIngredientsResponseData(String _id, String name, String type, Integer proteins, Integer fat,
                                      Integer carbohydrates, Integer calories, Integer price, String image,
                                      String image_mobile, String image_large, Integer __v) {
        this._id = _id;
        this.name = name;
        this.type = type;
        this.proteins = proteins;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
        this.price = price;
        this.image = image;
        this.image_mobile = image_mobile;
        this.image_large = image_large;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Integer getProteins() {
        return proteins;
    }

    public Integer getFat() {
        return fat;
    }

    public Integer getCarbohydrates() {
        return carbohydrates;
    }

    public Integer getCalories() {
        return calories;
    }

    public Integer getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getImage_mobile() {
        return image_mobile;
    }

    public String getImage_large() {
        return image_large;
    }

    public Integer get__v() {
        return __v;
    }
}
