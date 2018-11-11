package planet.detail;

import javafx.embed.swing.SwingFXUtils;
import org.junit.Assert;
import org.junit.Test;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;


public class PlanetTest {

    @Test
    public void invalidNameTest() {
        PlanetFactory factory = new PlanetFactory();
        String name = "@Pluto";
        int diameter = 49244;
        double temperature= -201.0;
        int nrOfMoons = 13;
        String imgPath = "";
        Assert.assertNull(factory.createPlanet(name,diameter,temperature, nrOfMoons, imgPath));
    }

    @Test
    public void invalidDiameterTest() {
        PlanetFactory factory = new PlanetFactory();
        String name = "Pluto";
        int diameter = -2;
        double temperature= -201.0;
        int nrOfMoons = 13;
        String imgPath = "";
        Assert.assertNull(factory.createPlanet(name,diameter,temperature, nrOfMoons, imgPath));
    }

    @Test
    public void invalidTemperatureTest() {
        PlanetFactory factory = new PlanetFactory();
        String name = "Neptune";
        int diameter = 49244;
        double temperature= -900.0;
        int nrOfMoons = 13;
        String imgPath = "";
        Assert.assertNull(factory.createPlanet(name,diameter,temperature, nrOfMoons, imgPath));
    }

    @Test
    public void invalidNumberOfMoonsTest() {
        PlanetFactory factory = new PlanetFactory();
        String name = "Neptune";
        int diameter = 49244;
        double temperature= -201.0;
        int nrOfMoons = 10000;
        String imgPath = "";
        Assert.assertNull(factory.createPlanet(name,diameter,temperature, nrOfMoons, imgPath));
    }

    @Test
    public void defaultImagePathTest() {
        PlanetFactory factory = new PlanetFactory();
        String name = "Neptune";
        int diameter = 49244;
        double temperature= -201.0;
        int nrOfMoons = 13;
        String imgPath = "";
        String defaultName = "no_image.png";
        Planet planetToCheck = factory.createPlanet(name,diameter,temperature, nrOfMoons, imgPath);
        Assert.assertEquals(defaultName, planetToCheck.getImageName());
    }

    @Test
    public void temperatureConversionTest() {
        PlanetFactory factory = new PlanetFactory();
        String name = "Neptune";
        int diameter = 49244;
        double temperature= -201.0;
        int nrOfMoons = 13;
        String imgPath = "";
        double convertedTemperature = (temperature * 9.0/5.0) + 32.0;
        Planet planetToCheck = factory.createPlanet(name,diameter,temperature, nrOfMoons, imgPath);
        Assert.assertEquals(convertedTemperature, planetToCheck.getMeanSurfaceTempF(), 0.001D);
    }

    @Test
    public void diameterConversionTest() {
        PlanetFactory factory = new PlanetFactory();
        String name = "Neptune";
        int diameter = 49244;
        double temperature= -201.0;
        int nrOfMoons = 13;
        String imgPath = "";
        double convertedDiameter = 49244 * 0.62137119224;
        Planet planetToCheck = factory.createPlanet(name,diameter,temperature, nrOfMoons, imgPath);
        Assert.assertEquals(convertedDiameter, planetToCheck.getDiameterM(), 0.001D);
    }

    @Test
    public void imageImportTest() {
        String imageToImport = "no_image.png";
        Image imgToCheck = ImageLoader.getImageFromName(imageToImport);

        Assert.assertNotNull(imgToCheck);
    }

    @Test
    public void planetWriteTest() {
        String name = "Neptune";
        int diameter = 49244;
        double temperature= -201.0;
        int nrOfMoons = 13;
        String imgPath = "";

        PlanetFactory planetCreator = new PlanetFactory();
        Planet planetToStore = planetCreator.createPlanet(name, diameter, temperature, nrOfMoons, imgPath);

        PlanetIO planetWriter = new PlanetIO();
        planetWriter.WriteToFile(planetToStore);

        File storedFile = new File(Paths.get(".\\planetObjects").toAbsolutePath().normalize().toString() + "\\" + planetToStore.getName());
        Assert.assertNotNull(storedFile);
    }

    @Test
    public void planetReadTest() {
        String name = "Neptune";
        int diameter = 49244;
        double temperature= -201.0;
        int nrOfMoons = 13;
        String imgPath = "";

        PlanetFactory planetCreator = new PlanetFactory();
        Planet actualPlanet = planetCreator.createPlanet(name, diameter, temperature, nrOfMoons, imgPath);

        PlanetIO planetReader = new PlanetIO();
        String readLocation = Paths.get(".\\planetObjects").toAbsolutePath().normalize().toString() + "\\" + name;
        Planet planetRead = planetReader.ReadFromFile(readLocation);

        Assert.assertEquals(planetRead.toString(), actualPlanet.toString());
    }
}
