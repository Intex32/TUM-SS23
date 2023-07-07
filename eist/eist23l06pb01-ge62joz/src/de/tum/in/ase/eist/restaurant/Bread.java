package de.tum.in.ase.eist.restaurant;

public class Bread extends Dishes {

    private final String name = "Bread";

    public void makeBread() {
        System.out.println("Your Bread has been made!");
    }

    public String getName() {
        return name;
    }
}
