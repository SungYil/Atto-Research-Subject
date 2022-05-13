package com.atto.AttoSubject.repositories;

import com.atto.AttoSubject.entities.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host,Long> {
    Host findByIp(String ip);
    Host findByName(String name);
    List<Host> findAllByOrderByName();
    boolean existsByIp(String ip);
}
