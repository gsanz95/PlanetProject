package planet.detail;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private String imagePath;

    @FXML
    public void initialize() {
        this.fancyPlanetName.textProperty().bind(this.planetName.textProperty());
    }

    @FXML
    void showImage(ActionEvent event) {
        this.imagePath = ImageLoader.chooseImageFromChooser();
        Image imageToShow = ImageLoader.getImageFromPath(this.imagePath);
        setImage(imageToShow);
    }

    void setImage(Image imageToSet) {
        if(imageToSet != null)
            this.planetImage.setImage(imageToSet);
    }

    @FXML
    void loadPlanet(ActionEvent event) {
        PlanetIO planetReader = new PlanetIO();
        Planet planetToLoad = planetReader.choosePlanetFromChooser();
        //System.err.println(planetToLoad.toString());
        displayPlanetOnView(planetToLoad);

    }

    void displayPlanetOnView(Planet planetToDisplay) {
        Image imageToLoad = ImageLoader.getImageFromPath(planetToDisplay.getImagePath());
        setImage(imageToLoad);
        this.planetName.setText(planetToDisplay.getName());
        this.planetDiameterKM.setText(Integer.toString(planetToDisplay.getDiameterKM()));
        this.planetDiameterM.setText(Double.toString(planetToDisplay.getDiameterM()));
        this.planetMeanSurfaceTempC.setText(Double.toString(planetToDisplay.getMeanSurfaceTempC()));
        this.planetMeanSurfaceTempF.setText(Double.toString(planetToDisplay.getMeanSurfaceTempF()));
        this.planetNumberOfMoons.setText(Integer.toString(planetToDisplay.getNumberOfMoons()));
    }

    @FXML
    void savePlanet(ActionEvent event) {
        String name = this.planetName.getText();
        int diameter = Integer.parseInt(this.planetDiameterKM.getText());
        double surfaceTemp = Double.parseDouble(this.planetMeanSurfaceTempC.getText());
        int numberOfMoons = Integer.parseInt(this.planetNumberOfMoons.getText());

        if(hasValidPlanetContent(name, diameter, surfaceTemp, numberOfMoons))
        {
            PlanetFactory planetCreator = new PlanetFactory();
            Planet createdPlanet = planetCreator.createPlanet(name, diameter, surfaceTemp, numberOfMoons, this.imagePath);

            PlanetIO planetWriter = new PlanetIO();
            planetWriter.WriteToFile(createdPlanet);

        }
    }

    boolean hasValidPlanetContent(String planetName, int planetDiameter, double planetSurfaceTemp, int planetNumberOfMoons) {
        return PlanetFactory.hasValidAttributes(planetName, planetDiameter,
                                                    planetSurfaceTemp, planetNumberOfMoons);
    }
}