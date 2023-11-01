package com.github.quyen.qlthuvien.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookBorrow {
    Reader reader;
    List<BooksDetail> details;


    @Override
    public String toString() {
        return "\nBookBorrow{" +
                "reader=" + reader +
                ",\n details=" + details +
                '}';
    }
}
