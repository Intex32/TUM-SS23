package de.tum.in.ase.eist;

public class Main {

    public static void main(String[] args) {
        detectDeadlock(new SwimmingPool());
    }

    public static void detectDeadlock(SwimmingPool swimmingPool) {
        new Thread(() -> {
            swimmingPool.handleEntryRequest(new Swimmer(), SwimmingPoolActionOrder.CHANGING_ROOM_BEFORE_LOCKER);
        }).start();

        swimmingPool.handleEntryRequest(new Swimmer(), SwimmingPoolActionOrder.LOCKER_BEFORE_CHANGING_ROOM);
    }
}
