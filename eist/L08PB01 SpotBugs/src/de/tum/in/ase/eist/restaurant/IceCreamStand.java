package de.tum.in.ase.eist.restaurant;

public class IceCreamStand extends Dishes {

    private static final String name = "IceCreamStand";

    public void makeIceCream() {
        System.out.println("Your Ice Cream has been made!");
    }

    public void serveIceCream() {
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
