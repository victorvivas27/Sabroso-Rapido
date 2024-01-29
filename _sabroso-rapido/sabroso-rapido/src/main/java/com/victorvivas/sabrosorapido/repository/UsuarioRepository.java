package com.victorvivas.sabrosorapido.repository;

import com.victorvivas.sabrosorapido.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
