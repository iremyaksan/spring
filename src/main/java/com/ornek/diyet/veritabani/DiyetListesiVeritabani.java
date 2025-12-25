package com.ornek.diyet.veritabani;

import com.ornek.diyet.model.DiyetListesi;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DiyetListesiVeritabani extends JpaRepository<DiyetListesi, Long> {

    List<DiyetListesi> findByHastaId(Long hastaId);
}
