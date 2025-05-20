package com.tfg.backend.services.operations;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.backend.data.ErrorMessages.ErrorMessageConflicts;
import com.tfg.backend.data.ErrorMessages.ErrorMessageRNF;
import com.tfg.backend.exceptions.exceptions.ConflictException;
import com.tfg.backend.exceptions.exceptions.ResourceNotFoundException;
import com.tfg.backend.models.Boardgame;
import com.tfg.backend.models.Collection;
import com.tfg.backend.models.User;
import com.tfg.backend.repository.BoardgameRepository;
import com.tfg.backend.repository.CollectionRepository;
import com.tfg.backend.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CollectionService {

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardgameRepository boardgameRepository;

    public void addBoardgameToCollection(Integer userId, Integer boardgameId) {
        if (collectionRepository.existsByFkUser_UserIdAndFkBoardgame_BoardgameId(userId, boardgameId)) {
            throw new ConflictException(ErrorMessageConflicts.boardgameExistsInCollection());
        }
        User user = userRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.USER_RNF));
        Boardgame boardgame = boardgameRepository.findById(boardgameId)
        .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.BOARDGAME_RNF));

        Collection collectionEntry = new Collection();
        collectionEntry.setFkUser(user);
        collectionEntry.setFkBoardgame(boardgame);
        collectionEntry.setRegistryDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        collectionRepository.save(collectionEntry);
    }

    @Transactional
    public void deleteBoardgameToCollection(Integer userId, Integer boardgameId) {
        if (collectionRepository.existsByFkUser_UserIdAndFkBoardgame_BoardgameId(userId, boardgameId)) {
            collectionRepository.deleteByFkUser_UserIdAndFkBoardgame_BoardgameId(userId, boardgameId);
        }    
    }

    public List<Boardgame> getUserCollection(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException(ErrorMessageRNF.USER_RNF);
        }

        return collectionRepository.findByFkUser_UserId(userId)
                .stream()
                .map(Collection::getFkBoardgame)
                .collect(Collectors.toList());
    }
}
