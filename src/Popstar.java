public class Popstar extends Creature {
    private Level.Room playerRoom;

    public Popstar(Level.Room playerRoom) {
        this.playerRoom = playerRoom;
    }

    public void move() {
        //TODO: fix popstar!
        if (currentRoom.containsNeighbor(playerRoom.getName())) {
            moveToRoom(playerRoom);
            System.out.println(getName() + " moved to " + playerRoom.getName());
        } else if (!(currentRoom.containsNeighbor(playerRoom.getName()))) {
            for (Level.Room temp : currentRoom.getNeighborList()) {
                if (temp.containsNeighbor(playerRoom.getName())) {
                    moveToRoom(temp);
                    System.out.println(getName() + " moved to " + temp.getName());
                }
            }
        } else {
            Level.Room newRoom = currentRoom.getRandomNeighbor();
            moveToRoom(newRoom);
            System.out.println(getName() + " moved to " + newRoom.getName());
        }
    }
}