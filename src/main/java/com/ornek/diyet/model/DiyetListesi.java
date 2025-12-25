package com.ornek.diyet.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DiyetListesi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diyetAdi;
    private String icerik;

    @ManyToOne
    private Hasta hasta;

    @ManyToOne
    private Diyetisyen diyetisyen;
}
