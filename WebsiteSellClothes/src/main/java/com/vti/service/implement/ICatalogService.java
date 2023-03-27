package com.vti.service.implement;

import com.vti.form.creating.CatalogFormForCreating;
import com.vti.form.updating.CatalogFormForUpdating;
import com.vti.entity.Catalog;

import java.util.List;

public interface ICatalogService {

    List<Catalog> getAllCatalogs(String search);

    Catalog getCatalogByID(int id);

    Catalog createCatalog(CatalogFormForCreating form);

    Catalog updateCatalog(int id, CatalogFormForUpdating form);

    void deleteCatalog(int id);

}
