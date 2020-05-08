package view.hatami;

import controller.RequestController;
import controller.managers.Printer;
import view.bagheri.Panel;

public class RequestsManagingMenu extends ManagingMenu {

    public RequestsManagingMenu() {
        super("requests managing menu");
        this.manager = RequestController.getInstance();
        submenus.put("details (\\S+)", createOneItemDisplayPanel("request", (Printer) manager));
        submenus.put("(accept|decline) (\\S+)", getAcceptDeclinePanel());
    }

    private Panel getAcceptDeclinePanel(){
        return new Panel("modify request") {
            @Override
            public void execute() {
                // TODO : hatami
            }
        };
    }

    protected void showHelp() {

    }
}
