package planet.detail;

import java.io.Serializable;

public class Planet implements Serializable {

    // serialVersion ID
    //private static final long serialVersionUID = 1L;

    private String name;  // Must contain only [A-z0-9\s-.] with length 1-255.
    private int diameterKM;   // Real number 0 to 200,000, defaults to -1
    private double diameterM;    // Calculate this from diameterKM
    private double meanSurfaceTempC;  // Real number -273.15 to 500.0
    private double meanSurfaceTempF;  // Calculate this from meanSurfaceTempC
    private int numberOfMoons; // Integer 0 to 1,000, defaults to -1
    private String imageName;

    // Constructor
    public Planet(String name, int diameterKM, double meanSurfaceTempC, int numberOfMoons, String imageName) {
        this.name = name;
        this.diameterKM = diameterKM;
        this.diameterM = diameterKM * 0.62137119224;
        this.meanSurfaceTempC = meanSurfaceTempC;
        this.meanSurfaceTempF = (meanSurfaceTempC * 9.0/5.0) + 32.0;
        this.numberOfMoons = numberOfMoons;
        this.imageName = imageName;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getDiameterKM() {
        return diameterKM;
    }

    public double getDiameterM() {
        return diameterM;
    }

    public double getMeanSurfaceTempC() {
        return meanSurfaceTempC;
    }

    public double getMeanSurfaceTempF() {
        return meanSurfaceTempF;
    }

    public int getNumberOfMoons() {
        return numberOfMoons;
    }

    public String getImageName() {
        return imageName;
    }

    // Validators
    public static boolean isValidName(String nameToCheck) {
        if(nameToCheck.isEmpty())
            return false;
        else if(nameToCheck.matches(".*[^A-Za-z0-9 \\-.]+.*"))
            return false;
        return nameToCheck.length() <= 255;

    }

    public static boolean isValidDiameter(int diameterToCheck) {
        return diameterToCheck >= 0 && diameterToCheck <= 200000;
    }

    public static boolean isValidSurfaceTemp(double temperatureToCheck) {
        return temperatureToCheck >= -273.15 && temperatureToCheck <= 500.0;
    }

    public static boolean isValidNumberOfMoons(int numberOfMoons) {
        return numberOfMoons >= 0 && numberOfMoons <= 1000;
    }


    @Override
    public String toString() {
        return  "Name: " + this.name +
                "\nDiameter (KM): " + this.diameterKM +
                "\nDiameter (Miles): " + this.diameterM +
                "\nTemp (C): " + this.meanSurfaceTempC +
                "\nTemp (F): " + this.meanSurfaceTempF +
                "\nMoons: " + this.numberOfMoons +
                "\nImage: " + this.imageName + "\n";
    }
}
