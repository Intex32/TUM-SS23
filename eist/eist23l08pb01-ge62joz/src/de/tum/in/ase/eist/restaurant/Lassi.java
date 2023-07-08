package de.tum.in.ase.eist.restaurant;

public class Lassi extends Dishes {

    private static final String name = "Lassi";

    public void makeLassi() {
        System.out.println("Your Lassi (Indian Drink) has been made!");
    }

    public void serveLassi() {
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
