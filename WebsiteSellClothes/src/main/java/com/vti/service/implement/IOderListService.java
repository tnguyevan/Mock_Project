package com.vti.service.implement;

import com.vti.entity.OderList;
import com.vti.form.creating.OderListFormForCreating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOderListService {

    OderList getOderListById(int id);

    Page<OderList> getOderListByUsername(Pageable pageable, String username);

    Page<OderList> getOderListByUsernameAndStatus(Pageable pageable, String username, OderList.Status status);

    OderList createOderList(String username, OderListFormForCreating form);


}
