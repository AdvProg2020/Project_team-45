package view;

import view.personalinfopanel.PersonalInfoPanel;

public abstract class UserMenu extends Menu {

    protected UserMenu(String name) {
        super(name);
        this.submenus.put("view personal info", PersonalInfoPanel.getInstance());
        needBeLogin = true;
    }

}