package core;

import java.util.List;

public class PaymentService implements Runnable {

    private final Account debtor;
    private final List<Payment> payments;
    private final List<Account> accounts;
    private final OnPaymentFinish callback;

    public PaymentService(Account debtor, List<Account> accounts, List<Payment> payments, OnPaymentFinish callback) {
        this.debtor = debtor;
        this.payments = payments;
        this.accounts = accounts;
        this.callback = callback;
    }

    @Override
    public void run() {
        for (Payment payment : payments) {
            Account account = DataManager.findAccountById(this.accounts, payment.getAccountId());
            if (account.getAccountId().equals(this.debtor.getAccountId())) continue;
            try {
                debtor.pay(payment.getAmount(), account);
                DataManager.addTransaction(payment, debtor);
            } catch (NotEnoughAmountsException e) {
                System.out.println("NotEnoughAmountException");
            }
        }
        this.callback.finish(accounts);
    }


}
