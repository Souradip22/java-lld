package org.example.projects.vendingmachine;

public class DispenseState implements VendingMachineState{
    private final VendingMachine machine;

    public DispenseState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Product already selected. please collect");
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Product already selected. please collect");
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("Product already selected. please collect");
    }

    @Override
    public void dispenseProduct() {
        Product product = machine.getSelectedProduct();
        machine.getInventory().updateProductQuantity(product,
                machine.getInventory().getProductQuantity(product) - 1);
        System.out.println("Product dispensed: " + product.getName());
        machine.setState(machine.getReturnChangeState());
    }

    @Override
    public void returnChange() {
        System.out.println("Please collect the dispensed product first.");
    }
}
