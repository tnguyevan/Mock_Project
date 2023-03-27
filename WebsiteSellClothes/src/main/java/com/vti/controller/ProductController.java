package com.vti.controller;

import com.vti.dto.ProductDTO;
import com.vti.entity.Product;
import com.vti.form.creating.ProductFormForCreating;
import com.vti.form.updating.ProductFormForUpdating;
import com.vti.service.implement.IProductService;
import com.vti.utils.UserDetailsUtils;
import com.vti.validation.product.CatalogIDInProductExists;
import com.vti.validation.product.ProductIDInProductExists;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/products")
@CrossOrigin("*")
@Validated
public class ProductController {

    @Autowired
    private IProductService service;

    @Autowired
    private ModelMapper modelMapper;

    // user

    //  API lấy 5 sản phẩm mới thêm nhất
    @GetMapping()
    public ResponseEntity<?> getProduct() {

        List<Product> product = service.getProduct();

        List<ProductDTO> dtos = modelMapper.map(product, new TypeToken<List<ProductDTO>>() {
        }.getType());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProductByID(@ProductIDInProductExists @PathVariable(name = "id") int id) {

        Product product = service.getProductByID(id);

        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    // Get 9 product
    @GetMapping(value = "/catalog/{catalogId}")
    public ResponseEntity<?> getProductByCatalogID(@CatalogIDInProductExists @PathVariable(name = "catalogId") int catalogId) {

        List<Product> product = service.getProductByCatalogId(catalogId);

        List<ProductDTO> dtos = modelMapper.map(product, new TypeToken<List<ProductDTO>>() {
        }.getType());


        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @GetMapping(value = "/allProduct/{catalogId}")
    public ResponseEntity<?> getAllProductByCatalogID(Pageable pageable, @CatalogIDInProductExists @PathVariable(name = "catalogId") int catalogId) {

        Page<Product> product = service.getAllProductByCatalogID(pageable, catalogId);

        List<ProductDTO> dtos = modelMapper.map(product.getContent(), new TypeToken<List<ProductDTO>>() {
        }.getType());

        Page<ProductDTO> dtoPages = new PageImpl<>(dtos, pageable, product.getTotalElements());

        return new ResponseEntity<>(dtoPages, HttpStatus.OK);
    }

    // Staff

    @PostMapping(value = "/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductFormForCreating form) {
        Product product = service.createProduct(UserDetailsUtils.UserDetails().getUsername(), form);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }


    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateProduct(@ProductIDInProductExists @PathVariable(name = "id") int id, @RequestBody ProductFormForUpdating form) {
        Product product = service.updateProduct(UserDetailsUtils.UserDetails().getUsername(), id, form);
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);

        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    // Delete list
    @DeleteMapping(value = "/deletes/{ids}")
    public ResponseEntity<?> deleteProducts(@PathVariable(name = "ids") List<Integer> ids) {
        service.deleteProducts(UserDetailsUtils.UserDetails().getUsername(), ids);
        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }

    // Delete one
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteProduct(@ProductIDInProductExists @PathVariable(name = "id") int id) {
        service.deleteProduct(UserDetailsUtils.UserDetails().getUsername(), id);
        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }
}
