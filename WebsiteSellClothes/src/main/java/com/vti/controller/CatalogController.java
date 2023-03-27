package com.vti.controller;

import com.vti.dto.CatalogDTO;
import com.vti.form.creating.CatalogFormForCreating;
import com.vti.form.updating.CatalogFormForUpdating;
import com.vti.entity.Catalog;
import com.vti.service.implement.ICatalogService;
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

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/catalogs")
@Validated
public class CatalogController {

    @Autowired
    private ICatalogService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<?> getAllCatalogs(@RequestParam(name = "search", required = false) String search) {
        List<Catalog> entities = service.getAllCatalogs(search);

        // convert entities --> dtos
        List<CatalogDTO> dtos = modelMapper.map(entities, new TypeToken<List<CatalogDTO>>() {
        }.getType());

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getCatalogByID(@PathVariable(name = "id") int id) {

        Catalog catalog = service.getCatalogByID(id);

        CatalogDTO catalogDTO = modelMapper.map(catalog, CatalogDTO.class);

        return new ResponseEntity<>(catalogDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createCatalog(@RequestBody CatalogFormForCreating form) {
        Catalog catalog = service.createCatalog(form);
        CatalogDTO catalogDTO = modelMapper.map(catalog, CatalogDTO.class);

        return new ResponseEntity<>(catalogDTO, HttpStatus.OK);
    }


    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateCatalog(@PathVariable(name = "id") short id, @RequestBody CatalogFormForUpdating form) {
        Catalog catalog = service.updateCatalog(id, form);
        CatalogDTO catalogDTO = modelMapper.map(catalog, CatalogDTO.class);

        return new ResponseEntity<>(catalogDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteCatalog(@PathVariable(name = "id") int id) {
        service.deleteCatalog(id);
        return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
    }
}
