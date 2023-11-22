package com.quyen.qlhd.service;

import com.quyen.qlhd.entity.Customer;
import com.quyen.qlhd.entity.Invoice;
import com.quyen.qlhd.exception.CustomerNotFoundException;
import com.quyen.qlhd.exception.ServiceNotFoundException;
import com.quyen.qlhd.model.request.CustomerCreationRequest;
import com.quyen.qlhd.model.request.InvoiceCreationRequest;
import com.quyen.qlhd.model.response.CustomerDetailResponse;
import com.quyen.qlhd.model.response.ServiceDetailResponse;
import com.quyen.qlhd.repository.CustomerRepository;
import com.quyen.qlhd.repository.InvoiceRepository;
import com.quyen.qlhd.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final CustomerRepository customerRepository;
    private final ServiceRepository serviceRepository;

    public List<Invoice> getAll() {
        return invoiceRepository.getAll();
    }

    public List<Invoice> createInvoice(InvoiceCreationRequest request) throws ServiceNotFoundException, CustomerNotFoundException {
        Customer customer = customerRepository.findById(request.getCustomerId());
        if (customer == null) {
            throw new RuntimeException("Không tìm thấy khách hàng!");
        }
        com.quyen.qlhd.entity.Service service = serviceRepository.findById(request.getServiceId());
        if (service == null) {
            throw new RuntimeException("Không tìm thấy dịch vụ!");
        }


        LocalDate registerDate = request.getRegisterDate();
        LocalDate extensionDate = request.getExtensionDate();

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
