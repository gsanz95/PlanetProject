package planet.detail;

public class PlanetFactory {

    public Planet createPlanet(String planetName, int planetDiameterKM, double planetMeanSurfaceTempC, int planetNumberOfMoons, String planetImgPath) {
        return new Planet(planetName, planetDiameterKM, planetMeanSurfaceTempC, planetNumberOfMoons, planetImgPath);
    }

    public static boolean hasValidAttributes(String planetName, int planetDiameterKM, double planetMeanSurfaceTempC, int planetNumberOfMoons) {
        if(!Planet.isValidName(planetName))
            return false;
        else if(!Planet.isValidDiameter(planetDiameterKM))
            return false;
        else if(!Planet.isValidSurfaceTemp(planetMeanSurfaceTempC))
            return false;
        else
            return Planet.isValidNumberOfMoons(planetNumberOfMoons);
    }
}