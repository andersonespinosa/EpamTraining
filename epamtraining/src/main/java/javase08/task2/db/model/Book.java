package javase08.task2.db.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import static lombok.AccessLevel.PRIVATE;

@FieldDefaults(level = PRIVATE)
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
public class Book extends Entity {

    String title;
    String author;
    String publishingHouse;
    int pages;

}
