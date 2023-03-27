package com.vti.repository;

import com.vti.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment, Integer> {

    Page<Comment> findByProductId(Pageable pageable, int productId);

    @Modifying
    void deleteCommentByUserUsernameAndProductId(String username, int productId);

    boolean existsCommentByProductId(int id);

    List<Comment> findCommentsByUserUsername(String username);
}
