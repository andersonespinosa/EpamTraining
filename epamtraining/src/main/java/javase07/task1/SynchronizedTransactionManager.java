package javase07.task1;

import java.math.BigDecimal;

public class SynchronizedTransactionManager {

    public void transferMoney(BankAccount sender, BankAccount recipient, Transaction transaction) {
        synchronized (this) {
            BigDecimal senderBalance = sender.getBalance();
            BigDecimal recipientBalance = recipient.getBalance();
            senderBalance = senderBalance.subtract(transaction.getAmountOfMoney());
            recipientBalance = recipientBalance.add(transaction.getAmountOfMoney());
        }
    }
}
