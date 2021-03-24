package services.operation;

public class BalanceCard implements Balance{
    private double amount;
    private String currency;

    public BalanceCard(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public String getCurrency() {
        return currency;
    }
}
