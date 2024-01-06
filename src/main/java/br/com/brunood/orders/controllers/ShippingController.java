package br.com.brunood.orders.controllers;

import br.com.brunood.orders.enums.ShippingStatus;
import br.com.brunood.orders.usecases.UpdateShippingStatusUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shipping")
public class ShippingController {

    @Autowired
    private UpdateShippingStatusUseCase updateShippingStatusUseCase;

    @PutMapping("/update/{orderId}")
    public ResponseEntity<Object> updateShippingStatus(@PathVariable(name = "orderId") Long orderId, @RequestParam(name = "status") ShippingStatus status) {
        try {
            this.updateShippingStatusUseCase.execute(orderId, status);
            return ResponseEntity.ok().body("updated");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
