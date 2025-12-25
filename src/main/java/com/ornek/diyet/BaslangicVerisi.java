package com.ornek.diyet;

import com.ornek.diyet.model.Diyetisyen;
import com.ornek.diyet.model.Hasta;
import com.ornek.diyet.servis.DiyetisyenServisi;
import com.ornek.diyet.servis.HastaServisi;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class BaslangicVerisi {

    private final HastaServisi hastaServisi;
    private final DiyetisyenServisi diyetisyenServisi;

    public BaslangicVerisi(HastaServisi hastaServisi, DiyetisyenServisi diyetisyenServisi) {
        this.hastaServisi = hastaServisi;
        this.diyetisyenServisi = diyetisyenServisi;
    }

    @PostConstruct
    public void veriEkle() {

        Hasta h = new Hasta();
        h.setKullaniciAdi("hasta1");
        h.setSifre("123");
        h.setAd("Ali");
        h.setSoyad("Yılmaz");
        h.setEmail("ali@mail.com");
        h.setRol("HASTA");
        hastaServisi.kaydet(h);

        Diyetisyen d = new Diyetisyen();
        d.setKullaniciAdi("diyet1");
        d.setSifre("123");
        d.setAd("Ayşe");
        d.setSoyad("Demir");
        d.setUnvan("Diyetisyen");
        d.setRol("DIYETISYEN");
        diyetisyenServisi.kaydet(d);
    }
}
