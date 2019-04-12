import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HashMap<String, Command> commands = new HashMap<>();

        Level g = new Level();
        g.initialize();
        Player player = createPlayer(g);

        //initCommands(commands, g, player);

        String response, descResponse;
        Scanner s = new Scanner(System.in);

        printPlayerCommands();
        do {
            System.out.println("You are in: " + player.getCurrentRoom().getName());
            System.out.print("What you do want to do? >");
            response = s.nextLine();

//            Command command = lookupCommand(response);
//            command.execute();
//

            String[] resp = response.split(" ");

            if (resp[0].equals("go:")) {
                String nextRoomName = response.substring(4, response.length());
                if (player.getCurrentRoom().containsNeighbor(nextRoomName)) {
                    player.moveToRoom(nextRoomName);
                    g.moveAllCreatures();
                }

            } else if (response.equals("look")) {
                System.out.println("You can go to: " + player.getCurrentRoom().getNeighborNames());
                System.out.println("Items in room: " + player.getCurrentRoom().displayItemsInRoom());
                System.out.println("Creatures in room: " + player.getCurrentRoom().displayCreaturesInRoom());

            } else if (resp[0].equals("add:")) {
                String newRoomName = response.substring(5, response.length());
                System.out.print("What's it's description? >");
                descResponse = s.nextLine();
                player.getCurrentRoom().addNeighbor(player.getCurrentRoom().getName(), newRoomName, descResponse);

            } else if (resp[0].equals("take:")) {
                String itemName = response.substring(6, response.length());
                Item item = player.getCurrentRoom().containsItem(itemName);
                if (item != null) {
                    player.addItem(item);
                    player.getCurrentRoom().removeItem(itemName);
                    System.out.println(itemName + " has been added to your inventory");
                }
            } else if (resp[0].equals("drop:")) {
                String itemName = response.substring(6, response.length());
                Item item = player.inventoryContainsItem(itemName);
                if (item != null) {
                    player.getCurrentRoom().addItem(itemName);
                    player.removeItem(itemName);
                    System.out.println(itemName + " has been removed from your inventory");
                }

            } else if (response.equals("see inventory")) {
                String out = "Your inventory: ";
                for (Item item : player.getInventory()) {
                    out += item.getName() + "; ";
                }
                System.out.println(out);

            } else {
                printPlayerCommands();
            }
        } while (!response.equals("quit"));
    }

//    private  Command lookupCommand(String response) {
//        String commandWord = getFirstWordIn(response);
//        Command c = commands.get(commandWord);
//        if (c == null) return new EmptyCommand();
//
//        c.init(response);
//
//        return c;
//    }
//
//    private static void initCommands(HashMap<String, Command> commands, Level level, Player player) {
//        commands.put("take", new TakeCommand(level));
//        commands.put("look", new LookCommand(player));
//        commands.put("add: ", new AddRoomCommand(player));
//    }



    private static void printPlayerCommands() {
        System.out.println("Commands:{ go: <room name>, look, add: <room name>, take: <item name>, drop: <item name>, see inventory, quit }");
    }


    private static Player createPlayer(Level g) {
        Player player = new Player("you", "it's you duh");
        player.setCurrentRoom(g.getRoom("hall"));
        return player;
    }
}