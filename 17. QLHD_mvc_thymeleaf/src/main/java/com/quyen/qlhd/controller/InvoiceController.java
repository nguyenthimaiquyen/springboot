package com.quyen.qlhd.controller;

import com.quyen.qlhd.entity.Customer;
import com.quyen.qlhd.entity.Invoice;
import com.quyen.qlhd.entity.Service;
import com.quyen.qlhd.model.request.InvoiceCreationRequest;
import com.quyen.qlhd.service.CustomerService;
import com.quyen.qlhd.service.InvoiceService;
import com.quyen.qlhd.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;
    private final CustomerService customerService;
    private final ServiceService serviceService;


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
    public String createInvoice(@ModelAttribute("InvoiceCreationRequest") InvoiceCreationRequest request, Model model) {
        List<Invoice> invoices = invoiceService.createInvoice(request);
        model.addAttribute("invoices", invoices);
        return "invoice/invoices";
    }



}
