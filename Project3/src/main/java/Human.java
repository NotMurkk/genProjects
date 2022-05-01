import java.awt.*;
import java.awt.event.KeyEvent;

public class Human extends creature {

    public Human(Point pos, Scene scene) {
        super(pos, scene, "Human");
        health = 100;
    }

    public void keyPressed(KeyEvent e) {

        if (scene.ended)
            return;
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            move(new Point(0, -1));
        }
        if (key == KeyEvent.VK_RIGHT) {
            move(new Point(1, 0));
        }
        if (key == KeyEvent.VK_DOWN) {
            move(new Point(0, 1));
        }
        if (key == KeyEvent.VK_LEFT) {
            move(new Point(-1, 0));
        }
    }

    public void die() {
        super.die();
        scene.triggerEnd();
    }


}