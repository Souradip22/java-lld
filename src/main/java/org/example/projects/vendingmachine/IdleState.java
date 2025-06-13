package org.example.projects.vendingmachine;

public class IdleState implements VendingMachineState{
    private final VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void selectProduct(Product prod) {
        if (vendingMachine.getInventory().isProductAvailable(prod)){
            System.out.println("Selected product: " + prod.getName());
            vendingMachine.setSelectedProduct(prod);
            this.vendingMachine.setState(vendingMachine.getReadyState());
        }
    }

    @Override
    public void insertCoin(Coin coin) {
        System.out.println("Please select a product first");
    }

    @Override
    public void insertNote(Note note) {
        System.out.println("Please select a product first");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Please select a product first");
    }

    @Override
    public void returnChange() {
        System.out.println("Please select a product first");
    }
}
