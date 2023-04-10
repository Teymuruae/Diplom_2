package pages.responses.order.getingredients;

import java.util.List;

public class GetIngredientsResponseMain {
    public boolean success;
    public List<GetIngredientsResponseData> data;

    public GetIngredientsResponseMain(boolean success, List<GetIngredientsResponseData> data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<GetIngredientsResponseData> getData() {
        return data;
    }


}
