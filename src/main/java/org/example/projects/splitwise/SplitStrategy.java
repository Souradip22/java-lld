package org.example.projects.splitwise;

import java.util.List;
import java.util.stream.IntStream;

public interface SplitStrategy {
    List<Split> calculateSplit(double amount, List<User> users,
                               List<Double> values);
}

class EqualSplitStrategy implements SplitStrategy{
    @Override
    public List<Split> calculateSplit(double amount, List<User> users, List<Double> values) {
        double splitAmount = amount / users.size();
        return users.stream().map(user -> new Split(user, splitAmount)).toList();
    }
}
class ExactSplitStrategy implements SplitStrategy{
    @Override
    public List<Split> calculateSplit(double amount, List<User> users, List<Double> values) {
        if (values.size() != users.size()){
            throw new IllegalArgumentException("values and users size " +
                    "different");
        }
        double totalSplitAmount = values.stream().reduce(0.0, Double::sum);
        if (totalSplitAmount != amount ){
            throw  new IllegalArgumentException("Total split amount is not " +
                    "same as actual total amount");
        }
//        List<Split> splits = new ArrayList<>();
//        for (int i = 0; i < users.size(); i++){
//            splits.add(new Split(users.get(i), values.get(i)));
//        }
//        return null;
        return IntStream.range(0, users.size())
                .mapToObj(ind -> new Split(users.get(ind), values.get(ind)))
                .toList();
    }
}
class PercentageSplitStrategy implements SplitStrategy{
    @Override
    public List<Split> calculateSplit(double amount, List<User> users, List<Double> values) {
        if (values == null || users == null){
            throw new IllegalArgumentException("users or values are null");
        }
        if (values.size() != users.size()){
            throw new IllegalArgumentException("values and users size " +
                    "different");
        }
        double totalSplitAmount = values.stream().reduce(0.0, Double::sum);
        if (totalSplitAmount != 100.00 ){
            throw  new IllegalArgumentException("percentage split is not " +
                    "equal to 100% ");
        }
        return IntStream.range(0, users.size())
                .mapToObj(ind -> new Split(users.get(ind),
                        (amount * values.get(ind)) / 100))
                .toList();
    }
}
