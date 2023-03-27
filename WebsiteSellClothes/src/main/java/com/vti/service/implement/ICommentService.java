package com.vti.service.implement;

import com.vti.entity.Comment;
import com.vti.form.creating.CommentFormForCreating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICommentService {


    Page<Comment> getCommentByProductId(Pageable pageable, int productId);

//    Comment createComment(CommentFormForCreating form);


    Comment createComment(String username, CommentFormForCreating form);


    void deleteCommentByUserUsernameAndProductId(String username, int productId);


    boolean existsCommentByProductId(int productId);
}
