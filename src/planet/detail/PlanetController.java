package planet.detail;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;

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
    public void initialize() {
        //this.planetName.textProperty().addListener((observable, oldValue, newValue) -> this.fancyPlanetName.setText(newValue));
    }

    // Image path as text
    private String imgPath;

    @FXML
    void showImage(ActionEvent event) {
        this.imgPath = chooseImage();
        loadImage(this.imgPath);
    }

    String chooseImage() {
        FileChooser imgChooser = new FileChooser();
        String imgPath = Paths.get(".\\images").toAbsolutePath().normalize().toString();
        imgChooser.setInitialDirectory(new File(imgPath));
        File selectedImg = imgChooser.showOpenDialog(null);

        return selectedImg.getPath();
    }

    void loadImage(String pathOfImageToLoad) {
        BufferedImage imgBuffer;
        try {
            imgBuffer = ImageIO.read(new File(pathOfImageToLoad));
            Image img = SwingFXUtils.toFXImage(imgBuffer, null);
            this.planetImage.setImage(img);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Cannot find path: " + pathOfImageToLoad);
        }
    }

    @FXML
    void loadPlanet(ActionEvent event) {

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
            Planet createdPlanet = planetCreator.createPlanet(name, diameter, surfaceTemp, numberOfMoons, this.imgPath);
            System.out.println(createdPlanet.toString());
        }
    }

    boolean hasValidPlanetContent(String planetName, int planetDiameter, double planetSurfaceTemp, int planetNumberOfMoons) {
        return PlanetFactory.hasValidAttributes(planetName, planetDiameter,
                                                    planetSurfaceTemp, planetNumberOfMoons);
    }
}