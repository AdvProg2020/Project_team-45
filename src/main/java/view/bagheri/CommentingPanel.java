package view.bagheri;

import controller.ProductController;

public class CommentingPanel extends Panel {
    private static final CommentingPanel instance = new CommentingPanel();
    private final ProductController productController;

    private CommentingPanel() {
        super("comment panel");
        productController = ProductController.getInstance();
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
                addComment();
                break;
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