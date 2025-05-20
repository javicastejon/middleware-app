package com.tfg.backend.services.operations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.backend.data.ErrorMessages.ErrorMessageRNF;
import com.tfg.backend.exceptions.exceptions.ResourceNotFoundException;
import com.tfg.backend.models.Country;
import com.tfg.backend.repository.CountryRepository;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    // OPERATIONS \\

    public Country getCountry(Integer id) {
        Country country = countryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.COUNTRY_RNF));
        return country;
    }

    public Country getCountry(String name) {
        Country country = countryRepository.findByCountryName(name)
        .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.COUNTRY_RNF));
        return country;
    }

    public List<Country> getAllCountries() {
        List<Country> country = countryRepository.findAll();
        return country;
    }
}
