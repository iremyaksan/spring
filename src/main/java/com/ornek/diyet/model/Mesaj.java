package com.ornek.diyet.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Mesaj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String icerik;

    private LocalDateTime tarih;

    @ManyToOne
    private Hasta hasta;

    public void setTarih(LocalDateTime tarih) {
        this.tarih = tarih;
    }
}
