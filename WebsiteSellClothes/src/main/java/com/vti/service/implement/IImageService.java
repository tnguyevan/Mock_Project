package com.vti.service.implement;

import com.vti.entity.Image;
import com.vti.form.creating.ImageFormForCreating;

public interface IImageService {

//    List<Catalog> getAllCatalogs(String search);

    Image getImageByID(int id);

    Image createImage(ImageFormForCreating form);

//    Catalog updateCatalog(int id, CatalogFormForUpdating form);
//
//    void deleteCatalog(int id);

}
