import java.util.Arrays;
import java.util.Random;

public class Items extends GameObject {

    int damage;
    int health;
    String name;

    public Items() {
        this.name = Arrays.asList("Gun", "Sword").get(new Random().nextInt(2));
        this.damage = (int) (Math.random() * 5) + 1;
        this.health = (int) (Math.random() * 5) + 1;

    }

}