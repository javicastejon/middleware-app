package com.tfg.backend.services.operations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.backend.data.ErrorMessages.ErrorMessageRNF;
import com.tfg.backend.exceptions.exceptions.ResourceNotFoundException;
import com.tfg.backend.models.BoardgameGender;
import com.tfg.backend.repository.BoardgameGenderRepository;

@Service
public class BoardgameGenderService {

    @Autowired
    private BoardgameGenderRepository boardgameGenderRepository;

    // OPERATIONS \\

    public BoardgameGender getBoardgameGender(Integer id) {
        BoardgameGender boardgameGender = boardgameGenderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.BOARDGAME_GENDER_RNF));
        return boardgameGender;
    }

    public BoardgameGender getBoardgameGender(String name) {
        BoardgameGender boardgameGender = boardgameGenderRepository.findByBoardgameGenderName(name)
            .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.BOARDGAME_GENDER_RNF));
        return boardgameGender;
    }

    public List<BoardgameGender> getAllBoardgameGenders() {
        List<BoardgameGender> boardgameGenders = boardgameGenderRepository.findAll();
        return boardgameGenders;
    }
}
