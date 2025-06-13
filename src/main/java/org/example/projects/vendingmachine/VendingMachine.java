package org.example.projects.vendingmachine;

public class VendingMachine {
    private static VendingMachine instance;
    private VendingMachineState currentState;
    private VendingMachineState idleState;
    private VendingMachineState readyState;
    private VendingMachineState dispenseState;
    private VendingMachineState returnChangeState;
    private Inventory inventory;
    private Product selectedProduct;
    private double totalPrice;

    public VendingMachine() {
        this.inventory = new Inventory();

        this.idleState = new IdleState(this);
        this.readyState = new ReadyState(this);
        this.dispenseState = new DispenseState(this);
        this.returnChangeState = new ReturnChangeState(this);
        this.currentState = idleState;

        this.selectedProduct = null;
        this.totalPrice  = 0.0;
    }

    public static VendingMachine getInstance(){
        if (instance == null){
            instance = new VendingMachine();
        }
        return instance;
    }

    public Product addProduct(String name, double price, int quantity) {
        Product product = new Product(name, price);
        inventory.addProductQuantity(product, quantity);
        return product;
    }

    public void insertCoin(Coin coin) {
        currentState.insertCoin(coin);
    }

    public void insertNote(Note note) {
        currentState.insertNote(note);
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
    }

    public void returnChange() {
        currentState.returnChange();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
        this.setState(readyState);
    }
    public void setState(VendingMachineState state){
        this.currentState = state;
    }

    public VendingMachineState getCurrentState() {
        return currentState;
    }

    public VendingMachineState getIdleState() {
        return idleState;
    }

    public VendingMachineState getReadyState() {
        return readyState;
    }

    public VendingMachineState getDispenseState() {
        return dispenseState;
    }

    public VendingMachineState getReturnChangeState() {
        return returnChangeState;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void addCoin(Coin coin){
        totalPrice += coin.getValue();
    }

    public void addNote(Note note){
        totalPrice += note.getValue();
    }

    public void resetPayment(){
        totalPrice = 0.0;
    }


    public void resetSelectedProduct() {
        this.selectedProduct = null;
    }
}
