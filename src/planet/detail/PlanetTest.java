package planet.detail;

import org.junit.Assert;
import org.junit.Test;

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
    public void checkTemperatureConversion() {
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
    public void checkDiameterConversion() {
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
}
