package com.vti.repository;

import com.vti.entity.OderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOderDetailRepository extends JpaRepository<OderDetail, Integer> {


    Page<OderDetail> findAllByOderListId(Pageable pageable, int oderId);


    boolean existsByOderListId(int id);
}