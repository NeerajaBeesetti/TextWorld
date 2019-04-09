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

}
