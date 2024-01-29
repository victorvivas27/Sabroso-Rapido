package com.victorvivas.sabrosorapido.repository;

import com.victorvivas.sabrosorapido.entity.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long> {
}
