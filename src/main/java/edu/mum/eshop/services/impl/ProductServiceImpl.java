package edu.mum.eshop.services.impl;
import edu.mum.eshop.domain.Product;
import edu.mum.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductService productService;
    @Override public List<Product> getAll() { return productService.getAll();}
    @Override public void save(Product product) { productService.save(product);}
    @Override public Product getById(Integer id) { return productService.getById(id);}
}
