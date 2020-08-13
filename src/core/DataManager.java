package core;

import java.util.List;
import java.util.ArrayList;

public class DataManager {

    public static List<Account> listAccounts() {
        List<String> lines = FileUtils.readLines(Settings.ACCOUNT_FILE_PATH);
        List<Account> accounts = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("\t");
            if (parts.length < 2) {
                throw new Error("Invalid Account File.");
            }
            accounts.add(new Account(parts[0], parts[1]));
        }
        return accounts;
    }

    public static List<Payment> listPayments() {
        List<String> lines = FileUtils.readLines(Settings.PAYMENT_FILE_PATH);
        List<Payment> payments = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split("\t");
            if (parts.length < 3) {
                throw new Error("Invalid Account File.");
            }
            payments.add(new Payment(parts[0].toLowerCase(), parts[1], parts[2]));
        }
        return payments;
    }

    public static void updateAccounts(List<Account> accounts) {
        int size = accounts.size();
        String[] lines = new String[size];
        for (int i = 0; i < lines.length; i++) {
            Account account = accounts.get(i);
            lines[i] = account.getAccountId() + "\t" + account.getAmount();
        }
        FileUtils.write(Settings.ACCOUNT_FILE_PATH, lines, false);
    }

    public static Account findAccountById(List<Account> accounts, String id) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(id)) {
                return account;
            }
        }
        return null;
    }

    public static Payment findDebtorPayment(List<Payment> payments) {
        for (Payment payment : payments) {
            if (payment.isDebtor()) {
                return payment;
            }
        }
        return null;
    }

    public synchronized static int sumCreditorsAmount(List<Payment> payments) {
        int sum = 0;
        for (Payment payment : payments) {
            if (payment.isDebtor()) continue;
            sum += payment.getAmount();
        }
        return sum;
    }

    public synchronized static void addTransaction(Payment payment, Account debtor) {
        int amount = payment.getAmount();
        String source = payment.getAccountId();
        String dest = debtor.getAccountId();
        String message = source + "\t" + dest + "\t" + amount + "\r\n";
        FileUtils.write(Settings.TRANSACTIONS_FILE_PATH, message, true);
    }

}
