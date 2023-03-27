package com.vti.service;

import com.vti.entity.Catalog;
import com.vti.entity.Image;
import com.vti.form.creating.CatalogFormForCreating;
import com.vti.form.creating.ImageFormForCreating;
import com.vti.form.updating.CatalogFormForUpdating;
import com.vti.repository.ICatalogRepository;
import com.vti.repository.IImageRepository;
import com.vti.service.implement.ICatalogService;
import com.vti.service.implement.IImageService;
import com.vti.specification.CatalogSpecificationBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ImageService implements IImageService {

    @Autowired
    private IImageRepository imageRepository;

    @Autowired
    private ModelMapper modelMapper;

//    @Override
//    public List<Catalog> getAllCatalogs(String search) {
//
//        CatalogSpecificationBuilder specification = new CatalogSpecificationBuilder(search);
//
//        return imageRepository.findAll(specification.build());
//    }

    @Override
    public Image getImageByID(int id) {
        return imageRepository.findById(id).get();
    }

    @Override
    public Image createImage(ImageFormForCreating form) {

        // convert form to entity
        Image image = modelMapper.map(form, Image.class);
        imageRepository.save(image);

        Image image1 = imageRepository.findById(image.getId()).get();

        return image1;
    }

//    @Override
//    public Catalog updateCatalog(int id, CatalogFormForUpdating form) {
//        Catalog entity = imageRepository.findById(id).get();
//        entity.setName(form.getName());
//        entity.setImage(form.getImage());
//        catalogRepository.save(entity);
//        return entity;
//    }
//
//    @Override
//    public void deleteCatalog(int id) {
//        catalogRepository.deleteById(id);
//    }


}
