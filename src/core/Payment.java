package core;

public class Payment {

    private final String type;
    private final String accountId;
    private final int amount;

    public Payment(String type, String accountId, int amount) {
        this.type = type;
        this.accountId = accountId;
        this.amount = amount;
    }

    public Payment(String type, String accountId, String amount) {
        this(type, accountId, Integer.parseInt(amount));
    }

    public boolean isDebtor() {
        return this.type.equals("debtor");
    }

    public String getType() {
        return type;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getAmount() {
        return amount;
    }

}
