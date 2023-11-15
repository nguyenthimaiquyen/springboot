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
    private static final String CUSTOMER_DATA_FILE_NAME = "invoices";
    public static int AUTO_ID = 1;
    private final FileUtil<Invoice> fileUtil;
    private List<Invoice> invoices = new ArrayList<>();

    public List<Invoice> getAll() {
        return invoices;
    }

    public List<Invoice> createInvoice(Invoice invoice) {
        List<Invoice> invoices = getAll();
        if (CollectionUtils.isEmpty(invoices)) {
            invoices = new ArrayList<>();
        }
        invoices.add(invoice);
        fileUtil.writeDataToFile(CUSTOMER_DATA_FILE_NAME, invoices);
        return invoices;
    }




}
