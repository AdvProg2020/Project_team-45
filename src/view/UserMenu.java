package view;

public abstract class UserMenu extends Menu {
    protected UserMenu(String name, Menu parent) {
        super(name, parent);
    }

    public void showPersonalInfo() {
    }

    private void editUserInfo(String fieldName) {
        new Panel("") {

        };
    }

}
