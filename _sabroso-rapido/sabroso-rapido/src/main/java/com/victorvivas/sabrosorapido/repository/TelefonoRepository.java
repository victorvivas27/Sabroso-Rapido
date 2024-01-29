package com.victorvivas.sabrosorapido.repository;

import com.victorvivas.sabrosorapido.entity.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefonoRepository extends JpaRepository<Telefono, Long> {
}
