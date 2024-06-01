package com.springboot.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.model.OrderDetail;
import com.springboot.app.repository.OrderDetailRepository;

@Service
public class OrderDetailService {

    // Inject the OrderDetailRepository
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    // Add the methods to interact with the database

    /** Method to get an order detail by id */
    public OrderDetail findById(Long id) {
        return orderDetailRepository.findById(id).get();
    }

    /** Method to create and associate a new order detail with the order */
    public void saveOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

}
