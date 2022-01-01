package hello.proxy.app.v1;

public class OrderServiceV1Impl implements OrderServiceV1{

    private final OrderReposirotyV1 orderReposirotyV1;

    public OrderServiceV1Impl(OrderReposirotyV1 orderReposirotyV1) {
        this.orderReposirotyV1 = orderReposirotyV1;
    }

    @Override
    public String orderItem(String itemId) {
        return orderReposirotyV1.save(itemId);
    }
}
