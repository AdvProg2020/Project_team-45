package newModel;

public abstract class DataBaseCompatible {
    protected String id;
    protected boolean justId;

    public DataBaseCompatible(String id, boolean justId) {
        this.id = id;
        this.justId = justId;
        if (!justId) {
            readFromDataBase();
        }
    }

    protected abstract void readFromDataBase();

    protected abstract void saveToDataBase();

    public String getId() {
        return id;
    }

    public boolean isJustId() {
        return justId;
    }

    public boolean equals(DataBaseCompatible d) {
        return this.id == d.id;
    }
}