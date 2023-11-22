package com.quyen.qlhd.controller;

import com.quyen.qlhd.entity.Customer;
import com.quyen.qlhd.entity.Invoice;
import com.quyen.qlhd.entity.Service;
import com.quyen.qlhd.exception.CustomerNotFoundException;
import com.quyen.qlhd.exception.ServiceNotFoundException;
import com.quyen.qlhd.model.request.InvoiceCreationRequest;
import com.quyen.qlhd.service.CustomerService;
import com.quyen.qlhd.service.InvoiceService;
import com.quyen.qlhd.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class InvoiceController {
    public final InvoiceService invoiceService;
    public final CustomerService customerService;
    public final ServiceService serviceService;


    @GetMapping("/invoice")
    public String getAllInvoice(Model model) {
        List<Invoice> invoices = invoiceService.getAll();
        model.addAttribute("invoices", invoices);
        return "invoice/invoices";
    }

    @GetMapping("/create-invoice")
    public String initInvoice(Model model) {
        List<Customer> customers = customerService.getAll();
        model.addAttribute("customers", customers);

        List<Service> services = serviceService.getAll();
        model.addAttribute("services", services);

        model.addAttribute("InvoiceCreationRequest", new InvoiceCreationRequest());
        return "invoice/invoice-creation";
    }

    @PostMapping("/create-invoice")
    public String createInvoice(@ModelAttribute("InvoiceCreationRequest") @Valid InvoiceCreationRequest request,
                                Errors errors) throws ServiceNotFoundException, CustomerNotFoundException {
        if (errors !=  null && errors.getErrorCount() > 0) {
            return "invoice/invoice-creation";
        }
        List<Invoice> invoices = invoiceService.createInvoice(request);
//        model.addAttribute("invoices", invoices);
        return "redirect:/invoice";
    }



}
