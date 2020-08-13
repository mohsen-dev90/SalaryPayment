import core.*;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import static core.Settings.THREAD_COUNT;

public class Program {

    public static void main(String[] args) {

        Bootstrap bootstrap = new Bootstrap(
                Settings.ACCOUNT_FILE_PATH,
                Settings.PAYMENT_FILE_PATH,
                Settings.TRANSACTIONS_FILE_PATH
        );

        bootstrap.init();

        List<Account> accounts = DataManager.listAccounts();
        List<Payment> payments = DataManager.listPayments();

        int paymentsSize = payments.size();
        int listCount = (paymentsSize - 1) / THREAD_COUNT;
        int restCount = (paymentsSize - 1) % THREAD_COUNT;

        DataPrinter.printAccounts(accounts);
        DataPrinter.printPayments(payments);

        Payment debtorPayment = DataManager.findDebtorPayment(payments);
        int creditorsSum = DataManager.sumCreditorsAmount(payments);
        Account debtor = DataManager.findAccountById(accounts, debtorPayment.getAccountId());
        System.out.println("DebtorFund: " + debtor.getAmount());
        System.out.println("CreditorsSum: " + creditorsSum);
        if (debtor.getAmount() < creditorsSum) {
            System.out.println("You have not enough money for all payments.");
            return;
        }

        payments.remove(0);
        System.out.println("REST COUNT IS " + restCount);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            int count = i * listCount + listCount;
            int startIndex = i * listCount;
            if (i == THREAD_COUNT - 1) {
                count += restCount;
            }
            pool.execute(new PaymentService(debtor, accounts, payments.subList(startIndex, count), new OnPaymentFinish() {
                @Override
                public void finish(List<Account> accounts) {
                    DataManager.updateAccounts(accounts);
                    DataPrinter.printAccounts(accounts);
                }
            }));
        }
        pool.shutdown();
    }

}
