public class Treasure extends creature {

    public int[] cords;
    public Treasure(int[] cords) {
        super(5);
        this.cords = cords;
        this.addRandomItems(5, true, "all");
    }

    @Override
    public String toString() {
        StringBuilder contains;
        if (this.checkInventory()) {
            contains = new StringBuilder("This chest contains: ");
            for (int i = 0; i < this.inventory.length; i++) {
                if (this.inventory[i] != null){
                    contains.append(this.inventory[i].name);
                }
                if (i == this.inventory.length - 1){
                    contains.append(".");
                }
                else if (i == this.inventory.length - 1 && this.inventory[i] != null){
                    contains.append(", ");
                }
            }
        } else contains = new StringBuilder("This chest is empty.");

        return "Treasure Chest: " + contains;
    }
}