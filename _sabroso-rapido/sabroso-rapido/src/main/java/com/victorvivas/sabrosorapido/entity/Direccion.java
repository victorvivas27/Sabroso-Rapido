package com.victorvivas.sabrosorapido.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "DIRECCIONES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String calle;
    @Column(length = 50)
    private Long numero;
    @Column(length = 50)
    private String dpto;
    @Column(length = 100)
    private String pais;
    @Column(length = 100)
    private String provincia;
    @Column(length = 100)
    private String localidad;
    @Column(length = 100)
    private String barrio;


}
