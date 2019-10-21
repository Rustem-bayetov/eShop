package edu.mum.eshop.services;

import edu.mum.eshop.domain.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {
    List<PurchaseOrder> getAll();

    void save(PurchaseOrder purchaseOrder);

    PurchaseOrder getById(Integer id);
}
