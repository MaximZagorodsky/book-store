package com.example.bookmodel.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class Book {
    int id;
    String author;
    String title;

}
