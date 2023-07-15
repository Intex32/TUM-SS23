package de.tum.in.ase.eist.restaurant;

public class SaladBar extends Dishes {

    private final String name = "SaladBar";

    public void makeSalad() {
        System.out.println("Your Salad has been made!");
    }

    public String getName() {
        return name;
    }
}
