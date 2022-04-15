import java.util.*;

public class MainClass {
    public static void main( String[] args ) throws Exception {
        Land map = new Land();
        Human player = new Human(21);
        map.player = player;
        Scanner input = new Scanner(System.in);
        ArrayList<int[]> goblinLocation = map.addGoblins(10);
        ArrayList<int[]> treasureLocation = map.addTreasure(7);
        for (int[] gobsCord : goblinLocation) {
            map.allGoblins.add(new Goblin(2, gobsCord));
        }
        for (int[] treasureCord : treasureLocation) {
            map.allTreasures.add(new Treasure(treasureCord));
        }

        System.out.println("Welcome to Humans vs Goblins!");
        int round = 1;
        while (true) {
            HashMap<String, Object> surroundings;
            System.out.println(map);
            surroundings = map.checkSurroundings(player.cords);
            if (surroundings.size() > 0) {
                Set<String> keys = surroundings.keySet();
                ArrayList<String> directions = new ArrayList<>(keys);
                System.out.println(directions);
                if (surroundings.size() > 1) {
                    while (surroundings.size() != 1) {
                        StringBuilder directs = new StringBuilder();
                        for (String key : directions)
                            directs.append("(").append(key).append(") - ").append(surroundings.get(key).getClass().getSimpleName()).append("\n");
                        System.out.println(
                                "You have encountered multiple entities! Choose which one you will interact with first.\n" + directs
                        );
                        String encounter = input.next().toUpperCase();
                        if (directions.contains(encounter)) {
                            if (surroundings.get(encounter).getClass() == Goblin.class) {
                                player.interactWith(player, (Goblin) surroundings.get(encounter), input);
                                map.removeCreature((Goblin) surroundings.get(encounter));
                                surroundings.remove(encounter);
                                if (player.health <= 0) {
                                    System.out.println("You have Died! Game over...");
                                    System.exit(0);
                                }
                                player.postBattle();
                            } else {
                                player.interactWith(player, (Treasure) surroundings.get(encounter), input);
                                map.removeCreature((Treasure) surroundings.get(encounter));
                                surroundings.remove(encounter);
                            }
                        } else {
                            System.out.println("Invalid Input please try again!");
                        }
                    }

                } if (surroundings.size() == 1) {
                    if (surroundings.get(directions.get(0)).getClass() == Goblin.class) {
                        if (round == 1) System.out.println("You got unlucky and spawned next to a Goblin! Let the fight begin!");
                        player.interactWith(player, (Goblin) surroundings.get(directions.get(0)), input);
                        map.removeCreature((Goblin) surroundings.get(directions.get(0)));
                        System.out.println(map);
                        if (player.health <= 0) {
                            System.out.println("You have Died! Game over...");
                            System.exit(0);
                        }
                        player.postBattle();
                    } else {
                        if (round == 1) System.out.println("You got lucky and spawned next to a Treasure chest!");
                        player.interactWith(player, (Treasure) surroundings.get(directions.get(0)), input);
                        map.removeCreature((Treasure) surroundings.get(directions.get(0)));
                        System.out.println(map);
                    }
                }
            }
            if (map.allGoblins.size() == 0) {
                System.out.println("Congratulations! You killed all the goblins and won the game!");
                System.exit(0);
            }
            while (true) {
                System.out.println("Which direction would you like to move? (N, S, E, W) or (M) to manageInventory");
                String direction = input.next().toUpperCase();
                if (direction.equals("N") || direction.equals("S") || direction.equals("E") || direction.equals("W")) {
                    int[] newPosition = player.moveCreature(direction, player.cords);
                    if (!(newPosition[0] == player.cords[0] && newPosition[1] == player.cords[1])) {
                        boolean successfulMove = map.moveEntityIcon(player.cords, newPosition, player);
                        if (successfulMove) {
                            player.cords = newPosition;
                            break;
                        } else {
                            System.out.println(
                                    "You are unable to move that direction due to an obstacle in your path, Please chose a new direction!"
                            );
                        }
                    } else {
                        System.out.println(
                                "You are unable to move that direction due to an obstacle in your path, Please chose a new direction!"
                        );
                    }
                } else if (direction.equals("M")) {
                    player.manageInventory(input);
                    break;
                } else {
                    System.out.println("You must enter a valid direction!");
                }
            }
            for (Goblin gob : map.allGoblins) {
                String direction = gob.trackPlayer(player);
                if (direction != null) {
                    int[] newPosition = gob.moveCreature(direction, gob.cords);
                    if (!Arrays.equals(newPosition, gob.cords)) {
                        boolean worked = map.moveEntityIcon(gob.cords, newPosition, gob);
                        if (worked) {
                            gob.cords = newPosition;
                        }
                    }
                }
            }
            round++;
        }
    }

    public static void timeOut(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
