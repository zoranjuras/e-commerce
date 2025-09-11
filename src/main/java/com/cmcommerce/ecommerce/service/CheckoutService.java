package com.cmcommerce.ecommerce.service;

import com.cmcommerce.ecommerce.dto.Purchase;
import com.cmcommerce.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
