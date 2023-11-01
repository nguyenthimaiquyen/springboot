package com.github.quyen.qlthuvien.entity;

import com.github.quyen.qlthuvien.statics.ReaderType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reader extends Person{
    static int AUTO_ID = 10000;
    int readerId;
    String fullname;
    String address;
    String phone;
    ReaderType readerType;

    public Reader() {
        this.readerId = AUTO_ID;
        AUTO_ID++;
    }

    @Override
    public String toString() {
        return "\nReader{" +
                ",\n readerId=" + readerId +
                ",\n fullname='" + fullname + '\'' +
                ",\n address='" + address + '\'' +
                ",\n phone='" + phone + '\'' +
                ",\n readerType=" + readerType +
                '}';
    }

    public void inputInfoReader() {
        System.out.print("Nhap ho ten ban doc: ");
        this.setFullname(new Scanner(System.in).nextLine());
        System.out.print("Nhap dia chi cua ban doc: ");
        this.setAddress(new Scanner(System.in).nextLine());
        System.out.print("Nhap so dien thoai cua ban doc: ");
        this.setPhone(new Scanner(System.in).nextLine());
        System.out.println("Nhap loai ban doc: ");
        System.out.println("1. Sinh vien");
        System.out.println("2. Hoc vien cao hoc");
        System.out.println("3. Giao vien");
        System.out.print("Moi ban chon: ");
        int choiceReaderType;
        do {
            choiceReaderType = new Scanner(System.in).nextInt();
            if (choiceReaderType >= 1 && choiceReaderType <= 3) {
                break;
            }
            System.out.println("Nhap sai chuc nang, moi ban nhap lai!");
        } while (true);
        switch (choiceReaderType) {
            case 1:
                this.setReaderType(ReaderType.SINHVIEN);
                break;
            case 2:
                this.setReaderType(ReaderType.HOCVIENCAOHOC);
                break;
            case 3:
                this.setReaderType(ReaderType.GIAOVIEN);
                break;
        }
    }

}
