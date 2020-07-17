package client.newViewNedaei;

public abstract class Panel {

    public void goBack() {
        MenuController.getInstance().enableCurrentPane();
    }
}
