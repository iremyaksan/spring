package com.ornek.diyet.guvenlik;

import com.ornek.diyet.model.Diyetisyen;
import com.ornek.diyet.model.Hasta;
import com.ornek.diyet.servis.DiyetisyenServisi;
import com.ornek.diyet.servis.HastaServisi;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class KullaniciServisi implements UserDetailsService {

    private final DiyetisyenServisi diyetisyenServisi;
    private final HastaServisi hastaServisi;

    public KullaniciServisi(DiyetisyenServisi d, HastaServisi h) {
        this.diyetisyenServisi = d;
        this.hastaServisi = h;
    }

    @Override
    public UserDetails loadUserByUsername(String kullaniciAdi) throws UsernameNotFoundException {

      
        Diyetisyen d = diyetisyenServisi.diyetisyenBul(kullaniciAdi);
        if (d != null) {
            return User
                    .withUsername(d.getKullaniciAdi())
                    .password(d.getSifre())
                    .roles("DIYETISYEN")
                    .build();
        }

        
        Hasta h = hastaServisi.hastaBul(kullaniciAdi);
        if (h != null) {
            return User
                    .withUsername(h.getKullaniciAdi())
                    .password(h.getSifre())
                    .roles("HASTA")
                    .build();
        }

        throw new UsernameNotFoundException("Kullanıcı bulunamadı: " + kullaniciAdi);
    }
}
