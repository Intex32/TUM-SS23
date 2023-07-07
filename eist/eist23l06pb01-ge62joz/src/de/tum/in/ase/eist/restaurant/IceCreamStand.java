package de.tum.in.ase.eist.restaurant;

public class IceCreamStand extends Dishes {

    private final String name = "IceCreamStand";

    public void makeIceCream() {
        System.out.println("Your Ice Cream has been made!");
    }

    public String getName() {
        return name;
    }

}
