package de.tum.in.ase.eist.restaurant;

import org.json.JSONObject;

import java.util.Arrays;

public class ShopManager {

    private final String name = "Aniruddh";

    private final int id = 1;

    private FoodPalace foodPalace;

    public ShopManager() {
        this.foodPalace = new FoodPalace(new SaladBar(), new Curry(), new IceCreamStand());
    }

    public void testSalad() {
        foodPalace.makeSalad();
    }

    public void testCurry() {
        foodPalace.makeCurry();
    }

    public void testIceCream() {
        foodPalace.makeIceCream();
    }

    public static void main(String[] args) {
        ShopManager shopManager = new ShopManager();
        shopManager.testSalad();
        shopManager.testCurry();
        shopManager.testIceCream();

        JSONObject jsonObject = new JSONObject(shopManager);
        jsonObject.put("name", shopManager.name);
        jsonObject.put("id", 1);
        jsonObject.put("foodPalace", Arrays.asList(shopManager.foodPalace.getSaladBar().getName(), shopManager.foodPalace.getCurry().getName(), shopManager.foodPalace.getIceCreamStand().getName()));
        System.out.println(jsonObject);
    }

}
