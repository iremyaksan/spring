package com.ornek.diyet.veritabani;

import com.ornek.diyet.model.Hasta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HastaVeritabani extends JpaRepository<Hasta, Long> {

    Hasta findByKullaniciAdi(String kullaniciAdi);
}
