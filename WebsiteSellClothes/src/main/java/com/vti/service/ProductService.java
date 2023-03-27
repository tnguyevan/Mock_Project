package com.vti.service;

import com.vti.entity.CreatorProduct;
import com.vti.entity.Image;
import com.vti.entity.Product;
import com.vti.entity.User;
import com.vti.form.creating.ProductFormForCreating;
import com.vti.form.updating.ProductFormForUpdating;
import com.vti.repository.ICreatorProductRepository;
import com.vti.repository.IImageRepository;
import com.vti.repository.IProductRepository;
import com.vti.repository.IUserRepository;
import com.vti.service.implement.IProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IImageRepository imageRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICreatorProductRepository creatorProductRepository;


    @Override
    public Product getProductByID(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Page<Product> getAllProductByCatalogID(Pageable pageable, int catalogId) {
        return productRepository.findProductByCatalogId(pageable, catalogId);
    }

    @Override
    public List<Product> getProductByCatalogId(int catalogId) {
        return productRepository.getProductByCatalogId(catalogId);
    }

    @Override
    public List<Product> getProduct() {
        return productRepository.getProduct();
    }

    @Override
    public boolean existsProductByProductId(int id) {
        return productRepository.existsById(id);
    }

    @Override
    public boolean existsProductsByCatalogId(int catalogId) {
        return productRepository.existsProductByCatalogId(catalogId);
    }


    // staff
    @Override
    public Product createProduct(String username, ProductFormForCreating form) {

        User user = userRepository.findByUsername(username);

        // omit id field
        TypeMap<ProductFormForCreating, Product> typeMap = modelMapper.getTypeMap(ProductFormForCreating.class, Product.class);
        if (typeMap == null) { // if not already added
            // skip field
            modelMapper.addMappings(new PropertyMap<ProductFormForCreating, Product>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }

        // convert form to entity
        Product product = modelMapper.map(form, Product.class);

        if (form.getImage() != null) {
            // create image
            Image image = new Image();
            image.setImage1(form.getImage().getImage1());
            image.setImage2(form.getImage().getImage2());
            image.setImage3(form.getImage().getImage3());
            image.setImage4(form.getImage().getImage4());
            image.setImage5(form.getImage().getImage5());
            image.setImage6(form.getImage().getImage6());

            // create image
            Image saveImage = imageRepository.save(image);

            product.setImage(saveImage);
        }
        // save product
        Product returnProduct = productRepository.save(product);

        // create CreatorProduct
        CreatorProduct creatorProduct = new CreatorProduct();
        creatorProduct.setUser(user);
        creatorProduct.setProduct(returnProduct);

        creatorProductRepository.save(creatorProduct);

        //
        Product returnProduct1 = productRepository.findById(returnProduct.getId()).get();
        return returnProduct1;
    }

    @Override
    public Product updateProduct(String username, int productId, ProductFormForUpdating form) {

        List<CreatorProduct> creatorProducts = creatorProductRepository.findCreatorProductsByUserUsername(username);

        for (CreatorProduct creatorProduct : creatorProducts) {
            if (creatorProduct.getProduct().getId() == productId) {
                Product entity = productRepository.findById(productId).get();
                entity.setName(form.getName());
                entity.setDescribe(form.getDescribe());
                entity.setSize(form.getSize());
                entity.setAmount(form.getAmount());
                entity.setPurchasePrice(form.getPurchasePrice());
                entity.setPrice(form.getPrice());
                entity.setSalePrice(form.getSalePrice());
                Product returnProduct = productRepository.save(entity);
                return returnProduct;
            }
        }
        throw new RuntimeException("Không tồn tại id của sản phẩm");
    }


    @Override
    public void deleteProducts(String username, List<Integer> ids) {
        for (Integer id : ids) {
            boolean i = creatorProductRepository.existsCreatorProductByUserUsernameAndProductId(username, id);
            if (i) {
                creatorProductRepository.deleteCreatorProductByProductId(id);
                productRepository.deleteById(id);
            } else {
                throw new RuntimeException("Không tồn tại id của sản phẩm");
            }
        }
    }

    @Override
    public void deleteProduct(String username, int id) {
        List<CreatorProduct> creatorProducts = creatorProductRepository.findCreatorProductsByUserUsername(username);
        for (CreatorProduct creatorProduct : creatorProducts) {
            if (creatorProduct.getProduct().getId() == id) {
                creatorProductRepository.deleteCreatorProductByProductId(id);
                productRepository.deleteById(id);
                return;
            }
        }
        throw new RuntimeException("Không tồn tại id của sản phẩm");

    }


}
