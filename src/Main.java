import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static HashMap<String, Command> commands;
    private static Level g;

    public static void main(String[] args) {
        commands = new HashMap<>();
        g = new Level();

        g.initialize();
        initCommands(g);

        String response, descResponse;
        Scanner s = new Scanner(System.in);

        printPlayerCommands();
        do {
            System.out.println("You are in: " + g.getPlayer().getCurrentRoom().getName());
            System.out.print("What you do want to do? >");
            response = s.nextLine();

            Command command = lookupCommand(response);
            command.execute();

            g.moveAllCreatures();

        } while (!response.equals("quit"));
    }

    private static Command lookupCommand(String response) {
        String[] words = response.split(" ");
        Command c = commands.get(words[0]);
        if (c == null) return new EmptyCommand();

        c.init(response);

        return c;
    }

    private static void initCommands(Level level) {
        commands.put("take:", new TakeCommand(level.getPlayer()));
        commands.put("look", new LookCommand(level.getPlayer()));
        commands.put("see-inventory", new SeeInventoryCommand(level.getPlayer()));
        commands.put("add:", new AddRoomCommand(level));
        commands.put("go:", new GoCommand(level.getPlayer()));
        commands.put("drop:", new DropCommand(level.getPlayer()));
    }


    private static void printPlayerCommands() {
        System.out.println("Commands:{ go: <room name>, look, add: <room name>, take: <item name>, drop: <item name>, see-inventory, quit }");
    }
}