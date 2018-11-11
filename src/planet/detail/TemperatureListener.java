package planet.detail;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Takes the change received and converts into
 * a string representing the temperature in Fahrenheit.
 */
public class TemperatureListener implements ChangeListener<String> {

    private TextField temperatureF;

    public TemperatureListener(TextField temperatureF) {
        this.temperatureF = temperatureF;
    }


    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        try {
            Double valueInCelsius = Double.parseDouble(newValue);
            Double valueInFahrenheit = (valueInCelsius * 9.0 / 5.0) + 32.0;
            temperatureF.setText(valueInFahrenheit.toString());
        }catch (Exception e){
            System.err.println("Invalid Temperature Value:" + newValue);
            temperatureF.setText("");
        }
    }
}
