package hello.proxy.app.v2;


public class OrderServiceV2 {

    private final OrderReposiroryV2 orderReposirotyV2;

    public OrderServiceV2(OrderReposiroryV2 orderReposirotyV2) {
        this.orderReposirotyV2 = orderReposirotyV2;
    }

    public String orderItem(String itemId) {
        return orderReposirotyV2.save(itemId);
    }
}
