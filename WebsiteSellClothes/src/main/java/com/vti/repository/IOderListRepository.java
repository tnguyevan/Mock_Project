package com.vti.repository;

import com.vti.entity.OderList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOderListRepository extends JpaRepository<OderList, Integer> {

    Page<OderList> findByUserUsername(Pageable pageable, String username);

    Page<OderList> findByUserUsernameAndStatus(Pageable pageable, String username, OderList.Status status);


}
