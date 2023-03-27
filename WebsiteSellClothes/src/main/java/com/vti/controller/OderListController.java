package com.vti.controller;

import com.vti.dto.OderListDTO;
import com.vti.entity.OderList;
import com.vti.form.creating.OderListFormForCreating;
import com.vti.service.implement.IOderListService;
import com.vti.utils.UserDetailsUtils;
import io.swagger.v3.oas.annotations.Parameter;
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
@Validated
@RequestMapping(value = "/api/v1/oderLists")
public class OderListController {

    @Autowired
    private IOderListService service;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping(value = "/username")
    public ResponseEntity<?> getOderListByUsername(Pageable pageable) {


        Page<OderList> oderLists = service.getOderListByUsername(pageable, UserDetailsUtils.UserDetails().getUsername());

        List<OderListDTO> dtos = modelMapper.map(oderLists.getContent(), new TypeToken<List<OderListDTO>>() {
        }.getType());

        Page<OderListDTO> dtoPages = new PageImpl<>(dtos, pageable, oderLists.getTotalElements());

        return new ResponseEntity<>(dtoPages, HttpStatus.OK);
    }

    @GetMapping(value = "/username/status")
    public ResponseEntity<?> getOderListByUsernameAndStatus(Pageable pageable, @Parameter(name = "status") OderList.Status status) {


        Page<OderList> oderLists = service.getOderListByUsernameAndStatus(pageable, UserDetailsUtils.UserDetails().getUsername(), status);

        List<OderListDTO> dtos = modelMapper.map(oderLists.getContent(), new TypeToken<List<OderListDTO>>() {
        }.getType());

        Page<OderListDTO> dtoPages = new PageImpl<>(dtos, pageable, oderLists.getTotalElements());

        return new ResponseEntity<>(dtoPages, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createOderList(@RequestBody OderListFormForCreating form) {

        OderList oderList =  service.createOderList(UserDetailsUtils.UserDetails().getUsername(), form);
        OderListDTO oderListDTO = modelMapper.map(oderList, OderListDTO.class);
        return new ResponseEntity<>(oderListDTO, HttpStatus.OK);
    }


}
