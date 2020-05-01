package view.nedaei.buyermenu;

import view.bagheri.Panel;

public class PurchasePanel extends Panel {
    private static PurchasePanel instance;

    private PurchasePanel() {
        super("purchase panel");
    }

    public static PurchasePanel getInstance() {
        if (instance == null) {
            instance = new PurchasePanel();
        }
        return instance;
    }

    @Override
    protected void execute() {
        runReceiverInformationPanel();
        runDiscountCodePanel();
        runPaymentPanel();
    }

    private void runReceiverInformationPanel() {
        new Panel("receiver information panel") {

            @Override
            public void execute() {

            }

        }.execute();
    }

    private void runDiscountCodePanel() {
        new Panel("discount code panel") {

            @Override
            public void execute() {
                super.execute();
            }

        }.execute();
    }

    private void runPaymentPanel() {
        new Panel("payment panel") {

            @Override
            protected void execute() {
                super.execute();
            }

        }.execute();
    }

}
