package com.vit.model;

import java.util.Arrays;

/**
 * Created by kashishsinghal on 11/04/16.
 */
public class Product {

    CarData[] products;

    public CarData[] getProducts() {
        return products;
    }

    public void setProducts(CarData[] products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(products, product.products);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(products);
    }

    @Override
    public String toString() {
        return "Product{" +
                "products=" + Arrays.toString(products) +
                '}';
    }
}
