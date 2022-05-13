package com.atto.AttoSubject.repositories;

import com.atto.AttoSubject.entities.Alive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AliveRepository extends JpaRepository<Alive,Long> {
    Alive findByHost(String ip);
}
