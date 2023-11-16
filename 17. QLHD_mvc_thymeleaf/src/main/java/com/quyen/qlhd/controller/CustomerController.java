package com.quyen.qlhd.controller;

import com.quyen.qlhd.entity.Customer;
import com.quyen.qlhd.exception.CustomerNotFoundException;
import com.quyen.qlhd.model.request.CustomerCreationRequest;
import com.quyen.qlhd.model.response.CustomerDetailResponse;
import com.quyen.qlhd.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/")
    public String home(Model model) {
        List<Customer> customers = customerService.getAll();
        model.addAttribute("customers", customers);
        return "customer/customers";
    }

    @GetMapping("/delete-customer/{id}")
    public String deleteCustomer(@PathVariable("id") int id, Model model) throws CustomerNotFoundException {
        List<Customer> customers = customerService.deleteCustomer(id);
        model.addAttribute("customers", customers);
        return "customer/customers";
    }

    @GetMapping("/create-customer")
    public String forwardToCustomerCreation(Model model) {
        model.addAttribute("CustomerCreationRequest", new CustomerCreationRequest());
        return "customer/customer-creation";
    }

    @PostMapping("/create-customer")
    public String createCustomer(@ModelAttribute("CustomerCreationRequest") @Valid CustomerCreationRequest customer,
                        Model model, Errors errors) {
        if (errors !=  null && errors.getErrorCount() > 0) {
            return "customer/customer-creation";
        }
        List<Customer> customers = customerService.createCustomer(customer);
        model.addAttribute("customers", customers);
        return "/customer/customers";
    }

    @GetMapping("/update-customer/{customer-id}")
    public String forwardToCustomerUpdate(@PathVariable("customer-id") int id, Model model) throws CustomerNotFoundException {
        CustomerDetailResponse customer = customerService.findById(id);
        model.addAttribute("CustomerUpdateRequest", customer);
        return "customer/customer-update";
    }

    @PostMapping("/update-customer")
    public String updateCustomer(@ModelAttribute("CustomerUpdateRequest") @Valid CustomerDetailResponse customer,
                                 Model model, Errors errors) throws CustomerNotFoundException {
        if (errors !=  null && errors.getErrorCount() > 0) {
            return "customer/customer-update";
        }
        List<Customer> customers = customerService.updateCustomer(customer);
        model.addAttribute("customers", customers);
        return "customer/customers";
    }

}
