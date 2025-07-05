package org.example.projects.splitwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalanceSheet {
    Map<User, Map<User, Double>> balance = new HashMap<>();
    /*
    {
        "user1" : { "user2" : 100, "user5" : 50 },
        "user2" : { "user3" : 40}
     }
     note: user1 owes user2 100 and user5 50 rupees
     */
    public void addDebt(User paidBy, List<Split> splits){

        for (Split split: splits) {
            User targetUser = split.getUser();
            // No Debt to self
            if (paidBy.equals(targetUser)){
                continue;
            }
            double amount = split.getAmount();

            if (balance.containsKey(targetUser)){

                Map<User, Double> existingDebtMap = balance.get(targetUser);

                if (existingDebtMap.containsKey(paidBy)){
                    existingDebtMap.put(paidBy,
                            existingDebtMap.get(paidBy) + amount);
                } else {
                    existingDebtMap.put(paidBy, amount);
                }

            } else {
                balance.put(targetUser, new HashMap<>(Map.of(paidBy, amount)));
            }
        }

    }
    public void settleDebt(User paidBy, User targetUser, double amount){

        Double val = balance.get(paidBy).get(targetUser);
        if (val != amount){
            throw new RuntimeException("Settlement amount is not same as " +
                    "amount owes");
        }
        Map<User, Double> userDoubleMap = balance.get(paidBy);
        userDoubleMap.remove(targetUser);
        System.out.println(paidBy.getName() + " paid an amount of " + amount + " to " + targetUser.getName() +" and settled debt" );

    }

    public void showBalanceSheet(){
        System.out.println("====== PRINTING BALANCE SHEET ======");
        for (Map.Entry<User, Map<User, Double>> userMapEntry : balance.entrySet()) {
            User user = userMapEntry.getKey();
            Map<User, Double> userMapEntryValue = userMapEntry.getValue();
//            System.out.println(user.getName() + " details:");
            for (Map.Entry<User, Double> userDoubleEntry : userMapEntryValue.entrySet()) {
                System.out.println(user.getName() + " owes amount " +
                        "of " + userDoubleEntry.getValue() + " to user " + userDoubleEntry.getKey().getName());
            }
        }
        System.out.println("====== COMPLETED ======");

    }

    public void printBalanceMap() {
        System.out.println("{");
        for (Map.Entry<User, Map<User, Double>> entry : balance.entrySet()) {
            User user = entry.getKey();
            Map<User, Double> innerMap = entry.getValue();
            System.out.print("  \"" + user.getName() + "\" : { ");
            List<String> innerEntries = new ArrayList<>();
            for (Map.Entry<User, Double> innerEntry : innerMap.entrySet()) {
                innerEntries.add("\"" + innerEntry.getKey().getName() + "\" : " + innerEntry.getValue());
            }
            System.out.print(String.join(", ", innerEntries));
            System.out.println(" },");
        }
        System.out.println("}");
    }

}
