package planet.detail;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

public class PlanetIO {

    private String savePath;

    public PlanetIO(String planetName) {
        this.savePath = Paths.get(".\\planetObjects").toAbsolutePath().normalize().toString() + "\\" + planetName;
    }

    public void WriteToFile(Planet planetToWrite) {
        System.out.println("Trying to save:" + planetToWrite.getName());
        try{
            FileOutputStream fileOutStream = new FileOutputStream(this.savePath);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(fileOutStream);
            objectOutStream.writeObject(planetToWrite);
            objectOutStream.close();
        }catch (Exception e){
            e.getMessage();
        }
    }
}
