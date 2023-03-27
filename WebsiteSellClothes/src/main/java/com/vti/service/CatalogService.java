package com.vti.service;

import com.vti.form.creating.CatalogFormForCreating;
import com.vti.form.updating.CatalogFormForUpdating;
import com.vti.entity.Catalog;
import com.vti.repository.ICatalogRepository;
import com.vti.service.implement.ICatalogService;
import com.vti.specification.CatalogSpecificationBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CatalogService implements ICatalogService {

    @Autowired
    private ICatalogRepository catalogRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Catalog> getAllCatalogs(String search) {

        CatalogSpecificationBuilder specification = new CatalogSpecificationBuilder(search);

        return catalogRepository.findAll(specification.build());
    }

    @Override
    public Catalog getCatalogByID(int id) {
        return catalogRepository.findById(id).get();
    }

    @Override
    public Catalog createCatalog(CatalogFormForCreating form) {

        // convert form to entity
        Catalog catalog = modelMapper.map(form, Catalog.class);
        Catalog returnCatalog = catalogRepository.save(catalog);
        return returnCatalog;
    }

    @Override
    public Catalog updateCatalog(int id, CatalogFormForUpdating form) {
        Catalog entity = catalogRepository.findById(id).get();
        entity.setName(form.getName());
        entity.setImage(form.getImage());
        Catalog returnCatalog =catalogRepository.save(entity);
        return returnCatalog;
    }

    @Override
    public void deleteCatalog(int id) {
        catalogRepository.deleteById(id);
    }


}
