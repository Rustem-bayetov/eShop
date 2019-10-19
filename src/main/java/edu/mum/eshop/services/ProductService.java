package edu.mum.eshop.services;

import edu.mum.eshop.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    void save(Product product);
    Product getById(Integer id);
}