package model;

public class IdKeeper {
    private static IdKeeper instance = new IdKeeper();

    private IdKeeper() {

    }

    public static IdKeeper getInstance() {
        return instance;
    }

    public static void setInstance(IdKeeper instance) {
        IdKeeper.instance = instance;
    }

    public void updateIds() {

    }
}
