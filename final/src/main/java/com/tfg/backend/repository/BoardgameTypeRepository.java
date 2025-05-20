package com.tfg.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.backend.models.BoardgameType;

@Repository
public interface BoardgameTypeRepository extends JpaRepository<BoardgameType, Integer> {
    Optional<BoardgameType> findByBoardgameTypeName(String boardgameTypeName);
    BoardgameType getByBoardgameTypeName(String boardgameTypeName);
}
