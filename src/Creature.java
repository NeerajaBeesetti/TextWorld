public abstract class Creature {
    protected Level.Room currentRoom;
    protected String name;
    protected String desc;

    public abstract void move();

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public Level.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Level.Room newRoom) {
        currentRoom = newRoom;
    }

    protected boolean moveToRoom(String name) {
        if (currentRoom.getNeighbor(name) != null) {
            Level.Room newRoom = currentRoom.getNeighbor(name);
            newRoom.addCreature(this);
            currentRoom.removeCreature(this);
            setCurrentRoom(newRoom);
            return true;
        }
        return false;
    }

    protected boolean moveToRoom(Level.Room newRoom) {
        if (newRoom != null) {
            newRoom.addCreature(this);
            currentRoom.removeCreature(this);
            setCurrentRoom(newRoom);
            return true;
        }
        return false;
    }

}
