package com.ornek.diyet.veritabani;

import com.ornek.diyet.model.Mesaj;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MesajVeritabani extends JpaRepository<Mesaj, Long> {

    List<Mesaj> findByHastaId(Long hastaId); 
}
