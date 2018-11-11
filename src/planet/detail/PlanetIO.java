package planet.detail;

import javafx.stage.FileChooser;

import java.io.*;
import java.nio.file.Paths;

/**
 * Delegate to handle all IO interactions for planets
 */
public class PlanetIO {

    private String savePath;

    public PlanetIO() {
        this.savePath = Paths.get(".\\planetObjects").toAbsolutePath().normalize().toString();
    }

    /**
     * Takes a planet and writes it to a file.
     * @param planetToWrite Planet to be stored in a file
     */
    public void WriteToFile(Planet planetToWrite) {
        String fileDestination = this.savePath + "\\" + planetToWrite.getName();
        try{
            FileOutputStream fileOutStream = new FileOutputStream(fileDestination);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);
            objectOutStream.writeObject(planetToWrite);
            objectOutStream.close();
        }catch (Exception e){
            e.getMessage();
        }
    }

    /**
     * Displays a dialog window and returns the planet
     * selected.
     * @return Planet object read from file
     */
    public Planet choosePlanetFromChooser() {
        FileChooser planetChooser = new FileChooser();
        String planetPath = this.savePath;
        planetChooser.setInitialDirectory(new File(planetPath));
        File planetFile  = planetChooser.showOpenDialog(null);

        return this.ReadFromFile(planetFile.getPath());
    }

    /**
     * Takes fileLocation and returns the planet read at that location.
     * @param fileLocation Location of a planet object (absolute path)
     * @return Planet object read
     */
    public Planet ReadFromFile(String fileLocation) {
        try{
            FileInputStream inStream = new FileInputStream(fileLocation);
            ObjectInputStream planetInStream = new ObjectInputStream(inStream);

            Planet readPlanet = (Planet) planetInStream.readObject();

            planetInStream.close();
            return readPlanet;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
