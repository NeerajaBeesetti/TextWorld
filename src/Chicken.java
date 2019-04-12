public class Chicken extends Creature {

    public Chicken() { }

    public void move() {
        Level.Room newRoom = currentRoom.getRandomNeighbor();
        moveToRoom(newRoom);

        System.out.println(getName() + " moved to " + newRoom.getName());
    }

}