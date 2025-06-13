package org.example.projects.vendingmachine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {
    private Map<Product, Integer> productQuantities;

    public Inventory() {
        this.productQuantities = new ConcurrentHashMap<>();
    }

    public void addProductQuantity(Product prod, int quantity){
        productQuantities.put(prod,
                productQuantities.getOrDefault(prod, 0) + quantity);
    }

    public void reduceProductQuantity(Product prod, int quantity) {
        if (productQuantities.containsKey(prod)) {
            Integer currentQuantity = productQuantities.get(prod);

            if (currentQuantity < quantity) {
                System.out.println("Insufficient stock for product: " + prod.getName() +
                        ". Available: " + currentQuantity + ", Requested: " + quantity + ".");
            } else if (currentQuantity.equals(quantity)) {
                removeProduct(prod);
                System.out.println("Product: " + prod.getName() + " completely removed from inventory.");
            } else {
                productQuantities.put(prod, currentQuantity - quantity);
                System.out.println("Reduced " + quantity + " units of " + prod.getName() +
                        ". Remaining: " + (currentQuantity - quantity) + ".");
            }

        } else {
            System.out.println("Product: " + prod.getName() + " is not present in the inventory.");
        }
    }

    public void removeProduct(Product prod){
        if (productQuantities.containsKey(prod)){
            productQuantities.remove(prod);
        } else {
            System.out.println("Product is not present in the inventory");
        }
    }

    public Integer getProductQuantity(Product prod){
        if (productQuantities.containsKey(prod)){
            return productQuantities.get(prod);
        }
        throw  new RuntimeException("Product not present in inventory");
    }

    public boolean isProductAvailable(Product prod){
        return productQuantities.getOrDefault(prod, 0) > 0;
    }

    public void updateProductQuantity(Product product, int i) {
        productQuantities.put(product, i);
    }
}
