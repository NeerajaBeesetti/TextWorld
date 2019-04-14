public class Popstar extends Creature {
    private Level.Room playerRoom;

    public Popstar(Level.Room playerRoom) {
        this.playerRoom = playerRoom;
    }

    public void move() {
        if (currentRoom.containsNeighbor(playerRoom.getName())) {
            moveToRoom(playerRoom);
            System.out.println(getName() + " is in your room");
        } else if (!(currentRoom.containsNeighbor(playerRoom.getName()))) {
            for (Level.Room temp : currentRoom.getNeighborList()) {
                if (temp.containsNeighbor(playerRoom.getName())) {
                    moveToRoom(temp);
                    System.out.println(getName() + " moved to " + temp.getName());
                    break;
                }
            }
        } else {
            Level.Room newRoom = currentRoom.getRandomNeighbor();
            moveToRoom(newRoom);
            System.out.println(getName() + " moved randomly to " + newRoom.getName());
        }
    }
}