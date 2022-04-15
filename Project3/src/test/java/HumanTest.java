import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HumanTest {
    private final Human player = new Human(21);

    @Test
    public void checkToSeeIfPlayerHasConsumable() {
        assertFalse(player.hasConsumable());
        player.addRandomItems(1, false, "consumables");
        assertTrue(player.hasConsumable());
    }
}