package com.tfg.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.backend.models.Boardgame;
import com.tfg.backend.models.Stock;
import com.tfg.backend.models.User;

public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findByFkUser(User user);
    Optional<Stock> findByFkUserAndFkBoardgame(User user, Boardgame boardgame);
}
