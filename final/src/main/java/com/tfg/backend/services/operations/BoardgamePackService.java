package com.tfg.backend.services.operations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.backend.data.ErrorMessages.ErrorMessageConflicts;
import com.tfg.backend.data.ErrorMessages.ErrorMessageRNF;
import com.tfg.backend.exceptions.exceptions.ConflictException;
import com.tfg.backend.exceptions.exceptions.ResourceNotFoundException;
import com.tfg.backend.models.Boardgame;
import com.tfg.backend.models.BoardgamePack;
import com.tfg.backend.models.Pack;
import com.tfg.backend.repository.BoardgamePackRepository;
import com.tfg.backend.repository.BoardgameRepository;
import com.tfg.backend.repository.PackRepository;
import jakarta.transaction.Transactional;

@Service
public class BoardgamePackService {

    @Autowired
    private BoardgamePackRepository boardgamePackRepository;

    @Autowired
    private PackRepository packRepository;

    @Autowired
    private BoardgameRepository boardgameRepository;

    public void addBoardgameToPack(Integer packId, Integer boardgameId) {

        Pack pack = packRepository.findById(packId)
            .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.PACK_RNF));

        Boardgame boardgame = boardgameRepository.findById(boardgameId)
            .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.BOARDGAME_RNF));

        if (boardgamePackRepository.existsByFkPack_PackIdAndFkBoardgame_BoardgameId(packId, boardgameId)) {
            throw new ConflictException(ErrorMessageConflicts.boardgameExistsInPack());
        }

        BoardgamePack boardgamePack = new BoardgamePack();
        boardgamePack.setFkPack(pack);
        boardgamePack.setFkBoardgame(boardgame);       
        boardgamePackRepository.save(boardgamePack);
    }

    @Transactional
    public void deleteBoardgameToPack(Integer userId, Integer boardgameId) {
        if (boardgamePackRepository.existsByFkPack_PackIdAndFkBoardgame_BoardgameId(userId, boardgameId)) {
            boardgamePackRepository.deleteByFkPack_PackIdAndFkBoardgame_BoardgameId(userId, boardgameId);
        }    
    }
    
    @Transactional
    public List<Boardgame> getBoardgamesByPack(Integer packId) {
        if (!packRepository.existsById(packId)) {
            throw new ResourceNotFoundException(ErrorMessageRNF.PACK_RNF);
        }
        return boardgamePackRepository.findByFkPack_PackId(packId).stream()
            .map(BoardgamePack::getFkBoardgame)
            .collect(Collectors.toList());
    }

}
