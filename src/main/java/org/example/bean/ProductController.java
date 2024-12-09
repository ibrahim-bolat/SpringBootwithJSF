package org.example.bean;

import org.example.entity.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;


@Component
@Scope("session")
public class ProductController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private ProductService productService;

    private List<Product> products;

    private Product product = new Product();

    @PostConstruct
    public void init() {
        loadProducts();
    }

    public void loadProducts() {
        products = productService.getAllProducts();
    }

    public void addProduct() {
        productService.saveProduct(product);
        product = new Product();
        loadProducts();
    }

    public void deleteProduct(Long id) {
        productService.deleteProduct(id);
        loadProducts();
    }


    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String loadIndexPage() {
        return "/index.xhtml";
    }

    public String loadAddProductPage() {
       return "/add-product.xhtml";
    }
}