package com.ornek.diyet.kontrol;

import com.ornek.diyet.model.Diyetisyen;
import com.ornek.diyet.servis.DiyetisyenServisi;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DiyetisyenKayitKontrolu {

    private final DiyetisyenServisi diyetisyenServisi;
    private final PasswordEncoder passwordEncoder;

    public DiyetisyenKayitKontrolu(DiyetisyenServisi diyetisyenServisi,
                                   PasswordEncoder passwordEncoder) {
        this.diyetisyenServisi = diyetisyenServisi;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/diyetisyen/kayit")
    public String diyetisyenKayitForm(Model model,
                                      @RequestParam(value = "hata", required = false) String hata) {

        model.addAttribute("diyetisyen", new Diyetisyen());
        model.addAttribute("hata", hata);

        return "diyetisyen_kayit";
    }

    @PostMapping("/diyetisyen/kaydet")
    public String diyetisyenKaydet(@ModelAttribute Diyetisyen diyetisyen) {

        if (diyetisyenServisi.kullaniciAdiVarMi(diyetisyen.getKullaniciAdi())) {
            return "redirect:/diyetisyen/kayit?hata=Kullanıcı adı zaten kayıtlı!";
        }

       
        diyetisyen.setSifre(passwordEncoder.encode(diyetisyen.getSifre()));

        diyetisyen.setRol("DIYETISYEN");
        diyetisyenServisi.kaydet(diyetisyen);

        return "redirect:/giris";
    }
}
