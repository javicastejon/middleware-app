package com.tfg.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.backend.models.BoardgameGender;

@Repository
public interface BoardgameGenderRepository extends JpaRepository<BoardgameGender, Integer> {
    Optional<BoardgameGender> findByBoardgameGenderName(String boardgameGenderName);
}
