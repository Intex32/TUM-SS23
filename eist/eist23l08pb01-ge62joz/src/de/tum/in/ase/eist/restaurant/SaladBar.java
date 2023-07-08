package de.tum.in.ase.eist.restaurant;

public class SaladBar extends Dishes {

    private static final String name = "SaladBar";

    public void makeSalad() {
        System.out.println("Your Salad has been made!");
    }

    public void serveSalad() {
        if(Table.currentTableRepresentation != Table.cleanTableRepresentation) {
            Table.cleanTable();
        }
        if(Table.areCandlesLighted == false) {
            Table.lightCandles();
        }
        if(Table.waterLevel == 0.5 || Table.waterLevel < 0.5) {
            Table.fillWater();
        }
        Table.readDaysMenu();
        Table.askForFurtherRequests();
    }

    public String getName() {
        return name;
    }
}
