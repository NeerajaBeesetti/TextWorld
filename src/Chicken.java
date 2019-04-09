public class Chicken extends Creature {

    public Chicken() { }

    public void move() {

        Level.Room newRoom = currentRoom.getRandomNeighbor();
        newRoom.addCreature(this);
        currentRoom.removeCreature(this);
        setCurrentRoom(newRoom);
        System.out.println(getName() + " moved to " + newRoom.getName());
    }

}