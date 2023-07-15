package de.tum.in.ase.eist.orderCooking;

public class PartyMenuCooking {
    public static final int TOTAL_AMOUNT_PARTYMENUS = 500;

    public static void main(String[] args) {
        // create buffers
        Buffer[] buffers = new Buffer[9];
        buffers[0] = new Buffer(TOTAL_AMOUNT_PARTYMENUS);
        buffers[buffers.length - 1] = new Buffer(TOTAL_AMOUNT_PARTYMENUS);
        for (int i = 1; i < buffers.length - 1; i++)
            buffers[i] = new Buffer(50);

        // create dishes
        for (int i = 0; i < TOTAL_AMOUNT_PARTYMENUS; i++) {
            try {
                buffers[0].produce(new PartyMenu(i));
            } catch (InterruptedException e) {
                // Theoretically cannot happen since we should never wait here
                e.printStackTrace();
            }
        }

        // Create tasks and threads
        Runnable[] dishes = new Runnable[8];
        for (int i = 0; i < dishes.length; i++) {
            dishes[i] = new ChefCook(buffers[i], buffers[(i + 1) % 8], i + 1,
                    buffers[buffers.length - 1]);
        }

        // TODO 3.5 Create the Threads for the 80 Cooks


        for (int i = 0; i < TOTAL_AMOUNT_PARTYMENUS; i++) {
            try {
                finalizeDishes(buffers[buffers.length - 1].consume());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Todo 3.6 This can be uncommented after all Steps before are implemented
		/*
		for (Thread t : Cooks)
			t.interrupt();


		 */
        System.out.println("All Dishes are made and loaded in the Truck! :)");
    }

    public static void finalizeDishes(PartyMenu partyMenu) {
        System.out.println("The Party Menu number: " + partyMenu.getId() + " has been loaded in the Truck.");
    }
}