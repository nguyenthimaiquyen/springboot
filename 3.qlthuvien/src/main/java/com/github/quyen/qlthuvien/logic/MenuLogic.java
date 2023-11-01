package com.github.quyen.qlthuvien.logic;

import com.github.quyen.qlthuvien.Exception.CheckNumberException;
import com.github.quyen.qlthuvien.entity.Book;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

@Component //thiếu
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MenuLogic {
    BookLogic bookLogic;
    ReaderLogic readerLogic;
    BookBorrowLogic bookBorrowLogic;

//    @Autowired
//    public MenuLogic(@Qualifier("bookLogic") BookLogic bookLogic, @Qualifier("readerLogic") ReaderLogic readerLogic,
//                     @Qualifier("bookBorrowLogic") BookBorrowLogic bookBorrowLogic) {
//        this.bookLogic = bookLogic;
//        this.readerLogic = readerLogic;
//        this.bookBorrowLogic = bookBorrowLogic;
//    }

    public void run() {
        while (true) {
            showMenu();
            int luaChon = choiceFunction();
            switch (luaChon) {
                case 1:
                    bookLogic.inputNewBook();
                    break;
                case 2:
                    bookLogic.printBooks();
                    break;
                case 3:
                    readerLogic.inputNewReader();
                    break;
                case 4:
                    readerLogic.printReader();
                    break;
                case 5:
                    bookBorrowLogic.inputBookBorrow();
                    break;
                case 6:
                    bookBorrowLogic.showBookBorrow();
                    break;
                case 7:
                    sortMenu();
                    break;
                case 8:
                    bookBorrowLogic.seachAndShow();
                    break;
                case 9:
                    return;

            }
        }




    }


    private void sortMenu() {
        System.out.println("1. Theo ten ban doc");
        System.out.println("2. Theo so luong sach duoc muon giam dan");
        System.out.println("3. Quay lai menu tong");
        int choice;
        do {
            try {
                choice = new Scanner(System.in).nextInt();
                if (choice < 1 || choice > 3) {
                    throw new CheckNumberException();
                }
                break;
            } catch ( InputMismatchException e) {
                System.out.println("Nhap sai dinh dang so, vui long nhap lai!");
            } catch (CheckNumberException e) {
                System.out.print("Vui long nhap tu 1 - 3: ");
            }
        } while (true);

        switch(choice){
            case 1:
                bookBorrowLogic.sortByReaderName();
                break;
            case 2:
                bookBorrowLogic.sortByBookDescending();
                break;
            case 3:
                return;
        }
    }

    private int choiceFunction() {
        int choice;
        do {
            try {
                choice = new Scanner(System.in).nextInt();
                if (choice < 1 || choice > 9){
                    throw new CheckNumberException();
                }
                break;
            } catch (InputMismatchException e){
                System.out.println("Nhap sai dinh dang so, vui long nhap lai!");
            } catch (CheckNumberException e){
                System.out.println("Vui long nhap tu 1 - 9: ");
            }
        } while (true);
        return choice;
    }

    private void showMenu() {
        System.out.println("=====================MENU Quan ly muon sach=========================");
        System.out.println("1. Nhap danh sach dau sach moi");
        System.out.println("2. In ra danh sach cac dau sach da co");
        System.out.println("3. Nhap danh sach ban doc moi");
        System.out.println("4. In ra danh sach cac ban doc da co");
        System.out.println("5. Lap bang quan ly muon sach cho tung ban doc");
        System.out.println("6. In danh sach quan ly muon sach");
        System.out.println("7. Sap xep danh sach quan ly muon sach");
        System.out.println("8. Tim kiem va hien thi danh sach muon sach theo ten ban doc");
        System.out.println("9. Thoat chuong trinh.");
        System.out.print("Moi ban chon: ");
    }
}
