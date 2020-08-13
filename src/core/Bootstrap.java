package core;

public class Bootstrap {

    private final String paymentsPath;
    private final String accountsPath;
    private final String transactionsPath;

    public Bootstrap(String accountsPath, String paymentsPath, String transactionsPath) {
        this.paymentsPath = paymentsPath;
        this.accountsPath = accountsPath;
        this.transactionsPath = transactionsPath;
    }

    public void init() {
        initAccounts();
        initPayments();
        FileUtils.createFile(transactionsPath);
    }

    private void initAccounts() {
        StringBuilder data = new StringBuilder("1.10.100.1\t5000000\r\n");
        for (int i = 0; i < 10000; i++) {
            data.append("1.20.100.").append(i).append("\t500\r\n");
        }
        FileUtils.write(this.accountsPath, data.toString(), false);
    }

    private void initPayments() {
        StringBuilder data = new StringBuilder("debtor\t1.10.100.1\t5000000\r\n");
        for (int i = 0; i < 10000; i++) {
            data.append("creditor\t1.20.100.").append(i).append("\t100\r\n");
        }
        FileUtils.write(this.paymentsPath, data.toString(), false);
    }

}
