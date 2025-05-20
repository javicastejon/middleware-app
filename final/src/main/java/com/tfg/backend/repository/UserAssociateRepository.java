package com.tfg.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.backend.models.User;
import com.tfg.backend.models.UserAssociate;

@Repository
public interface UserAssociateRepository extends JpaRepository<UserAssociate, Integer> {
    List<UserAssociate> findByFkHostUser(User hostUser);
    List<UserAssociate> findByFkAssociatedUser(User associatedUser);
}
