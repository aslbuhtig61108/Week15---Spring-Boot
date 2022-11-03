package com.promineotech.jeep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.promineotech.jeep.dao.JeepOrderDao;
import com.promineotech.jeep.entity.Customer;
import com.promineotech.jeep.entity.Order;
import com.promineotech.jeep.entity.OrderRequest;

@Service
public class DefaultJeepOrderService implements JeepOrderService {

   @Autowired
   private JeepOrderDao jeepOrderDao;
  
  @Transactional
  @Override
  public Order createOrder(OrderRequest orderRequest) {
    // System.out.println(orderRequest);
    Customer customer = jeepOrderDao.fetchCustomer(orderRequest.getCustomer());
    // System.out.println(customer);
    return null;
  }

}
