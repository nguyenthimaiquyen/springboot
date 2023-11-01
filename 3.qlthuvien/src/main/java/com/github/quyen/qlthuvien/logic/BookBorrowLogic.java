package com.github.quyen.qlthuvien.logic;

import com.github.quyen.qlthuvien.Exception.CheckNumberException;
import com.github.quyen.qlthuvien.entity.Book;
import com.github.quyen.qlthuvien.entity.BookBorrow;
import com.github.quyen.qlthuvien.entity.BooksDetail;
import com.github.quyen.qlthuvien.entity.Reader;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Component //thiếu
//@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookBorrowLogic {
    List<BookBorrow> BookBorrowList = new ArrayList<>();
    BookLogic bookLogic;
    ReaderLogic readerLogic;

    @Autowired
    public BookBorrowLogic(BookLogic bookLogic, ReaderLogic readerLogic) {
        this.bookLogic = bookLogic;
        this.readerLogic = readerLogic;
    }

    public void inputBookBorrow() {
        if (bookLogic.booksIsEmpty() || readerLogic.readersIsEmpty()) {
            System.out.println("Chua co du lieu sach va ban doc, vui long nhap du lieu truoc khi lap bang quan ly muon sach!");
            return;
        }

        System.out.print("Ban muon lap bang quan ly muon sach cho bao nhieu ban doc: ");
        int readerNumber = readerLogic.inputNumber();

        do {
            if (readerNumber > readerLogic.getReaders().size()) {
                System.out.println("Vui long chon so ban doc trong gioi han du lieu hien co!");
                readerNumber = new Scanner(System.in).nextInt();
            } else {
                break;
            }
        } while (true);

        for (int i = 0; i < readerNumber; i++) {
            //nhập ID bạn đọc và check ID đó
            System.out.print("Nhap ID ban doc thu "+ (i+1) + " ban muon lap bang quan ly muon sach: ");
            int IdReader;
            Reader reader = null;
            do {
                try {
                    IdReader = new Scanner(System.in).nextInt();
                    if (IdReader < 1000) {
                        throw new CheckNumberException();
                    }
                    for (int j = 0; j < readerLogic.getReaders().size(); j++) {
                        if(readerLogic.getReaders().get(j).getReaderId() == IdReader) {
                            reader = readerLogic.getReaders().get(j);
                            break;
                        }
                    }
                    if (reader != null) {
                        break;
                    } else {
                        System.out.println("Khong ton tai ma ban doc vua nhap, vui long nhap lai!");
                    }
                } catch ( InputMismatchException e) {
                    System.out.println("Nhap sai dinh dang so, vui long nhap lai!");
                } catch (CheckNumberException e) {
                    System.out.print("Vui long nhap ID ban doc >= 10000: ");
                }
            } while (true);
            //nhập số lượng đầu sách mà bạn đọc đã mượn
            System.out.print("Ban doc thu "+ (i+1) + " da muon bao nhieu loai dau sach: ");
            int bookNumber;
            do {
                try {
                    bookNumber = new Scanner(System.in).nextInt();
                    if (bookNumber < 1 || bookNumber > bookLogic.getBooks().size()) {
                        throw new CheckNumberException();
                    }
                    break;
                } catch ( InputMismatchException e) {
                    System.out.println("Nhap sai dinh dang so, vui long nhap lai!");
                } catch (CheckNumberException e) {
                    System.out.print("Vui long nhap so luong dau sach >= 1 và <= so luong dau sach hien co: ");
                }
            } while (true);
            List<BooksDetail> details = new ArrayList<>();
            //Nhập ID đầu sách và check ID đó
            int BorrowedBookSum = 0;
            do {
                for (int j = 0; j < bookNumber; j++) {
                    System.out.print("Nhap ma dau sach thu " + (j+1) + " ma ban doc thu " + (i+1) + " da muon: ");
                    int IdBook;
                    Book book = null;
                    do {
                        try {
                            IdBook = new Scanner(System.in).nextInt();
                            if (IdBook < 10000) {
                                throw new CheckNumberException();
                            }
                            for (int k = 0; k < bookLogic.getBooks().size(); k++) {
                                if(bookLogic.getBooks().get(k).getId() == IdBook) {
                                    book = bookLogic.getBooks().get(k);
                                    break;
                                }
                            }
                            if (book != null) {
                                break;
                            } else {
                                System.out.println("Khong ton tai ma dau sach ban vua nhap, vui long nhap lai!");
                            }
                        } catch ( InputMismatchException e) {
                            System.out.println("Nhap sai dinh dang so, vui long nhap lai!");
                        } catch (CheckNumberException e) {
                            System.out.print("Vui long nhap ma dau sach >= 10000: ");
                        }
                    } while (true);
                    System.out.print("Nhap so luong sach ma ban doc thu "+ (i+1) + " da muon tuong ung voi dau sach thu "+ (j+1) + ": ");
                    int borrowedbookNumber;
                    do {
                        try {
                            borrowedbookNumber = new Scanner(System.in).nextInt();
                            if (borrowedbookNumber > 3) {
                                throw new CheckNumberException();
                            }
                            break;
                        } catch ( InputMismatchException e) {
                            System.out.println("Nhap sai dinh dang so, vui long nhap lai!");
                        } catch (CheckNumberException e) {
                            System.out.print("Vui long nhap tong so sach da muon thuoc 1 dau sach khong vuot qua 3 cuon: ");
                        }
                    } while (true);
                    BooksDetail booksDetail = new BooksDetail(book, borrowedbookNumber);
                    details.add(booksDetail);
                }
                BookBorrow bookBorrow = new BookBorrow(reader, details);
                BookBorrowList.add(bookBorrow);
                try {
                    for (int j = 0; j < bookNumber; j++){
                        BorrowedBookSum += BookBorrowList.get(i).getDetails().get(j).getBookQuantity();
                    }
                    if ( BorrowedBookSum > 5) {
                        throw new CheckNumberException();
                    }
                    break;
                } catch (CheckNumberException e) {
                    System.out.println("Ban da muon tong so dau sach > 5, vui long nhap lai!");
                }
            } while (true);
        }
    }

    public void showBookBorrow() {
        System.out.println(BookBorrowList);
    }

    public void sortByReaderName() {
        if (readerLogic.getReaders().isEmpty()) {
            System.out.println("Chua co du lieu ban doc, vui long nhap du lieu truoc khi sap xep!");
            return;
        }

        for (int i = 0; i < BookBorrowList.size(); i++) {
            for (int j = i + 1; j < BookBorrowList.size() - 1; j++) {
                if (BookBorrowList.get(i).getReader().getFullname().compareToIgnoreCase(BookBorrowList.get(j).getReader().getFullname()) > 0){
                    BookBorrow temp = BookBorrowList.get(i);
                    BookBorrowList.set(i, BookBorrowList.get(j));
                    BookBorrowList.set(j, temp);
                }
            }
        }
        System.out.println(BookBorrowList);
    }

    public void sortByBookDescending() {
        if (bookLogic.getBooks().isEmpty()) {
            System.out.println("Chua co du lieu dau sach, vui long nhap du lieu truoc khi sap xep.");
            return;
        }

        for (int i = 0; i < BookBorrowList.size(); i++) {
            if (BookBorrowList.get(i) != null) {
                for (int j = 0; j < BookBorrowList.get(i).getDetails().size() - 1; j++) {
                    for (int k = j + 1; k < BookBorrowList.get(i).getDetails().size(); k++) {
                        if(BookBorrowList.get(i).getDetails().get(j).getBookQuantity() < BookBorrowList.get(i).getDetails().get(k).getBookQuantity()){
                            BooksDetail temp = BookBorrowList.get(i).getDetails().get(j);
                            BookBorrowList.get(i).getDetails().set(j, BookBorrowList.get(i).getDetails().get(k));
                            BookBorrowList.get(i).getDetails().set(k, temp);
                        }
                    }
                }
            }
        }
        System.out.println(BookBorrowList);
    }

    public void seachAndShow() {
        System.out.print("Nhap ten ban doc muon tim kiem: ");
        String nameFind = new Scanner(System.in).nextLine();
        for (int i = 0; i < BookBorrowList.size(); i++) {
            if (BookBorrowList.get(i).getReader().getFullname().equals(nameFind)) {
                System.out.println(BookBorrowList.get(i));
                break;
            }
        }
    }

}
