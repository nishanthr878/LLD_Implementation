package com.lld.fooddelivery.contoller;

import com.lld.fooddelivery.command.CancelOrderCommand;
import com.lld.fooddelivery.command.CommandHistory;
import com.lld.fooddelivery.command.PlaceOrderCommand;
import com.lld.fooddelivery.model.*;
import com.lld.fooddelivery.model.enums.OrderStatus;
import com.lld.fooddelivery.observer.CustomerNotificationObserver;
import com.lld.fooddelivery.observer.DeliveryAgentObserver;
import com.lld.fooddelivery.observer.ResturantObserver;
import com.lld.fooddelivery.service.OrderService;
import com.lld.fooddelivery.strategy.RegularPricing;
import com.lld.fooddelivery.strategy.SurgePricing;
import com.lld.fooddelivery.template.CodOrderProcessor;
import com.lld.fooddelivery.template.OnlineOrderProcessor;
import com.lld.fooddelivery.template.OrderProcessor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final CommandHistory commandHistory;

    public OrderController() {
        this.orderService = new OrderService(new RegularPricing());
        this.orderService.addObserver(new CustomerNotificationObserver());
        this.orderService.addObserver(new ResturantObserver());
        this.orderService.addObserver(new DeliveryAgentObserver());
        this.commandHistory = new CommandHistory();
    }

    @PostMapping("/place")
    public String placeOrder(@RequestParam String customerName, @RequestParam String restaurantName, @RequestParam boolean isSurge, @RequestParam boolean isCod) {

        // builder customer
        Customer customer = new Customer();
        customer.setCustomerId(UUID.randomUUID().toString());
        customer.setName(customerName);

        //build restaurant
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantId(UUID.randomUUID().toString());
        restaurant.setName(restaurantName);
        restaurant.setAddress("123 Main St, City");

        // build menu item
        MenuItem item = new MenuItem();
        item.setItemId(UUID.randomUUID().toString());
        item.setName("Paneer Butter Masala");
        item.setPrice(280.0);
        item.setAvailable(true);

        // build order item
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemId(UUID.randomUUID().toString());
        orderItem.setMenuItem(item);
        orderItem.setQuantity(2);
        orderItem.setItemTotal(item.getPrice() * 2);

        // build order
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setCustomer(customer);
        order.setRestaurant(restaurant);
        order.setItems(List.of(orderItem));
        order.setPlacedAt(LocalDateTime.now());

        // swap strategy based on surge
        if (isSurge) {
            orderService.setPricingStrategy(new SurgePricing());
        } else {
            orderService.setPricingStrategy(new RegularPricing());
        }


        // place via command
        PlaceOrderCommand placeCommand = new PlaceOrderCommand(order, orderService);
        commandHistory.executeCommand(placeCommand);

        // process via template method
        OrderProcessor processor = isCod
                ? new CodOrderProcessor(orderService)
                : new OnlineOrderProcessor(orderService);
        processor.processOrder(order);

        return "Order placed: " + order.getOrderId()
                + " | Total: ₹" + order.getTotalAmount()
                + " | Status: " + order.getStatus();
    }

    @PostMapping("/cancel")
    public String cancelOrder(@RequestParam String orderId) {
        // in real system fetch from DB — simulating here
        Order order = new Order();
        order.setOrderId(orderId);
        order.setStatus(OrderStatus.CONFIRMED);

        CancelOrderCommand cancelCommand = new CancelOrderCommand(order, orderService);
        commandHistory.executeCommand(cancelCommand);

        return "Order cancelled: " + orderId;
    }


    @PostMapping("/undo")
    public String undoLast() {
        commandHistory.undoLast();
        return "Last command undone";
    }


}
