package core;

public class DataManager {

    public static Account[] listAccounts() {
        String[] lines = FileUtils.readLines(Settings.ACCOUNT_FILE_PATH);
        Account[] accounts = new Account[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split("\t");
            if (parts.length < 2) {
                throw new Error("Invalid Account File.");
            }
            accounts[i] = new Account(parts[0], parts[1]);
        }
        return accounts;
    }

    public static Payment[] listPayments() {
        String[] lines = FileUtils.readLines(Settings.PAYMENT_FILE_PATH);
        Payment[] payments = new Payment[lines.length];
        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split("\t");
            if (parts.length < 3) {
                throw new Error("Invalid Account File.");
            }
            payments[i] = new Payment(parts[0].toLowerCase(), parts[1], parts[2]);
        }
        return payments;
    }

    public static void updateAccounts(Account[] accounts) {
        int size = accounts.length;
        String[] lines = new String[size];
        for (int i = 0; i < lines.length; i++) {
            Account account = accounts[i];
            lines[i] = account.getAccountId() + "\t" + account.getAmount();
        }
        FileUtils.write(Settings.ACCOUNT_FILE_PATH, lines, false);
    }

    public static Account findAccountById(Account[] accounts, String id) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(id)) {
                return account;
            }
        }
        return null;
    }

    public static Payment findDebtorPayment(Payment[] payments) {
        for (Payment payment : payments) {
            if (payment.isDebtor()) {
                return payment;
            }
        }
        return null;
    }

    public static int sumCreditorsAmount(Payment[] payments) {
        int sum = 0;
        for (Payment payment : payments) {
            if (payment.isDebtor()) continue;
            sum += payment.getAmount();
        }
        return sum;
    }

    public static void addTransaction(Payment payment, Account debtor) {
        int amount = payment.getAmount();
        String source = payment.getAccountId();
        String dest = debtor.getAccountId();
        String message = source + "\t" + dest + "\t" + amount + "\n";
        FileUtils.write(Settings.TRANSACTIONS_FILE_PATH, message, true);
    }

}





