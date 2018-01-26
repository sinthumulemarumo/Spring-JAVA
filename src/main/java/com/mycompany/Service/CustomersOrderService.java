/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.mycompany.Model.CustomerOrder;
import com.mycompany.Model.ProductsCopy;
import com.mycompany.Repositories.CustomersOrderRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LebyanaWT
 */
@Service
public class CustomersOrderService {
    
    @Autowired
    private CustomersOrderRepository customersOrderRepository;
    
    private List<CustomerOrder> customerOrders;
    
    List<ProductsCopy> productsCopyList = new ArrayList<ProductsCopy>();
    
    public List<CustomerOrder> findAllcustomerOrders()
    {
        customerOrders = new ArrayList<>();
        customersOrderRepository.findAll().forEach(customerOrders::add);
        return customerOrders; 
    }
    
    public void saveCustomerOrder(CustomerOrder  customerOrders){
        CustomerOrder cOrderObj = new CustomerOrder();
        
        cOrderObj.setCustId(customerOrders.getCustId());
        cOrderObj.setBankDetailId((int) customerOrders.getBankDetailId());
        cOrderObj.setQuantity(customerOrders.getQuantity());
        cOrderObj.setPrice(customerOrders.getPrice());
        cOrderObj.setStatus(customerOrders.getStatus());
        cOrderObj.setOrderedDate(customerOrders.getOrderedDate());
        customersOrderRepository.save(cOrderObj);
    }
}
