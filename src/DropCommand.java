public class DropCommand implements Command {
    Player player;
    String itemName;

    public DropCommand(Player player) {
        this.player = player;
    }

    public void init(String userString) {
        this.itemName = userString.substring(6, userString.length());
    }

    public boolean execute() {
        Item item = player.inventoryContainsItem(itemName);
        if (item != null) {
            player.getCurrentRoom().addItem(itemName);
            player.removeItem(itemName);
            System.out.println(itemName + " has been removed from your inventory");
            return true;
        }
        return false;
    }
}
