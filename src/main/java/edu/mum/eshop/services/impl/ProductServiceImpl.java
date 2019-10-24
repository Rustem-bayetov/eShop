package edu.mum.eshop.services.impl;

import edu.mum.eshop.Session;
import edu.mum.eshop.classes.ZenResult;
import edu.mum.eshop.controllers.ProfileController;
import edu.mum.eshop.domain.notification.NotificationChannel;
import edu.mum.eshop.domain.product.Category;
import edu.mum.eshop.domain.product.Product;
import edu.mum.eshop.domain.product.ProductFilter;
import edu.mum.eshop.domain.users.User;
import edu.mum.eshop.repositories.CategoryRepository;
import edu.mum.eshop.repositories.ProductRepository;
import edu.mum.eshop.services.ProductService;
import edu.mum.eshop.services.UsersService;
import edu.mum.eshop.util.Messaging;
import edu.mum.eshop.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl extends BaseService implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UsersService usersService;

    @Autowired
    Messaging messaging;

    @Override
    public List<Product> getAllProducts(ProductFilter filter) {

        List<Product> products = Util.iterableToCollection(productRepository.getAllProducts());

        if (filter.getCategoryId() != null && filter.getCategoryId() > 0){
            products = products.stream().filter(x->x.getCategory().getId() == filter.getCategoryId()).collect(Collectors.toList());
        }

        return products;
    }

    @Override
    public List<Product> getMyProducts(ProductFilter filter) {
        List<Product> products = Util.iterableToCollection(productRepository.getMyProducts(getUserId()));
        if (filter.getCategoryId() != null && filter.getCategoryId() > 0) {
            products = products.stream().filter(x -> x.getCategory().getId() == filter.getCategoryId()).collect(Collectors.toList());
        }

        return products;
    }

    @Override
    public Product save(Product product) {
        product.setUser(getUser());

        if (product.getId() == 0) {
            List<User> followers = usersService.getSellerFollowers(getUserId());

            messaging.send(getUser(), "New product posted", "New product posted in your store", NotificationChannel.WEB);

            if (followers != null){
                for (User follower : followers) {
                    messaging.send(follower, "New product available", "New product posted in " + getUser().getFirstName() + " " + getUser().getLastName() + " store", NotificationChannel.WEB);
                }
            }
        }

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

    @Override
    public ZenResult checkDecreaseAvailableProductCount(Integer productId, Integer decreaseAmount) {
        Product product = getById(productId);

        if (product.getAvailableCount() < decreaseAmount) return new ZenResult("Amount of product not enough");

        return new ZenResult();
    }

    @Override
    public ZenResult decreaseAvailableProductCount(Integer productId, Integer decreaseAmount){
        Product product = getById(productId);

        if (product.getAvailableCount() < decreaseAmount) return new ZenResult("Amount of product not enough");

        product.setAvailableCount(product.getAvailableCount() - decreaseAmount);
        productRepository.save(product);

        return new ZenResult();
    }

}
