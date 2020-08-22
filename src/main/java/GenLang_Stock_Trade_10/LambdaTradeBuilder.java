package GenLang_Stock_Trade_10;

import java.util.function.Consumer;

public class LambdaTradeBuilder {

    public Trade trade = new Trade();

    public void quantity(int quantity) {
        trade.setQuantity(quantity);
    }

    public void price(int price) {
        trade.setPrice(price);
    }

    public void stock(Consumer<LambdaStockBuilder> consumer) {

        LambdaStockBuilder lambdaStockBuilder = new LambdaStockBuilder();
        consumer.accept(lambdaStockBuilder);
        trade.setStock(lambdaStockBuilder.stock);

    }




}
