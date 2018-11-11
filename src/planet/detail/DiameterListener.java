package planet.detail;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Takes the change received and converts into
 * a string representing the diameter in miles.
 */
public class DiameterListener implements ChangeListener<String> {

    private TextField diameterInMiles;

    public DiameterListener(TextField diameterInMiles) {
        this.diameterInMiles = diameterInMiles;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        try{
            Double valueInKilometers = Double.parseDouble(newValue);
            Double valueInMiles = valueInKilometers * 0.62137119224;
            this.diameterInMiles.setText(valueInMiles.toString());
        }catch (Exception e){
            System.err.println("Invalid Diameter Value:" + newValue);
            diameterInMiles.setText("");
        }
    }
}
