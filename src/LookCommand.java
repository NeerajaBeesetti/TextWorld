public class LookCommand implements Command {
    Player player;

    public LookCommand(Player player) {
        this.player = player;
    }

    public void init(String userString) { }

    public boolean execute() {
        System.out.println("You can go to: " + player.getCurrentRoom().getNeighborNames());
        System.out.println("Items in room: " + player.getCurrentRoom().displayItemsInRoom());
        System.out.println("Creatures in room: " + player.getCurrentRoom().displayCreaturesInRoom());

        return true;
    }
}