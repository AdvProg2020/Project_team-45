package newModel;

public abstract class DataBaseCompatible {
    protected int id;
    protected boolean justId;

    public DataBaseCompatible() {
    }

    public DataBaseCompatible(int id, boolean justId) {
        this.id = id;
        this.justId = justId;
        if (!justId) {
            readFromDataBase();
        }
    }

    protected abstract void readFromDataBase();

    protected abstract void saveToDataBase();

    public int getId() {
        return id;
    }

    public boolean isJustId() {
        return justId;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DataBaseCompatible)) {
            return false;
        }
        return this.getId() == ((DataBaseCompatible) obj).getId();
    }
}