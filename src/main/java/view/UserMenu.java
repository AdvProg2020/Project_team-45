package view;

public abstract class UserMenu extends Menu {
    protected UserMenu(String name, Menu parent) {
        super(name, parent);
    }

    public void showPersonalInfo() {
    }

    protected PersonalInfoPanel getUserInfoEditingPanel(String fieldName) {
        new Panel("") {

        };
    }

}