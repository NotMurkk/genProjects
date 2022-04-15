import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class Land {
    public HashMap<Integer, String[]> map = new HashMap<>();
    public ArrayList<Goblin> allGoblins = new ArrayList<>();
    public ArrayList<Treasure> allTreasures = new ArrayList<>();
    public Human player;

    public Land() {
        int i = 1;
        while (i <= 23) {
            if (i == 1 || i == 23) {
                String[] topAndBottomArray = {"-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-",
                        "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-", "-"};
                this.map.put(i, topAndBottomArray);
            } else
                this.map.put(i, new String[] { "|", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*",
                        "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "*", "|" });
            i++;
        }
        String[] temp = this.map.get(12);
        temp[15] = "H";
        this.map.put(12, temp);
    }

    public ArrayList<int[]> addGoblins(int totalNum) {
        ArrayList<int[]> allGobs = new ArrayList<>();
        for (int i = 0; i < totalNum; i++) {
            int upAndDown = (int) Math.floor(Math.random() * 21) + 2;
            int sideToSide = (int) Math.floor(Math.random() * 30) + 1;
            String[] temp = this.map.get(upAndDown);
            if (!Objects.equals(temp[sideToSide], "*")) {
                i--;
            } else {
                temp[sideToSide] = "G";
                this.map.put(upAndDown, temp);
                allGobs.add(new int[] { upAndDown, sideToSide });
            }
        }
        return allGobs;
    }
    //
    public ArrayList<int[]> addTreasure(int totalNum) {
        ArrayList<int[]> allTreasure = new ArrayList<>();
        for (int i = 0; i < totalNum; i++) {
            int upAndDown = (int) Math.floor(Math.random() * 21) + 2;
            int sideToSide = (int) Math.floor(Math.random() * 30) + 1;
            String[] temp = this.map.get(upAndDown);
            if (!Objects.equals(temp[sideToSide], "*")) {
                i--;
            } else {
                temp[sideToSide] = "T";
                this.map.put(upAndDown, temp);
                allTreasure.add(new int[] { upAndDown, sideToSide });
            }
        }
        return allTreasure;
    }

    public boolean moveEntityIcon(int[] currentPos, int[] newPos, Object entity) throws Exception {
        String[] currentLine = this.map.get(currentPos[0]);
        String[] newLine = this.map.get(newPos[0]);
        String type;
        if (entity.getClass() == Human.class){
            type = "H";
        }
        else if (entity.getClass() == Goblin.class){
            type ="G";
        }
        else {
            throw new Exception("Treasure Chests can not be moved!");
        }
        if (!currentLine[currentPos[1]].equals(type)) {
            throw new Exception("The Entity does not exist at the cords provided thus can not be moved!");
        }
        if (!newLine[newPos[1]].equals("*")) {
            return false;
        }
        if (newPos[0] == 1 || newPos[0] == 23){
            return false;
        }
        if (newPos[1] == 0 || newPos[1] == 31){
            return false;
        }
        currentLine[currentPos[1]] = "*";
        this.map.replace(currentPos[0], currentLine);
        currentLine = this.map.get(newPos[0]);
        currentLine[newPos[1]] = type;
        this.map.replace(newPos[0], currentLine);
        return true;
    }

    public HashMap<String, Object> checkSurroundings(int[] currentPos) {
        HashMap<String, Object> entitiesNearby = new HashMap<>();
        ArrayList<int[]> directions = new ArrayList<>();
        directions.add(new int[]{currentPos[0] - 1, currentPos[1]});
        directions.add(new int[]{currentPos[0] + 1, currentPos[1]});
        directions.add(new int[]{currentPos[0], currentPos[1] - 1});
        directions.add(new int[]{currentPos[0], currentPos[1] + 1});
        String[] direct = new String[] {"N", "S", "W", "E"};
        int i = 0;
        for (int[] direction : directions) {
            if (this.map.get(direction[0])[direction[1]].equals("G")) {
                for (Goblin gob : this.allGoblins) {
                    if (gob.cords[0] == direction[0] && gob.cords[1] == direction[1]) {
                        entitiesNearby.put(direct[i], gob);
                    }
                }
            } else if (this.map.get(direction[0])[direction[1]].equals("T")) {
                for (Treasure treasure : this.allTreasures) {
                    if (treasure.cords[0] == direction[0] && treasure.cords[1] == direction[1]) {
                        entitiesNearby.put(direct[i], treasure);
                    }
                }
            } else if (this.map.get(direction[0])[direction[1]].equals("H")) {
                entitiesNearby.put(direct[i], player);
            }
            i++;
        }
        return entitiesNearby;
    }

    public void removeCreature(Goblin gob) {
        String[] newArray = this.map.get(gob.cords[0]);
        newArray[gob.cords[1]] = "*";
        this.map.put(gob.cords[0], newArray);
        int i = 0;
        for (Goblin goblin : allGoblins) {
            if (goblin.cords[0] == gob.cords[0] && goblin.cords[1] == gob.cords[1]) {
                allGoblins.remove(i);
                break;
            }
            i++;
        }
    }

    public void removeCreature(Treasure chest) {
        String[] newArray = this.map.get(chest.cords[0]);
        newArray[chest.cords[1]] = "*";
        this.map.put(chest.cords[0], newArray);
        int i = 0;
        for (Treasure loot : allTreasures) {
            if (loot.cords[0] == chest.cords[0] && loot.cords[1] == chest.cords[1]) {
                allTreasures.remove(i);
                break;
            }
            i++;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringToPrint = new StringBuilder();
        for (int i = 1; i <= 23; i++) {
            StringBuilder tempLine = new StringBuilder();
            for (int j = 0; j < this.map.get(i).length; j++) {
                tempLine.append(this.map.get(i)[j]).append(" ");
            }
            stringToPrint.append(tempLine).append("\n");
        }
        return stringToPrint.toString();
    }
}