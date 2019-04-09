import java.util.ArrayList;

public class Player {
    private String name, desc;
    private ArrayList<Item> inventory;
    private Level.Room currentRoom;

    public Player(String name, String desc) {
        this.name = name;
        this.desc = desc;
        inventory = new ArrayList<>();
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public Item removeItem(String name) {
        Item out = null;
        for (Item temp : inventory) {
            if (temp.getName().equals(name)) {
                out = temp;
                inventory.remove(temp);
            }
        }
        return out;
    }

    public boolean destroyItem(String name) {
        boolean isDestroyed = false;
        for (Item temp : inventory) {
            if (temp.getName().equals(name)) {
                inventory.remove(temp);
                isDestroyed = true;
            }
        }
        return isDestroyed;
    }

    public ArrayList<Item> getInventory() {
        return inventory;

    }

    public void displayInventory() {
        String out = "Inventory: ";
        for (Item temp : inventory) {
            out += temp.getName() + " (" + temp.getDesc() + "); ";
        }
        System.out.println(out);
    }

    public Item inventoryContainsItem(String name) {
        for (Item item : inventory) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room newRoom) {
        currentRoom = newRoom;

    }

    public boolean moveToRoom(String name) {
        Level.Room toMove = currentRoom.getNeighbor(name);
        if (toMove != null) {
            currentRoom = toMove;
            return true;
        }
        return false;
    }


}
