import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class creatureTest {
    private Human player;
    private Items item;
    private Items consumable;
    private Treasure chest;

    @Before
    public void setUp() {
        player = new Human(21);
        item = new Items("weapon");
        consumable = new Items("consumable");
        do {
            chest = new Treasure(new int[]{15, 10});
        } while (chest.inventory.length <= 2);
    }

    @Test
    public void checkAddRemove() {
        assertFalse(player.getConsumables());
        assertTrue(player.checkInventory());
        assertTrue(player.addToInventory(item));
        assertTrue(player.checkInventory());
        assertTrue(player.addToInventory(consumable));
        assertTrue(player.getConsumables());
    }

    @Test
    public void creatureShouldBeAbleToMoveInAllDirections() {
        assertEquals(11, player.moveCreature("N", player.cords)[0]);
        assertEquals(13, player.moveCreature("S", player.cords)[0]);
        assertEquals(16, player.moveCreature("E", player.cords)[1]);
        assertEquals(14, player.moveCreature("W", player.cords)[1]);
    }

    @Test
    public void lootSingleItem() {
        assertSame(chest.lootSingleItem(0).getClass(), Items.class);
    }

    @Test
    public void lootAllItems() {
        Items[] allItems = chest.lootAllItems();
        for (Items item : allItems) {
            if (item != null)
                assertSame(item.getClass(), Items.class);
        }
    }
}