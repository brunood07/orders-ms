package br.com.brunood.orders.controllers;

import br.com.brunood.orders.dtos.CreateOrderUseCaseRequestDTO;
import br.com.brunood.orders.usecases.CreateOrderUseCase;
import br.com.brunood.orders.usecases.FindOrdersByClientIdUseCase;
import br.com.brunood.orders.usecases.GetOrderByOrderIdUseCase;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {

    @Autowired
    private CreateOrderUseCase createOrderUseCase;
    @Autowired
    private GetOrderByOrderIdUseCase getOrderByOrderIdUseCase;
    @Autowired
    private FindOrdersByClientIdUseCase findOrdersByClientIdUseCase;

    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody CreateOrderUseCaseRequestDTO data, UriComponentsBuilder uriBuilder) {
        try {
            var order = this.createOrderUseCase.execute(data);
            var uri = uriBuilder.path("/api/v1/orders/{id}").buildAndExpand(order.getOrder().getOrderId()).toUri();

            return ResponseEntity.created(uri).body(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Object> getOrderByOrderId(@PathVariable(name = "orderId") Long orderId) {
        try {
            var order = this.getOrderByOrderIdUseCase.execute(orderId);

            return ResponseEntity.ok().body(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Object> findOrdersByClientId(@PathVariable(name = "clientId") Long clientId) {
        try {
            var orders = this.findOrdersByClientIdUseCase.execute(clientId);

            return ResponseEntity.ok().body(orders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
