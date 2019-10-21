package edu.mum.eshop.services.impl;

import edu.mum.eshop.domain.product.Category;
import edu.mum.eshop.domain.product.Product;
import edu.mum.eshop.domain.product.ProductFilter;
import edu.mum.eshop.repositories.CategoryRepository;
import edu.mum.eshop.repositories.ProductRepository;
import edu.mum.eshop.services.ProductService;
import edu.mum.eshop.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<Product> getAll(ProductFilter filter) {

        return Util.iterableToCollection(productRepository.findAll());
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Category> getCategoris() {
        return Util.iterableToCollection(categoryRepository.findAll());
    }


}
