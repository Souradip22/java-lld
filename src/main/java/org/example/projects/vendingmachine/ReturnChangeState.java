package org.example.projects.vendingmachine;

public class ReturnChangeState implements VendingMachineState{
    private final VendingMachine machine;

    public ReturnChangeState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Product already dispensed... Please collect the " +
                "change");
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Product already dispensed... Please collect the \" +\n" +
                "                \"change");
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("Product already dispensed... Please collect the " +
                "change");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Product already dispensed... Please collect the " +
                "change");
    }

    @Override
    public void returnChange() {
        double change =
                machine.getTotalPrice() - machine.getSelectedProduct().getPrice();
        if (change > 0) {
            System.out.println("Change returned: $" + change);
        } else {
            System.out.println("No change to return.");
        }

        machine.resetPayment();
        machine.resetSelectedProduct();
        machine.setState(machine.getIdleState());
    }
}
