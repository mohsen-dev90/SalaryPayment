import core.*;

public class Program {

    public static void main(String[] args) {

        Account[] accounts = DataManager.listAccounts();
        Payment[] payments = DataManager.listPayments();
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

        for (Payment payment : payments) {
            if (payment.isDebtor()) continue;
            Account account = DataManager.findAccountById(accounts, payment.getAccountId());

            if (account.getAccountId().equals(debtor.getAccountId())) continue;
            try {
                debtor.pay(payment.getAmount(), account);
                DataManager.addTransaction(payment, debtor);
            }
            catch(NotEnoughAmountsException e) {
                System.out.println("NotEnoughAmountException");
            }
        }

        DataManager.updateAccounts(accounts);
        DataPrinter.printAccounts(accounts);
    }

}
