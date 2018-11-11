package planet.detail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlanetController {

    @FXML
    private ImageView planetImage;

    @FXML
    private Button selectImageButton;

    @FXML
    private TextField planetName;

    @FXML
    private TextField planetDiameterKM;

    @FXML
    private TextField planetDiameterM;

    @FXML
    private TextField planetMeanSurfaceTempC;

    @FXML
    private TextField planetMeanSurfaceTempF;

    @FXML
    private TextField planetNumberOfMoons;

    @FXML
    private Label fancyPlanetName;

    @FXML
    private String imageName;

    @FXML
    public void initialize() {
        connectNameToLabel();
        connectDiameterTextFields();
        connectTemperatureTextFields();
        this.imageName = "";
    }

    /**
     * Binds fancyPlanetName text to planetName text
     */
    void connectNameToLabel() {
        this.fancyPlanetName.textProperty().bind(this.planetName.textProperty());
    }

    /**
     * Adds a change listener to planetDiameterKM and updates
     * planetDiameterM each time it is updated
     */
    void connectDiameterTextFields() {
        DiameterListener milesListener = new DiameterListener(this.planetDiameterM);
        this.planetDiameterKM.textProperty().addListener(milesListener);
    }

    /**
     * Adds a change listener to planetMeanSurfaceTempC and updates
     * planetMeanSurfaceTempF each time it is updated
     */
    void connectTemperatureTextFields() {
        TemperatureListener celsiusListener = new TemperatureListener(this.planetMeanSurfaceTempF);
        this.planetMeanSurfaceTempC.textProperty().addListener(celsiusListener);
    }

    @FXML
    void showImage(ActionEvent event) {
        this.imageName = ImageLoader.chooseImageFromChooser();
        Image imageToShow = ImageLoader.getImageFromName(this.imageName);
        setImage(imageToShow);
    }

    /**
     * Takes an image and displays it on the scene
     *
     * @param imageToSet Image object to display in the scene
     */
    void setImage(Image imageToSet) {
        if(imageToSet != null)
            this.planetImage.setImage(imageToSet);
    }

    @FXML
    void loadPlanet(ActionEvent event) {
        PlanetIO planetReader = new PlanetIO();
        Planet planetToLoad = planetReader.choosePlanetFromChooser();
        displayPlanetOnView(planetToLoad);
    }

    /**
     * Takes a planet object and updates all text in the scene
     * with the fields from the object.
     *
     * @param planetToDisplay Planet containing all relevant info to display
     */
    void displayPlanetOnView(Planet planetToDisplay) {
        if(!isOverwritingDisplayedInfo())
            return;
        Image imageToLoad = ImageLoader.getImageFromName(planetToDisplay.getImageName());
        setImage(imageToLoad);
        this.planetName.setText(planetToDisplay.getName());
        this.planetDiameterKM.setText(Integer.toString(planetToDisplay.getDiameterKM()));
        this.planetDiameterM.setText(Double.toString(planetToDisplay.getDiameterM()));
        this.planetMeanSurfaceTempC.setText(Double.toString(planetToDisplay.getMeanSurfaceTempC()));
        this.planetMeanSurfaceTempF.setText(Double.toString(planetToDisplay.getMeanSurfaceTempF()));
        this.planetNumberOfMoons.setText(Integer.toString(planetToDisplay.getNumberOfMoons()));
    }

    /**
     * Creates an alert and makes the user confirm whether
     * they want to overwrite the info on the screen or not.
     * @return User's answer to the prompt.
     */
    boolean isOverwritingDisplayedInfo() {
        Alert confirmationBox = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationBox.setTitle("Overwrite Fields");
        confirmationBox.setContentText("Overwrite the current fields?");
        ButtonType buttonChoice = confirmationBox.showAndWait().get();
        if(buttonChoice == ButtonType.OK)
            return true;
        else
            return false;
    }

    /**
     * Once the event is triggered and takes all the input fields
     * and converts them to their appropriate types, uses the
     * converted information to create a planet object in a
     * factory.
     */
    @FXML
    void savePlanet(ActionEvent event) {
        String name = this.planetName.getText();
        int diameter = Integer.parseInt(this.planetDiameterKM.getText());
        double surfaceTemp = Double.parseDouble(this.planetMeanSurfaceTempC.getText());
        int numberOfMoons = Integer.parseInt(this.planetNumberOfMoons.getText());

        PlanetFactory planetCreator = new PlanetFactory();
        Planet createdPlanet = planetCreator.createPlanet(name, diameter, surfaceTemp, numberOfMoons, this.imageName);

        if(createdPlanet != null) {
            PlanetIO planetWriter = new PlanetIO();
            planetWriter.WriteToFile(createdPlanet);
        }
    }
}