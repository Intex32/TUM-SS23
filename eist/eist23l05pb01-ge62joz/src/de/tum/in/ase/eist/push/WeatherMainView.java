package de.tum.in.ase.eist.push;

public final class WeatherMainView implements Observer {

    private final WeatherController controller;
    private final WeatherModel model;

    private int temp;

    public WeatherMainView(WeatherController controller, WeatherModel model) {
        this.controller = controller;
        this.model = model;
        model.addObserver(this);
    }

    public void display() {
        System.out.println("temp in main view " + temp);
    }

    public void update(final int temperature) {
        this.temp = temperature;
    }
}
