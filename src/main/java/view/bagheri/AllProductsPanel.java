package view.bagheri;

public class AllProductsPanel extends Panel {
    private static AllProductsPanel instance = new AllProductsPanel();


    private AllProductsPanel() {
        super("allProductPanel");
    }

    @Override
    protected void execute() {
        show();
        String inputCommand;
        while (!(inputCommand = scanner.nextLine()).equals("back")) {
            if ((matcher = getMatcher("", inputCommand)) != null) {

            } else if((matcher = getMatcher("", inputCommand)) != null) {

            } else {
                System.out.println("invalid command!");
            }
        }
    }

    @Override
    protected void show() {

    }
}