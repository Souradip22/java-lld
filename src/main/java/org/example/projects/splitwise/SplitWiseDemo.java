package org.example.projects.splitwise;

import java.util.List;

public class SplitWiseDemo {
    public static void main(String[] args) {
        SplitWiseSystem instance = SplitWiseSystem.getInstance();

        User user1 = instance.createUser("user1", "Alice", "alice@example.com", 
                "1234567890");
        User user2 = instance.createUser("user2", "Bob", "bob@example.com", "9876543210");
        User user3 = instance.createUser("user3", "Charlie", "charlie@example.com", "5556667777");
        User user4 = instance.createUser("user4", "David", "david@example.com", "4443332222");
        
        Group group1 = instance.createGroup("Goa Trip", "goa trip details",
                List.of(user1, user2, user3, user4));

        Expense lunchExpense = instance.addExpense("Lunch", group1, user1, 1000, SplitType.EXACT,
                List.of(200.00, 300.0, 200.0, 300.0));
        lunchExpense.showDetails();
        instance.showBalanceSheet();


        Expense gameExpense = instance.addExpense("Gaming Experience", group1,
                user4, 2300,
                SplitType.EQUAL,
                null);
        gameExpense.showDetails();
        instance.showBalanceSheet();

        instance.settleUp(user4, user1, 300.0);
        instance.showBalanceSheet();



    }
}
