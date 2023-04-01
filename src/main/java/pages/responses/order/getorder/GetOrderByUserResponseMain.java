package pages.responses.order.getorder;

import java.util.List;

public class GetOrderByUserResponseMain {

    private boolean success;
    private List<GetOrderByUserResponseOrder> orders;
    private Integer total;
    private Integer totalToday;

    public GetOrderByUserResponseMain(boolean success, List<GetOrderByUserResponseOrder> orders, Integer total, Integer totalToday) {
        this.success = success;
        this.orders = orders;
        this.total = total;
        this.totalToday = totalToday;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<GetOrderByUserResponseOrder> getOrders() {
        return orders;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getTotalToday() {
        return totalToday;
    }
}
