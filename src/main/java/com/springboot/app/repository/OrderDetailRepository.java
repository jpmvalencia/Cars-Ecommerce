package com.springboot.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.app.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
