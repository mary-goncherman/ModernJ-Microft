package GenLang_Stock_Trade_10;

import java.util.function.Consumer;

public class LambdaOrderBuilder {

    private Order order = new Order();

    public static Order order(Consumer<LambdaOrderBuilder> consumer) {
        LambdaOrderBuilder lambdaOrderBuilder = new LambdaOrderBuilder();
        consumer.accept(lambdaOrderBuilder);
        return lambdaOrderBuilder.order;
    }

    public void forCustomer(String customer) {
        order.setCustomer(customer);
    }

    public void buy(Consumer<LambdaTradeBuilder> consumer) {
        trade(consumer, Trade.Type.BUY);
    }

    public void sell(Consumer<LambdaTradeBuilder> consumer) {
        trade(consumer, Trade.Type.SELL);
    }

    private void trade(Consumer<LambdaTradeBuilder> consumer, Trade.Type type) {

        LambdaTradeBuilder tradeBuilder = new LambdaTradeBuilder();
        tradeBuilder.trade.setType(type);
        consumer.accept(tradeBuilder);
        order.addTrade(tradeBuilder.trade);

    }


}
