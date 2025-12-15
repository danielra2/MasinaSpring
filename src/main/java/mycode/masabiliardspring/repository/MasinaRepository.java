package mycode.masabiliardspring.repository;

import mycode.masabiliardspring.model.Masina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MasinaRepository extends JpaRepository<Masina, Long> {

    Optional<Masina> findByMarcaAndCuloare(String marca, String culoare);
    List<Masina> findByMarime(int marime);
    List<Masina> findByCuloare(String culoare);
}