package com.ornek.diyet.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Hasta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String kullaniciAdi;


    @Column(name = "SIFRE")
    private String sifre;

    @Column(name = "AD")
    private String ad;

    @Column(name = "SOYAD")
    private String soyad;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ROL")
    private String rol = "HASTA";
}
