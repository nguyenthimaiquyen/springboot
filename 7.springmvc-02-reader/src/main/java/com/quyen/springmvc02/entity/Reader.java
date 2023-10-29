package com.quyen.springmvc02.entity;

import com.quyen.springmvc02.statics.ReaderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//builder và no.. và all... đi cùng nhau, thiếu thì không dùng được
public class Reader {

    private int id;
    private String fullname;
    private String address;
    private String phone;
    private List<ReaderType> readerTypes;


}
