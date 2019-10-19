package edu.mum.eshop.services.impl;
import edu.mum.eshop.domain.PurchaseOrder;
import edu.mum.eshop.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    @Autowired PurchaseOrderService purchaseOrderService;
    @Override public List<PurchaseOrder> getAll() { return purchaseOrderService.getAll(); }
    @Override public void save(PurchaseOrder purchaseOrder) { purchaseOrderService.save(purchaseOrder); }
    @Override public PurchaseOrder getById(Integer id) { return purchaseOrderService.getById(id); }
}
