package view.bagheri;

import controller.ProductController;
import controller.UserController;

public class CommentingPanel extends Panel {
    private static final CommentingPanel instance = new CommentingPanel();
    private final ProductController productController;
    private final UserController userController;

    private CommentingPanel() {
        super("comment panel");
        productController = ProductController.getInstance();
        userController = UserController.getInstance();
    }

    public static CommentingPanel getInstance() {
        return instance;
    }

    @Override
    public void execute() {
        show();
        String inputCommand;
        while (!(inputCommand = scanner.nextLine()).equals("back")) {
            if (inputCommand.equals("Add comment")) {
                if (userController.isLoggedIn()) {
                    addComment();
                } else {
                    System.out.println("You are not logged in");
                }
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    @Override
    protected void show() {
        System.out.println("score: " + productController.getAverageScore());
        for (String productComment : productController.getProductComments()) {
            System.out.println(productComment);
        }
    }

    private void addComment() {
        String title;
        String content;
        String inputCommand;
        while (!(inputCommand = scanner.nextLine()).matches("Title:.+")) {
            if(inputCommand.equals("back"))
                return;
            System.out.println("invalid command!");
        }
        title = inputCommand;
        while (!(inputCommand = scanner.nextLine()).matches("Content:.+")) {
            if(inputCommand.equals("back"))
                return;
            System.out.println("invalid command!");
        }
        content = inputCommand;
        productController.addComment(title, content);
    }
}