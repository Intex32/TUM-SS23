package de.tum.in.ase.eist.restaurant;

public class Rice extends Dishes {

    private final String name = "Rice";

    public void makeRice() {
        System.out.println("Your Rice has been made!");
    }

    public String getName() {
        return name;
    }
}
