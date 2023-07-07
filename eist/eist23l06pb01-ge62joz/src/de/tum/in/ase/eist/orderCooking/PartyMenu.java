package de.tum.in.ase.eist.orderCooking;

import de.tum.in.ase.eist.restaurant.*;

import java.util.ArrayList;
import java.util.List;

public class PartyMenu {

    private int id;

    private Bread bread;

    private Curry curry;

    private IceCreamStand iceCream;

    private Lassi lassi;

    private Rice rice;

    private SaladBar salad;

    private Samosas samosas;

    private Soup soup;

    private List<Dishes> dishes = new ArrayList<>();

    public PartyMenu(int id) {
        this.id = id;
        this.bread = new Bread();
        this.curry = new Curry();
        this.iceCream = new IceCreamStand();
        this.lassi = new Lassi();
        this.rice = new Rice();
        this.salad = new SaladBar();
        this.samosas = new Samosas();
        this.soup = new Soup();
    }

    public void addDish(int dishNumber) {
        switch (dishNumber) {
            case 1 -> {
                dishes.add(bread);
            }
            case 2 -> {
                dishes.add(curry);
            }
            case 3 -> {
                dishes.add(iceCream);
            }
            case 4 -> {
                dishes.add(lassi);
            }
            case 5 -> {
                dishes.add(rice);
            }
            case 6 -> {
                dishes.add(salad);
            }
            case 7 -> {
                dishes.add(samosas);
            }
            case 8 -> {
                dishes.add(soup);
            }
            default -> {

            }
        }
    }

    public List<Dishes> getDishes() {
        return dishes;
    }

    public int getId() {
        return id;
    }
}
