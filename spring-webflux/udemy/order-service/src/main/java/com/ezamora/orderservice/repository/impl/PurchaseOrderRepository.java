package com.ezamora.orderservice.repository.impl;

import com.ezamora.orderservice.domain.PurchaseOrder;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

  List<PurchaseOrder> findByUserId(int userId);


}
