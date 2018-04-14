package javase07.task1;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
public class BankAccount {

    final int id;
    final String accountNumber;
    final String holderName;
    final String bankName;
    BigDecimal balance;

}
