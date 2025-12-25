package com.ornek.diyet.servis;

import com.ornek.diyet.model.Hasta;
import com.ornek.diyet.veritabani.HastaVeritabani;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HastaServisi {

    private final HastaVeritabani hastaVeritabani;

    public HastaServisi(HastaVeritabani hastaVeritabani) {
        this.hastaVeritabani = hastaVeritabani;
    }

    public Hasta kaydet(Hasta hasta) {
        return hastaVeritabani.save(hasta);
    }

    public boolean kullaniciAdiVarMi(String kullaniciAdi) {
        return hastaVeritabani.findByKullaniciAdi(kullaniciAdi) != null;
    }

  
    public Hasta girisYap(String kullaniciAdi, String sifre) {
        Hasta hasta = hastaVeritabani.findByKullaniciAdi(kullaniciAdi);
        if (hasta != null && hasta.getSifre().equals(sifre)) {
            return hasta;
        }
        return null;
    }

    public Optional<Hasta> idIleBul(Long id) {
        return hastaVeritabani.findById(id);
    }

    public List<Hasta> tumHastalar() {
        return hastaVeritabani.findAll();
    }


    public Hasta hastaBul(String kullaniciAdi) {
        return hastaVeritabani.findByKullaniciAdi(kullaniciAdi);
    }
}
