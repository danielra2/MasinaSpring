package mycode.masabiliardspring.repository;

import mycode.masabiliardspring.model.Masina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MasinaRepository extends JpaRepository<Masina,Long> {
    @Query("SELECT m from Masina m")
    List<Masina> getAllMasina();
    Optional<Masina> getMasinasByMarcaAndCuloare(String marca,String culoare);


}
