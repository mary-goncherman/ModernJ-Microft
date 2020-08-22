package Streams_trader_transactions;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class Result {

    private static Trader raoul = new Trader("Raoul", "Cambridge");
    private static Trader mario = new Trader("Mario","Milan");
    private static Trader alan = new Trader("Alan","Cambridge");
    private static Trader brian = new Trader("Brian","Cambridge");

    private static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950) );


    public static void main(String[] args) {

        //Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).
        List<Transaction> transactions_2011_sorted =
                transactions.stream().filter(action -> action.getYear() == 2011)
                        .sorted(comparing(Transaction::getValue)).collect(Collectors.toList());
        System.out.println(transactions_2011_sorted);

        //Вывести список неповторяющихся городов, в которых работают трейдеры.
        List<String> distinctTraderCity = transactions.stream()
                .map(action -> action.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(distinctTraderCity);

        //Найти всех трейдеров из Кембриджа и отсортировать их по именам.
        List<Trader> sortedCambrigeTraders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(sortedCambrigeTraders);

        //Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.
        Optional<String> traderNames = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .sorted(comparing(Trader::getName))
                .map(Trader::getName)
                .reduce((a,b) -> a + " " + b);
        System.out.println(traderNames);

        //Выяснить, существует ли хоть один трейдер из Милана
        boolean isAniMilanTrader = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCity().equals("Milan"));
        System.out.println("There is trader from Milan: " + isAniMilanTrader);

        //Вывести суммы всех транзакций трейдеров из Кембриджа
        List<Integer> cambridgeTradersSumms = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .collect(Collectors.toList());
        System.out.println(cambridgeTradersSumms);

        //Какова максимальная сумма среди всех транзакций?
        Optional<Integer> maxTransactiomSumm = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(maxTransactiomSumm);

        //Найти транзакцию с минимальной суммой.
        Optional<Transaction> transactionWithMinSumm = transactions.stream()
                .reduce((tr1, tr2) -> tr1.getValue() < tr2.getValue() ? tr1 : tr2);
        Optional<Transaction> transactionWithMinSumm2 = transactions.stream()
                .sorted(comparing(Transaction::getValue))
                .findFirst();
        System.out.println(transactionWithMinSumm);
        System.out.println(transactionWithMinSumm2);
    }



}
