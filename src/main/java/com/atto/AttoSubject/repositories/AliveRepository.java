package com.atto.AttoSubject.repositories;

import com.atto.AttoSubject.entities.Alive;
import com.atto.AttoSubject.entities.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AliveRepository extends JpaRepository<Alive,Long> {
    @Query(value="select a from Alive a join a.host h where h.id=:id")
    Alive findByHostId(long id);
    List<Alive> findAll();
}
