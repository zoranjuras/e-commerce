package com.cmcommerce.ecommerce.controller;

import com.cmcommerce.ecommerce.dto.Purchase;
import com.cmcommerce.ecommerce.dto.PurchaseResponse;
import com.cmcommerce.ecommerce.service.CheckoutService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("api/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    // just one constructor, so we don't need @Autowired
    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {

        return checkoutService.placeOrder(purchase);
    }
}
