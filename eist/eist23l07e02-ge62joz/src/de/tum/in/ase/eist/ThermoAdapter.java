package de.tum.in.ase.eist;

public class ThermoAdapter implements ThermoInterface {
    
    private final FahrenheitThermo thermo = new FahrenheitThermo();
    
    @Override
    public double getTempC() {
        return (thermo.getFahrenheitTemperature() - 32) * 5.0 / 9.0;
    }

    public FahrenheitThermo getThermo() {
        return thermo;
    }
}
