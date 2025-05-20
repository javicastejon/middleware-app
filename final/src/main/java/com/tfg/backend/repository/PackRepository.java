package com.tfg.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfg.backend.models.Pack;

import org.springframework.stereotype.Repository;

@Repository
public interface PackRepository extends JpaRepository<Pack, Integer> {

    boolean existsByPackNameAndFkUser_UserId(String packName, Integer userId);

    List<Pack> findByFkUser_UserId(Integer userId);

}
