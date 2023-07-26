package de.tum.in.ase.eist.ecommerce;

public class ECommerceFacade {

    private final OrderController orderController;
    private final AdvertisementController adController;
    private final ShippingController shippingController = new ShippingController();

    public ECommerceFacade(OrderController orderController, AdvertisementController adController) {
        this.orderController = orderController;
        this.adController = adController;

    }

    public void processOrder(Order order) {
        orderController.processOrder(order);
    }

    public void processOrder(Order order, String phoneNumber) {
        orderController.processOrder(order, phoneNumber);
    }

    public Order retrieveLatestOrder(int id) {
        return orderController.retrieveLatestOrder(id);
    }

    public void playAdvertisement(int ageRestriction) {
        adController.playAdvertisement(ageRestriction);
    }

    public void shipOrder(Order order, String address) {
        var shipping = shippingController.createShipping(address);
        order.setShipping(shipping);
        shippingController.shipOrder(order);
    }

}
