package edu.mum.eshop.interfaces;

import edu.mum.eshop.domain.product.Category;
import edu.mum.eshop.domain.product.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product getProduct(Integer id);

    List<Category> getCategoris();
}
