package de.tum.in.ase.eist.restaurant;

public class Curry extends Dishes {

    private final String name = "Curry";

    public void makeCurry() {
        System.out.println("Your Curry has been made!");
    }

    public String getName() {
        return name;
    }

}
