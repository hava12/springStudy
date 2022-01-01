package hello.proxy.app.v3;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceV3 {

    private final OrderReposiroryV3 orderReposirotyV2;

    public OrderServiceV3(OrderReposiroryV3 orderReposirotyV2) {
        this.orderReposirotyV2 = orderReposirotyV2;
    }

    public String orderItem(String itemId) {
        return orderReposirotyV2.save(itemId);
    }
}
