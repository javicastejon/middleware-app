package com.tfg.backend.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.backend.models.Boardgame;


@Repository
public interface BoardgameRepository extends JpaRepository<Boardgame, Integer> {
    Optional<Boardgame> findByBoardgameName(String BoardGameName);
    Optional<Boardgame> findByApiBggRef(String apiBggRef);
    boolean existsByApiBggRef(String refApiBggRq);
}