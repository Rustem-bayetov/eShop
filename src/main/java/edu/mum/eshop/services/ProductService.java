package edu.mum.eshop.services;

import edu.mum.eshop.classes.ZenResult;
import edu.mum.eshop.domain.product.Category;
import edu.mum.eshop.domain.product.Product;
import edu.mum.eshop.domain.product.ProductFilter;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts(ProductFilter filter);

    List<Product> getMyProducts(ProductFilter filter);

    Product save(Product product);

    Product getById(Integer id);

    List<Category> getCategoris();

    ZenResult checkDecreaseAvailableProductCount(Integer productId, Integer decreaseAmount);

    ZenResult decreaseAvailableProductCount(Integer productId, Integer decreaseAmount);
}