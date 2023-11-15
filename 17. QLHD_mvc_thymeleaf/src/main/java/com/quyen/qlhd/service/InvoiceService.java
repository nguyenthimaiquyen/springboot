package com.quyen.qlhd.service;

import com.quyen.qlhd.entity.Customer;
import com.quyen.qlhd.entity.Invoice;
import com.quyen.qlhd.model.request.CustomerCreationRequest;
import com.quyen.qlhd.model.request.InvoiceCreationRequest;
import com.quyen.qlhd.model.response.CustomerDetailResponse;
import com.quyen.qlhd.model.response.ServiceDetailResponse;
import com.quyen.qlhd.repository.CustomerRepository;
import com.quyen.qlhd.repository.InvoiceRepository;
import com.quyen.qlhd.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private CustomerRepository customerRepository;
    private ServiceRepository serviceRepository;

    public List<Invoice> getAll() {
        return invoiceRepository.getAll();
    }

    public List<Invoice> createInvoice(InvoiceCreationRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerId());
        if (customer == null) {
            throw new RuntimeException("Không tìm thấy khách hàng!");
        }
        com.quyen.qlhd.entity.Service service = serviceRepository.findById(request.getServiceId());
        if (service == null) {
            throw new RuntimeException("Không tìm thấy dịch vụ!");
        }
        Invoice invoice = Invoice.builder()
                .id(invoiceRepository.AUTO_ID++)
                .customer(customer)
                .service(service)
                .registerDate(request.getRegisterDate())
                .extensionDate(request.getExtensionDate())
                .build();
        return invoiceRepository.createInvoice(invoice);
    }

}
