package com.github.quyen.qlthuvien.logic;

import com.github.quyen.qlthuvien.entity.Book;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
@Component //thiếu
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookLogic {
    List<Book> books = new ArrayList<>();

//    @Autowired
//    public BookLogic() {
//        this.books = new ArrayList<>();
//    }

    public void inputNewBook() {
        System.out.print("Ban muon nhap bao nhieu dau sach moi: ");
        int newBook = new Scanner(System.in).nextInt();
        for (int i = 0; i < newBook; i++) {
            System.out.println("Nhap thong tin dau sach thu "+ (i + 1));
            Book book = new Book();
            book.inputInfoBook();
            books.add(book);
        }
    }

    public void printBooks() {
        System.out.println(books);
    }

    public boolean booksIsEmpty() {
        return books.isEmpty();
    }
}
