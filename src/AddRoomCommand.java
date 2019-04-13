public class AddRoomCommand implements Command {
    Level level;
    String roomName;

    public AddRoomCommand(Level level) {
        this.level = level;
    }

    public void init(String userString) {
        roomName = userString.substring(5, userString.length());
    }

    public boolean execute() {
        Player player = level.getPlayer();

        player.getCurrentRoom().addNeighbor(player.getCurrentRoom().getName(), roomName);
        return true;
    }
}
