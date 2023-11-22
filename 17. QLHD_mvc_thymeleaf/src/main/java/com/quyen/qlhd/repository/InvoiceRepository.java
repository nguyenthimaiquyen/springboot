package com.quyen.qlhd.repository;

import com.quyen.qlhd.entity.Invoice;
import com.quyen.qlhd.util.FileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class InvoiceRepository {
    private static final String INVOICE_DATA_FILE_NAME = "D:\\5. Hoc Trung Tam ve CNTT\\3. Java fullStack\\5. Spring boot\\4.springboot\\data\\invoice.json";
    public static int AUTO_ID = 1;
    private final FileUtil<Invoice> fileUtil;

    public List<Invoice> getAll() {
        return fileUtil.readDataFromFile(INVOICE_DATA_FILE_NAME, Invoice[].class);
    }

    public List<Invoice> createInvoice(Invoice invoice) {
        List<Invoice> invoices = getAll();
        if (CollectionUtils.isEmpty(invoices)) {
            invoices = new ArrayList<>();
        }
        invoices.add(invoice);
        fileUtil.writeDataToFile(INVOICE_DATA_FILE_NAME, invoices);
        return invoices;
    }




}
