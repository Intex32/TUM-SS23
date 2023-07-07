package de.tum.in.ase.eist.restaurant;

public class Samosas extends Dishes {

    private final String name = "Samosas";

    public void makeSamosas() {
        System.out.println("Your Samosas (Indian Snack) has been made!");
    }

    public String getName() {
        return name;
    }
}
