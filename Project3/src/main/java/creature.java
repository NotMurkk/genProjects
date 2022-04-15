import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class creature {
    public Items[] inventory;

    public creature(int inventorySize) {
        inventory = new Items[inventorySize];
    }

    public void getInventory(Items[] equipped) {
        if (this.getClass() == Human.class) {
            System.out.println("Your Inventory: ");
        }
        else if (this.getClass() == Goblin.class){
            System.out.println("Goblins Drops: ");
        }
        else if (this.getClass() == Treasure.class){
            System.out.println("Treasures Loot: ");
        }
        ArrayList<Items> equippedItems = new ArrayList<>();
        if (equipped != null){
            Collections.addAll(equippedItems, equipped);
        }
        if (this.getClass() == Human.class){
            for (int i = 0; i < this.inventory.length; i++) {
                if (this.inventory[i] != null) {
                    if (equippedItems.contains(this.inventory[i])){
                        System.out.println("(" + i + "): " + this.inventory[i].toString() + " (Equipped)");
                    }
                    else System.out.println("(" + i + "): " + this.inventory[i].toString());
                } else System.out.println("(" + i + "): Empty");
            }
        } else {
            for (int i = 0; i < this.inventory.length; i++) {
                if (this.inventory[i] != null) {
                    System.out.println("(" + i + "): " + this.inventory[i].toString());
                }
            }
        }

        System.out.println("\n");
    }

    public boolean getConsumables() {
        System.out.println("Consumables: ");
        boolean consumablesFound = false;
        for (int i = 0; i < this.inventory.length; i++) {
            if (this.inventory[i] != null && this.inventory[i].type.equals("consumable")) {
                System.out.println("(" + i + "): " + this.inventory[i].toString());
                consumablesFound = true;
            }
        }
        if (!consumablesFound) {
            System.out.println("You do not own any consumables!");
            return false;
        }
        return true;
    }

    public boolean checkInventory() {
        for (Items items : this.inventory) {
            if (items != null) {
                return true;
            }
        }
        return false;
    }

    public boolean addToInventory(Items item) {
        int i = 0;
        while(true) {
            if (i == this.inventory.length) {
                System.out.println("Your Inventory is full!" );
                return false;
            } else if (this.inventory[i] == null) {
                this.inventory[i] = item;
                return true;
            }
            i++;
        }
    }

    public void removeFromInventory(int index) {
        if (this.inventory[index] == null) {
            if (this.getClass() == Human.class) {
                System.out.println("Their is nothing in that inventory slot!");
            }
            return;
        }
        this.inventory[index] = null;
    }

    public void addRandomItems(int howMany, boolean randomizeNum, String typesAllowed) {
        int quantity = howMany;
        if (randomizeNum){
            quantity = (int) Math.floor(Math.random() * (howMany + 1));
        }
        for (int i = 0; i < quantity; i++) {
            switch (typesAllowed) {
                case "all":
                    addToInventory(new Items("all"));
                    break;
                case "weapons":
                    addToInventory(new Items("weapon"));
                    break;
                case "armor":
                    addToInventory(new Items("armor"));
                    break;
                case "consumables":
                    addToInventory(new Items("consumables"));
                    break;
            }
        }
    }

    public int[] moveCreature(String direction, int[] currentLocation) {
        int[] cords = new int[] { currentLocation[0], currentLocation[1] };
        direction = direction.toUpperCase();
        switch (direction) {
            case "N":
                if (currentLocation[0] != 2){
                    cords[0] -= 1;
                }
                break;
            case "S":
                if (currentLocation[0] != 22){
                    cords[0] += 1;
                }
                break;
            case "E":
                if (currentLocation[1] != 30){
                    cords[1] += 1;
                }
                break;
            case "W":
                if (currentLocation[1] != 1){
                    cords[1] -= 1;
                }
                break;
        }
        return cords;
    }

    public void interactWith(Human player, Goblin gob, Scanner input) {
        System.out.println("You encountered a Goblin! Time to fight!");
        System.out.println(gob.toString());
        System.out.println(player.toString());
        while (true) {
            if (player.hasConsumable()) {
                while (true) {
                    System.out.println("Chose your action! Attack or use consumable? (A or C");
                    String action = input.next().toUpperCase();
                    if (action.equals("A")) {
                        int damage = player.strength + (int) Math.floor(Math.random() * 3) + (int) Math.floor(Math.random() * -3);
                        int blocked = (int) Math.floor(Math.random() * (gob.armor + 1));
                        damage -= blocked;
                        if (damage < 0){
                            damage = 0;
                        }
                        gob.health -= damage;
                        if (gob.health < 0){
                            gob.health = 0;
                        }
                        System.out.println("\nYou attack the goblin for " + damage + " damage! The goblins health is now " + gob.health);
                        MainClass.timeOut(2500);
                        break;
                    } else if (action.equals("C")) {
                        Items item = player.useConsumable(input);
                        if (item.modifier > 0) {
                            player.health += item.modifier;
                            if (player.health > 30) {
                                player.health = 30;
                            }
                            System.out.println(
                                    "You used a " + item.name + " and gained " + item.modifier
                                            + " health! Your health is now " + player.health);
                        } else {
                            gob.health += item.modifier;
                            System.out.println("You used a " + item.name + " on the goblin and dealt " + item.modifier * -1
                                    + " damage! The goblins health is now " + gob.health);
                        }
                        break;
                    } else {
                        System.out.println("Invalid input, must enter A or C");
                    }
                }
            } else {
                int damage = player.strength + (int) Math.floor(Math.random() * 3) + (int) Math.floor(Math.random() * -3);
                int blocked = (int) Math.floor(Math.random() * (gob.armor + 1));
                damage -= blocked;
                if (damage < 0){
                    damage = 0;
                }
                gob.health -= damage;
                if (gob.health < 0){
                    gob.health = 0;
                }
                System.out.println("\nYou attack the goblin for " + damage + " damage! The goblins health is now " + gob.health);
                MainClass.timeOut(2500);
            }
            if (gob.health <= 0) {
                System.out.println("You have defeated the goblin!");
                if (gob.checkInventory()) {
                    System.out.println("The Goblin has dropped some loot:");
                    gob.getInventory(null);
                    while (true) {
                        System.out.println(
                                "Enter an inventory slot # to loot single items, or any # greater than 2 to loot all Items");
                        try {
                            int indexOfLoot = input.nextInt();
                            if (indexOfLoot >= 2) {
                                Items[] loot = gob.lootAllItems();
                                for (Items item : loot) {
                                    if (item != null) {
                                        if (player.addToInventory(item)) {
                                            System.out.println("Looted: " + item.name);
                                        } else {
                                            System.out.println("Please remove items to make room for all the loot!");
                                            player.manageInventory(input);
                                        }
                                    }
                                }
                            } else if (indexOfLoot >= 0) {
                                if (gob.inventory[indexOfLoot] != null) {
                                    Items item = gob.inventory[indexOfLoot];
                                    if (player.addToInventory(gob.lootSingleItem(indexOfLoot))) {
                                        System.out.println("Looted: " + item.name);
                                    } else {
                                        player.manageInventory(input);
                                    }
                                } else
                                    System.out.println("Their is nothing in that slot to loot!");
                            } else
                                System.out.println("Invalid input, please try again.");
                            if (!gob.checkInventory()) {
                                System.out.println("You have looted All items from this chest!");
                                player.getInventory(player.equipped);
                                break;
                            }
                            System.out.println("Would you like to loot more items? (N or any key to continue)");
                            String continueLooting = input.next().toUpperCase();
                            if (continueLooting.equals("N") || continueLooting.equals("NO")) {
                                player.getInventory(player.equipped);
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("You must enter a #!");
                            input.next();
                        }
                    }
                } else System.out.println("The goblin dropped no loot...");
                break;
            }
            int damage = gob.strength + (int) Math.floor(Math.random() * 3) + (int) Math.floor(Math.random() * -3);
            int blocked = (int) Math.floor(Math.random() * (player.armor + 1));
            damage -= blocked;
            if (damage < 0){
                damage = 0;
            }
            player.health -= damage;
            if (player.health < 0) {
                player.health = 0;
            }
            System.out.println("\nThe Goblin attacks you for " + damage + " damage! Your health is now " + player.health);
            MainClass.timeOut(2500);
            if (player.health <= 0) {
                break;
            }
        }
    }
    public void interactWith(Human player, Treasure chest, Scanner input) {
        System.out.println("You encountered a chest! Lets see what is inside!");
        if (!chest.checkInventory()) {
            System.out.println("How Unfortunate this chest is empty!");
            return;
        }
        while (true) {
            chest.getInventory(null);
            System.out.println("Enter an inventory slot # to loot single items, or any # greater than 5 to loot all Items");
            try {
                int indexOfLoot = input.nextInt();
                if (indexOfLoot >= 5) {
                    Items[] loot = chest.lootAllItems();
                    for (Items item : loot) {
                        if (item != null) {
                            if(player.addToInventory(item)) {
                                System.out.println("Looted: " + item.name);
                            } else {
                                System.out.println("Please remove items to make room for all the loot!");
                                player.manageInventory(input);
                            }
                        }
                    }
                } else if (indexOfLoot >= 0) {
                    if (chest.inventory[indexOfLoot] != null) {
                        Items item = chest.inventory[indexOfLoot];
                        if (player.addToInventory(chest.lootSingleItem(indexOfLoot))) {
                            System.out.println("Looted: " + item.name);
                        } else {
                            player.manageInventory(input);
                        }
                    } else System.out.println("Their is nothing in that slot to loot!");
                } else System.out.println("Invalid input, please try again.");
                if (!chest.checkInventory()) {
                    System.out.println("You have looted All items from this chest!");
                    player.getInventory(player.equipped);
                    break;
                }
                System.out.println("Would you like to loot more items? (N or any key to continue)");
                String continueLooting = input.next().toUpperCase();
                if (continueLooting.equals("N") || continueLooting.equals("NO")) {
                    player.getInventory(player.equipped);
                    break;
                }
            } catch (Exception e) {
                System.out.println("You must enter a #!");
                input.next();
            }
        }
    }

    public Items lootSingleItem(int index) {
        Items loot = this.inventory[index];
        this.removeFromInventory(index);
        return loot;
    }

    public Items[] lootAllItems() {
        Items[] loot = new Items[this.inventory.length];
        for (int i = 0; i < loot.length; i++) {
            loot[i] = this.inventory[i];
            this.removeFromInventory(i);
        }
        return loot;
    }
}
