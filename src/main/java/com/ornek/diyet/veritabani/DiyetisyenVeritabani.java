package com.ornek.diyet.veritabani;

import com.ornek.diyet.model.Diyetisyen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiyetisyenVeritabani extends JpaRepository<Diyetisyen, Long> {

    Diyetisyen findByKullaniciAdi(String kullaniciAdi);
}
