package newModel;

public abstract class User extends DataBaseCompatible {

    public User(String id, boolean justId) {
        super(id, justId);
    }

    @Override
    protected void readFromDataBase() {

    }

    @Override
    protected void saveToDataBase() {

    }
}
