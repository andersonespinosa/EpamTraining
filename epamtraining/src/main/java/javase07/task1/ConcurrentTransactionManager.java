package javase07.task1;

import java.math.BigDecimal;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentTransactionManager {
    private Lock lock = new ReentrantLock();

    public void transferMoney(BankAccount sender, BankAccount recipient, Transaction transaction) {

        lock.lock();
        try {
            BigDecimal senderBalance = sender.getBalance();
            BigDecimal recipientBalance = recipient.getBalance();
            senderBalance = senderBalance.subtract(transaction.getAmountOfMoney());
            recipientBalance = recipientBalance.add(transaction.getAmountOfMoney());
        } finally {
            lock.unlock();
        }

    }
}
