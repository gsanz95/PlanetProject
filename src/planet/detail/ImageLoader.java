package planet.detail;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/*
    Delegate that handles all loading of images
 */
public class ImageLoader {

    public static String chooseImageFromChooser() {
        FileChooser imgChooser = new FileChooser();
        String imgPath = Paths.get(".\\images").toAbsolutePath().normalize().toString();
        imgChooser.setInitialDirectory(new File(imgPath));
        File selectedImg = imgChooser.showOpenDialog(null);

        return selectedImg.getPath();
    }

    public static Image getImageFromPath(String pathOfImageToLoad) {
        BufferedImage imgBuffer;
        try {
            imgBuffer = ImageIO.read(new File(pathOfImageToLoad));
            Image img = SwingFXUtils.toFXImage(imgBuffer, null);
            return img;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Cannot find path: " + pathOfImageToLoad);
            return null;
        }
    }
}
