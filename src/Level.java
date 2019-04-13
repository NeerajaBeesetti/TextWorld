import java.util.ArrayList;
import java.util.HashMap;

public class Level {
    private HashMap<String, Room> rooms;
    private ArrayList<Creature> allCreaturesInGame;
    private Player player;

    public Level() {
        rooms = new HashMap<>();
        allCreaturesInGame = new ArrayList<>();
    }

    public void initialize() {
        addRoomsAndEdges();
        addItemsInRooms();
        player = createPlayer();
        createCreatures();
    }

    private Player createPlayer() {
        Player player = new Player("you", "it's you duh");
        player.setCurrentRoom(getRoom("hall"));
        return player;
    }

    private void addRoomsAndEdges() {
        addRoom("hall", "a long hallway");
        addRoom("closet", "you can store clothes here");
        addRoom("dungeon", "do not enter");

        addUndirectedEdge("hall", "dungeon");
        addUndirectedEdge("hall", "closet");
    }

    private void addItemsInRooms() {
        getRoom("hall").addItem("Ball");
        getRoom("closet").addItem("Shirt");
        getRoom("dungeon").addItem("Key");
    }

    private void createCreatures() {
        for (int i = 0; i < 5; i++) {
            Creature c = new Chicken();
            c.setName("Chicken" + i);
            Room randRoom = getRandomRoom();
            c.setCurrentRoom(randRoom);
            c.getCurrentRoom().addCreature(c);
            allCreaturesInGame.add(c);
        }

        Creature w = new Wumpus(getRoom("hall"));
        w.setName("Wumpy");
        w.setCurrentRoom(getRandomRoom());
        w.getCurrentRoom().addCreature(w);
        allCreaturesInGame.add(w);

        Creature p = new Popstar(player.getCurrentRoom());
        p.setName("Popstar");
        p.setCurrentRoom(getRandomRoom());
        p.getCurrentRoom().addCreature(p);
        allCreaturesInGame.add(p);
    }

    public Room getRandomRoom() {
        ArrayList<Room> tempList = new ArrayList<>(rooms.values());
        return tempList.get((int)(Math.random() * tempList.size()));
    }

    public void moveAllCreatures() {
        for (Creature c : allCreaturesInGame) {
            c.move();
        }
    }

    public void addRoom(String name, String desc) {
        Room newRoom = new Room(name);
        rooms.put(name, newRoom);
    }

    public void addDirectedEdge(String name1, String name2) {
        Room n1 = getRoom(name1);
        Room n2 = getRoom(name2);

        if (n1 != null && n2 != null) {
            n1.addNeighbor(n2);
        }
    }

    public void addUndirectedEdge(String name1, String name2) {
        addDirectedEdge(name1, name2);
        addDirectedEdge(name2, name1);
    }

    public Room getRoom(String name) {
        return rooms.get(name);
    }

    public Player getPlayer() {
        return player;
    }

    public HashMap<String, Room> getAllRooms() {
        return rooms;
    }

    public class Room {
        private String name;
        private HashMap<String, Room> neighbors;
        private String desc;
        private ArrayList<Item> itemsInRoom;
        private ArrayList<Creature> creaturesInRoom;

        private Room(String name) {
            neighbors = new HashMap<>();
            itemsInRoom = new ArrayList<>();
            creaturesInRoom = new ArrayList<>();
            this.name = name;
//            this.desc = desc;
        }

        public String getName() {
            return name;
        }

        public void addNeighbor(Room n) {
            neighbors.put(n.getName(), n);
            rooms.put(n.getName(), n);
        }

        public void addNeighbor(String current, String name) {
            Room newRoom = new Room(name);
            rooms.put(name, newRoom);
            neighbors.put(name, newRoom);
            addUndirectedEdge(current, name);
        }

        public Room getNeighbor(String name) {
            return neighbors.get(name);
        }

        public Room getRandomNeighbor() {
            ArrayList<Room> tempList = new ArrayList<>(neighbors.values());
            return tempList.get((int) (Math.random() * tempList.size()));
        }

        public boolean isNeighbor(Room room) {
            if (neighbors.get(room.getName()) != null) {
                return true;
            }
            return false;
        }

        public String getNeighborNames() {
            String out = "";
            for (String keyName : neighbors.keySet()) {
                out += keyName + " (" + neighbors.get(keyName).getDesc() + "); ";
            }
            return out;
        }

        public ArrayList<Room> getNeighborList() {
            ArrayList<Room> out = new ArrayList<>(neighbors.values());
            return out;
        }

        public boolean containsNeighbor(String name) {
            for (String keyName : neighbors.keySet()) {
                if (keyName.equals(name)) {
                    return true;
                }
            }
            return false;
        }

        public Item containsItem(String name) {
            for (Item temp : itemsInRoom) {
                if (temp.getName().equals(name)) {
                    return temp;
                }
            }
            return null;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public ArrayList<Item> getItemsInRoom() {
            return itemsInRoom;
        }

        public String displayItemsInRoom() {
            String out = "";
            for (Item temp : itemsInRoom) {
                out += temp.getName() + " (" + temp.getDesc() + "); ";
            }
            return out;
        }

        public String displayCreaturesInRoom() {
            String out = "";
            for (Creature c : creaturesInRoom) {
                out += c.getName() + "; ";
            }
            return out;
        }


        public void addItem(String name) {
            Item toAdd = new Item(name);
            itemsInRoom.add(toAdd);
        }

        public void addItem(String name, String desc) {
            Item toAdd = new Item(name, desc);
            itemsInRoom.add(toAdd);
        }

        public void addItem(Item item) {
            itemsInRoom.add(item);
        }

        public Item removeItem(String name) {
            Item out = null;
            for (Item temp : itemsInRoom) {
                if (temp.getName().equals(name)) {
                    out = temp;
                    itemsInRoom.remove(temp);
                    break;
                }
            }
            return out;
        }

        public boolean destroyItem(String name) {
            boolean isDestroyed = false;
            for (Item temp : itemsInRoom) {
                if (temp.getName().equals(name)) {
                    itemsInRoom.remove(temp);
                    isDestroyed = true;
                }
            }
            return isDestroyed;
        }

        public void addCreature(Creature c) {
            creaturesInRoom.add(c);
        }

        public void removeCreature(Creature c) {
            creaturesInRoom.remove(c);
        }

        public void removeCreature(String creatureName) {
            for (Creature c : creaturesInRoom) {
                if (c.getName().equals(creatureName)) {
                    creaturesInRoom.remove(c);
                }
            }
        }

    }
}