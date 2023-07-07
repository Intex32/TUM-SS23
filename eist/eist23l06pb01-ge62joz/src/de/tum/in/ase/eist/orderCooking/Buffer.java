package de.tum.in.ase.eist.orderCooking;

import java.util.concurrent.Semaphore;

public class Buffer {
    private PartyMenu[] data;
    private Semaphore free;
    private Semaphore occupied;
    int first = 0, last = 0;

    public Buffer(int maxSize) {
        if (maxSize < 0)
            throw new IllegalArgumentException(
                    "Buffer must have positive size!");
        // TODO 3.1 Initialize the variables
    }

    public PartyMenu consume() throws InterruptedException {
        // TODO 3.2 Implement the consume method as described
        return null;
    }

    public void produce(PartyMenu partyMenu) throws InterruptedException {
        // TODO 3.3 Implement the produce method as described
    }

    public PartyMenu[] getData() {
        return data;
    }
}
