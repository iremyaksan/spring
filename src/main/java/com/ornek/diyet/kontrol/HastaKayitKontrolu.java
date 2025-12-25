package com.ornek.diyet.kontrol;

import com.ornek.diyet.model.Hasta;
import com.ornek.diyet.servis.HastaServisi;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HastaKayitKontrolu {

    private final HastaServisi hastaServisi;
    private final PasswordEncoder passwordEncoder;

    public HastaKayitKontrolu(HastaServisi hastaServisi,
                              PasswordEncoder passwordEncoder) {
        this.hastaServisi = hastaServisi;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/hasta/kayit")
    public String hastaKayitForm(Model model,
                                 @RequestParam(value = "hata", required = false) String hata) {

        model.addAttribute("hasta", new Hasta());
        model.addAttribute("hata", hata);

        return "hasta_kayit";
    }

    @PostMapping("/hasta/kaydet")
    public String hastaKaydet(@ModelAttribute Hasta hasta) {

        if (hastaServisi.kullaniciAdiVarMi(hasta.getKullaniciAdi())) {
            return "redirect:/hasta/kayit?hata=Kullanıcı adı zaten kayıtlı!";
        }

    
        hasta.setSifre(passwordEncoder.encode(hasta.getSifre()));

        hasta.setRol("HASTA");
        hastaServisi.kaydet(hasta);

        return "redirect:/giris";
    }
}
