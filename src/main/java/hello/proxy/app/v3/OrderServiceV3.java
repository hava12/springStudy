package hello.proxy.app.v3;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceV3 {

    private final OrderReposiroryV3 orderReposiroty;

    public OrderServiceV3(OrderReposiroryV3 orderReposirotyV2) {
        this.orderReposiroty = orderReposirotyV2;
    }

    public String orderItem(String itemId) {
        return orderReposiroty.save(itemId);
    }
}
