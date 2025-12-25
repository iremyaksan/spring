package com.ornek.diyet.kontrol;

import com.ornek.diyet.servis.HastaServisi;
import com.ornek.diyet.servis.DiyetisyenServisi;
import com.ornek.diyet.servis.DiyetListesiServisi;
import com.ornek.diyet.servis.MesajServisi;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GirisKontrolu {

    private final HastaServisi hastaServisi;
    private final DiyetisyenServisi diyetisyenServisi;
    private final DiyetListesiServisi diyetListesiServisi;
    private final MesajServisi mesajServisi;

    public GirisKontrolu(HastaServisi hastaServisi,
                         DiyetisyenServisi diyetisyenServisi,
                         DiyetListesiServisi diyetListesiServisi,
                         MesajServisi mesajServisi) {

        this.hastaServisi = hastaServisi;
        this.diyetisyenServisi = diyetisyenServisi;
        this.diyetListesiServisi = diyetListesiServisi;
        this.mesajServisi = mesajServisi;
    }

    @GetMapping("/giris")
    public String girisSayfasi() {
        return "giris";
    }

    @GetMapping("/panel")
    public String panelYonlendir() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String rol = auth.getAuthorities().iterator().next().getAuthority();

        if (rol.equals("ROLE_DIYETISYEN")) {
            return "redirect:/diyetisyen/panel";
        } else if (rol.equals("ROLE_HASTA")) {
            return "redirect:/hasta/panel";
        }

        return "redirect:/giris";
    }


    @GetMapping("/diyetisyen/panel")
    public String diyetisyenPanel(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String kullaniciAdi = auth.getName();

        var diyetisyen = diyetisyenServisi.diyetisyenBul(kullaniciAdi);

        model.addAttribute("aktifKisi", diyetisyen);
        model.addAttribute("hastalar", hastaServisi.tumHastalar());
        model.addAttribute("diyetler", diyetListesiServisi.hepsiniGetir());
        model.addAttribute("mesajlar", mesajServisi.tumMesajlar());

        return "diyetisyen_panel";
    }



    @GetMapping("/hasta/panel")
    public String hastaPaneli(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String kullaniciAdi = auth.getName();

        var hasta = hastaServisi.hastaBul(kullaniciAdi);

        model.addAttribute("hasta", hasta);
        model.addAttribute("diyetler",
                diyetListesiServisi.hastaninDiyetleri(hasta.getId()));

        return "hasta_panel";
    }

    @PostMapping("/mesaj/gonder")
    public String mesajGonder(@RequestParam String icerik) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String kullaniciAdi = auth.getName();

        var hasta = hastaServisi.hastaBul(kullaniciAdi);

        mesajServisi.hastaMesajGonder(hasta, icerik);

        return "redirect:/hasta/panel?mesaj=ok";
    }
}
