package com.cmcommerce.ecommerce.dto;

import com.cmcommerce.ecommerce.entity.Address;
import com.cmcommerce.ecommerce.entity.Customer;
import com.cmcommerce.ecommerce.entity.Order;
import com.cmcommerce.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;

    private Address shippingAddress;

    private Address billingAddress;

    private Order order;

    private Set<OrderItem> orderItems;

}
