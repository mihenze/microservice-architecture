package com.mihenze.mscurse.orderservice.controller.impl;

import com.mihenze.mscurse.orderservice.controller.OrderController;
import com.mihenze.mscurse.orderservice.rest.order.CreateOrderRequest;
import com.mihenze.mscurse.orderservice.rest.order.OrderResponse;
import com.mihenze.mscurse.orderservice.rest.order.UpdateOrderRequest;
import com.mihenze.mscurse.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {
    private final OrderService orderService;

    @PostMapping
    @Override
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @PutMapping
    @Override
    public ResponseEntity<OrderResponse> updateOrder(@RequestBody UpdateOrderRequest request) {
        return ResponseEntity.ok(orderService.updateOrder(request));
    }

    @GetMapping("/{order_id}")
    @Override
    public ResponseEntity<OrderResponse> getOrder(@PathVariable("order_id") Long orderId) {
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }

    @GetMapping
    @Override
    public ResponseEntity<List<OrderResponse>> getOrders() {
        return ResponseEntity.ok(orderService.getAllOrder());
    }

    @DeleteMapping("/{order_id}")
    @Override
    public ResponseEntity<Void> deleteOrder(@PathVariable("order_id") Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }
}
