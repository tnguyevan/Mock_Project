package com.vti.service.implement;

import com.vti.entity.Product;
import com.vti.form.creating.ProductFormForCreating;
import com.vti.form.updating.ProductFormForUpdating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {


    Product getProductByID(int id);

    Product createProduct(String username, ProductFormForCreating form);

    Product updateProduct(String username, int id, ProductFormForUpdating form);

    void deleteProducts(String username, List<Integer> ids);

    void deleteProduct(String username, int id);

    Page<Product> getAllProductByCatalogID(Pageable pageable, int catalogId);

    List<Product> getProductByCatalogId(int catalogId);

    List<Product> getProduct();

    boolean existsProductByProductId(int id);

    boolean existsProductsByCatalogId(int catalogId);
}
