public class EmptyCommand implements Command {

    public void init(String userString) { }

    public boolean execute() {
        System.out.println("Empty Command");
        return true;
    }
}
