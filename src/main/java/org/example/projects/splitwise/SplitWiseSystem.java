package org.example.projects.splitwise;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SplitWiseSystem {
    private Map<String, Group> groups;
    private Map<String, Expense> expenses;
    private BalanceSheet balanceSheet;
    private SplitStrategy splitStrategy;

    private SplitWiseSystem(){
        groups = new ConcurrentHashMap<>();
        expenses = new ConcurrentHashMap<>();
        balanceSheet = new BalanceSheet();
    }
    private static SplitWiseSystem instance;
    public static SplitWiseSystem getInstance(){
        if (instance == null){
            instance = new SplitWiseSystem();
        }
        return instance;
    }

    public void setSplitStrategy(SplitStrategy strategy){
        this.splitStrategy = strategy;
    }

    public User createUser(String id, String name, String email, String phone){
        User newUser = new User(id, name, email, phone);
        return newUser;
    }

    public Group createGroup(String name, String description, List<User> users){
        Group newGroup = new Group(name, description, users);
        groups.put(newGroup.getId(), newGroup);
        return newGroup;
    }

    private SplitStrategy getSplitStrategy(SplitType type){
        if (type.equals(SplitType.EQUAL)){
            return new EqualSplitStrategy();
        } else if (type.equals(SplitType.EXACT)) {
            return new ExactSplitStrategy();
        } else if (type.equals(SplitType.PERCENTAGE)) {
            return new PercentageSplitStrategy();
        }
        throw new IllegalArgumentException("Error Split Type is not valid: " + type);
    }

    public Expense addExpense(String name, Group group, User paidBy,
                           double totalAmount, SplitType splitType,
                           List<Double> values){
        SplitStrategy splitStrategy = getSplitStrategy(splitType);
        List<Split> splits = splitStrategy.calculateSplit(totalAmount, group.getUsers(), values);

        Expense newExpense = new Expense(name, group, paidBy, totalAmount,
                splits);
        expenses.put(newExpense.getId(), newExpense);
        group.addExpense(newExpense);
        balanceSheet.addDebt(paidBy, splits);
        return newExpense;
    }


    public void showBalanceSheet() {
        balanceSheet.printBalanceMap();
    }

    public void settleUp(User payer, User payee, double amount) {
        balanceSheet.settleDebt(payer, payee, amount);
    }
}
