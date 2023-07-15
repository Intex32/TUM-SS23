package de.tum.in.ase.eist.orderCooking;

public class ChefCook implements Runnable {
    private Buffer in;
    private Buffer out;
    private int dishNumber;
    private Buffer finished;

    public ChefCook(Buffer in, Buffer out, int dishNumber, Buffer finished) {
        this.in = in;
        this.out = out;
        this.dishNumber = dishNumber;
        this.finished = finished;
    }

    @Override
    public void run() {
        // TODO 3.4 Implement the run method which consumes a Order and processes it.

    }

    private boolean cookDish(PartyMenu partyMenu, int dishNumber) {
        if (partyMenu.getDishes().size() != 8) {
            // cook
            partyMenu.addDish(dishNumber);
            return false;
        } else {
            // all Dishes cooked
            return true;
        }
    }
}
