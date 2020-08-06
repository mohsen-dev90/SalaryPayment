package core;

public class Account {

    private String accountsId;
    private int amount;

    public Account(String accountsId, int amount){
        this.accountsId = accountsId;
        this.amount = amount;
    }

    public Account(String accountsId, String amount){
        this(accountsId,Integer.valueOf(amount));
    }

    public void invest(int amount){
        this.amount += amount;
    }

    public void pay(int amount)throws NotEnoughAmountsException {
        if (amount > this.amount) {
            String error = String.format("can not pay %d from %d ", amount, this.amount);
            throw new NotEnoughAmountsException(error);
        }
        this.amount = this.amount - amount;
    }

    public void pay(int amount, Account target) throws NotEnoughAmountsException {
        this.pay(amount);
        target.invest(amount);
    }

    public String getAccountsId() {
        return accountsId;
    }

    public int getAmount(){
        return amount;
    }






}
