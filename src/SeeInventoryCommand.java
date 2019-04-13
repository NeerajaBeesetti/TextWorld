public class SeeInventoryCommand implements Command {
    Player player;

    public SeeInventoryCommand(Player player) {
        this.player = player;
    }

    public void init(String userString) {
    }

    public boolean execute() {
        String out = "Your inventory: ";
        for (Item item : player.getInventory()) {
            out += item.getName() + "; ";
        }
        System.out.println(out);
        return true;
    }
}