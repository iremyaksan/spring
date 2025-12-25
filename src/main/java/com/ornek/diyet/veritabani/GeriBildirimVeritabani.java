package com.ornek.diyet.veritabani;

import com.ornek.diyet.model.GeriBildirim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeriBildirimVeritabani extends JpaRepository<GeriBildirim, Long> {

    List<GeriBildirim> findByDiyetisyenId(Long diyetisyenId);
}
