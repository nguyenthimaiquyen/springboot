package com.quyen.qlhd.repository;

import com.quyen.qlhd.entity.Customer;
import com.quyen.qlhd.exception.CustomerNotFoundException;
import com.quyen.qlhd.model.request.CustomerUpdateRequest;
import com.quyen.qlhd.model.response.CustomerDetailResponse;
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
public class CustomerRepository {
    private static final String CUSTOMER_DATA_FILE_NAME = "customers";
    public static int AUTO_ID = 11;
    private final FileUtil<Customer> fileUtil;

    public List<Customer> getAll() {
        return fileUtil.readDataFromFile(CUSTOMER_DATA_FILE_NAME, Customer[].class);
    }

    public List<Customer> delete(int id) throws CustomerNotFoundException {
        List<Customer> customers = getAll();
        if (CollectionUtils.isEmpty(customers)) {
            throw new CustomerNotFoundException("Customers not found");
        }
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == id) {
                customers.remove(i);
                fileUtil.writeDataToFile(CUSTOMER_DATA_FILE_NAME, customers);
                return customers;
            }
        }
        return null;
    }

    public List<Customer> createCustomer(Customer customer) {
        List<Customer> customers = getAll();
        if (CollectionUtils.isEmpty(customers)) {
            customers = new ArrayList<>();
        }
        customers.add(customer);
        fileUtil.writeDataToFile(CUSTOMER_DATA_FILE_NAME, customers);
        return customers;
    }


    public Customer findById(int id) throws CustomerNotFoundException {
        List<Customer> customers = getAll();
        if (CollectionUtils.isEmpty(customers)) {
            throw new CustomerNotFoundException("Customers not found");
        }
        return customers.stream().filter(b -> b.getId() == id).findFirst().get();
    }

    public List<Customer> updateCustomer(CustomerUpdateRequest customer) throws CustomerNotFoundException {
        List<Customer> customers = getAll();
        if (CollectionUtils.isEmpty(customers)) {
            throw new CustomerNotFoundException("Customers not found");
        }
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == customer.getId()) {
                customers.get(i).setFullname(customer.getFullname());
                customers.get(i).setAddress(customer.getAddress());
                customers.get(i).setPhone(customer.getPhone());
                customers.get(i).setCustomerType(customer.getCustomerType());
                fileUtil.writeDataToFile(CUSTOMER_DATA_FILE_NAME, customers);
                return customers;
            }
        }
        return null;
    }


}
