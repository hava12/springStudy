package hello.proxy.app.v1;

public class OrderReposiroryV1Impl implements OrderReposirotyV1{
    @Override
    public String save(String itemId) {
        if (itemId.equals("ex")) {
            throw new IllegalStateException("예외 발생!");
        }
        sleep(1000);
        return "ok";
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
