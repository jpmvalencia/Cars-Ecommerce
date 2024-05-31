package com.springboot.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.model.Order;
import com.springboot.app.model.OrderDetail;
import com.springboot.app.model.User;
import com.springboot.app.repository.OrderDetailRepository;
import com.springboot.app.repository.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    /** This method is used to get all the orders by user id*/
    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    /** This method is used to get all the orders */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /** This method is used to create a new order */
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    /** This method is used to create a new Order Detail*/
    public OrderDetail newOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

}
