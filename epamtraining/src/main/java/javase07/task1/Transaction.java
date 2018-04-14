package javase07.task1;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class Transaction {

    int senderId;
    int recipientId;
    BigDecimal amountOfMoney;

}
