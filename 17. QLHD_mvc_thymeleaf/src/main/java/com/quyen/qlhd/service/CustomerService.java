package com.quyen.qlhd.service;

import com.quyen.qlhd.entity.Customer;
import com.quyen.qlhd.exception.CustomerNotFoundException;
import com.quyen.qlhd.model.request.CustomerCreationRequest;
import com.quyen.qlhd.model.request.CustomerUpdateRequest;
import com.quyen.qlhd.model.response.CustomerDetailResponse;
import com.quyen.qlhd.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    public List<Customer> deleteCustomer(int id) throws CustomerNotFoundException {
        return customerRepository.delete(id);
    }

    public List<Customer> createCustomer(CustomerCreationRequest customerCreationRequest) {
        Customer customer = Customer.builder()
                .id(customerRepository.AUTO_ID++)
                .fullname(customerCreationRequest.getFullname())
                .address(customerCreationRequest.getAddress())
                .phone(customerCreationRequest.getPhone())
                .customerType(customerCreationRequest.getCustomerType())
                .build();
        return customerRepository.createCustomer(customer);
    }

    public CustomerDetailResponse findById(int id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id);
        return CustomerDetailResponse.builder()
                .id(customer.getId())
                .fullname(customer.getFullname())
                .address(customer.getAddress())
                .phone(customer.getPhone())
                .customerType(customer.getCustomerType())
                .build();
    }

    public List<Customer> updateCustomer(CustomerUpdateRequest customer) throws CustomerNotFoundException {
        return customerRepository.updateCustomer(customer);
    }
}
