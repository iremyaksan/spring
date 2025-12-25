package com.ornek.diyet.servis;

import com.ornek.diyet.model.Diyetisyen;
import com.ornek.diyet.veritabani.DiyetisyenVeritabani;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DiyetisyenServisi {

    private final DiyetisyenVeritabani diyetisyenVeritabani;

    public DiyetisyenServisi(DiyetisyenVeritabani diyetisyenVeritabani) {
        this.diyetisyenVeritabani = diyetisyenVeritabani;
    }

    public Diyetisyen kaydet(Diyetisyen diyetisyen) {
        return diyetisyenVeritabani.save(diyetisyen);
    }

    public boolean kullaniciAdiVarMi(String kullaniciAdi) {
        return diyetisyenVeritabani.findByKullaniciAdi(kullaniciAdi) != null;
    }

   
    public Diyetisyen girisYap(String kullaniciAdi, String sifre) {
        Diyetisyen d = diyetisyenVeritabani.findByKullaniciAdi(kullaniciAdi);
        if (d != null && d.getSifre().equals(sifre)) {
            return d;
        }
        return null;
    }

    public Optional<Diyetisyen> idIleBul(Long id) {
        return diyetisyenVeritabani.findById(id);
    }


    public Diyetisyen diyetisyenBul(String kullaniciAdi) {
        return diyetisyenVeritabani.findByKullaniciAdi(kullaniciAdi);
    }
}
