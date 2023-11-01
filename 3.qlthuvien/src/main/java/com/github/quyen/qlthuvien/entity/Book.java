package com.github.quyen.qlthuvien.entity;

import com.github.quyen.qlthuvien.statics.BookSpecialization;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Scanner;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    static int AUTO_ID = 10000;
    int id;
    String name;
    String author;
    int publishedYear;
    BookSpecialization bookSpecialization;

    public Book() {
        this.id = AUTO_ID;
        AUTO_ID++;
    }

    @Override
    public String toString() {
        return "\nBook{" +
                "id=" + id +
                ",\n name='" + name + '\'' +
                ",\n author='" + author + '\'' +
                ",\n publishedYear=" + publishedYear +
                ",\n bookSpecialization=" + bookSpecialization +
                '}';
    }

    public void inputInfoBook() {
        System.out.print("Nhap ten sach: ");
        this.setName(new Scanner(System.in).nextLine());
        System.out.print("Nhap tac gia: ");
        this.setAuthor(new Scanner(System.in).nextLine());

        System.out.println("Nhap chuyen nganh: ");
        System.out.println("1. Khoa hoc tu nhien");
        System.out.println("2. Van hoc nghe thuat");
        System.out.println("3. Dien tu vien thong");
        System.out.println("4. Cong nghe thong tin");
        System.out.print("Moi ban chon: ");
        int choiceSpecialization;
        do {
            choiceSpecialization = new Scanner(System.in).nextInt();
            if (choiceSpecialization >= 1 && choiceSpecialization <= 4) {
                break;
            }
            System.out.println("Nhap sai chuc nang, moi ban nhap lai!");
        } while (true);
        switch (choiceSpecialization) {
            case 1:
                this.setBookSpecialization(BookSpecialization.KHTN);
                break;
            case 2:
                this.setBookSpecialization(BookSpecialization.VHNT);
                break;
            case 3:
                this.setBookSpecialization(BookSpecialization.DTVT);
                break;
            case 4:
                this.setBookSpecialization(BookSpecialization.CNTT);
                break;
        }
        System.out.print("Nhap nam xuat ban: ");
        this.setPublishedYear(new Scanner(System.in).nextInt());
    }


}
