package de.tum.in.ase.eist.push;

public class WeatherWidgetView implements Observer {

    private final WeatherController controller;
    private final WeatherModel model;

    private int temp;

    public WeatherWidgetView(WeatherController controller, WeatherModel model) {
        this.controller = controller;
        this.model = model;
        // TODO: subscribe this view to the updates from model
        model.addObserver(this);
    }

    public void display() {
        System.out.println("temp " + temp);
    }

    public void update(final int temperature) {
        this.temp = temperature;
    }
}
