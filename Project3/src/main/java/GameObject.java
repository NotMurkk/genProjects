import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class GameObject {

    private BufferedImage image;

    public void loadImage(String imageName) {
        try {

            image = ImageIO.read(new File("src/main/images/"+imageName+".png"));
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    public void draw(int x, int y, Graphics g, ImageObserver observer) {
        g.drawImage(image, x, y, observer);
    }

}