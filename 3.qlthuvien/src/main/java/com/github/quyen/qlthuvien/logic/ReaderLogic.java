package com.github.quyen.qlthuvien.logic;

import com.github.quyen.qlthuvien.Exception.CheckNumberException;
import com.github.quyen.qlthuvien.entity.Book;
import com.github.quyen.qlthuvien.entity.Reader;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Data
@Component //thiếu
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReaderLogic {
    List<Reader> readers = new ArrayList<>();

//    @Autowired
//    public ReaderLogic() {
//        this.readers = new ArrayList<>();
//    }

    public void inputNewReader() {
        System.out.print("Ban muon nhap bao nhieu ban doc moi: ");
        int newReaderNumber = new Scanner(System.in).nextInt();
        for (int i = 0; i < newReaderNumber; i++) {
            System.out.println("Nhap thong tin ban doc moi thu "+ (i + 1));
            Reader newReader = new Reader();
            newReader.inputInfoReader();
            readers.add(newReader);
        }
    }

    public void printReader() {
        System.out.println(readers);
    }

    public boolean readersIsEmpty() {
        return readers.isEmpty();
    }

    public int inputNumber() {
        int readerNumber;
        do {
            try {
                readerNumber = new Scanner(System.in).nextInt();
                if (readerNumber < 1) {
                    throw new CheckNumberException();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Nhap sai dinh dang so, vui long nhap lai!");
            } catch (CheckNumberException e) {
                System.out.print("Vui long nhap so luong ban doc >= 1: ");
            }
        } while (true);
        return readerNumber;
    }
}
