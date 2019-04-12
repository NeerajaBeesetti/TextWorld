public class Wumpus extends Creature {

    private Level.Room playerRoom;

    public Wumpus(Level.Room playerRoom) {
        this.playerRoom = playerRoom;
    }

    public void move() {
            Level.Room newRoom = getRandomRoomPlayerIsNotIn();
            if (moveToRoom(newRoom)) {
                System.out.println(getName() + " moved to " + newRoom.getName());
            } else {
            System.out.println(getName() + " is in " + currentRoom.getName());
        }
    }

    public Level.Room getRandomRoomPlayerIsNotIn() {
        if (currentRoom.getNeighbor(playerRoom.getName()) != null) {
            return currentRoom.getRandomNeighbor();
        }
        return null;
    }
}