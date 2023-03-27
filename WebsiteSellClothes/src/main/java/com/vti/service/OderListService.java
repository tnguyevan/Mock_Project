package com.vti.service;

import com.vti.entity.OderList;
import com.vti.entity.User;
import com.vti.form.creating.OderListFormForCreating;
import com.vti.repository.ICartRepository;
import com.vti.repository.IOderListRepository;
import com.vti.repository.IPayRepository;
import com.vti.repository.IUserRepository;
import com.vti.service.implement.ICartService;
import com.vti.service.implement.IOderDetailService;
import com.vti.service.implement.IOderListService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OderListService implements IOderListService {

    @Autowired
    private IUserRepository IUserRepository;

    @Autowired
    private IOderListRepository oderListRepository;

    @Autowired
    private IOderDetailService oderDetailService;

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IPayRepository payRepository;

    @Autowired
    private ICartService cartService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OderList createOderList(String username, OderListFormForCreating form) {

        User user = IUserRepository.findByUsername(username);

        int total = cartService.total(user.getId());

        //Set form
        form.setUserId(user.getId());

        if (form.getTotalPayment() == null) {
            form.setTotalPayment(total);
        }

        if (form.getStatus() == null) {
            form.setStatus(OderList.Status.WAITING);
        }

        TypeMap<OderListFormForCreating, OderList> typeMap = modelMapper.getTypeMap(OderListFormForCreating.class, OderList.class);
        if (typeMap == null) { // if not already added
            // skip field
            modelMapper.addMappings(new PropertyMap<OderListFormForCreating, OderList>() {
                @Override
                protected void configure() {
                    skip(destination.getId());
                }
            });
        }

        OderList oderList = modelMapper.map(form, OderList.class);

        oderListRepository.save(oderList);

        oderDetailService.createOderDetailByOderId(oderList.getId());

        cartRepository.deleteCartByUserId(user.getId());

        payRepository.deleteByUserId(user.getId());

        OderList oderListById = getOderListById(oderList.getId());

        return oderListById;
    }


    @Override
    public OderList getOderListById(int id) {
        return oderListRepository.findById(id).get();
    }

    @Override
    public Page<OderList> getOderListByUsername(Pageable pageable, String username) {
        return oderListRepository.findByUserUsername(pageable, username);
    }

    @Override
    public Page<OderList> getOderListByUsernameAndStatus(Pageable pageable, String username, OderList.Status status) {
        return oderListRepository.findByUserUsernameAndStatus(pageable, username, status);
    }
}
