package de.tum.in.ase.eist.restaurant;

public class Lassi extends Dishes {

    private final String name = "Lassi";

    public void makeLassi() {
        System.out.println("Your Lassi (Indian Drink) has been made!");
    }

    public String getName() {
        return name;
    }
}
