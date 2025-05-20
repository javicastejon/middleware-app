package com.tfg.backend.services.operations;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.backend.data.ErrorMessages.ErrorMessageRNF;
import com.tfg.backend.exceptions.exceptions.ResourceNotFoundException;
import com.tfg.backend.models.UserType;
import com.tfg.backend.repository.UserTypeRepository;

@Service
public class UserTypeService {

    @Autowired
    private UserTypeRepository userTypeRepository;

    // OPERATIONS \\
    public UserType getUserType(Integer id) {
        UserType userType = userTypeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.TYPE_USER_RNF));
        return userType;
    }

    public UserType getUserType(String name){
        UserType userType = userTypeRepository.findByUserTypeName(name)
            .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.TYPE_USER_RNF));
        return userType;
    }    

    public List<UserType> getAllUserTypes() {
        List<UserType> userType = userTypeRepository.findAll();
        return userType;
    }
}