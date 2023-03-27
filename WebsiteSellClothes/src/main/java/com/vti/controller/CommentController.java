package com.vti.controller;

import com.vti.dto.CommentDTO;
import com.vti.entity.Comment;
import com.vti.form.creating.CommentFormForCreating;
import com.vti.service.implement.ICommentService;
import com.vti.utils.UserDetailsUtils;
import com.vti.validation.comment.ProductIDInCommentExists;
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

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@Validated
@RequestMapping(value = "/api/v1/comments")
public class CommentController {

    @Autowired
    private ICommentService service;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping(value = "/productId/{productId}")
    public ResponseEntity<?> getCommentByProductId(Pageable pageable, @PathVariable(name = "productId") int productId) {

        Page<Comment> comments = service.getCommentByProductId(pageable, productId);

        // convert entities --> dtos
        List<CommentDTO> dtos = modelMapper.map(comments.getContent(), new TypeToken<List<CommentDTO>>() {
        }.getType());

        Page<CommentDTO> dtoPages = new PageImpl<>(dtos, pageable, comments.getTotalElements());

        return new ResponseEntity<>(dtoPages, HttpStatus.OK);
    }


    @PostMapping()
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentFormForCreating form) {
        Comment comment = service.createComment(UserDetailsUtils.UserDetails().getUsername(), form);
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }


//    @DeleteMapping()
//    public ResponseEntity<?> deleteCommentByUserIdAndProductId(@Parameter(name = "userId") @UserIDExists int userId, @Parameter(name = "productId") int productId) {
//        service.deleteCommentByUserIdAndProductId(userId, productId);
//        return new ResponseEntity<String>("Delete successfully!", HttpStatus.OK);
//    }

    @DeleteMapping()
    public ResponseEntity<?> deleteCommentByUserUsernameAndProductId(@ProductIDInCommentExists @Parameter(name = "productId") int productId) {


            service.deleteCommentByUserUsernameAndProductId(UserDetailsUtils.UserDetails().getUsername(), productId);


        return new ResponseEntity<>("Delete successfully!", HttpStatus.OK);
    }
}
