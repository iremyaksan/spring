package com.ornek.diyet.servis;

import com.ornek.diyet.model.Hasta;
import com.ornek.diyet.model.Mesaj;
import com.ornek.diyet.veritabani.MesajVeritabani;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MesajServisi {

    private final MesajVeritabani mesajVeritabani;

    public MesajServisi(MesajVeritabani mesajVeritabani) {
        this.mesajVeritabani = mesajVeritabani;
    }

    public Mesaj kaydet(Mesaj mesaj) {
        mesaj.setTarih(LocalDateTime.now());
        return mesajVeritabani.save(mesaj);
    }

    public List<Mesaj> hastaninMesajlari(Long hastaId) {
        return mesajVeritabani.findByHastaId(hastaId);
    }

    public List<Mesaj> tumMesajlar() {
        return mesajVeritabani.findAll();
    }

    public void hastaMesajGonder(Hasta hasta, String icerik) {

        Mesaj mesaj = new Mesaj();
        mesaj.setHasta(hasta);
        mesaj.setIcerik(icerik);
        mesaj.setTarih(LocalDateTime.now()); 

        mesajVeritabani.save(mesaj); 
    }
}
