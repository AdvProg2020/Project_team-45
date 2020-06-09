package view.hatemi.adminMenus;

import controller.RequestController;
import controller.managers.Printer;
import controller.userControllers.UserController;
import model.Market;
import model.request.Request;
import view.ManagingMenu;
import view.Panel;

public class RequestsManagingMenu extends ManagingMenu {

    public RequestsManagingMenu() {
        super("requests managing menu");
        this.manager = RequestController.getInstance();
        this.managingObject = "Requests";
        submenus.put("details (\\S+)", createOneItemDisplayPanel("request", (Printer) manager));
        submenus.put("(accept|decline) (\\S+)", getAcceptDeclinePanel());
    }

    private Panel getAcceptDeclinePanel(){
        return new Panel("modify request") {
            @Override
            public void execute() {
                Request modifyingRequest = Market.getInstance().getRequestById(matcher.group(2));
                if (modifyingRequest == null) {
                    System.out.println("wrong id");
                    return;
                }
                if (matcher.group(1).equals("accept"))
                    modifyingRequest.accept();
                else
                    modifyingRequest.decline();
            }
        };
    }

    @Override
    public void execute() {
        if (!UserController.isAdminLoggedIn()) {
            back();
            return;
        }
        super.execute();
    }

    @Override
    protected void showHelp() {
        super.showHelp();
    }
}
