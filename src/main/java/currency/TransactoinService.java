package currency;

import static java.util.stream.Collectors.groupingBy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactoinService {

    public static void main (String[] args) {

        Currency rub = new Currency("RUB");
        Currency usd = new Currency("USD");

        List<Transaction> transactionList = new ArrayList<Transaction>();
        transactionList.add(new Transaction(rub, 9518));
        transactionList.add(new Transaction(usd, 958));
        transactionList.add(new Transaction(rub, 9188));
        transactionList.add(new Transaction(rub, 1118));
        transactionList.add(new Transaction(usd, 9518));
        transactionList.add(new Transaction(usd, 8888));
        transactionList.add(new Transaction(rub, 18));

        Map<Currency, List<Transaction>> transactionsByCurrency = getTransactionsByCurrency(transactionList);
        System.out.println(transactionsByCurrency);
        Map<Currency, List<Transaction>> transactionsByCurrencySuper = getTransactionsByCurrencySuper(transactionList);
        System.out.println(transactionsByCurrencySuper);

    }

    public static Map<Currency, List<Transaction>> getTransactionsByCurrency(List<Transaction> transactions) {
        Map<Currency, List<Transaction>> transactionsByCurrency = new HashMap<Currency, List<Transaction>>();
        for (Transaction transaction : transactions) {
            if (transaction.getPrice() > 1000) {
                Currency currency = transaction.getCurrency();
                List<Transaction> transactionsForCurrency = transactionsByCurrency.get(currency);
                if (transactionsForCurrency == null) {
                    transactionsForCurrency = new ArrayList<Transaction>();
                    transactionsByCurrency.put(currency, transactionsForCurrency);
                }
                transactionsForCurrency.add(transaction);
            }
        }
        return transactionsByCurrency;
    }

    public static Map<Currency, List<Transaction>> getTransactionsByCurrencySuper(List<Transaction> transactions) {

        Map<Currency, List<Transaction>> transactionsByCurrency =
                transactions.stream()
                .filter((Transaction t) -> t.getPrice() > 1000)
                .collect(groupingBy(Transaction::getCurrency));

        return transactionsByCurrency;
    }
}


