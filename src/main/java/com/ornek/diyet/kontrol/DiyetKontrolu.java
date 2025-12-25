package com.ornek.diyet.kontrol;

import com.ornek.diyet.model.DiyetListesi;
import com.ornek.diyet.model.Diyetisyen;
import com.ornek.diyet.model.Hasta;
import com.ornek.diyet.servis.DiyetListesiServisi;
import com.ornek.diyet.servis.HastaServisi;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/diyet")   
public class DiyetKontrolu {

    private final HastaServisi hastaServisi;
    private final DiyetListesiServisi diyetListesiServisi;

    public DiyetKontrolu(HastaServisi hastaServisi,
                         DiyetListesiServisi diyetListesiServisi) {
        this.hastaServisi = hastaServisi;
        this.diyetListesiServisi = diyetListesiServisi;
    }

    
    @GetMapping("/ekle")
    public String diyetEkleSayfasi(@RequestParam("hastaId") Long hastaId, Model model) {

        Hasta hasta = hastaServisi.idIleBul(hastaId).orElse(null);
        model.addAttribute("hasta", hasta);

        return "diyet_ekle";
    }

 
    @PostMapping("/kaydet")
    public String diyetKaydet(@RequestParam Long hastaId,
                              @RequestParam String diyetAdi,
                              @RequestParam String icerik,
                              HttpSession oturum) {

        Diyetisyen aktifDiyetisyen = (Diyetisyen) oturum.getAttribute("aktifKisi");

   
        DiyetListesi d = new DiyetListesi();
        d.setDiyetAdi(diyetAdi);
        d.setIcerik(icerik);

        Hasta hasta = hastaServisi.idIleBul(hastaId).orElse(null);
        d.setHasta(hasta);

        d.setDiyetisyen(aktifDiyetisyen);

    
        diyetListesiServisi.ekle(d);

        return "redirect:/diyetisyen/panel";
    }

    @GetMapping("/sil")
    public String diyetSil(@RequestParam Long id) {

        diyetListesiServisi.sil(id); 
        return "redirect:/diyetisyen/panel";
    }


}
