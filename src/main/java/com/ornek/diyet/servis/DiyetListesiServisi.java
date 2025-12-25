package com.ornek.diyet.servis;

import com.ornek.diyet.model.DiyetListesi;
import com.ornek.diyet.veritabani.DiyetListesiVeritabani;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiyetListesiServisi {

    private final DiyetListesiVeritabani diyetListesiVeritabani;

    public DiyetListesiServisi(DiyetListesiVeritabani diyetListesiVeritabani) {
        this.diyetListesiVeritabani = diyetListesiVeritabani;
    }

   
    public DiyetListesi ekle(DiyetListesi diyetListesi) {
        return diyetListesiVeritabani.save(diyetListesi);
    }

    
    public List<DiyetListesi> hepsiniGetir() {
        return diyetListesiVeritabani.findAll();
    }


  
    public Optional<DiyetListesi> idIleBul(Long id) {
        return diyetListesiVeritabani.findById(id);
    }

    
    public List<DiyetListesi> hastaninDiyetleri(Long hastaId) {
        return diyetListesiVeritabani.findByHastaId(hastaId);
    }

    
    public void sil(Long id) {
        diyetListesiVeritabani.deleteById(id);
    }
}
