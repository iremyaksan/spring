package com.ornek.diyet.servis;

import com.ornek.diyet.model.GeriBildirim;
import com.ornek.diyet.veritabani.GeriBildirimVeritabani;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeriBildirimServisi {

    private final GeriBildirimVeritabani repo;

    public GeriBildirimServisi(GeriBildirimVeritabani repo) {
        this.repo = repo;
    }

    public GeriBildirim kaydet(GeriBildirim g) {
        return repo.save(g);
    }

    public List<GeriBildirim> diyetisyeninMesajlari(Long diyetisyenId) {
        return repo.findByDiyetisyenId(diyetisyenId);
    }
}
