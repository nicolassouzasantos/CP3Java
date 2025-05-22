package br.com.brinquedos.hihappy.repository;


import br.com.brinquedos.hihappy.entity.Brinquedo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrinquedoRepository extends JpaRepository<Brinquedo, Long> {
}
