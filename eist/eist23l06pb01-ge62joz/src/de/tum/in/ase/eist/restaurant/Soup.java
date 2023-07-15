package de.tum.in.ase.eist.restaurant;

public class Soup extends Dishes {

    private final String name = "Soup";

    public void makeSoup() {
        System.out.println("Your Soup has been made!");
    }

    public String getName() {
        return name;
    }
}
