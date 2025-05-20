package com.tfg.backend.services.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.backend.models.LoanState;
import com.tfg.backend.repository.LoanStateRepository;

import java.util.List;

@Service
public class LoanStateService {

    @Autowired
    private LoanStateRepository loanStateRepository;

    public List<LoanState> getAllLoanStates() {
        return loanStateRepository.findAll();
    }
}
