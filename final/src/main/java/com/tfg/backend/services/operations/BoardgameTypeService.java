package com.tfg.backend.services.operations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.backend.data.ErrorMessages.ErrorMessageRNF;
import com.tfg.backend.exceptions.exceptions.ResourceNotFoundException;
import com.tfg.backend.models.BoardgameType;
import com.tfg.backend.repository.BoardgameTypeRepository;

@Service
public class BoardgameTypeService {

    @Autowired
    private BoardgameTypeRepository boardgameTypeRepository;

    // OPERATIONS \\

    public BoardgameType getBoardgameType(Integer id) {
        BoardgameType boardgameType = boardgameTypeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.BOARDGAME_TYPE_RNF));
        return boardgameType;
    }

    public BoardgameType getBoardgameType(String name) {
        BoardgameType boardgameType = boardgameTypeRepository.findByBoardgameTypeName(name)
            .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.BOARDGAME_TYPE_RNF));
        return boardgameType;
    }

    public List<BoardgameType> getAllBoardgameTypes() {
        List<BoardgameType> boardgameTypes = boardgameTypeRepository.findAll();
        return boardgameTypes;
    }
}
