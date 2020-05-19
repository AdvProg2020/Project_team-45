package view.nedaei;

import view.bagheri.Menu;
import view.nedaei.personalinfopanel.PersonalInfoPanel;

public abstract class UserMenu extends Menu {

    protected UserMenu(String name) {
        super(name);
        this.submenus.put("view personal info", PersonalInfoPanel.getInstance());
        needBeLogin = true;
    }

}