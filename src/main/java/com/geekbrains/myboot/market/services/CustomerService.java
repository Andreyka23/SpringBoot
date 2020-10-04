package com.geekbrains.myboot.market.services;

import com.geekbrains.myboot.market.models.Customer;
import com.geekbrains.myboot.market.models.Order;
import com.geekbrains.myboot.market.repositories.CustomerRepository;
import com.geekbrains.myboot.market.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }


    public Customer newCustomer(String name, String phone, String address) {
        Customer customer = new Customer();
        customer.setName(name);
        customer.setPhone(phone);
        customer.setAddress(address);
        customerRepository.save(customer);
        return customer;
    }

}
