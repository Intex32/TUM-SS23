package de.tum.in.ase.eist.ecommerce;

public class ECommerceFacade {

    private final OrderController orderController = new OrderController();
    private final AdvertisementController advertisementController = new AdvertisementController();
    private final ShippingController shippingController = new ShippingController();

    public void shipOrder(Order order, String address) {
        order.setShipping(shippingController.createShipping(address));
        shippingController.shipOrder(order);
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
        advertisementController.playAdvertisement(ageRestriction);
    }

}
