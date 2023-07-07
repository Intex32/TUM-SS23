package de.tum.in.ase.eist.orderReceival;

import java.util.LinkedList;
import java.util.List;

public class SynchronizedRestaurantList<String> {
    private final List<String> restaurantOrderList = new LinkedList<>();
    private final RW lock = new RW();

    // TODO 1.3 update the methods so that the RW lock is used
    public void add(int index, String order) throws InterruptedException {
        lock.startWrite();
        restaurantOrderList.add(index, order);
        lock.endWrite();
    }

    public String remove(int index) throws InterruptedException {
        lock.startWrite();
        String result = restaurantOrderList.remove(index);
        lock.endWrite();
        return result;
    }

    public String get(int index) throws InterruptedException {
        lock.startRead();
        String result = restaurantOrderList.get(index);
        lock.endRead();
        return result;
    }

    public boolean contains(String order) throws InterruptedException {
        lock.startRead();
        boolean result = restaurantOrderList.contains(order);
        lock.endRead();
        return result;
    }

    public int size() {
        return restaurantOrderList.size();
    }
}
