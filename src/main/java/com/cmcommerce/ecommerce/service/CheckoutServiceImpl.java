package com.cmcommerce.ecommerce.service;

import com.cmcommerce.ecommerce.dao.CustomerRepository;
import com.cmcommerce.ecommerce.dto.Purchase;
import com.cmcommerce.ecommerce.dto.PurchaseResponse;
import com.cmcommerce.ecommerce.entity.Customer;
import com.cmcommerce.ecommerce.entity.Order;
import com.cmcommerce.ecommerce.entity.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;

    // @Autowired is optional since we have only one constructor
    public CheckoutServiceImpl (CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();

//        orderItems.forEach(item -> order.add(item));
        orderItems.forEach(order::add); // using method reference instead of lambda

        // populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        Customer customer = purchase.getCustomer();

        // check if this is an existing customer
        String theEmail = customer.getEmail();

        Customer customerFromDB = customerRepository.findByEmail(theEmail);

        if (customerFromDB != null) {
            // customer found... let's assign it accordingly
            customer = customerFromDB;
        }

        customer.add(order);

        // save to the database
        customerRepository.save(customer);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);

    }

    private String generateOrderTrackingNumber() {

        // generate random UUID number (UUID version-4)
        return UUID.randomUUID().toString(); // TODO: check if it exists in database
    }
}
