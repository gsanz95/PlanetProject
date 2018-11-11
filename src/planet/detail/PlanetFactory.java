package planet.detail;

/**
 *  Creates planets based on information passed into this class
 */
public class PlanetFactory {

    public Planet createPlanet(String planetName, int planetDiameterKM, double planetMeanSurfaceTempC, int planetNumberOfMoons, String planetImgName) {
        if(this.hasValidAttributes(planetName, planetDiameterKM, planetMeanSurfaceTempC, planetNumberOfMoons))
        {
            if(planetImgName.isEmpty())
                planetImgName = "no_image.png";

            return new Planet(planetName, planetDiameterKM, planetMeanSurfaceTempC, planetNumberOfMoons, planetImgName);
        }
        return null;
    }

    /**
     * Checks all attributes passed on whether they are valid
     * to be stored as object.
     * @param planetName
     * @param planetDiameterKM
     * @param planetMeanSurfaceTempC
     * @param planetNumberOfMoons
     * @return If the attributes are valid
     */
    public boolean hasValidAttributes(String planetName, int planetDiameterKM, double planetMeanSurfaceTempC, int planetNumberOfMoons) {
        if(!Planet.isValidName(planetName))
        {
            System.err.println("Invalid name: " + planetName);
            return false;
        }
        if(!Planet.isValidDiameter(planetDiameterKM))
        {
            System.err.println("Invalid diameter: " + planetDiameterKM);
            return false;
        }
        if(!Planet.isValidSurfaceTemp(planetMeanSurfaceTempC))
        {
            System.err.println("Invalid surface temp C: " + planetDiameterKM);
            return false;
        }

        if(!Planet.isValidNumberOfMoons(planetNumberOfMoons))
        {
            System.err.println("Invalid number of moons: " + planetNumberOfMoons);
            return false;
        }

        return true;
    }
}
