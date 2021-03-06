package com.leoamorimr.customer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder().firstName(request.firstName()).lastName(request.lastName()).email(request.email()).build();
        //  todo: check if email is valid

        //  todo: check if email not taken
        Customer customerByEmail = customerRepository.findCustomerByEmail(customer.getEmail());
        if(customerByEmail != null) {
            log.error("Email already exists! {}",customerByEmail.getEmail());
            throw new IllegalStateException("Customer email already exists.");
        }

        customerRepository.saveAndFlush(customer); //to have access to customerId

        FroudCheckResponse froudCheckResponse = restTemplate.getForObject(
                "http://FROUD/api/v1/froud-check/{customerId}",
                FroudCheckResponse.class,
                customer.getId());
        if (froudCheckResponse.isFroudster()) {
            throw new IllegalStateException("froudster");
        }
        //  todo: send notification
    }
}
