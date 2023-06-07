package de.tum.in.ase.eist.push;

public class MainClass {
    public static void main(String[] args) {
        // Setup
        WeatherModel model = new WeatherModel();
        WeatherController controller = new WeatherController(model);
        WeatherMainView mainView = new WeatherMainView(controller, model);
        WeatherWidgetView widgetView = new WeatherWidgetView(controller, model);

        // Views are displaying the temperature
        System.out.println("\nThe temperature before updates:");
        mainView.display();
        widgetView.display();

        // Controller receives information about new temperature
        controller.handleNewTemperatureForecast(35);

        // Views are displaying the new temperature
        System.out.println("\nThe temperature after updates:");
        mainView.display();
        widgetView.display();
    }
}
