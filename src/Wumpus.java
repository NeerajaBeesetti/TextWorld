public class Wumpus extends Creature {

    private Level.Room playerRoom;

    public Wumpus(Level.Room playerRoom) {
        this.playerRoom = playerRoom;
    }

    public void move() {
        if (currentRoom.isNeighbor(playerRoom) || currentRoom == playerRoom) {
            Level.Room newRoom = getRandomRoomPlayerIsNotIn();
            newRoom.addCreature(this);
            currentRoom.removeCreature(this);
            setCurrentRoom(newRoom);
            System.out.println(getName() + " moved to " + newRoom.getName());

        } else {
            System.out.println(getName() + " is in " + currentRoom.getName());
        }
    }

    public Level.Room getRandomRoomPlayerIsNotIn() {
        Level.Room rand;

//        if (playerRoom.getNeighbor(currentRoom.getName()) != null) {
//            this.setCurrentRoom(this.getCurrentRoom().getRandomNeighbor());
//        }


        do {
            rand = currentRoom.getRandomNeighbor();
            //TODO
        } while (rand != playerRoom);
        return rand;
    }
}