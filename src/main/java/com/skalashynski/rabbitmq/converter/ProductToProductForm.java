package com.skalashynski.rabbitmq.converter;


import com.skalashynski.rabbitmq.command.ProductForm;
import com.skalashynski.rabbitmq.entity.Product;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;

@Component
public class ProductToProductForm implements Converter<Product, ProductForm> {
    @Override
    public ProductForm convert(Product product) {
        ProductForm productForm = new ProductForm();
        productForm.setId(product.getId());
        productForm.setDescription(product.getDescription());
        productForm.setPrice(product.getPrice());
        productForm.setImageUrl(product.getImageUrl());
        return productForm;
    }
}
