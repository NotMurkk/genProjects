import java.awt.*;

public class Goblin extends creature {


    public Goblin(Point pos, Scene scene) {
        super(pos, scene, "Goblin");
    }

    public Point getHumanVector() {
        Point v = new Point(
                getLocation().x - scene.human.getLocation().x,
                getLocation().y - scene.human.getLocation().y);
        float divisor = -(int) Math.sqrt(v.x*v.x + v.y*v.y);
        v.setLocation(Math.round(v.x/divisor), Math.round(v.y/divisor));
        if (v.x != 0 && v.y != 0)
            v.setLocation(0, v.y);
        return v;
    }

    public void die() {
        super.die();
        scene.goblins.remove(this);
        scene.generateGoblin();
        if (Math.random() < 0.33)
            scene.generateGoblin();
    }
}