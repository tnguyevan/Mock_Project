package com.vti.service;

import com.vti.entity.OderDetail;
import com.vti.entity.OderDetailDBConvert;
import com.vti.repository.IOderDetailConvertRepository;
import com.vti.repository.IOderDetailRepository;
import com.vti.service.implement.IOderDetailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OderDetailService implements IOderDetailService {

    @Autowired
    private IOderDetailRepository oderDetailRepository;

    @Autowired
    private IOderDetailConvertRepository oderDetailConvertRepository;


    @Override
    public void createOderDetailByOderId(int oderId) {
        // set oderId
        List<OderDetailDBConvert> oderDetailList = oderDetailConvertRepository.getOderDetailByOderId(oderId);
        for (OderDetailDBConvert oderDetailDBConvert : oderDetailList
        ) {
            OderDetail oderDetail = new OderDetail();
            // convert form to entity
            BeanUtils.copyProperties(oderDetailDBConvert, oderDetail, "idConvert", "id");

            oderDetailRepository.save(oderDetail);
        }
    }

    @Override
    public boolean existsOderDetailByOderListId(int id) {
        return oderDetailRepository.existsByOderListId(id);
    }


    @Override
    public Page<OderDetail> getOderDetailByOderId(Pageable pageable, int oderId) {
        return oderDetailRepository.findAllByOderListId(pageable, oderId);
    }
}
