public class GoCommand implements Command {
    Player player;
    String nextRoomName;

    public GoCommand(Player player) {
        this.player = player;
    }

    public void init(String userString) {
        nextRoomName = userString.substring(4, userString.length());
    }

    public boolean execute() {
        if (player.getCurrentRoom().containsNeighbor(nextRoomName)) {
            player.moveToRoom(nextRoomName);
            return true;
        }
        return false;
    }
}