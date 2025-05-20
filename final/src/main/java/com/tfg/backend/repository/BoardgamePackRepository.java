package com.tfg.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.backend.models.BoardgamePack;
import com.tfg.backend.models.Pack;

import org.springframework.stereotype.Repository;

@Repository
public interface BoardgamePackRepository extends JpaRepository<BoardgamePack, Integer> {

    boolean existsByFkPack_PackIdAndFkBoardgame_BoardgameId(Integer packId, Integer boardgameId);

    Optional<Pack> findByFkPack_PackIdAndFkBoardgame_BoardgameId(Integer packId, Integer boardgameId);

    void deleteByFkPack_PackIdAndFkBoardgame_BoardgameId(Integer userId, Integer boardgameId);

    List<BoardgamePack> findByFkPack_PackId(Integer packId);

    boolean existsByFkPack_PackId(Integer packId);

}
