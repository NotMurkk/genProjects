import java.awt.*;
import java.awt.image.ImageObserver;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class creature extends GameObject {

    int health = 10;
    int gridSize;
    Point pos;
    Items weapon;
    Scene scene;
    boolean isAlive = true;
    String species;

    public creature(Point pos, Scene scene, String species) {
        super.loadImage(species);
        this.gridSize = scene.GRID_SIZE;
        this.scene = scene;
        this.weapon = new Items();
        this.species = species;
        setLocation(pos);
    }

    public Point getLocation() {
        return pos;
    }


    public void setLocation(Point newPos) {
        if (scene.getEntity(newPos) != null)
            scene.onCollision(this, scene.getEntity(newPos));
        if (pos != null)
            scene.setEntity(pos, null);
        pos = newPos;
        scene.setEntity(pos, this);
    }

    public void move(Point vec) {
        Point newPos = new Point(pos);
        newPos.translate(vec.x, vec.y);
        newPos.setLocation(max(min(newPos.x, scene.GRID_SIZE - 1), 0), max(min(newPos.y, scene.GRID_SIZE - 1), 0));
        if (newPos.equals(pos))
            return;
        setLocation(newPos);
    }

    public void attack(creature other) {
        scene.inAttack = true;
        other.health -= weapon.damage / ((int) (Math.random()*2) + 1);
        System.out.println(species + " attacked " + other.species + " with " + weapon.damage + " damage!");
        weapon.health -= 1;
        if (weapon.health <= 0) {
            System.out.println("The " + species + "'s weapon has broken!");
        }
        if(other.health <= 0) {
            weapon = other.weapon;
            weapon.health += 5;
            health += 5;
            other.die();
        }
    }


    public void die() {
        isAlive = false;
        System.out.println(species + " has died!");
        scene.inAttack = false;
    }

    public void pickUp(Items item) {
        weapon = item;
        System.out.println(species + " picked up " + item.name);
    }

    public void draw(Graphics g, ImageObserver observer) {
        super.draw(pos.x*scene.TILE_SIZE, pos.y*scene.TILE_SIZE, g, observer);
    }
}