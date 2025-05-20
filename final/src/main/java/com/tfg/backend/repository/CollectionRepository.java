package com.tfg.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.tfg.backend.models.Collection;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Integer> {

    boolean existsByFkUser_UserIdAndFkBoardgame_BoardgameId(Integer userId, Integer boardgameId);

    @Modifying
    void deleteByFkUser_UserIdAndFkBoardgame_BoardgameId(Integer userId, Integer boardgameId);
    
    List<Collection> findByFkUser_UserId(Integer userId);
}

