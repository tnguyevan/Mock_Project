package com.vti.controller;

import com.vti.dto.CatalogDTO;
import com.vti.dto.ImageDTO;
import com.vti.entity.Catalog;
import com.vti.entity.Image;
import com.vti.form.creating.CatalogFormForCreating;
import com.vti.form.creating.ImageFormForCreating;
import com.vti.form.updating.CatalogFormForUpdating;
import com.vti.service.implement.ICatalogService;
import com.vti.service.implement.IImageService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/images")
@Validated
public class ImageController {

    @Autowired
    private IImageService imageService;

    @Autowired
    private ModelMapper modelMapper;




    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getImageByID(@PathVariable(name = "id") int id) {

        Image image = imageService.getImageByID(id);

        ImageDTO imageDTO = modelMapper.map(image, ImageDTO.class);

        return new ResponseEntity<>(imageDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createImage(@RequestBody ImageFormForCreating form) {
        Image image = imageService.createImage(form);
        ImageDTO imageDTO = modelMapper.map(image, ImageDTO.class);

        return new ResponseEntity<>(imageDTO, HttpStatus.OK);
    }


//    @PutMapping(value = "/update/{id}")
//    public ResponseEntity<?> updateCatalog(@PathVariable(name = "id") short id, @RequestBody CatalogFormForUpdating form) {
//        Catalog catalog = service.updateCatalog(id, form);
//        CatalogDTO catalogDTO = modelMapper.map(catalog, CatalogDTO.class);
//
//        return new ResponseEntity<>(catalogDTO, HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/delete/{id}")
//    public ResponseEntity<?> deleteCatalog(@PathVariable(name = "id") int id) {
//        service.deleteCatalog(id);
//        return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
//    }
}
