package GenLang_Stock_Trade_10;

public class Main {

    public static void main(String[] args) {

        Order order = MethodChainingOrderBuilder
                .forCustomer("BigBank")
                .buy(80)
                .stock("IBM")
                    .on("NYSE")
                .at(125.00)
                .sell(50)
                .stock("GOOGLE")
                    .on("NASDAQ")
                .at(375.00)
                .end();

        System.out.println(order.getValue());


        Order lambdaOrder = LambdaOrderBuilder.order(o ->
                {
                    o.forCustomer("BigBank");
                    o.buy(t ->
                            {
                               t.quantity(80);
                               t.price(125);
                               t.stock(s ->
                                       {
                                           s.symbol("IBM");
                                           s.market("NYSE");
                                       }
                                       );
                            }
                            );
                    o.sell(t ->
                            {
                                t.quantity(50);
                                t.price(375);
                                t.stock(s ->
                                        {
                                            s.symbol("GOOGLE");
                                            s.market("NASDAQ");
                                        }
                                );
                            }
                            );
                }
            );

        System.out.println(lambdaOrder.getValue());


    }


}
