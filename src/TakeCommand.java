public class TakeCommand implements Command {
    Player player;
    String itemName;

    public TakeCommand(Player player) {
        this.player = player;
    }

    public void init(String userString) {
        this.itemName = userString.substring(6, userString.length());
    }

    public boolean execute() {
        Item item = player.getCurrentRoom().containsItem(itemName);

        if (item != null) {
            player.addItem(item);
            player.getCurrentRoom().removeItem(itemName);
            System.out.println(itemName + " has been added to your inventory");
            return true;
        }
        return false;
    }
}
