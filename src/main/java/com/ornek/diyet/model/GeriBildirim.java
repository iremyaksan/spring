package com.ornek.diyet.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class GeriBildirim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mesaj;

    @ManyToOne
    private Hasta hasta;

    @ManyToOne
    private Diyetisyen diyetisyen;
}
