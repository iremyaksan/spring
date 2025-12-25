package com.ornek.diyet.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Diyetisyen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String kullaniciAdi;
    private String sifre;
    private String ad;
    private String soyad;
    private String unvan;
    private String email;     
    private String rol = "DIYETISYEN";
}
