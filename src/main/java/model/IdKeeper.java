package model;

public class IdKeeper {
    private static IdKeeper instance = new IdKeeper();

    private IdKeeper() {

    }

    public static IdKeeper getInstance() {
        return instance;
    }

    public void updateIds() {

    }
}
