package org.example.projects.vendingmachine;

public class ReadyState implements VendingMachineState{
    private final VendingMachine machine;

    public ReadyState(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public void selectProduct(Product product) {
        System.out.println("Product already selected. Please make payment.");
    }

    @Override
    public void insertCoin(Coin coin) {
        machine.addCoin(coin);
        System.out.println("Coin inserted: " + coin.getValue());
        checkPaymentStatus();
    }

    @Override
    public void insertNote(Note note) {
        machine.addNote(note);
        System.out.println("Note inserted: " + note.getValue());
        checkPaymentStatus();
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Product make payment first");
    }

    @Override
    public void returnChange() {
        System.out.println("Product make payment first");
    }

    private void checkPaymentStatus() {
        System.out.println("Payment status check: inserted amount -> " + machine.getTotalPrice() + " product price: "+ machine.getSelectedProduct().getPrice());
        if (machine.getTotalPrice() >= machine.getSelectedProduct().getPrice()) {
            machine.setState(machine.getDispenseState());
        }
    }
}
