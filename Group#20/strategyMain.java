interface PaymentStrategy<T> {
    void pay(T item);
}

class WalletStrategy<T> implements PaymentStrategy<T> {
    public void pay(T amout) {
        System.out.println(" Оплата кошельком " + amout);
    }
}

class CardStrategy<T> implements PaymentStrategy<T> {
    public void pay(T bank) {
        System.out.println(" Перенаправляем в банк " + bank);
    }
}

class PaymentProcessor<T> {
    public PaymentStrategy<T> strategy;

    public PaymentProcessor(PaymentStrategy<T> strategy) {
        this.strategy = strategy;
    }
}

public class аа {
    public static void main(String[] args) {
        WalletStrategy<Integer> wallet = new WalletStrategy<>();
        CardStrategy<String> card = new CardStrategy<>();

        PaymentProcessor<String> processor = new PaymentProcessor(wallet);

        processor.strategy.pay("" + 42);

        processor.strategy = card;
        processor.strategy.pay("Приколов");

    }
}