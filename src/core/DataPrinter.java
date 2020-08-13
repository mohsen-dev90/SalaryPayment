package core;

import java.util.List;

public class DataPrinter {

    public static void printAccounts(List<Account> accounts) {
        System.out.println();
        System.out.println("############# Accounts List #############\n");
        for (Account account : accounts) {
            System.out.println("Account " + account.getAccountId() + " has " + account.getAmount());
        }
        System.out.println("\n############# End Accounts List #############");
        System.out.println();
    }

    public static void printPayments(List<Payment> payments) {
        System.out.println();
        System.out.println("############# Payments List #############\n");
        for (Payment payment : payments) {
            System.out.println("Payment " + "type:" + payment.getType() + ", account:" + payment.getAccountId() + ", amount:" + payment.getAmount());
        }
        System.out.println("\n############# End Payments List #############");
        System.out.println();
    }

}
