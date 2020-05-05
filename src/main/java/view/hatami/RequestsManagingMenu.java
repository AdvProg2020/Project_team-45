package view.hatami;

import controller.RequestController;
import view.bagheri.Panel;

public class RequestsManagingMenu extends ManagingMenu {

    public RequestsManagingMenu() {
        super("requests managing menu");
        this.printer = RequestController.getInstance();
        submenus.put("details (\\S+)", createOneItemDisplayPanel("request", printer));
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
